package com.example.GeneralStore.GeneralStoreManagement.Sale;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByProductName(String productName);

}
