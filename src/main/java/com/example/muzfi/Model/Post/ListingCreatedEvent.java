package com.example.muzfi.Model.Post;

import org.springframework.context.ApplicationEvent;

public class ListingCreatedEvent extends ApplicationEvent {

    private final String listingId;

    public ListingCreatedEvent(Object source, String listingId) {
        super(source);
        this.listingId = listingId;
    }

    public String getListingId() {
        return listingId;
    }
}

