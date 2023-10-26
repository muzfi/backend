package com.example.muzfi.Services;

import com.example.muzfi.Model.*;
import com.example.muzfi.Repository.OrderDetailsRepository;
import com.example.muzfi.Repository.ProductRepository;
import com.example.muzfi.Repository.UserRepository;
import com.example.muzfi.Util.OktaLoginSuccessListener;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    //status temporary hardcoded
    private static final String ORDER_PLACED = "Placed";

    private final OrderDetailsRepository orderDetailsRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public void placeOrder(OrderInput orderInput){
        List<OrderProductQuantity> orderProductQuantityList = orderInput.getOrderProductQuantityList();

//         Getting currentUsername
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        for (OrderProductQuantity o: orderProductQuantityList){
            Product product = productRepository.findById(o.getProductID()).get();
            User user = userRepository.findById(currentUserName).get();
            OrderDetails orderDetails = new OrderDetails(
                    orderInput.getOrderFullName(),
                    orderInput.getOrderFullAddress(),
                    orderInput.getOrderContactNumber(),
                    orderInput.getOrderAlternateContactNumber(),
                    ORDER_PLACED,
                    product.getPrice()*o.getQuantity(),
                    product,
                    user
            );
            orderDetailsRepository.save(orderDetails);
        }

    }
}


