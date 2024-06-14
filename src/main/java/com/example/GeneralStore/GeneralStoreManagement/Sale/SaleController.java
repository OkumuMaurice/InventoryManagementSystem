package com.example.GeneralStore.GeneralStoreManagement.Sale;

import com.example.GeneralStore.GeneralStoreManagement.Inventory.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/add_sale")
    public ResponseEntity<?> createSale(@RequestBody SaleRequest saleRequest) {
        // Check if there is sufficient inventory before creating the sale
        boolean sufficientInventory = inventoryService.checkSufficientInventory(saleRequest.getProductName(), saleRequest.getTotalKg());
        if (!sufficientInventory) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient inventory to conduct the sale.");
        }

        // Proceed with creating the sale if inventory is sufficient
        Sale sale = saleService.createSale(
                saleRequest.getProductName(),
                saleRequest.getCostPerKg(),
                saleRequest.getTotalKg(),
                saleRequest.getTotalSales(),
                saleRequest.getPaymentMethod()
        );
        return ResponseEntity.ok(sale);
    }

    @GetMapping("/all")
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }
}
