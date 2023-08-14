package com.example.muzfi.Model.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductShippingDetails {
    private Boolean isFreeShipping;

    private Boolean isFlatShippingRateForRegion;

    private BigDecimal rate;

    private Boolean isCombineShippingRate;
}
