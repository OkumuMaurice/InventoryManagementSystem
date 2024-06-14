package com.example.GeneralStore.GeneralStoreManagement.Sale;

import lombok.Data;



@Data
public class SaleRequest {
    private String productName;
    private double costPerKg;
    private double totalKg;
    private double totalSales;
    private String paymentMethod;

}
