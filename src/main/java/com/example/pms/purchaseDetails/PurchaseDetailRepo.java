package com.example.pms.purchaseDetails;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseDetailRepo extends JpaRepository<PurchaseDetail,Long>, JpaSpecificationExecutor<PurchaseDetail> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM PurchaseDetail p WHERE p.purchase.id = :purchase_id")
    public void deleteByPurchaseId(Long purchase_id);
}
