package com.ecommerce.shoppingmarket.controller;

import com.ecommerce.shoppingmarket.model.Wishlist;
import com.ecommerce.shoppingmarket.service.wishlistservice.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/products/{id}/wishlist")
    public String addItemToWishlist(@PathVariable String id, @RequestBody Wishlist item) {
        item.setProductId(id); // Set the product ID from the URL
        wishlistService.addItemToWishlist(item);
        return "Item with product ID " + id + " added to the wishlist successfully";
    }

    @GetMapping("/user/{userId}/wishlist")
    public List<Wishlist> getUserWishlist(@PathVariable String userId) {
        return wishlistService.getUserWishlist(userId);
    }
}
