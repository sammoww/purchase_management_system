package com.example.pms.purchaseDetails;

import org.springframework.data.jpa.domain.Specification;

public class PurchaseDetailsSpecification {
    public static Specification<PurchaseDetail> hasPurchaseId(Long purchaseId){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("purchase").get("id"),purchaseId));
    }
}
