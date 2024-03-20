package com.designCenter.designCenter.service;

import com.designCenter.designCenter.dto.advance.AdvanceReqDto;
import org.springframework.http.ResponseEntity;

public interface AdvanceService {

    ResponseEntity<?> getRecordByRegNumber(long regNo);

    ResponseEntity<?> createAdvance(AdvanceReqDto reqDto);

    ResponseEntity<?> getTodayAdvanceDetails(long regNo);
}
