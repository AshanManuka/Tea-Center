package com.designCenter.designCenter.service;

import com.designCenter.designCenter.dto.collections.CollectionReqDto;
import org.springframework.http.ResponseEntity;

public interface CollectionService {

    ResponseEntity<?> saveCollectionDetail(CollectionReqDto reqDto);
}
