package com.example.GeneralStore.GeneralStoreManagement.ProfitLoss;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfitLossRepository extends JpaRepository<ProfitLoss, Long> {
    ProfitLoss findByProductName(String productName);
}
