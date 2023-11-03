package com.example.muzfi.Controller;

import com.example.muzfi.Model.Purchase;
import com.example.muzfi.Services.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/purchases")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping
    public Purchase createPurchase(@RequestBody Purchase purchase){
        return purchaseService.createPurchase(purchase);
    }

    @GetMapping
    public List<Purchase> findAllPurchases(){
        return purchaseService.findAllPurchases();
    }

    @DeleteMapping
    public void deleteAllPurchases(){
        purchaseService.deleteAllPurchases();
    }

    @GetMapping("/{id}")
    public Purchase findPurchaseById(@PathVariable String id){
        return purchaseService.findPurchaseById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id){
        purchaseService.deleteByPurchaseId(id);
    }
}
