package com.example.GeneralStore.GeneralStoreManagement.Sale;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Sales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String productName;
    private double costPerKg;
    private double totalKg;
    private double totalSales;
    private String paymentMethod;

}
