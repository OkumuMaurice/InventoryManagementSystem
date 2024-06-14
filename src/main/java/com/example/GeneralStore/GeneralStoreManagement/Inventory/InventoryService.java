package com.example.GeneralStore.GeneralStoreManagement.Inventory;

import com.example.GeneralStore.GeneralStoreManagement.Purchase.Purchase;
import com.example.GeneralStore.GeneralStoreManagement.Purchase.PurchaseRepository;
import com.example.GeneralStore.GeneralStoreManagement.Sale.Sale;
import com.example.GeneralStore.GeneralStoreManagement.Sale.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private final InventoryRepository inventoryRepository;
    @Autowired
    private final PurchaseRepository purchaseRepository;
    @Autowired
    private final SaleRepository saleRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, PurchaseRepository purchaseRepository, SaleRepository saleRepository) {
        this.inventoryRepository = inventoryRepository;
        this.purchaseRepository = purchaseRepository;
        this.saleRepository = saleRepository;
    }

    public boolean checkSufficientInventory(String productName, double requestedQuantity) {
        double currentInventory = calculateCurrentInventory(productName);
        return currentInventory >= requestedQuantity;
    }

    public double calculateCurrentInventory(String productName) {
        List<Purchase> purchases = purchaseRepository.findByProductName(productName);
        List<Sale> sales = saleRepository.findByProductName(productName);

        double totalPurchases = purchases.stream().mapToDouble(Purchase::getTotalKg).sum();
        double totalSales = sales.stream().mapToDouble(Sale::getTotalKg).sum();

        return totalPurchases - totalSales;
    }

    public boolean reduceInventory(String productName, double quantity) {
        double currentInventory = calculateCurrentInventory(productName);
        if (currentInventory >= quantity) {
            // Logic to reduce inventory is handled by tracking sales
            return true;
        }
        return false;
    }

    public List<Inventory> getAllInventories() {
        List<String> productNames = purchaseRepository.findAllProductNames();
        return productNames.stream()
                .map(productName -> new Inventory(productName, calculateCurrentInventory(productName)))
                .collect(Collectors.toList());
    }
}
