package com.designCenter.designCenter.dto.deduction;

import com.designCenter.designCenter.enums.DeductionType;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BasicDeducResDto {
    private long id;

    private Date date;
    private int month;
    private int year;
    private long registerNumber;
    private String name;
    private DeductionType type;
    private double amount;

}
