package com.designCenter.designCenter.repository;

import com.designCenter.designCenter.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection,Long> {

    @Query(value = "SELECT * FROM collection WHERE register_number=?1 AND DATE(tr_date)=DATE(?2)", nativeQuery=true)
    List<Collection> getTodayCollectionByRegNumber(long regNo, Date date);

    @Query(value ="SELECT c FROM Collection c WHERE c.id=?1")
    Collection getCollectionById(long collectionId);

    @Query(value = "SELECT c.netGross FROM Collection c WHERE c.trYear=:currentYear AND c.trMonth=:currentMonth")
    List<Double> getAllNetGrossByMonth(@Param("currentYear") int currentYear, @Param("currentMonth") int currentMonth);

    @Query(value = "SELECT * FROM collection WHERE DATE(tr_date)=DATE(?1)", nativeQuery=true)
    List<Collection> getTodayAllCollection(Date today);
}
