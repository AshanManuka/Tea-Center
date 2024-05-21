package com.designCenter.designCenter.dto.deduction;

import com.designCenter.designCenter.enums.DeductionType;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeductionResDto {
    private long id;
    private Date date;
//    private int month;
//    private int year;
    private long registerNumber;
    private DeductionType type;
    private double amount;
}
