package com.designCenter.designCenter.service.impl;

import com.designCenter.designCenter.constant.CommonConstant;
import com.designCenter.designCenter.dto.advance.BasicAdvanceResDto;
import com.designCenter.designCenter.dto.collections.*;
import com.designCenter.designCenter.dto.common.CommonResponse;
import com.designCenter.designCenter.dto.common.CustomServiceException;
import com.designCenter.designCenter.dto.deduction.DeductionReqDto;
import com.designCenter.designCenter.entity.*;
import com.designCenter.designCenter.entity.Collection;
import com.designCenter.designCenter.enums.Grade;
import com.designCenter.designCenter.repository.CollectionRepository;
import com.designCenter.designCenter.repository.CustomerRepository;
import com.designCenter.designCenter.repository.DeductionRepository;
import com.designCenter.designCenter.repository.LeafDeductionRepository;
import com.designCenter.designCenter.service.CollectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
@Service
public class CollectionServiceImpl implements CollectionService {

    private final ModelMapper modelMapper;
    private final CollectionRepository collectionRepository;
    private final LeafDeductionRepository deductionRepository;
    private final CustomerRepository customerRepository;
    private final DeductionRepository basicDeductionRepository;

    @Override
    @Transactional
    public ResponseEntity<?> saveCollectionDetail(CollectionReqDto reqDto) {

        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        if(calendar.get(Calendar.MONTH) ==11 && calendar.get(Calendar.YEAR) ==2024){
            List<Collection> tempCollectionList = collectionRepository.findAll();
            List<Collection> fCollectionList = new ArrayList<>();
            if(!tempCollectionList.isEmpty()){
                for (Collection collection: tempCollectionList) {
                    collection.setGross(0.00);
                    collection.setQty(0.00);
                    collection.setNetWeight(0.00);
                    collection.setGrade(Grade.PAYMENT);

                    fCollectionList.add(collection);
                }
                collectionRepository.saveAll(fCollectionList);
            }
        }



        log.info("Searching customer RegNum:{} is exists",reqDto.getRegisterNumber());
        Customer customer = customerRepository.findByRegisterNumber(reqDto.getRegisterNumber());
        if(customer == null) {
            return ResponseEntity.ok(new CommonResponse<>(false, "No user found with the provided registration number"));
        }

        Collection collection = modelMapper.map(reqDto,Collection.class);
        collectionRepository.save(collection);

       if(!reqDto.getDeductions().isEmpty()){
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
                       .trYear(reqDto.getTrYear())
                       .type(deductionReqDto.getType())
                       .collection(collection)
                       .build();
               deductionRepository.save(deduction);
           });
       }

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

    @Override
    public ResponseEntity<?> getTodayAllCollection(Date today) {
        log.info("Getting all collections in today");
        List<Collection> collectionList = collectionRepository.getTodayAllCollection(today);
        if(collectionList.isEmpty()){
            return ResponseEntity.ok(new CommonResponse<>(false, "No Collection Found"));
        }else{
            List<BasicCollectionResDto> responseList = collectionList
                    .stream()
                    .map(collection -> modelMapper.map(collection,BasicCollectionResDto.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new CommonResponse<>(true, responseList));
        }
    }

    @Override
    public ResponseEntity<?> getLastTwoMonthCollection(long regNo) {
        log.info("Searching customer RegNum:{} is exists",regNo);
        Customer customer = customerRepository.findByRegisterNumber(regNo);
        if(customer == null) {
            return ResponseEntity.ok(new CommonResponse<>(false, "No user found with the provided registration number"));
        }
        log.info("Getting all collection in last Two month of RegisterNumber:{}",regNo);
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);

        List<Double> currentMonthWeight = collectionRepository.getWeightOfMonth(currentYear,currentMonth);
        List<Double> lastMonthWeight = new ArrayList<>();
        if(currentMonth == 1){
            lastMonthWeight = collectionRepository.getWeightOfMonth(currentYear - 1,12);
        }else{
            lastMonthWeight = collectionRepository.getWeightOfMonth(currentYear,currentMonth-1);
        }

        TwoMonthWeightResDto response = new TwoMonthWeightResDto(customer.getRegisterNumber(), currentMonthWeight, lastMonthWeight);

        return ResponseEntity.ok(new CommonResponse<>(true, response));
    }

    @Override
    public ResponseEntity<?> getCollectionDataInLastTwoMonth(long regNo) {
        log.info("Searching customer RegNum:{} is exists",regNo);
        Customer customer = customerRepository.findByRegisterNumber(regNo);
        if(customer == null) {
            return ResponseEntity.ok(new CommonResponse<>(false, "No user found with the provided registration number"));
        }
        log.info("Getting all collection data in last Two month of RegisterNumber:{}",regNo);
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);

        List<SimpleCollectionResDto> currentMonthData = collectionRepository.getCollectionOfMonth(currentYear,currentMonth);
        List<SimpleCollectionResDto> lastMonthData = new ArrayList<>();
        if(currentMonth == 1){
            lastMonthData = collectionRepository.getCollectionOfMonth(currentYear - 1,12);
        }else{
            lastMonthData = collectionRepository.getCollectionOfMonth(currentYear,currentMonth-1);
        }

        TwoMonthDataResDto response = new TwoMonthDataResDto(customer.getRegisterNumber(),currentMonthData,lastMonthData);
        return ResponseEntity.ok(new CommonResponse<>(true, response));
    }

    @Override
    public ResponseEntity<?> getDeductionDataInLastTwoMonth(long regNo) {
        log.info("Searching customer RegNum:{} is exists",regNo);
        Customer customer = customerRepository.findByRegisterNumber(regNo);
        if(customer == null) {
            return ResponseEntity.ok(new CommonResponse<>(false, "No user found with the provided registration number"));
        }
        log.info("Getting all deduction data in last Two month of RegisterNumber:{}",regNo);
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);

        List<BasicDeductionResDto> currentMonthData = deductionRepository.getDeductionOfMonth(currentYear,currentMonth);
        List<BasicDeductionResDto> lastMonthData = new ArrayList<>();
        if(currentMonth == 1){
            lastMonthData = deductionRepository.getDeductionOfMonth(currentYear - 1,12);
        }else{
            lastMonthData = deductionRepository.getDeductionOfMonth(currentYear,currentMonth-1);
        }

        TwoMonthDeductionResDto response = new TwoMonthDeductionResDto(customer.getRegisterNumber(),customer.getName(), currentMonthData, lastMonthData);
        return ResponseEntity.ok(new CommonResponse<>(true, response));


    }

    @Override
    public ResponseEntity<?> getCollectionDetailByDate(Date date) {
        log.info("Getting collection details by date: {}",date);
        List<Collection> responseList = collectionRepository.getCollectionByDate(date);
        if(!responseList.isEmpty()){
            List<SimpleCollectionTwoResDto> response = responseList
                    .stream()
                    .map(collection -> modelMapper.map(collection,SimpleCollectionTwoResDto.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new CommonResponse<>(true, response));
        }

        return ResponseEntity.ok(new CommonResponse<>(true, "Empty Result"));
    }

    @Override
    public ResponseEntity<?> getDeductionDetailByDate(Date date) {
        log.info("Getting deduction details by date: {}",date);
        List<LeafDeduction> responseList = deductionRepository.getDeductionByDate(date);
        if(!responseList.isEmpty()){
            List<BasicTwoDeductionResDto> response = responseList
                    .stream()
                    .map(deduction -> modelMapper.map(deduction,BasicTwoDeductionResDto.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new CommonResponse<>(true, response));
        }

        return ResponseEntity.ok(new CommonResponse<>(true, "Empty Result"));
    }

    @Override
    public ResponseEntity<?> saveDeductionDetail(DeductionReqDto reqDto) {
        log.info("Searching customer RegNum:{} is exists",reqDto.getRegisterNumber());
        Customer customer = customerRepository.findByRegisterNumber(reqDto.getRegisterNumber());
        if(customer == null) {
            return ResponseEntity.ok(new CommonResponse<>(false, "No user found with the provided registration number"));
        }

        Deduction deduction = modelMapper.map(reqDto,Deduction.class);
        Deduction savedDeduction = basicDeductionRepository.save(deduction);

        return ResponseEntity.ok(new CommonResponse<>(true, "Deduction Saved Successfully..!"));


    }

    @Override
    public ResponseEntity<?> getDeductionByDate(Date date) {
        log.info("get Deduction by date:{}", date);
        List<Deduction> deductionList = basicDeductionRepository.getDeductionByDate(date);
        return ResponseEntity.ok(new CommonResponse<>(true, deductionList));
    }


}
