package com.example.muzfi.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "gears")
public class Gear {
    @Id
    private String id;
    private String name;
    private Double price;
    private Boolean customGear;
    private String brandName;
    private String model;
    private List<String>  photo;
    private String description;
    private String receiveMethod;
    private String details;
    private List<Review> reviews;
    private List<Rating> ratings;
    private List<String> pros;
    private List<String> cons;
    private Specs specs;
    private List<PriceTrend> priceTrends;
    private String category;
}

// You'll also need to define Review, Rating, Specs, PriceTrend classes.
