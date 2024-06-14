package com.example.GeneralStore.GeneralStoreManagement.Purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/add_purchase")
    public ResponseEntity<Purchase> createPurchase(@RequestBody PurchaseRequest purchaseRequest) {
        Purchase purchase = purchaseService.createPurchase(
                purchaseRequest.getProductName(),
                purchaseRequest.getCostPerKg(),
                purchaseRequest.getTotalKg(),
                purchaseRequest.getTotalPurchase(),
                purchaseRequest.getPaymentMethod()
        );
        return ResponseEntity.ok(purchase);
    }


    @GetMapping("/all_purchases")
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }
}
