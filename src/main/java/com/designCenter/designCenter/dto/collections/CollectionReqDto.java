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
public class CollectionReqDto {
    private String name;
    private Long registerNumber;
    private int trDay;
    private int trMonth;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date trDate;
    private int trYear;
    private String route;
    private double qty;
    private String vehicle;
    private double trRate;
    private double gross;
    private double netWeight;
    private String ws;
    private double incRate;
    private double commissionRate;
    private Grade grade;
    private boolean sms;
    List<DeductionReqDto> deductions = new ArrayList<>();
}
