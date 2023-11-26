package com.purchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public interface OrderRepository extends JpaRepository<OrderRepository, Long> {

    ArrayList<OrderRepository> findByOrderDate(LocalDate term);
}
