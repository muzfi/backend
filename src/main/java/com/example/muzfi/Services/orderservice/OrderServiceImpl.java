package com.ecommerce.shoppingmarket.service.orderservice;


import com.ecommerce.shoppingmarket.model.Order;
import com.ecommerce.shoppingmarket.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

    @Service
    public class OrderServiceImpl implements OrderService {

        @Autowired
        private OrderRepo orderRepo;

        @Override
        public String placeOrder(Order order) {
            // Set the order date to the current date and time
            order.setOrderDate(String.valueOf(LocalDateTime.now()));

            // You can add more logic here, such as calculating the order total or updating product availability.
            // For simplicity, we'll just save the order to the database.
            orderRepo.save(order);

            return "Order placed successfully with ID: " + order.getId();
        }

        @Override
        public List<Order> getUserOrders(String userId) {
            // Implement the logic to retrieve all orders made by a user with the specified userId
            return orderRepo.findByUserId(userId);
        }
    }


