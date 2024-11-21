package com.example.pms.purchaseDetails;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchaseItemDetail")
public class PurchaseDetailController {
    private final PurchaseDetailServiceImpl purchaseDetailService;

    public PurchaseDetailController(PurchaseDetailServiceImpl purchaseDetailService) {
        this.purchaseDetailService = purchaseDetailService;
    }

    @PostMapping("/getDetail/{id}")
    public ResponseEntity<String> getDetail(@PathVariable Long id) {
        return purchaseDetailService.sendPurchaseDetail(id);
    }

    @GetMapping("getDetails")
    public Page<PurchaseDetail> getDetails(@RequestParam(name = "page",defaultValue = "0")int page, @RequestParam(name="size",defaultValue="10")int pageSize){
        return purchaseDetailService.getDetails(page,pageSize);
    }

    @GetMapping("getPurchaseDetail/{purchase_id}")
    public Page<PurchaseDetailDTO> getPurchaseDetail(@PathVariable Long purchase_id){
        return purchaseDetailService.getDetail(purchase_id);
    }
}
