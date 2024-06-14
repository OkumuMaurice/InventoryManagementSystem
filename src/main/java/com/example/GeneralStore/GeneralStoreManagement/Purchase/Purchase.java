package com.example.GeneralStore.GeneralStoreManagement.Purchase;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Purchase")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String productName;
    private double costPerKg;
    private double totalKg;
    private double totalPurchases;
    private String paymentMethod;

  }
