package com.example.muzfi.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


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
}