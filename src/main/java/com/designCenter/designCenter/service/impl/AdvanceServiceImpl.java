package com.designCenter.designCenter.service.impl;

import com.designCenter.designCenter.dto.common.CommonResponse;
import com.designCenter.designCenter.dto.customer.BriefRecordResDto;
import com.designCenter.designCenter.dto.customer.CustomerResDto;
import com.designCenter.designCenter.entity.Customer;
import com.designCenter.designCenter.repository.AdvanceRepository;
import com.designCenter.designCenter.repository.CollectionRepository;
import com.designCenter.designCenter.repository.CustomerRepository;
import com.designCenter.designCenter.service.AdvanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class AdvanceServiceImpl implements AdvanceService {

    private final ModelMapper modelMapper;
    private final AdvanceRepository advanceRepository;
    private final CustomerRepository customerRepository;
    private final CollectionRepository collectionRepository;


    @Override
    public ResponseEntity<?> getRecordByRegNumber(long regNo) {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);

        log.info("Searching Customer by RegisterNumber:{}",regNo);
        Customer customer = customerRepository.findByRegisterNumber(regNo);
        if(customer == null){
            return ResponseEntity.ok(new CommonResponse<>(false, "No User found..!"));
        }
        BriefRecordResDto response = modelMapper.map(customer,BriefRecordResDto.class);
        List<Double> currentMonthGross = collectionRepository.getAllNetGrossByMonth(currentYear,currentMonth);
        log.info(currentMonthGross);
        if(!currentMonthGross.isEmpty()){
            double currentTotalWeight = 0;
            for (double gross: currentMonthGross) {
                currentTotalWeight +=gross;
            }
            response.setThisMonthGross(currentTotalWeight);
        }

        List<Double> lastMonthGross = new ArrayList<>();
        if(currentMonth == 1){
            lastMonthGross = collectionRepository.getAllNetGrossByMonth(currentYear-1, 12);
        }else{
            lastMonthGross = collectionRepository.getAllNetGrossByMonth(currentYear, currentMonth-1);
        }
        if(!lastMonthGross.isEmpty()){
            double lastTotalWeight = 0;
            for (double gross: lastMonthGross) {
                lastTotalWeight +=gross;
            }
            response.setLastMonthGross(lastTotalWeight);
        }

        return ResponseEntity.ok(new CommonResponse<>(true, response));
    }
}