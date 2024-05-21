package com.designCenter.designCenter.dto.deduction;

import com.designCenter.designCenter.dto.collections.BasicDeductionResDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LastTwoMonthDeductionResDto {
    private long registerNumber;
    private String registerName;
    private List<DeductionResDto> currentMonth = new ArrayList<>();
    private List<DeductionResDto> lastMonth = new ArrayList<>();
}
