package com.designCenter.designCenter.service;

import com.designCenter.designCenter.dto.customer.CustomerReqDto;
import com.designCenter.designCenter.dto.customer.CustomerResDto;

import java.util.List;

public interface CustomerService {

    CustomerResDto saveCustomerDetail(CustomerReqDto requestDto);

    List<CustomerResDto> searchByKeyword(String keyword);

    CustomerResDto searchByRegisterNumber(long regNo);
}
