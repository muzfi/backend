package com.example.muzfi.Services;

import com.example.muzfi.Model.Product;
import com.example.muzfi.Model.Purchase;
import com.example.muzfi.Model.User;
import com.example.muzfi.Repository.ProductRepository;
import com.example.muzfi.Repository.PurchaseRepository;
import com.example.muzfi.Repository.UserRepository;
import com.example.muzfi.Services.EmailConfirmationService.EmailNotification.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final EmailNotificationService emailNotificationService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public Purchase createPurchase(Purchase purchase) {
        purchase.setPurchaseDate(LocalDateTime.now());
        double totalAmount = 0;
        for (Product product : purchase.getProducts()) {
            totalAmount = totalAmount + product.getPrice();
        }
        purchase.setTotalAmount(totalAmount);
        Purchase createdPurchase = purchaseRepository.save(purchase);

        // Send an email notification for the sold item
        String recipientEmail = createdPurchase.getUser().getEmail();
        for (Product product : createdPurchase.getProducts()) {
            emailNotificationService.sendItemSoldNotification(recipientEmail, product.getName());
        }
        return createdPurchase;
    }

    @Override
    public Purchase findPurchaseById(String id) {
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        return purchase.orElse(null);
    }

    //Purchase history of all users
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

    //Purchase history of specific user
    @Override
    public List<Purchase> findPurchasesByUserId(String  userId) {
        Optional<User> user = userRepository.findById(userId);
        User user1 = null;
        if(user.isPresent()){
             user1 = user.get();
        }
        return purchaseRepository.findByUser(user1);
    }

    //Delete Purchase history of specific user
    @Override
    public void deletePurchasesByUserId(String  userId) {
        Optional<User> user = userRepository.findById(userId);
        User user1 = null;
        if(user.isPresent()){
            user1 = user.get();
        }
        purchaseRepository.deleteByUser(user1);
    }

    @Override
    public void giveFeedback(String message, String id) {
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        if (purchase.isPresent()) {
            Purchase purchase1 = purchase.get();
            purchase1.setFeedBack(message);
            purchaseRepository.save(purchase1);
        }
    }

    @Override
    public String findFeedback(String id) {
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        String feedbackMessage = null;
        if (purchase.isPresent()) {
            Purchase purchase1 = purchase.get();
            feedbackMessage =  purchase1.getFeedBack();
        }
        return feedbackMessage;
    }
}
