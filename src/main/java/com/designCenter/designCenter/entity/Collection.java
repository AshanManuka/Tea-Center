package com.designCenter.designCenter.entity;

import com.designCenter.designCenter.enums.Grade;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private String name;
    private Long registerNumber;
    private int trDay;
    private int trMonth;
    private Date trDate;
    private int trYear;
    private String route;
    private double qty;
    private String vehicle;
    private double trRate;
    private String ws;
    private double incRate;
    private double gross;
    private double netWeight;
    private double commissionRate;
    @Enumerated(value = EnumType.STRING)
    private Grade grade;
    private boolean sms;

    @OneToMany(mappedBy = "collection")
    private List<LeafDeduction> deductionList = new ArrayList<>();

}
