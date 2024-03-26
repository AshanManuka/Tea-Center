package com.designCenter.designCenter.dto.collections;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TwoMonthDataResDto {
    private long registerNumber;
    private List<SimpleCollectionResDto> currentMonth = new ArrayList<>();
    private List<SimpleCollectionResDto> lastMonth = new ArrayList<>();

}
