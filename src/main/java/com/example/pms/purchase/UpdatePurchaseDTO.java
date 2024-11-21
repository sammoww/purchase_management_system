package com.example.pms.purchase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePurchaseDTO {

    private Long id;
    private int box;
    private int unitsPerBox;

}
