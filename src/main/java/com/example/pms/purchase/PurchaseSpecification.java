package com.example.pms.purchase;

import com.example.pms.item.Item;
import org.springframework.data.jpa.domain.Specification;

public class PurchaseSpecification {
    public static Specification<Purchase> hasItemId(Long itemId) {
        System.out.println("I am in Purchase specification");
        if(itemId == null) throw new RuntimeException("Null value in PurchaseSpecification");
        return ((root,query,criteriaBuilder) -> criteriaBuilder.equal(root.get("item").get("id"),itemId));
    }
}
