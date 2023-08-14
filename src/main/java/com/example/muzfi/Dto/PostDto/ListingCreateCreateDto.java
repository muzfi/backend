package com.example.muzfi.Dto.PostDto;

import com.example.muzfi.Enums.DeliverMethod;
import com.example.muzfi.Model.Post.ProductShippingDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListingCreateCreateDto extends PostCreateDto {

    private String brand;

    private String model;

    private String year;

    private String finish;

    private String listingTitle;

    private Boolean isHandMade;

    private List<String> images;

    private String condition;

    private String conditionDescription;

    private String youTubeLink;

    private List<DeliverMethod> deliverMethod;

    private ProductShippingDetails shippingDetails;

    private BigDecimal price;

    private Boolean is3PercentFromFinalSellingPrice;

    private Boolean isAcceptOffers;

    private BigDecimal bumpRate;
}
