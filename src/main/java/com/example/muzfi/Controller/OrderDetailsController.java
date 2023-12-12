package com.example.muzfi.Controller;

import com.example.muzfi.Model.OrderInput;
import com.example.muzfi.Services.OrderDetailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderDetailsController {
    private final OrderDetailService OrderDetailService;

    @PostMapping("/placeOrder")
    public String placeOrder(@RequestBody OrderInput orderInput) throws MessagingException {
        OrderDetailService.placeOrder(orderInput);
        return "Order Placed";
    }
}
