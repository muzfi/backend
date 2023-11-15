package com.example.muzfi.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Page {
    private String id;
    private String title;
}
