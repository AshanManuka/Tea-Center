package com.designCenter.designCenter.service;

import org.springframework.http.ResponseEntity;

public interface AdvanceService {

    ResponseEntity<?> getRecordByRegNumber(long regNo);
}
