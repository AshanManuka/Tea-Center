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
public class LeafDeduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int trDay;
    private int trMonth;
    private Date trDate;
    private long registerNumber;
    private String route;
    private String name;
    private double gross;
    private int deduct;

    @Enumerated(value = EnumType.STRING)
    private DeductionType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    private Collection collection;



}
