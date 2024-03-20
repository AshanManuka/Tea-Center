package com.designCenter.designCenter.repository;

import com.designCenter.designCenter.entity.Advance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface AdvanceRepository extends JpaRepository<Advance,Long> {

    @Query(value = "SELECT * FROM Advance WHERE register_number=?1 AND DATE(issue_date)=DATE(?2)", nativeQuery=true)
    List<Advance> getTodayAdvanceByRegNo(long regNo, Date date);
}
