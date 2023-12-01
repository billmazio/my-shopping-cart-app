package com.purchase.service;

import com.purchase.entity.Item;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

public interface IItemCartService {
    List<Item> getCart();
    BigDecimal totalPrice();
    void emptyCart();
    void deleteItemWithId(Long itemId);
    HttpSession getSession();
}
