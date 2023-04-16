package com.ecommerce.itemService.controller;

import com.ecommerce.itemService.model.Item;
import com.ecommerce.itemService.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ddd/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item addItem(@RequestBody Item item){
        return itemService.addItem(item);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getAllItems(){
        return itemService.findAllItems();
    }

    @GetMapping("/id/{itemId}")
    public Item getItemById(@PathVariable String itemId){
        return itemService.findItemById(itemId);
    }

    @GetMapping("/category/{category}")
    public List<Item> getItemsByCategory(@PathVariable String category){
        return itemService.findItemsByCategory(category);
    }

    @GetMapping("/brand/{brand}")
    public List<Item> getItemsByBrand(@PathVariable String brand){

        return itemService.findItemsByBrand(brand);
    }

    @PutMapping("/update")
    public Item updateItem(@RequestBody Item updatedItem){
        return itemService.updateItem(updatedItem);
    }

    @DeleteMapping("/deleteItem/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteItem(String itemId){
        return itemService.deleteItem(itemId);
    }
}
