package com.example.pms.purchase;

import com.example.pms.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchaseService {
    public ResponseEntity<String> savePurchase(PurchaseDTO purchaseDto);
//    public ResponseEntity<UpdatePurchaseDTO> updatePurchase(UpdatePurchaseDTO updatePurchaseDto);
    public ResponseEntity<String> deletePurchase(Long id);

//    public Page<Purchase> getPurchases(Pageable pageable);
    public Page<PurchaseResponseDTO> getPurchase(Long item_id);

}
