package com.designCenter.designCenter.dto.collections;

import com.designCenter.designCenter.enums.DeductionType;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DeductionResDto {
    private long id;
    private int trDay;
    private String trMonth;
    private Date trDate;
    private long registerNumber;
    private String route;
    private String name;
    private double gross;
    private int deduct;
    private DeductionType type;
}
