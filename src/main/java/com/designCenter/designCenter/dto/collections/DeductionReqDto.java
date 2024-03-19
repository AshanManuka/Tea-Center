package com.designCenter.designCenter.dto.collections;

import com.designCenter.designCenter.enums.DeductionType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DeductionReqDto {
    private DeductionType type;
    private int deduct;
}
