package com.example.muzfi;
import com.example.muzfi.Model.MyBill;
import com.example.muzfi.Model.OrderDetails;
import com.example.muzfi.Model.Purchase;
import com.example.muzfi.Repository.MyBillRepository;
import com.example.muzfi.Repository.OrderDetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;



import java.time.LocalDateTime;


@SpringBootApplication
public class MuzfiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuzfiApplication.class, args);
    }
<<<<<<< HEAD
//    @Bean
//    public CommandLineRunner commandLineRunner(
//            OrderDetailsRepository orderDetailsRepository
//    ) {
//        return args -> {
//            var orderDetails = OrderDetails.builder()
//                            .OrderId("avndjavjdfkk")
//                                    .orderAmount(7849.0)
//                                            .orderContactNumber("7845944893")
//                                                    .build();
//            orderDetailsRepository.insert(orderDetails);
//        };
//    }
//}
=======

}
>>>>>>> 3583037c2e1ea8d63489d36693e3ee68524ebbc6
