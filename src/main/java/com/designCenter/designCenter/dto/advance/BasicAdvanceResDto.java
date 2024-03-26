package com.designCenter.designCenter.dto.advance;

import com.designCenter.designCenter.enums.TrType;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BasicAdvanceResDto {
    private long id;
    private Date effectedDate;
    private String route;
    private double amount;
    private TrType trType;
}
