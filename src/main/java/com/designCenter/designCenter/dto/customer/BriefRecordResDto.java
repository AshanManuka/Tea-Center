package com.designCenter.designCenter.dto.customer;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BriefRecordResDto {
    private Long id;
    private Long registerNumber;
    private String name;
    private String nic;
    private double thisMonthGross;
    private double lastMonthGross;
}
