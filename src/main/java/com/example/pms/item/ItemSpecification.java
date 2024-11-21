package com.example.pms.item;

import org.springframework.data.jpa.domain.Specification;

public class ItemSpecification {
    public static Specification<Item> hasName(String name){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"),name));
    }
}
