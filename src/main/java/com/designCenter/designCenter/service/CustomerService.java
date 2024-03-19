package com.designCenter.designCenter.service;

import com.designCenter.designCenter.dto.customer.CustomerReqDto;
import com.designCenter.designCenter.dto.customer.CustomerResDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    CustomerResDto saveCustomerDetail(CustomerReqDto requestDto);

    ResponseEntity<?> searchByKeyword(String keyword);

    ResponseEntity<?> searchByRegisterNumber(long regNo);
}
