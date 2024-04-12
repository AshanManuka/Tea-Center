package com.designCenter.designCenter.dto.collections;

import com.designCenter.designCenter.enums.DeductionType;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BasicTwoDeductionResDto {
    private long id;
    private long registerNumber;
    private String name;
    private Date trDate;
    private double gross;
    private int deduct;
    private DeductionType type;
}
