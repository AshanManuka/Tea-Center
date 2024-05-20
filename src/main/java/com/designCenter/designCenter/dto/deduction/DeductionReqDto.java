package com.designCenter.designCenter.dto.deduction;

import com.designCenter.designCenter.enums.DeductionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeductionReqDto {
    private Long registerNumber;
    private DeductionType type;
    private double amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date date;
}
