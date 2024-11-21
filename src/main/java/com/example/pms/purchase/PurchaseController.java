package com.example.pms.purchase;

import com.example.pms.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/purchase")
@RestController
public class PurchaseController {
    private final PurchaseServiceImpl purchaseService;

    PurchaseController(PurchaseServiceImpl purchaseService){
        this.purchaseService = purchaseService;
    }

    @PostMapping("/savePurchase")
    public ResponseEntity<String> savePurchase(@RequestBody PurchaseDTO purchaseDto){
        return purchaseService.savePurchase(purchaseDto);
    }

    @DeleteMapping("/deletePurchase/{id}")
    public ResponseEntity<String> deletePurchase(@PathVariable Long id){
        return purchaseService.deletePurchase(id);
    }

//    @GetMapping("/getPurchases")
//    public Page<Purchase> getPurchases(@RequestParam(name="page",defaultValue="0")int page,@RequestParam(name="size",defaultValue="10")int pageSize){
//        Pageable pageable = PageRequest.of(page,pageSize);
//        return purchaseService.getPurchases(pageable);
//    }

//    @PutMapping("/updatePurchase")
//    public ResponseEntity<UpdatePurchaseDTO> updatePurchase(@RequestBody UpdatePurchaseDTO updatePurchaseDto){
//        return purchaseService.updatePurchase(updatePurchaseDto);
//    }

//    @GetMapping("/getPurchase/{item_id}")
//    public Page<PurchaseResponseDTO> getPurchase(@PathVariable Long item_id){
//        return purchaseService.getPurchase(item_id);
//    }

    @GetMapping("/getPurchase/{item_id}")
    public Page<PurchaseResponseDTO> getPurchase(@PathVariable Long item_id){
        return purchaseService.getPurchase(item_id);
    }

}
