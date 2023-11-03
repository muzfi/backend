package com.example.muzfi.Services;

import com.example.muzfi.Model.Product;
import com.example.muzfi.Enums.ProductCondition;
import com.example.muzfi.Repository.ProductRepository;
import com.example.muzfi.Services.EmailConfirmationService.EmailConfirmationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EmailConfirmationService emailConfirmationService;

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProduct(String id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getProductsByCondition(ProductCondition condition) {
        return productRepository.findByCondition(condition);
    }

    @Override
    public List<Product> getProductsByBrand(String brandId) {
        return productRepository.findByBrandId(brandId);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<String> getTopBrands() {
        List<Product> products = productRepository.findAll();
        Map<String, Long> brandCountMap = products.stream()
                .collect(Collectors.groupingBy(Product::getBrandId, Collectors.counting()));

        return brandCountMap.entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public Product saveProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        if ("active".equals(savedProduct.getStatus())) {
            try {
                emailConfirmationService.sendNewListingConfirmation(savedProduct.getSellerEmail(), savedProduct);
            } catch (MessagingException e) {
                // Handle the exception if email confirmation fails
            }
        }
        return savedProduct;
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}