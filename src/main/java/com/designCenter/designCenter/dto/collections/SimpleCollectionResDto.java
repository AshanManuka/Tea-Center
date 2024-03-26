package com.designCenter.designCenter.dto.collections;

import com.designCenter.designCenter.enums.Grade;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SimpleCollectionResDto {
    private long id;
    private Date trDate;
    private double qty;
    private Grade grade;
}
