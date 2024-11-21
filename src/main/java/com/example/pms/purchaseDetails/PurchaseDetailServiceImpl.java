package com.example.pms.purchaseDetails;
import com.example.pms.purchase.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseDetailServiceImpl implements PurchaseDetailService {
    private final PurchaseDetailRepo purchaseDetailRepo;
    private final PurchaseRepo purchaseRepo;

    PurchaseDetailServiceImpl(PurchaseDetailRepo purchaseDetailRepo, PurchaseRepo purchaseRepo){
        this.purchaseDetailRepo = purchaseDetailRepo;
        this.purchaseRepo = purchaseRepo;
    }
    @Override
    public ResponseEntity<String> sendPurchaseDetail(Long purchase_id) {
        Purchase purchase = purchaseRepo.findById(purchase_id).orElseThrow();
        int box = purchase.getBox();
        int unit = purchase.getUnitsPerBox();

        List<PurchaseDetail> purchaseDetail = new ArrayList<>();

        for(int i=1; i<=box; i++){
            String boxSerialNumber = purchase_id + "-" + i;
            for(int j=1; j<=unit; j++){
                String unitSerialNumber = boxSerialNumber + "-" + j;

                //create purchaseDetail object
                PurchaseDetail purchaseDetail1 = new PurchaseDetail();
                purchaseDetail1.setPurchase(purchase);
                purchaseDetail1.setBoxSerialNumber(boxSerialNumber);
                purchaseDetail1.setUnitSerialNumber(unitSerialNumber);
                purchaseDetail1.setCreatedDate(LocalDateTime.now());
                //add newly created object in the purchaseDetail ArrayList
                purchaseDetail.add(purchaseDetail1);
            }
        }
        purchaseDetailRepo.saveAll(purchaseDetail);
        return ResponseEntity.status(HttpStatus.OK).body("Purchase details have been added");
    }

    @Override
    public ResponseEntity<String> deletePurchaseDetail(Long purchase_id) {
        System.out.println("Deleting. I am in PurchaseDetailServiceImpl");
        purchaseDetailRepo.deleteByPurchaseId(purchase_id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted from purchaseDetails");
    }

    @Override
    public Page<PurchaseDetail> getDetails(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return purchaseDetailRepo.findAll(pageable);
    }

    private PurchaseDetailDTO convertToPurchaseDetailDTO(PurchaseDetail purchaseDetail){
        PurchaseDetailDTO pdt = new PurchaseDetailDTO();
        pdt.setPurchase_id(purchaseDetail.getPurchase().getId());
        pdt.setId(purchaseDetail.getId());
        pdt.setBoxSerialNumber(purchaseDetail.getBoxSerialNumber());
        pdt.setUnitSerialNumber(purchaseDetail.getUnitSerialNumber());
        return pdt;
    }

    public Page<PurchaseDetailDTO> getDetail(Long purchase_id){
        Specification<PurchaseDetail> spc = Specification.where(PurchaseDetailsSpecification.hasPurchaseId(purchase_id));
        Pageable pageable = PageRequest.of(0,10);
        Page<PurchaseDetail> purchaseDetail = purchaseDetailRepo.findAll(spc,pageable);
        Page<PurchaseDetailDTO> purchaseDetailDto = purchaseDetail.map(this::convertToPurchaseDetailDTO);
        return purchaseDetailDto;
    }
}
