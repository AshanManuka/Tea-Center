package com.designCenter.designCenter.repository;

import com.designCenter.designCenter.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection,Long> {

    @Query(value = "SELECT * FROM Collection WHERE register_number=?1 AND DATE(tr_date)=DATE(?2)", nativeQuery=true)
    List<Collection> getTodayCollectionByRegNumber(long regNo, Date date);
}
