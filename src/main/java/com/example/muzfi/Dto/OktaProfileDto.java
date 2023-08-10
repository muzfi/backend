package com.example.muzfi.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OktaProfileDto {
    @JsonProperty("profile")
    private OktaProfileAttributesDto profile;
}

