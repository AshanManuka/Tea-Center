package com.designCenter.designCenter.dto.customer;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerReqDto {
    private Long registerNumber;
    private String name;
    private String nic;
    private String mobile;
    private String address;
}
