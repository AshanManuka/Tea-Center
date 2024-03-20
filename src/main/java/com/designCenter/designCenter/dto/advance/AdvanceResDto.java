package com.designCenter.designCenter.dto.advance;

import com.designCenter.designCenter.enums.TrType;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdvanceResDto {
    private long id;
    private Date effectedDate;
    private Date issueDate;
    private long registerNumber;
    private String route;
    private String registerName;
    private double amount;
    private TrType trType;
}
