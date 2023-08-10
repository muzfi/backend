package com.example.muzfi.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "gearRooms")
public class GearRoom {
    @Id
    private String id;

    private Integer userId; // Reference to the user who owns this room

    private List<Gear> gears; // List of gears in this room

    public Object getGearIds() {
        return null;
    }

    public void setGearIds(Object gearIds) {
    }
}
