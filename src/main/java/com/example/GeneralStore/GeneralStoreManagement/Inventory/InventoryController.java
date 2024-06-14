package com.example.GeneralStore.GeneralStoreManagement.Inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{productName}")
    public ResponseEntity<Double> getCurrentInventory(@PathVariable String productName) {
        double currentInventory = inventoryService.calculateCurrentInventory(productName);
        return ResponseEntity.ok(currentInventory);
    }

    @GetMapping("/{productName}/check")
    public ResponseEntity<?> checkSufficientInventory(@PathVariable String productName, @RequestParam double requestedQuantity) {
        boolean sufficientInventory = inventoryService.checkSufficientInventory(productName, requestedQuantity);
        if (sufficientInventory) {
            return ResponseEntity.ok("Sufficient inventory available.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient inventory to fulfill the request.");
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Inventory>> getAllInventories() {
        List<Inventory> inventories = inventoryService.getAllInventories();
        return ResponseEntity.ok(inventories);
    }
}
