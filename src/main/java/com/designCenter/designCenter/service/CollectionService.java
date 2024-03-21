package com.designCenter.designCenter.service;

import com.designCenter.designCenter.dto.collections.CollectionReqDto;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public interface CollectionService {

    ResponseEntity<?> saveCollectionDetail(CollectionReqDto reqDto);

    ResponseEntity<?> todayCollectionByRegNumber(long regNo);

    ResponseEntity<?> deleteCollectionDetail(long collectionId);

    ResponseEntity<?> getTodayAllCollection(Date today);
}
