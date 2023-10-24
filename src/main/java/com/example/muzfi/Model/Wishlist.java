package com.example.muzfi.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "wishlists")
public class Wishlist {
    @Id
    private String id;
    private String userId;
    private Set<String> listingIds;  // Represents IDs of the listings/items in the wishlist.
}

