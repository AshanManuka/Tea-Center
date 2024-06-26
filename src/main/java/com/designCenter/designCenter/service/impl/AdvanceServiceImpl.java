package com.designCenter.designCenter.service.impl;

import com.designCenter.designCenter.dto.advance.*;
import com.designCenter.designCenter.dto.collections.BasicDeductionResDto;
import com.designCenter.designCenter.dto.collections.TwoMonthDeductionResDto;
import com.designCenter.designCenter.dto.common.CommonResponse;
import com.designCenter.designCenter.dto.customer.BriefRecordResDto;
import com.designCenter.designCenter.dto.customer.CustomerResDto;
import com.designCenter.designCenter.entity.Advance;
import com.designCenter.designCenter.entity.Customer;
import com.designCenter.designCenter.enums.TrType;
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
import java.util.stream.Collectors;

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

    @Override
    public ResponseEntity<?> createAdvance(AdvanceReqDto reqDto) {

        log.info("Searching Customer by RegisterNumber:{}",reqDto.getRegisterNumber());
        Customer customer = customerRepository.findByRegisterNumber(reqDto.getRegisterNumber());
        if(customer == null){
            return ResponseEntity.ok(new CommonResponse<>(false, "No User found..!"));
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(reqDto.getEffectedDate());

        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);

        Advance advance = modelMapper.map(reqDto,Advance.class);
        advance.setTrMonth(currentMonth);
        advance.setTrYear(currentYear);
        advanceRepository.save(advance);
        if(reqDto.getTrType().equals(TrType.FERTILIZER)){
            //devide in to installment
            //create new installment table
            //save
        }
        return ResponseEntity.ok(new CommonResponse<>(true, "Successfully Saved Advance Record..!..!"));
    }

    @Override
    public ResponseEntity<?> getTodayAdvanceDetails(long regNo, Date issueDate) {
        log.info("Searching Customer by RegisterNumber:{}",regNo);
        Customer customer = customerRepository.findByRegisterNumber(regNo);
        if(customer == null){
            return ResponseEntity.ok(new CommonResponse<>(false, "No User found..!"));
        }

        List<Advance> advanceList = advanceRepository.getTodayAdvanceByRegNo(regNo,issueDate);
        if(!advanceList.isEmpty()){
            List<AdvanceResDto> responseList = advanceList
                    .stream()
                    .map(advance -> modelMapper.map(advance,AdvanceResDto.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new CommonResponse<>(true, responseList));
        }else{
            return ResponseEntity.ok(new CommonResponse<>(false, "No any Records in Entered Date..!"));
        }

    }

    @Override
    public ResponseEntity<?> deleteSingleAdvanceRecord(long id) {
        log.info("Checking is record exists in table");
        Advance advance = advanceRepository.getTodayAdvanceById(id,new Date());
        if(advance == null){
            return ResponseEntity.ok(new CommonResponse<>(false, "Cannot find in advance record..!"));
        }else{
            advanceRepository.delete(advance);
            return ResponseEntity.ok(new CommonResponse<>(true, "Deleted Record Successfully..!"));
        }

    }

    @Override
    public ResponseEntity<?> getAdvanceInTwoMonth(long regNo) {
        log.info("Searching customer RegNum:{} is exists",regNo);
        Customer customer = customerRepository.findByRegisterNumber(regNo);
        if(customer == null) {
            return ResponseEntity.ok(new CommonResponse<>(false, "No user found with the provided registration number"));
        }
        log.info("Getting all Advance data in last Two month of RegisterNumber:{}",regNo);
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);

        List<BasicAdvanceResDto> currentMonthData = advanceRepository.getAdvanceOfMonth(currentYear,currentMonth);
        List<BasicAdvanceResDto> lastMonthData = new ArrayList<>();
        if(currentMonth == 1){
            lastMonthData = advanceRepository.getAdvanceOfMonth(currentYear - 1,12);
        }else{
            lastMonthData = advanceRepository.getAdvanceOfMonth(currentYear,currentMonth-1);
        }

        TwoMonthAdvanceResDto response = new TwoMonthAdvanceResDto(customer.getRegisterNumber(),currentMonthData, lastMonthData);
        return ResponseEntity.ok(new CommonResponse<>(true, response));

    }

    @Override
    public ResponseEntity<?> getAdvanceDetailByDate(Date issueDate) {
        log.info("Getting advance details by date: {}",issueDate);
        List<Advance> responseList = advanceRepository.getAdvanceByDate(issueDate);
        if(!responseList.isEmpty()){
            List<BasicTwoAdvanceResDto> response = responseList
                    .stream()
                    .map(advance -> modelMapper.map(advance,BasicTwoAdvanceResDto.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new CommonResponse<>(true, response));
        }

        return ResponseEntity.ok(new CommonResponse<>(true, "Empty Result"));
    }


}
