package com.purchase.controller;

import com.purchase.dto.OrderRequest;
import com.purchase.entity.Order;
import com.purchase.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingCart/orders")
public class OrderController {

    private final IOrderService orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        // Assuming OrderResponse is another DTO that represents the response for displaying orders
        List<Order> allOrders = orderService.getAll();
        return ResponseEntity.ok(allOrders);
    }


    @PostMapping("/newOrder")
    public ResponseEntity<Void> createOrder(@RequestBody OrderRequest orderRequest) {
        // Assume OrderRequest is a DTO containing client ID and list of item IDs
        // Implement validation and handle the creation of the order in the service
        orderService.createOrder(orderRequest, orderRequest.getItemIds());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}