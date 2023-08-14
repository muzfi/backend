package com.example.muzfi.Model.Post;

import com.example.muzfi.Enums.DeliverMethod;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "listings")
public class Listing {
    @Id
    private String id;

    private String postId;

    private String authorId;

    @NotNull
    private String brand;

    @NotNull
    private String model;

    private String year;

    private String finish;

    @NotNull
    private String title;

    private Boolean isHandMade;

    @NotNull
    private List<String> images;

    @NotNull
    private String condition;

    @NotNull
    private String conditionDescription;

    private String youTubeLink;

    @NotNull
    private List<DeliverMethod> deliverMethod;

    private ProductShippingDetails shippingDetails;

    private BigDecimal price;

    private Boolean is3PercentFromFinalSellingPrice;

    private Boolean isAcceptOffers;

    private BigDecimal bumpRate;

    //TODO: update with user date and time
    private LocalDateTime createdDateTime = LocalDateTime.now();

    private LocalDateTime updatedDateTime;
}
