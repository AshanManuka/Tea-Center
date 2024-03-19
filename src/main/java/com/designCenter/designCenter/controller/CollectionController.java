package com.designCenter.designCenter.controller;

import com.designCenter.designCenter.dto.collections.CollectionReqDto;
import com.designCenter.designCenter.dto.common.CommonResponse;
import com.designCenter.designCenter.service.CollectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping(value = "collection")
public class CollectionController {

    private final CollectionService collectionService;

    @PostMapping
    public ResponseEntity<?> saveCollection(@RequestBody CollectionReqDto reqDto){
        log.info("Save Collection details of customerName:{}",reqDto.getName());
        collectionService.saveCollectionDetail(reqDto);
        return ResponseEntity.ok(new CommonResponse<>(true,"Collection Saved Successfully..!"));
    }


}
