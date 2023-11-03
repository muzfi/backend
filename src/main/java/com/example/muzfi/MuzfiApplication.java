package com.example.muzfi;
import com.example.muzfi.Model.MyBill;
import com.example.muzfi.Model.Purchase;
import com.example.muzfi.Repository.MyBillRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;






@SpringBootApplication
public class MuzfiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuzfiApplication.class, args);
    }

}