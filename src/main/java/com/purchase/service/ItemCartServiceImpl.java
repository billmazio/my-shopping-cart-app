package com.purchase.service;

import com.purchase.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCartServiceImpl implements IItemCartService {

    private final HttpSession session;

    @Autowired
    public ItemCartServiceImpl(HttpSession session) {
        this.session = session;
    }

    // Method to retrieve the session.
    public HttpSession getSession() {
        return session;
    }

    // Method to get the list of items in the cart.
    @Override
    public List<Item> getCart() {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        return cart;
    }

    // Method to calculate the total price of items in the cart.
    @Override
    public BigDecimal totalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        List<Item> cart = getCart();
        for (Item item : cart) {
            totalPrice = totalPrice.add(item.getPrice());
        }
        return totalPrice;
    }

    // Method to empty the cart.
    @Override
    public void emptyCart() {
        List<Item> cart = getCart();
        cart.clear();
    }

    // Method to delete an item from the cart by its ID.
    @Override
    public void deleteItemWithId(Long itemId) {
        List<Item> cart = getCart();
        cart.removeIf(item -> item.getId().equals(itemId));
    }
}
