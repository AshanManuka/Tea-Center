package com.designCenter.designCenter.repository;

import com.designCenter.designCenter.dto.deduction.DeductionReqDto;
import com.designCenter.designCenter.dto.deduction.DeductionResDto;
import com.designCenter.designCenter.entity.Deduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface DeductionRepository extends JpaRepository<Deduction, Long> {

    @Query(value = "SELECT * FROM deduction WHERE DATE(date)=DATE(?1)",nativeQuery=true)
    List<Deduction> getDeductionByDate(Date date);

    @Query(value = "SELECT new com.designCenter.designCenter.dto.deduction.DeductionResDto(d.id, d.date, d.registerNumber, d.type, d.amount) FROM Deduction d WHERE d.year=?1 AND d.month=?2")
    List<DeductionResDto> getDeductionOfMonth(int currentYear, int currentMonth);
}
