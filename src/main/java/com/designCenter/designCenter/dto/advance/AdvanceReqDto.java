package com.designCenter.designCenter.dto.advance;

import com.designCenter.designCenter.enums.TrType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdvanceReqDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date trDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date logDate;
    private long registerNumber;
    private String route;
    private String registerName;
    private double amount;
    private TrType trType;
}
