package com.example.GeneralStore.GeneralStoreManagement.Inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String> {
    Inventory findByProductName(String productName);
}
