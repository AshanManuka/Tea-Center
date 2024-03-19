package com.designCenter.designCenter.repository;

import com.designCenter.designCenter.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query(value = "SELECT c FROM Customer c WHERE c.registerNumber=?1")
    Customer findByRegisterNumber(long regNo);

    @Query(value = "SELECT c FROM Customer c WHERE c.nic LIKE %?1% GROUP BY c.id")
    List<Customer> searchByNic(String keyword);

    @Query(value = "SELECT c FROM Customer c WHERE c.mobile LIKE %?1% GROUP BY c.id")
    List<Customer> searchByMobile(String keyword);

    @Query(value = "SELECT c FROM Customer c WHERE c.id=?1")
    Customer getCustomerById(long id);

}
