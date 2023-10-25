package com.ecommerce.shoppingmarket.service.orderservice;

import com.ecommerce.shoppingmarket.model.Order;

import java.util.List;

public interface OrderService {
    String placeOrder(Order order);
    List<Order> getUserOrders(String userId);
}
