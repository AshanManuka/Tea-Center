package com.designCenter.designCenter.dto.collections;

import com.designCenter.designCenter.enums.Grade;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CollectionResDto {
    private long id;
    private String name;
    private Long registerNumber;
    private int trDay;
    private String trMonth;
    private Date trDate;
    private String route;
    private double qty;
    private String vehicle;
    private double trRate;
    private double gross;
    private String ws;
    private double incRate;
    private double commissionRate;
    private Grade grade;
    private boolean sms;
    List<DeductionResDto> deductions = new ArrayList<>();
}
