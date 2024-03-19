package com.designCenter.designCenter.service.impl;

import com.designCenter.designCenter.constant.CommonConstant;
import com.designCenter.designCenter.dto.collections.CollectionReqDto;
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
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Log4j2
@Service
public class CollectionServiceImpl implements CollectionService {

    private final ModelMapper modelMapper;
    private final CollectionRepository collectionRepository;
    private final LeafDeductionRepository deductionRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void saveCollectionDetail(CollectionReqDto reqDto) {
        log.info("Searching customer RegNum:{} is exists",reqDto.getRegisterNumber());
        Customer customer = customerRepository.findByRegisterNumber(reqDto.getRegisterNumber());
        if(customer == null) throw new CustomServiceException(CommonConstant.NotFoundConstants.NO_USER_FOUND);

        Collection collection = modelMapper.map(reqDto,Collection.class);
        collectionRepository.save(collection);

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
                    .build();
            deductionRepository.save(deduction);
        });
    }
}
