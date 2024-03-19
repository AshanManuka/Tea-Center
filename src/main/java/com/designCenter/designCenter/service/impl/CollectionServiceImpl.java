package com.designCenter.designCenter.service.impl;

import com.designCenter.designCenter.constant.CommonConstant;
import com.designCenter.designCenter.dto.collections.CollectionReqDto;
import com.designCenter.designCenter.dto.collections.CollectionResDto;
import com.designCenter.designCenter.dto.collections.DeductionResDto;
import com.designCenter.designCenter.dto.common.CommonResponse;
import com.designCenter.designCenter.dto.common.CustomServiceException;
import com.designCenter.designCenter.entity.Collection;
import com.designCenter.designCenter.entity.Customer;
import com.designCenter.designCenter.entity.LeafDeduction;
import com.designCenter.designCenter.repository.CollectionRepository;
import com.designCenter.designCenter.repository.CustomerRepository;
import com.designCenter.designCenter.repository.LeafDeductionRepository;
import com.designCenter.designCenter.service.CollectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
@Service
public class CollectionServiceImpl implements CollectionService {

    private final ModelMapper modelMapper;
    private final CollectionRepository collectionRepository;
    private final LeafDeductionRepository deductionRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public ResponseEntity<?> saveCollectionDetail(CollectionReqDto reqDto) {
        log.info("Searching customer RegNum:{} is exists",reqDto.getRegisterNumber());
        Customer customer = customerRepository.findByRegisterNumber(reqDto.getRegisterNumber());
        if(customer == null) {
            return ResponseEntity.ok(new CommonResponse<>(false, "No user found with the provided registration number"));
        }

        Collection collection = modelMapper.map(reqDto,Collection.class);
        collectionRepository.save(collection);

        log.info("Saving deduction details for registerNumber:{}",reqDto.getRegisterNumber());
        reqDto.getDeductions().forEach(deductionReqDto -> {
            LeafDeduction deduction = LeafDeduction.builder()
                    .deduct(deductionReqDto.getDeduct())
                    .gross(reqDto.getGross())
                    .name(reqDto.getName())
                    .registerNumber(reqDto.getRegisterNumber())
                    .route(reqDto.getRoute())
                    .trDate(reqDto.getTrDate())
                    .trDay(reqDto.getTrDay())
                    .trMonth(reqDto.getTrMonth())
                    .type(deductionReqDto.getType())
                    .collection(collection)
                    .build();
            deductionRepository.save(deduction);
        });

        return ResponseEntity.ok(new CommonResponse<>(true,"Collection Saved Successfully..!"));
    }

    @Override
    public ResponseEntity<?> todayCollectionByRegNumber(long regNo) {
        log.info("Getting collection Details from Collection table to registerNumber:{}",regNo);
        List<Collection> collectionList = collectionRepository.getTodayCollectionByRegNumber(regNo, new Date());
        if(!collectionList.isEmpty()){
            List<CollectionResDto> collectionResList = collectionList
                    .stream()
                    .map(collection -> {
                        CollectionResDto resDto = modelMapper.map(collection,CollectionResDto.class);
                        List<LeafDeduction> deductionList = deductionRepository.getDeductionByCollection(collection.getId());
                        if(!deductionList.isEmpty()){
                            List<DeductionResDto> deductionResList = deductionList
                                    .stream()
                                    .map(leafDeduction -> modelMapper.map(leafDeduction,DeductionResDto.class))
                                    .collect(Collectors.toList());
                            resDto.setDeductions(deductionResList);

                        }
                        return resDto;
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new CommonResponse<>(true, collectionResList));

        }else{
            return ResponseEntity.ok(new CommonResponse<>(false, "Could not find any collection record today..!"));
        }

    }

    @Override
    public ResponseEntity<?> deleteCollectionDetail(long collectionId) {
        log.info("Checking is collection exists Id:{}", collectionId);
        Collection collection = collectionRepository.getCollectionById(collectionId);
        if (collection == null) {
            log.info("No collection record found");
            return ResponseEntity.ok(new CommonResponse<>(false, "No Collection Found"));
        } else {
            log.info("Deleting deduction records");
            List<LeafDeduction> leafDeductionList = deductionRepository.getDeductionByCollection(collection.getId());
            if (!leafDeductionList.isEmpty()) {
                log.info("Deleting collection records");
                deductionRepository.deleteAll(leafDeductionList);
            }
            collectionRepository.delete(collection);
            return ResponseEntity.ok(new CommonResponse<>(true, "Record Deleted Successfully..!"));
        }
    }




}
