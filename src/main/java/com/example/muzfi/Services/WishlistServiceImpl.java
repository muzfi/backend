package com.example.muzfi.Services;

import com.example.muzfi.Model.Wishlist;
import com.example.muzfi.Repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Override
    public List<Wishlist> getWishlistsForUser(Integer userId) {
        return wishlistRepository.findAllByUserId(userId);
    }

    @Override
    public Wishlist createWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    @Override
    public void addItemToWishlist(Integer userId, String itemId) {
        Wishlist wishlist = (Wishlist) wishlistRepository.findByUserId(userId).orElse(null);
        if(wishlist != null) {
            wishlist.getListingIds().add(itemId);
            wishlistRepository.save(wishlist);
        }
    }

    @Override
    public void removeItemFromWishlist(Integer userId, String itemId) {
        Wishlist wishlist = (Wishlist) wishlistRepository.findByUserId(userId).orElse(null);
        if(wishlist != null) {
            wishlist.getListingIds().remove(itemId);
            wishlistRepository.save(wishlist);
        }
    }
}
