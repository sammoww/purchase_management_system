package com.example.pms.item;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public interface ItemService {
    public ResponseEntity<String> addItem(ItemDto item);
    public ResponseEntity<String> removeItem(Long id);
    public ResponseEntity<String> updateItem(Long id, ItemDto item);
    public ResponseEntity<String> deleteItem(Long id);
    public Page<ItemDto> getItem(String name,int pageNo, int pageSize);

}
