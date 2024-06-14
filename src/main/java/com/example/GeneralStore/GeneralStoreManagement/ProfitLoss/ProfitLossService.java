package com.example.GeneralStore.GeneralStoreManagement.ProfitLoss;

import com.example.GeneralStore.GeneralStoreManagement.Purchase.Purchase;
import com.example.GeneralStore.GeneralStoreManagement.Purchase.PurchaseRepository;
import com.example.GeneralStore.GeneralStoreManagement.Sale.Sale;
import com.example.GeneralStore.GeneralStoreManagement.Sale.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfitLossService {

    @Autowired
    private ProfitLossRepository profitLossRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private SaleRepository saleRepository;

    public ProfitLoss calculateProfitOrLoss(String productName) {
        List<Purchase> purchases = purchaseRepository.findByProductName(productName);
        List<Sale> sales = saleRepository.findByProductName(productName);

        double totalKgPurchased = purchases.stream().mapToDouble(Purchase::getTotalKg).sum();
        double totalKgSold = sales.stream().mapToDouble(Sale::getTotalKg).sum();
        double totalPurchases = purchases.stream().mapToDouble(purchase -> purchase.getTotalKg() * purchase.getCostPerKg()).sum();
        double totalSales = sales.stream().mapToDouble(sale -> sale.getTotalKg() * sale.getCostPerKg()).sum();

        double profitOrLoss = totalSales - totalPurchases;

        ProfitLoss profitLoss = new ProfitLoss();
        profitLoss.setProductName(productName);
        profitLoss.setTotalKgPurchased(totalKgPurchased);
        profitLoss.setTotalKgSold(totalKgSold);
        profitLoss.setTotalPurchases(totalPurchases);
        profitLoss.setTotalSales(totalSales);
        profitLoss.setProfitOrLoss(profitOrLoss);

        return profitLossRepository.save(profitLoss);
    }

    public List<ProfitLoss> getAllProfitLoss() {
        List<String> productNames = saleRepository.findAll().stream()
                .map(Sale::getProductName).distinct().collect(Collectors.toList());

        return productNames.stream()
                .map(this::calculateProfitOrLoss)
                .collect(Collectors.toList());
    }
}
