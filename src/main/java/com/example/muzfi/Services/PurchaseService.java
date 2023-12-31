package com.example.muzfi.Services;

import com.example.muzfi.Model.Purchase;
import com.example.muzfi.Model.User;

import java.util.List;

public interface PurchaseService {
    public Purchase createPurchase(Purchase purchase);
    public Purchase findPurchaseById(String id);
    public List<Purchase> findAllPurchases();
    public void deleteByPurchaseId(String id);
    public void deleteAllPurchases();
    public List<Purchase> findPurchasesByUserId(String userId);
    public void deletePurchasesByUserId(String  userId);
    public void giveFeedback(String message, String id);
    public String findFeedback(String id);
}
