package com.example.pms.purchase;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepo extends JpaRepository<Purchase,Long>, JpaSpecificationExecutor<Purchase> {
    @Modifying //for INSERT,UPDATE,DELETE @Modifying is used.
    @Transactional //if query is disturbed somehow, transactions can be rolled back.
    @Query(value = "DELETE FROM Purchase p WHERE p.item.id = :item_id ")
    public  void deleteByItemId(Long item_id);

    @Query(value = "SELECT id FROM Purchase p WHERE p.item.id =:item_id ")
    public Long getPurchaseIdByItemId(Long item_id);

    public Page<Purchase> getByItemId(Specification<Purchase> specification, Pageable pageable);

}
