package com.example.muzfi.Controller;

import com.example.muzfi.Enums.ProductCondition;
import com.example.muzfi.Model.Product;
import com.example.muzfi.Services.ProductService;
import com.example.muzfi.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marketplace")
public class MarketplaceController {

    @Autowired
    private ProductService productService;

    @GetMapping("/topBrands")
    public ResponseEntity<List<String>> getTopBrands() {
        List<String> topBrands = productService.getTopBrands();
        return ResponseEntity.ok(topBrands);
    }

    @GetMapping("/filterByBrand/{brand}")
    public ResponseEntity<List<Product>> filterProductsByBrand(@PathVariable String brand) {
        List<Product> products = productService.getProductsByBrand(brand);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        Optional<Product> product = productService.getProduct(id);
        return product.map(ResponseEntity::ok).orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
    }
}
