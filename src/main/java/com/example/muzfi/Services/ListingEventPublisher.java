package com.example.muzfi.Services;

import com.example.muzfi.Model.Post.ListingCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class ListingEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public ListingEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishListingCreatedEvent(String listingId) {
        ListingCreatedEvent event = new ListingCreatedEvent(this, listingId);
        applicationEventPublisher.publishEvent(event);
    }
}
