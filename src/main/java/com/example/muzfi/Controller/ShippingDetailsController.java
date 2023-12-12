package com.example.muzfi.Controller;

import com.example.muzfi.Model.ShippingDetails;
import com.example.muzfi.Services.ShippingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipping")
public class ShippingDetailsController {

    @Autowired
    private ShippingDetailsService shippingDetailsService;

    @PostMapping("/create")
    public ResponseEntity<ShippingDetails> createShippingDetails(@RequestBody ShippingDetails shippingDetails) {
        ShippingDetails createdShippingDetails = shippingDetailsService.createShippingDetails(shippingDetails);
        return ResponseEntity.ok(createdShippingDetails);
    }

    @GetMapping("/{shippingId}")
    public ResponseEntity<ShippingDetails> getShippingDetails(@PathVariable String shippingId) {
        ShippingDetails shippingDetails = shippingDetailsService.getShippingDetailsById(shippingId);
        return ResponseEntity.ok(shippingDetails);
    }

    @PutMapping("/update/{shippingId}")
    public ResponseEntity<ShippingDetails> updateShippingDetails(@PathVariable String shippingId, @RequestBody ShippingDetails shippingDetails) {
        ShippingDetails updatedShippingDetails = shippingDetailsService.updateShippingDetails(shippingId, shippingDetails);
        return ResponseEntity.ok(updatedShippingDetails);
    }
}
