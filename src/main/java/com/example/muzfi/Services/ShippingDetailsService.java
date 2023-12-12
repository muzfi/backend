package com.example.muzfi.Services;

import com.example.muzfi.Model.ShippingDetails;

public interface ShippingDetailsService {
    ShippingDetails createShippingDetails(ShippingDetails shippingDetails);
    ShippingDetails getShippingDetailsById(String shippingId);
    ShippingDetails updateShippingDetails(String shippingId, ShippingDetails shippingDetails);
}
