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

    @GetMapping(value = "/today-all")
    public ResponseEntity<?> getTodayAllCollection(@RequestParam Date today){
        log.info("Get Today all collection");
        return collectionService.getTodayAllCollection(today);
    }

    @GetMapping(value = "/two-month-weight")
    public ResponseEntity<?> getLastMonthsCollection(@RequestParam long regNo){
        log.info("Get all collection Weight in last Two month of RegisterNumber:{}",regNo);
        return collectionService.getLastTwoMonthCollection(regNo);
    }

    @GetMapping(value = "/two-month")
    public ResponseEntity<?> getCollectionDataInLastTwoMonth(@RequestParam long regNo){
        log.info("Get all basic collection Data in last Two month of RegisterNumber:{}",regNo);
        return collectionService.getCollectionDataInLastTwoMonth(regNo);
    }

    @GetMapping(value = "/deduction/two-month")
    public ResponseEntity<?> getDeductionDataInLastTwoMonth(@RequestParam long regNo){
        log.info("Get all basic deduction Data in last Two month of RegisterNumber:{}",regNo);
        return collectionService.getDeductionDataInLastTwoMonth(regNo);
    }

    @GetMapping(value = "/all-by-date")
    public ResponseEntity<?> getAllCollectionDetailsByDate(@RequestParam Date date){
        log.info("Get collection details by date:{}",date);
        return collectionService.getCollectionDetailByDate(date);
    }

    @GetMapping(value = "/deduction/all-by-date")
    public ResponseEntity<?> getAllDeductionDetailsByDate(@RequestParam Date date){
        log.info("Get Deduction details by date:{}",date);
        return collectionService.getDeductionDetailByDate(date);
    }




}
