package com.example.muzfi.Dto;

import com.example.muzfi.Model.Rating;
import com.example.muzfi.Model.Review;
import com.example.muzfi.Model.Specs;
import java.util.List;

public class GearDto {
    private String name;
    private Double price;
    private List<Review> reviews;
    private List<Rating> ratings;
    private List<String> pros;
    private List<String> cons;
    private Specs specs;

    // Constructors, Getters, Setters

    // Optionally, you can include any additional methods needed for mapping between the DTO and model
}
