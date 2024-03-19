package com.designCenter.designCenter.repository;

import com.designCenter.designCenter.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query(value = "SELECT c FROM Customer c WHERE c.nic LIKE %?1% OR c.mobile LIKE %?1% GROUP BY c.id")
    List<Customer> searchByKeyword(String keyword);

    @Query(value = "SELECT c FROM Customer c WHERE c.registerNumber=?1")
    Customer findByRegisterNumber(long regNo);

    //Customer findCustomerIsExist(String nic, String mobile, String address);
}
