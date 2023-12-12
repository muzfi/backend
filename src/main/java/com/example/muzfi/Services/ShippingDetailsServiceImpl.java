package com.example.muzfi.Services;

import com.example.muzfi.Model.ShippingDetails;
import com.example.muzfi.Repository.ShippingDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingDetailsServiceImpl implements ShippingDetailsService {

    private final ShippingDetailsRepository shippingDetailsRepository;

    @Override
    public ShippingDetails createShippingDetails(ShippingDetails shippingDetails) {
        // Logic to create shipping details
        return shippingDetailsRepository.save(shippingDetails);
    }

    @Override
    public ShippingDetails getShippingDetailsById(String shippingId) {
        // Logic to retrieve shipping details
        return shippingDetailsRepository.findById(shippingId)
                .orElseThrow(() -> new RuntimeException("Shipping details not found"));
    }

    @Override
    public ShippingDetails updateShippingDetails(String shippingId, ShippingDetails shippingDetails) {
        // Logic to update shipping details
        ShippingDetails existingDetails = getShippingDetailsById(shippingId);
        // Update the fields of existingDetails with those from shippingDetails
        // Note: Implement the actual field update logic as per your requirements
        return shippingDetailsRepository.save(existingDetails);
    }
}
