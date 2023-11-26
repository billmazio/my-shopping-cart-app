package com.purchase.repository;

import com.purchase.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    ArrayList<OrderRepository> findByOrderDate(LocalDate term);
}
