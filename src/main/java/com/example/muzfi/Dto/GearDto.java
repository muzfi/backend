package com.example.muzfi.Dto;

import com.example.muzfi.Model.Rating;
import com.example.muzfi.Model.Review;
import com.example.muzfi.Model.Specs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GearDto {
    private String name;
    private Double price;
    private Boolean customGear;
    private String brandName;
    private String model;
    private String receiveMethod;
    private String Description;
    private List<Review> reviews;
    private List<Rating> ratings;
    private List<String> pros;
    private List<String> cons;
    private Specs specs;

    // Constructors, Getters, Setters

    // Optionally, you can include any additional methods needed for mapping between the DTO and model
}
