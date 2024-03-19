package com.designCenter.designCenter.controller;

import com.designCenter.designCenter.service.AdvanceService;
import com.designCenter.designCenter.service.CollectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/advance")
@Log4j2
public class AdvanceController {

    private final AdvanceService advanceService;
    private final CollectionService collectionService;

    @GetMapping(value = "/record-by-reg")
    public ResponseEntity<?> getRecordDetailsByRegNumber(@RequestParam long regNo){
        return advanceService.getRecordByRegNumber(regNo);
    }





}
