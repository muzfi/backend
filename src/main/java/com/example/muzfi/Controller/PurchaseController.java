package com.example.muzfi.Controller;

import com.example.muzfi.Model.FeedbackRequest;
import com.example.muzfi.Model.Purchase;
import com.example.muzfi.Services.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/purchases")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping("/createPurchase")
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

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Purchase>> getPurchasesByUserId(@PathVariable String userId) {
        List<Purchase> purchases = purchaseService.findPurchasesByUserId(userId);
        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    public void deletePurchasesByUserId(@PathVariable String userId) {
        purchaseService.deletePurchasesByUserId(userId);
    }

    @PostMapping("/feedback")
    public ResponseEntity<String> giveFeedback(@RequestBody FeedbackRequest feedbackRequest) {
        String purchaseId = feedbackRequest.getPurchaseId();
        String feedbackMessage = feedbackRequest.getFeedbackMessage();
        purchaseService.giveFeedback(feedbackMessage, purchaseId);
        return new ResponseEntity<>("Feedback submitted successfully", HttpStatus.OK);
    }

    @GetMapping("/feedback/{purchaseId}")
    public ResponseEntity<String> getFeedback(@PathVariable String purchaseId) {
        String feedbackMessage = purchaseService.findFeedback(purchaseId);
        if (feedbackMessage != null) {
            return new ResponseEntity<>(feedbackMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Feedback not found", HttpStatus.NOT_FOUND);
        }
    }
}
