package com.example.pms.purchaseDetails;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDetailDTO {
    private Long id;
    private Long purchase_id;
    private String boxSerialNumber;
    private String unitSerialNumber;
}
