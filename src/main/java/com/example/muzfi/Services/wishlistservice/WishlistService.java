package com.ecommerce.shoppingmarket.service.wishlistservice;

import com.ecommerce.shoppingmarket.model.Wishlist;


import java.util.List;

public interface WishlistService {
    void addItemToWishlist(Wishlist item);
    List<Wishlist> getUserWishlist(String userId);
}
