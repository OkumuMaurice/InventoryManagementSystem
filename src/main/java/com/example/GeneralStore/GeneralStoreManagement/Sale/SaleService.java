package com.example.GeneralStore.GeneralStoreManagement.Sale;

import com.example.GeneralStore.GeneralStoreManagement.Inventory.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private InventoryService inventoryService;

    @Transactional
    public Sale createSale(String productName, double costPerKg, double totalKg, double totalSales, String paymentMethod) {
        // Check inventory
        if (!inventoryService.checkSufficientInventory(productName, totalKg)) {
            throw new RuntimeException("Insufficient inventory to conduct the sale.");
        }

        // Create sale
        Sale sale = new Sale();
        sale.setProductName(productName);
        sale.setCostPerKg(costPerKg);
        sale.setTotalKg(totalKg);
        sale.setTotalSales(totalSales);
        sale.setPaymentMethod(paymentMethod);

        // Update inventory
        boolean inventoryUpdated = inventoryService.reduceInventory(productName, totalKg);
        if (!inventoryUpdated) {
            throw new RuntimeException("Error updating inventory.");
        }

        return saleRepository.save(sale);
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
}
