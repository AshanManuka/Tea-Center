package com.designCenter.designCenter.service;

import com.designCenter.designCenter.dto.collections.CollectionReqDto;
import com.designCenter.designCenter.dto.collections.DeductionReqDto;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface CollectionService {

    ResponseEntity<?> saveCollectionDetail(CollectionReqDto reqDto);

    ResponseEntity<?> todayCollectionByRegNumber(long regNo);

    ResponseEntity<?> deleteCollectionDetail(long collectionId);

    ResponseEntity<?> getTodayAllCollection(Date today);

    ResponseEntity<?> getLastTwoMonthCollection(long regNo);

    ResponseEntity<?> getCollectionDataInLastTwoMonth(long regNo);

    ResponseEntity<?> getDeductionDataInLastTwoMonth(long regNo);

    ResponseEntity<?> getCollectionDetailByDate(Date date);

    ResponseEntity<?> getDeductionDetailByDate(Date date);

}
