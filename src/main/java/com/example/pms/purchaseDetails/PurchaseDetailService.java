package com.example.pms.purchaseDetails;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PurchaseDetailService {
    public ResponseEntity<String> sendPurchaseDetail(Long purchase_id);
    public ResponseEntity<String> deletePurchaseDetail(Long purchase_id );
    public Page<PurchaseDetail> getDetails(int page,int pageSize);
}
