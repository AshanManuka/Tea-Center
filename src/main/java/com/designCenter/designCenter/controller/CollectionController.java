package com.designCenter.designCenter.controller;

import com.designCenter.designCenter.dto.collections.CollectionReqDto;
import com.designCenter.designCenter.service.CollectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping(value = "/collection")
public class CollectionController {

    private final CollectionService collectionService;

    @PostMapping
    public ResponseEntity<?> saveCollection(@RequestBody CollectionReqDto reqDto){
        log.info("Save Collection details of customerName:{}",reqDto.getName());
        return collectionService.saveCollectionDetail(reqDto);
    }

    @GetMapping(value = "/today")
    public ResponseEntity<?> todayCollectionByRegisterNumber(@RequestParam long regNo){
        log.info("Get today collected details by registerNumber:{}",regNo);
        return collectionService.todayCollectionByRegNumber(regNo);
    }


}
