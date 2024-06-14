package com.example.GeneralStore.GeneralStoreManagement.Purchase;

import lombok.Data;

@Data
public class PurchaseRequest {
    private String productName;
    private Double costPerKg;
    private Double totalKg;
    private Double totalPurchase;
    private String paymentMethod;
}
