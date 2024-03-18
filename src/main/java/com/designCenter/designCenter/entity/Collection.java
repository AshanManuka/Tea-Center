package com.designCenter.designCenter.entity;

import com.designCenter.designCenter.enums.Grade;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int trDay;
    private String trMonth;
    private Date trDate;
    private long registerNumber;
    private String route;
    private String name;
    private double qty;
    private String vehicle;
    private double trRate;
    private String ws;
    private double incRate;
    private double commissionRate;
    @Enumerated(value = EnumType.STRING)
    private Grade grade;
    private boolean sms;

}
