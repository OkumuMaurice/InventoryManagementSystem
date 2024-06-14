package com.example.GeneralStore.GeneralStoreManagement.Inventory;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Inventory")
@Data
@NoArgsConstructor
public class Inventory {
    @Id
    private String productName;
    private double quantity;


    public Inventory(String productName, double quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }
}
