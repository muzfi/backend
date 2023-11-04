package com.example.muzfi.Services;

import com.example.muzfi.Model.*;
import com.example.muzfi.Repository.OrderDetailsRepository;
import com.example.muzfi.Repository.ProductRepository;
import com.example.muzfi.Repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderDetailService {
    // Status is temporarily hardcoded
    private static final String ORDER_PLACED = "Placed";

    private final OrderDetailsRepository orderDetailsRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private final JavaMailSender javaMailSender;

    public void placeOrder(OrderInput orderInput) throws MessagingException {
        List<OrderProductQuantity> orderProductQuantityList = orderInput.getOrderProductQuantityList();

        // Getting the current username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        for (OrderProductQuantity o : orderProductQuantityList) {
            Product product = productRepository.findById(o.getProductID()).orElse(null);
            if (product != null) {
                User user = userRepository.findById(currentUserName).orElse(null);
                if (user != null) {
                    // Create and save the order details
                    OrderDetails orderDetails = new OrderDetails(
                            orderInput.getOrderFullName(),
                            orderInput.getOrderFullAddress(),
                            orderInput.getOrderContactNumber(),
                            orderInput.getOrderAlternateContactNumber(),
                            orderInput.getOrderEmail(),
                            ORDER_PLACED,
                            product.getPrice() * o.getQuantity(),
                            product,
                            user
                    );
                    orderDetailsRepository.save(orderDetails);

                    // Send an email confirmation
                    sendEmailConfirmation(orderInput);
                } else {
                    // Handle the case where the user is not found
                    throw new RuntimeException("User not found");
                }
            } else {
                // Handle the case where the product is not found
                throw new RuntimeException("Product not found");
            }
        }
    }

    public void sendEmailConfirmation(OrderInput orderInput) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(orderInput.getOrderEmail());
        helper.setFrom("umrmoh200@gmail.com");
        helper.setSubject("Order Confirmation");
        helper.setText("Thank you for your order!\n\n" + getOrderInfo(orderInput));

        javaMailSender.send(message);
    }

    private String getOrderInfo(OrderInput orderInput) {
        // Create and return the order information as a string
        // You can format the information as needed
        return "You ordered " + orderInput.getOrderProductQuantityList().size() + " product(s).";
    }
}
