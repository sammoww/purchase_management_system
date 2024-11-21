package com.example.pms.purchase;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResponseDTO {
    private Long id;
    private Long item_id;
    private int box;
    private int unitsPerBox;
}
