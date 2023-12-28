package com.example.muzfi.Model;

import com.example.muzfi.Enums.ProductCondition;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String name;
    private String category;
    private String description;
    private Double price;
    private Integer seller_id;
    private ProductCondition condition;
    private String brandId;
    private String sellerEmail;
    private String status; // "active" for active listings
    @CreatedDate
    private LocalDateTime createdDate;

}