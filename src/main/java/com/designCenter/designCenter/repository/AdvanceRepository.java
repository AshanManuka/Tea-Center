package com.designCenter.designCenter.repository;

import com.designCenter.designCenter.dto.advance.BasicAdvanceResDto;
import com.designCenter.designCenter.entity.Advance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface AdvanceRepository extends JpaRepository<Advance,Long> {

    @Query(value = "SELECT * FROM advance WHERE register_number=?1 AND DATE(issue_date)=DATE(?2)", nativeQuery=true)
    List<Advance> getTodayAdvanceByRegNo(long regNo, Date date);

    @Query(value = "SELECT a FROM Advance a WHERE a.id=?1")
    Advance getAdvanceById(long id);

    @Query(value = "SELECT * FROM advance WHERE id=?1 AND DATE(issue_date)=DATE(?2)", nativeQuery=true)
    Advance getTodayAdvanceById(long id, Date date);

    @Query(value = "SELECT new com.designCenter.designCenter.dto.advance.BasicAdvanceResDto(a.id, a.effectedDate, a.route, a.amount, a.trType) FROM Advance a WHERE a.trYear=?1 AND a.trMonth=?2")
    List<BasicAdvanceResDto> getAdvanceOfMonth(int currentYear, int currentMonth);
}
