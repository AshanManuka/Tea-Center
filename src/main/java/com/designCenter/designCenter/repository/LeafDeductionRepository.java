package com.designCenter.designCenter.repository;

import com.designCenter.designCenter.dto.collections.BasicDeductionResDto;
import com.designCenter.designCenter.entity.LeafDeduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface LeafDeductionRepository extends JpaRepository<LeafDeduction,Long> {

    @Query(value = "SELECT * FROM leaf_deduction WHERE register_number=?1 AND DATE(tr_date)=DATE(?2)", nativeQuery=true)
    List<LeafDeduction> getTodayDeductionByRegNumber(long regNo, Date date);

    @Query(value = "SELECT d FROM LeafDeduction d WHERE d.collection.id=?1")
    List<LeafDeduction> getDeductionByCollection(long id);

    @Query(value = "SELECT new com.designCenter.designCenter.dto.collections.BasicDeductionResDto(d.id, d.trDate, d.gross, d.deduct, d.type)  FROM LeafDeduction d WHERE d.trYear=?1 AND d.trMonth=?2")
    List<BasicDeductionResDto> getDeductionOfMonth(int currentYear, int currentMonth);
}
