package com.example.GeneralStore.GeneralStoreManagement.ProfitLoss;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProfitLoss {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private double totalKgPurchased;
    private double totalPurchases;
    private double totalKgSold;
    private double totalSales;
    private double profitOrLoss;




}
