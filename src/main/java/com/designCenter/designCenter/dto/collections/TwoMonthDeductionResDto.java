package com.designCenter.designCenter.dto.collections;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TwoMonthDeductionResDto {
    private long registerNumber;
    private List<BasicDeductionResDto> currentMonth = new ArrayList<>();
    private List<BasicDeductionResDto> lastMonth = new ArrayList<>();
}
