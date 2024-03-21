package com.designCenter.designCenter.controller;

import com.designCenter.designCenter.dto.collections.CollectionReqDto;
import com.designCenter.designCenter.service.CollectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @DeleteMapping(value = "/record")
    public ResponseEntity<?> deleteCollection(@RequestParam long collectionId){
        log.info("Delete Collection detail Id:{}",collectionId);
        return collectionService.deleteCollectionDetail(collectionId);
    }

    @GetMapping(value = "today-all")
    public ResponseEntity<?> getTodayAllCollection(@RequestParam Date today){
        log.info("Get Today all collection");
        return collectionService.getTodayAllCollection(today);
    }

    @GetMapping(value = "two-month")
    public ResponseEntity<?> getLastMonthsCollection(@RequestParam long regNo){
        log.info("Get all collection in last Two month of RegisterNumber:{}",regNo);
        return collectionService.getLastTwoMonthCollection(regNo);
    }




}
