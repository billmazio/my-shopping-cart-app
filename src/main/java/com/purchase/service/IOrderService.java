package com.purchase.service;

import com.purchase.entity.Client;
import com.purchase.entity.Item;

import java.util.List;

public interface IOrderService {
    void createOrder(Client client, List<Item> items);

}
