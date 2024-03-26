package com.designCenter.designCenter.dto.advance;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TwoMonthAdvanceResDto {
    private long registerNumber;
    private List<BasicAdvanceResDto> currentMonth = new ArrayList<>();
    private List<BasicAdvanceResDto> lastMonth = new ArrayList<>();
}
