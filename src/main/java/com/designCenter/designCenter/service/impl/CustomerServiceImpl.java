package com.designCenter.designCenter.service.impl;

import com.designCenter.designCenter.constant.CommonConstant;
import com.designCenter.designCenter.dto.common.CommonResponse;
import com.designCenter.designCenter.dto.common.CustomServiceException;
import com.designCenter.designCenter.dto.customer.CustomerReqDto;
import com.designCenter.designCenter.dto.customer.CustomerResDto;
import com.designCenter.designCenter.entity.Customer;
import com.designCenter.designCenter.enums.SearchType;
import com.designCenter.designCenter.repository.CustomerRepository;
import com.designCenter.designCenter.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public CustomerResDto saveCustomerDetail(CustomerReqDto requestDto) {
        log.info("Checking Nic:{}, Mobile:{}, Name:{}", requestDto.getNic(),requestDto.getMobile(), requestDto.getName());
        Customer customer = Customer.builder()
                .address(requestDto.getAddress())
                .name(requestDto.getName())
                .nic(requestDto.getNic())
                .registerNumber(requestDto.getRegisterNumber())
                .mobile(requestDto.getMobile())
                .updated(new Date())
                .build();

        customer = customerRepository.save(customer);
        return modelMapper.map(customer, CustomerResDto.class);

//        if(!requestDto.getNic().isEmpty() && !requestDto.getMobile().isEmpty() && !requestDto.getMobile().isEmpty()){
//            Customer customer = customerRepository.findCustomerIsExist(requestDto.getNic(), requestDto.getMobile(),requestDto.getAddress());
//            if(customer == null){
//                customer = Customer.builder()
//                        .address(requestDto.getAddress())
//                        .name(requestDto.getName())
//                        .nic(requestDto.getNic())
//                        .registerNumber(requestDto.getRegisterNumber())
//                        .mobile(requestDto.getMobile())
//                        .updated(new Date())
//                        .build();
//            }else{
//                new CustomServiceException(AppConstants.NotFoundConstants.NO_CLIENT_FOUND)
//            }
//        }

    }

    @Override
    public ResponseEntity<?> searchByKeyword(String keyword, SearchType type) {
        List<Customer> customerList = new ArrayList<>();
        log.info("Searching Customers by Keyword:{}",type);
        if(type.equals(SearchType.NIC)){
            customerList = customerRepository.searchByNic(keyword);
        }else if(type.equals(SearchType.MOBILE)){
            customerList = customerRepository.searchByMobile(keyword);
        }

        if(customerList.isEmpty()){
            return ResponseEntity.ok(new CommonResponse<>(false,"Can't find any customer..!"));
        }

        List<CustomerResDto> responseList = customerList
                .stream()
                .map(customer ->modelMapper.map(customer,CustomerResDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new CommonResponse<>(true,responseList));

    }

    @Override
    public ResponseEntity<?> searchByRegisterNumber(long regNo) {
        log.info("Searching Customer by RegisterNumber:{}",regNo);
        Customer customer = customerRepository.findByRegisterNumber(regNo);
        if(customer == null){
            return ResponseEntity.ok(new CommonResponse<>(false, "No User found..!"));
        }
        CustomerResDto response = modelMapper.map(customer,CustomerResDto.class);
        return ResponseEntity.ok(new CommonResponse<>(true, response));
    }


}
