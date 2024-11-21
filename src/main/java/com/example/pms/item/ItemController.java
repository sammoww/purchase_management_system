package com.example.pms.item;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemServiceImpl itemService;

    ItemController(ItemServiceImpl itemService){
        this.itemService = itemService;
    }

    @GetMapping("/getItems")
    public Page<Item> getAllItems(@RequestParam(name="page",defaultValue = "0")int page, @RequestParam(name="size",defaultValue="10")int size){
        return itemService.getAllItems(page,size);
    }

    @PostMapping("/addItem")
    public ResponseEntity<String> addItem(@RequestBody ItemDto item){
        return itemService.addItem(item);
    }

    @PutMapping("/updateItem/{id}")
    public ResponseEntity<String> updateItem(@PathVariable Long id, @RequestBody ItemDto item){
        return itemService.updateItem(id,item);
    }

    @DeleteMapping("/deleteItem/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id){
        return itemService.deleteItem(id);
    }

    @GetMapping("/getItemByName")
    public Page<ItemDto> getItemPage(@RequestParam(name="name")String name,@RequestParam(name="page",defaultValue = "0")int page, @RequestParam(name="size",defaultValue="10")int size){
        System.out.println("name" + name );
        return itemService.getItem(name,page,size);
    }
}
