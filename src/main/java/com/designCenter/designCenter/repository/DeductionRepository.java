package com.designCenter.designCenter.repository;

import com.designCenter.designCenter.entity.Deduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface DeductionRepository extends JpaRepository<Deduction, Long> {

    @Query(value = "SELECT * FROM deduction WHERE DATE(date)=DATE(?1)",nativeQuery=true)
    List<Deduction> getDeductionByDate(Date date);
}
