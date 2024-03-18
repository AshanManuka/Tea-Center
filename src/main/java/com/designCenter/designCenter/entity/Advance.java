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

    private Date trDate;
    private String issueMonth;
    private String trMonth;
    private long registerNumber;
    private String route;
    private String registerName;
    private double amount;
    private Date logDate;

    @Enumerated(value = EnumType.STRING)
    private TrType trType;




}
