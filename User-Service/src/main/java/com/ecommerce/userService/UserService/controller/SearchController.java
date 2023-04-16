package com.ecommerce.userService.UserService.controller;

import com.ecommerce.userService.UserService.client.SearchService;
import com.ecommerce.userService.UserService.entity.ItemElastic;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/search/ddd")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/getDocument/{itemId}/{userId}/{recommendation}")
    public ItemElastic getElementById(@PathVariable String itemId, @PathVariable String userId,@PathVariable boolean recommendation){
        ItemElastic itemElastic = searchService.getSearchForUsersById(itemId, userId,recommendation);
        return itemElastic;
    }

    @GetMapping("/getByName/{name}/{userId}")
    public ResponseEntity<Object> getElementByName(@PathVariable String name, @PathVariable String userId){
        List<ItemElastic> itemElastic = searchService.getSearchForUsersByName(name, userId);
        return new ResponseEntity<>(itemElastic,HttpStatus.CREATED);
    }

    @GetMapping("/getByBrand/{brand}/{userId}")
    public ResponseEntity<Object> getElementByBrand(@PathVariable String brand, @PathVariable String userId){
        List<ItemElastic> itemElastic = searchService.getSearchForUsersByBrand(brand, userId);
        return new ResponseEntity<>(itemElastic,HttpStatus.CREATED);
    }

    @GetMapping("/getByCategory/{category}/{userId}")
    public ResponseEntity<Object> getElementByCategory(@PathVariable String category, @PathVariable String userId){
        List<ItemElastic> itemElastic = searchService.getSearchForUsersByCategory(category, userId);
        return new ResponseEntity<>(itemElastic,HttpStatus.CREATED);
    }
}
