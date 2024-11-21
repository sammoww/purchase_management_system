package com.example.pms.purchase;

import com.example.pms.item.Item;
import com.example.pms.item.ItemRepository;
import com.example.pms.purchaseDetails.PurchaseDetailRepo;
import com.example.pms.purchaseDetails.PurchaseDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService{
    @Autowired
    private final PurchaseRepo purchaseRepo;
    @Autowired
    private final ItemRepository itemRepo;
    @Autowired
    private final PurchaseDetailServiceImpl purchaseDetailService;
    @Autowired
    private PurchaseDetailRepo purchaseDetailRepo;

    PurchaseServiceImpl(PurchaseRepo purchaseRepo, ItemRepository itemRepo, PurchaseDetailServiceImpl purchaseDetailService){
        this.purchaseRepo = purchaseRepo;
        this.itemRepo = itemRepo;
        this.purchaseDetailService = purchaseDetailService;
    }

    @Override
    public ResponseEntity<String> savePurchase(PurchaseDTO purchaseDto){
        Item item = itemRepo.findById(purchaseDto.getItem_id()).orElseThrow();
        LocalDateTime localDateTime = LocalDateTime.now();
        Purchase purchase = new Purchase(item.getBox(),item.getUnitsPerBox(),item,localDateTime);
        purchase.setItem(item);
        purchaseRepo.save(purchase);

//      code added at 11/12/2024
//      I wanted to call purchaseDetails right after the savePurchase method is called, so that unique id generation could happen.
//      so, made a constructor of PurchaseDTO2,
//      passed purchase.getId() as parameter to the DTO2,
//      DTO2 is to send the PK of Purchase to PurchaseDetail
//      got the expected result.

        purchaseDetailService.sendPurchaseDetail(purchase.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Purchase and purchaseDetails has been saved.");
    }

    @Override
    public ResponseEntity<String> deletePurchase(Long item_id) {
        Long purchase_id = purchaseRepo.getPurchaseIdByItemId(item_id);
        System.out.println("Purchase id is:"+ item_id+ " I am in Purchase");
        purchaseDetailService.deletePurchaseDetail(purchase_id);
        System.out.println("I went to PurchaseDetail and have come out to Purchase");
        purchaseRepo.deleteByItemId(item_id);
        return ResponseEntity.status(HttpStatus.OK).body("Purchase has been deleted");
    }


    private PurchaseResponseDTO convertToPurchaseDTO(Purchase purchase){
        PurchaseResponseDTO purchaseResponseDTO = new PurchaseResponseDTO();
        purchaseResponseDTO.setId(purchase.getId());
        purchaseResponseDTO.setBox(purchase.getBox());
        purchaseResponseDTO.setItem_id(purchase.getItem().getId());
        purchaseResponseDTO.setUnitsPerBox(purchase.getUnitsPerBox());
        return purchaseResponseDTO;
    }

    @Override
    public Page<PurchaseResponseDTO> getPurchase(Long item_id) {
        Specification<Purchase> spc = Specification.where(PurchaseSpecification.hasItemId(item_id));
        System.out.println("I am out of PurchaseSpecification and in getPurchase");
        System.out.println(spc);
        Pageable pageable = PageRequest.of(0,1);
        System.out.println("I am below PageRequest");
        Page<Purchase> purchase = purchaseRepo.findAll(spc,pageable);
        System.out.println("I am out of getPurchase");
        Page<PurchaseResponseDTO> purchaseDto =  purchase.map(this::convertToPurchaseDTO);
        return purchaseDto;
    }




}
