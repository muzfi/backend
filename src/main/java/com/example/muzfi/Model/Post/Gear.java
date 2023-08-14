package com.example.muzfi.Model.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "gears")
public class Gear extends Post {
    private boolean isNewGear = true;
    private boolean isInGoodCondition = true;
    private String condition;
    private int usability;
    private int expectation;
    private int performance;
}
