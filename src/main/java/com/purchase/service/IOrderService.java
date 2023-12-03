package com.purchase.service;

import com.purchase.dto.OrderRequest;
import com.purchase.entity.Client;
import com.purchase.entity.Item;
import com.purchase.entity.Order;

import java.util.List;

public interface IOrderService {
    void createOrder(Client client, List<Item> items);

    void createOrder(OrderRequest orderRequest, List<Long> itemIds);

    List<Order> getAll();
}
