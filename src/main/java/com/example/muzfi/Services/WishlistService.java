package com.example.muzfi.Services;

import com.example.muzfi.Model.Wishlist;

import java.util.List;

public interface WishlistService {
    List<Wishlist> getWishlistsForUser(Integer userId);
    Wishlist createWishlist(Wishlist wishlist);
    void addItemToWishlist(Integer userId, String itemId);
    void removeItemFromWishlist(Integer userId, String itemId);
}
