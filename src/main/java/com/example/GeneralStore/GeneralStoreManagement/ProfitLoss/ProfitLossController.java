package com.example.GeneralStore.GeneralStoreManagement.ProfitLoss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profit-loss")
public class ProfitLossController {

    @Autowired
    private ProfitLossService profitLossService;

    @GetMapping("/calculate")
    public ResponseEntity<ProfitLoss> calculateProfitOrLoss(@RequestParam String productName) {
        ProfitLoss profitLoss = profitLossService.calculateProfitOrLoss(productName);
        return ResponseEntity.ok(profitLoss);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfitLoss>> getAllProfitLoss() {
        List<ProfitLoss> profitLossList = profitLossService.getAllProfitLoss();
        return ResponseEntity.ok(profitLossList);
    }
}
