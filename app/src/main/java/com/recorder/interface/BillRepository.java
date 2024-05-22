package com.recorder.dal.repository;

import com.recorder.dal.bean.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    
    @Query("SELECT b FROM Bill b WHERE b.dateTime BETWEEN ?1 AND ?2")
    Page<Bill> findByDateTimeBetween(Date startDate, Date endDate, Pageable pageable);

    
    @Query("SELECT b FROM Bill b WHERE b.cateID = ?1")
    List<Bill> findByCateId(int cateId);
}