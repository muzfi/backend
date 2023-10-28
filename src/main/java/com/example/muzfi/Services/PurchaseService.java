package com.example.muzfi.Services;

import com.example.muzfi.Model.Purchase;

import java.util.List;

public interface PurchaseService {
    public Purchase createPurchase(Purchase purchase);

    public Purchase findPurchaseById(String id);

    public List<Purchase> findAllPurchases();

    public void deleteByPurchaseId(String id);



    public void deleteAllPurchases();
}
