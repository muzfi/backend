package com.example.muzfi.Services;

import com.example.muzfi.Model.Purchase;
import com.example.muzfi.Repository.ProductRepository;
import com.example.muzfi.Repository.PurchaseRepository;
import com.example.muzfi.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    @Override
    public Purchase createPurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public Purchase findPurchaseById(String id) {
        return purchaseRepository.findById(id).get();
    }

    @Override
    public List<Purchase> findAllPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public void deleteByPurchaseId(String id) {
        purchaseRepository.deleteById(id);
    }

    @Override
    public void deleteAllPurchases() {
        purchaseRepository.deleteAll();
    }

}
