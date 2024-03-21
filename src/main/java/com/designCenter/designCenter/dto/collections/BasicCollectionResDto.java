package com.designCenter.designCenter.dto.collections;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BasicCollectionResDto {
    private Long id;
    private Long registerNumber;
    private Date trDate;
    private double netGross;

}
