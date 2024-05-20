package com.designCenter.designCenter.controller;

import com.designCenter.designCenter.dto.deduction.DeductionReqDto;
import com.designCenter.designCenter.service.CollectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/deduction")
@Log4j2
public class DeductionController {

    private final CollectionService collectionService;

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveDeduction(@RequestBody DeductionReqDto reqDto){
        log.info("Save deduction details of registerNumber:{}",reqDto.getRegisterNumber());
        return collectionService.saveDeductionDetail(reqDto);
    }

    @GetMapping(value = "by-date")
    public ResponseEntity<?> getDeductionByDate(@RequestParam Date date){
        log.info("get Deduction by date:{}", date);
        return collectionService.getDeductionByDate(date);
    }
}
