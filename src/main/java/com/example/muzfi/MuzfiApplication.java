package com.example.muzfi;
import com.example.muzfi.Model.Insights;
import com.example.muzfi.Model.MyBill;
import com.example.muzfi.Model.OrderDetails;
import com.example.muzfi.Model.Purchase;
import com.example.muzfi.Repository.InsightsRepository;
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

    @Bean
    public CommandLineRunner commandLineRunner(
            InsightsRepository insightsRepository
    ) {
        return args -> {
            var insights = Insights.builder()
                    .views(89)
                    .uniquesOnAvg(78)
                    .unsubscribed(789)
                    .subscribed(980)
                    .build();
            insightsRepository.insert(insights);
        };
    }


}

