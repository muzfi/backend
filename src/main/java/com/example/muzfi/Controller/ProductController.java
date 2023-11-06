package com.example.muzfi.Controller;

import com.example.muzfi.Enums.ProductCondition;
import com.example.muzfi.Model.Product;
import com.example.muzfi.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        Optional<Product> product = productService.getProduct(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/newProduct")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/byCondition/{condition}")
    public ResponseEntity<List<Product>> getProductsByCondition(@PathVariable ProductCondition condition) {
        List<Product> products = productService.getProductsByCondition(condition);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/byBrand/{brandId}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brandId) {
        List<Product> products = productService.getProductsByBrand(brandId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/byCategory/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/byPriceRange")
    public ResponseEntity<List<Product>> getProductsByPriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        List<Product> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }
}
