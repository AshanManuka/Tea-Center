package com.designCenter.designCenter.dto.collections;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TwoMonthWeightResDto {
    private long registerNumber;
    private List<Double> currentMonthWeight = new ArrayList<>();
    private List<Double> lastMonthWeight = new ArrayList<>();
}
