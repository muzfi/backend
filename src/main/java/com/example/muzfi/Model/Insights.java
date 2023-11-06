package com.example.muzfi.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Insights {
    @Id
    private String Id;
    private Integer views;
    private Integer uniquesOnAvg;
    private Integer subscribed;
    private Integer unsubscribed;
}
