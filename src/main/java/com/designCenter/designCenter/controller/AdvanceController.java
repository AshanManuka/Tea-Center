package com.designCenter.designCenter.controller;

import com.designCenter.designCenter.dto.advance.AdvanceReqDto;
import com.designCenter.designCenter.service.AdvanceService;
import com.designCenter.designCenter.service.CollectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/advance")
@Log4j2
public class AdvanceController {

    private final AdvanceService advanceService;

    @GetMapping(value = "/record-by-reg")
    public ResponseEntity<?> getRecordDetailsByRegNumber(@RequestParam long regNo){
        return advanceService.getRecordByRegNumber(regNo);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> createAdvance(@RequestBody AdvanceReqDto reqDto){
        log.info("Create new Advance record to customer regNo:{}",reqDto.getRegisterNumber());
        return advanceService.createAdvance(reqDto);
    }





}
