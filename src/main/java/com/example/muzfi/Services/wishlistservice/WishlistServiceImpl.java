package com.ecommerce.shoppingmarket.service.wishlistservice;

import com.ecommerce.shoppingmarket.model.Wishlist;
import com.ecommerce.shoppingmarket.repo.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepo wishlistRepo;

    @Override
    public void addItemToWishlist(Wishlist item) {
        wishlistRepo.save(item);
    }

    @Override
    public List<Wishlist> getUserWishlist(String userId) {
        return wishlistRepo.findByUserId(userId);
    }
}
