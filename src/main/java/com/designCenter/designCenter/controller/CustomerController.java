package com.designCenter.designCenter.controller;

import com.designCenter.designCenter.dto.common.CommonResponse;
import com.designCenter.designCenter.dto.customer.CustomerReqDto;
import com.designCenter.designCenter.dto.customer.CustomerResDto;
import com.designCenter.designCenter.enums.SearchType;
import com.designCenter.designCenter.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping(value = "/customer")
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping(value = "/search-by-other")
    public ResponseEntity<?> searchByKeyWord(@RequestParam String keyword, @RequestParam SearchType type){
        log.info("Search Customer by keyword:{}",keyword);
        return customerService.searchByKeyword(keyword,type);
    }

    @GetMapping(value = "/by-id")
    public ResponseEntity<?> searchById(@RequestParam long id){
        log.info("Search Customer by Id:{}",id);
        return customerService.searchById(id);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<?> searchByRegisterNumber(@RequestParam long regNo){
        log.info("Search Customer by RegisterNumber:{}",regNo);
        return customerService.searchByRegisterNumber(regNo);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllCustomer(){
        log.info("Get all Customers");
        return customerService.getAllCustomer();
    }

    @PostMapping
    public ResponseEntity<?> saveCustomer(@RequestBody CustomerReqDto requestDto){
        log.info("Saving new customer Details NIC:{}",requestDto.getNic());
        CustomerResDto response = customerService.saveCustomerDetail(requestDto);
        return ResponseEntity.ok(new CommonResponse<>(true, response));
    }


}
