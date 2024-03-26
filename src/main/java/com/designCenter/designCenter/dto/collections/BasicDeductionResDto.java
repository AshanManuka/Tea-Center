package com.designCenter.designCenter.dto.collections;

import com.designCenter.designCenter.enums.DeductionType;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BasicDeductionResDto {
    private long id;
    private Date trDate;
    private double gross;
    private int deduct;
    private DeductionType type;
}
