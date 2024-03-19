package com.designCenter.designCenter.service;

import com.designCenter.designCenter.dto.customer.CustomerReqDto;
import com.designCenter.designCenter.dto.customer.CustomerResDto;
import com.designCenter.designCenter.enums.SearchType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    CustomerResDto saveCustomerDetail(CustomerReqDto requestDto);

    ResponseEntity<?> searchByKeyword(String keyword, SearchType type);

    ResponseEntity<?> searchByRegisterNumber(long regNo);

    ResponseEntity<?> searchById(long id);
}
