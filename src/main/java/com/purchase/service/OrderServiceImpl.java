package com.purchase.service;

import com.purchase.dto.OrderRequest;
import com.purchase.entity.Client;
import com.purchase.entity.Item;
import com.purchase.entity.Order;
import com.purchase.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public void createOrder(Client client, List<Item> items) {
        for (Item item : items) {
            Order order = new Order();
            order.setClient(client);
            order.setOrderDate(LocalDate.now());
            order.setItem(item); // Set the items for the order
            orderRepository.save(order);
        }
    }

    @Override
    public void createOrder(OrderRequest orderRequest, List<Long> itemIds) {
        Long clientId = orderRequest.getClientId();

    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

}
