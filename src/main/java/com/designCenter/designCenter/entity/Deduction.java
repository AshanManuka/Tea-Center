package com.designCenter.designCenter.entity;

import com.designCenter.designCenter.enums.DeductionType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Deduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date date;
    private int month;
    private int year;
    private long registerNumber;
    @Enumerated(value = EnumType.STRING)
    private DeductionType type;

    private double amount;

}
