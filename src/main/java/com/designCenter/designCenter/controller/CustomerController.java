package com.designCenter.designCenter.controller;

import com.designCenter.designCenter.dto.common.CommonResponse;
import com.designCenter.designCenter.dto.customer.CustomerReqDto;
import com.designCenter.designCenter.dto.customer.CustomerResDto;
import com.designCenter.designCenter.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping(value = "customer")
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping(value = "/search-by-other")
    public ResponseEntity<?> searchByKeyWord(@RequestParam String keyword){
        log.info("Search Customer by keyword:{}",keyword);
        List<CustomerResDto> responseList = customerService.searchByKeyword(keyword);
        return ResponseEntity.ok(new CommonResponse<>(true,responseList));
    }

    @GetMapping(value = "/search")
    public ResponseEntity<?> searchByRegisterNumber(@RequestParam long regNo){
        log.info("Search Customer by RegisterNumber:{}",regNo);
        CustomerResDto response = customerService.searchByRegisterNumber(regNo);
        return ResponseEntity.ok(new CommonResponse<>(true,response));
    }



    @PostMapping
    public ResponseEntity<?> saveCustomer(@RequestBody CustomerReqDto requestDto){
        log.info("Saving new customer Details NIC:{}",requestDto.getNic());
        CustomerResDto response = customerService.saveCustomerDetail(requestDto);
        return ResponseEntity.ok(new CommonResponse<>(true, response));
    }


}
