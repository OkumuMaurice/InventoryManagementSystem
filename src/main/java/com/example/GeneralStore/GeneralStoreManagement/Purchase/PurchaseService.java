package com.example.GeneralStore.GeneralStoreManagement.Purchase;

import com.example.GeneralStore.GeneralStoreManagement.Inventory.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {
    private List<Purchase> purchases = new ArrayList<>();

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private InventoryService inventoryService;


    public Purchase createPurchase(String productName, Double costPerKg, Double totalKg, Double totalPurchase, String paymentMethod) {
        Purchase purchase = new Purchase();
        purchase.setProductName(productName);
        purchase.setCostPerKg(costPerKg);
        purchase.setTotalKg(totalKg);
        purchase.setTotalPurchases(totalPurchase);
        purchase.setPaymentMethod(paymentMethod);

        return purchaseRepository.save(purchase);
    }


    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }
}
