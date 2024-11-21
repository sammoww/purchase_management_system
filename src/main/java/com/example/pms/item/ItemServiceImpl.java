package com.example.pms.item;

import com.example.pms.purchase.PurchaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepo;
    private final PurchaseServiceImpl purchaseService;

    private ItemServiceImpl(ItemRepository itemRepo,PurchaseServiceImpl purchaseService){
        this.itemRepo = itemRepo;
        this.purchaseService = purchaseService;
    }

    @Override
    public ResponseEntity<String> addItem(ItemDto itemDto) {
        Item item = convertToEntity(itemDto);
        itemRepo.save(item);
        return ResponseEntity.status(HttpStatus.OK).body("Item has been added.\n" + item.toString());
    }

    @Override
    public ResponseEntity<String> removeItem(Long id) {
        itemRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Item has been deleted.");
    }

    @Override
    public ResponseEntity<String> updateItem(Long id, ItemDto itemDto) {
        Item item = convertToEntity(itemDto);
        Item previousItem = itemRepo.findById(id).orElseThrow();
        previousItem.setName(item.getName());
        previousItem.setBox(item.getBox());
        previousItem.setUnitsPerBox(item.getUnitsPerBox());
        previousItem.setUpdatedDate(LocalDateTime.now());
        itemRepo.save(previousItem);
        return ResponseEntity.status(HttpStatus.OK).body("Item updated!");
    }

    @Override
    public ResponseEntity<String> deleteItem(Long id) {
        purchaseService.deletePurchase(id);
        System.out.println("Deleting. I  am in ItemServiceImpl");
        itemRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Item with id: "+id+" has been deleted.");
    }

    private Item convertToEntity(ItemDto itemDto){
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setBox(itemDto.getBox());
        item.setUnitsPerBox(itemDto.getUnitsPerBox());
        return item;
    }

    private ItemDto convertToDto(Item item){
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setBox(item.getBox());
        itemDto.setUnitsPerBox(item.getUnitsPerBox());
        return itemDto;
    }

    public Page<Item> getAllItems(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return itemRepo.findAll(pageable);
    }

    @Override
    public Page<ItemDto> getItem(String name,int pageNo, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNo,pageSize);
        Page<Item> item = itemRepo.findAll(Specification.where(ItemSpecification.hasName(name)),pageRequest);
        Page<ItemDto> itemDTO = item.map(this::convertToDto);
        return itemDTO;
    }


}
