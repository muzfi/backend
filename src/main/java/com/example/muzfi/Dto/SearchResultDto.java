package com.example.muzfi.Dto;

import com.example.muzfi.Enums.SearchType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SearchResultDto {
    private String id;
    private String searchedString;
    private SearchType searchType;
}
