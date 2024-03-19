package com.designCenter.designCenter.entity;

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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private Long registerNumber;

    private String name;

    @Column(nullable = false, unique = true)
    private String nic;

    @Column(nullable = false, unique = true)
    private String mobile;

    private String address;

    private Date updated;
}
