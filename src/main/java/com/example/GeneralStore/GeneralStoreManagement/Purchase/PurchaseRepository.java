package com.example.GeneralStore.GeneralStoreManagement.Purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByProductName(String productName);

    @Query("SELECT DISTINCT p.productName FROM Purchase p")
    List<String> findAllProductNames();

}
