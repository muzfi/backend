package com.example.muzfi.Services;

import com.example.muzfi.Model.Purchase;
import com.example.muzfi.Repository.ProductRepository;
import com.example.muzfi.Repository.PurchaseRepository;
import com.example.muzfi.Repository.UserRepository;
import com.example.muzfi.Services.EmailConfirmationService.EmailConfirmationService;
import com.example.muzfi.Services.EmailConfirmationService.EmailNotification.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final EmailNotificationService emailNotificationService;


    @Override
    public Purchase createPurchase(Purchase purchase) {
        Purchase createdPurchase = purchaseRepository.save(purchase);

        // Send an email notification for the sold item
        String recipientEmail = createdPurchase.getUser().getEmail();
        String itemName = createdPurchase.getProduct().getName();
        emailNotificationService.sendItemSoldNotification(recipientEmail, itemName);

        return createdPurchase;
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
