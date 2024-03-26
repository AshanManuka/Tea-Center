package com.designCenter.designCenter.entity;

import com.designCenter.designCenter.enums.TrType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Advance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date effectedDate;
    private Date issueDate;
    private long registerNumber;
    private String route;
    private String registerName;
    private double amount;

    @Enumerated(value = EnumType.STRING)
    private TrType trType;
//    private String issueMonth;
    private int trMonth;
    private int trYear;




}
