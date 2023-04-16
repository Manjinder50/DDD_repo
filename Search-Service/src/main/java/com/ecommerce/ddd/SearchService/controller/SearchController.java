package com.ecommerce.ddd.SearchService.controller;

import com.ecommerce.ddd.SearchService.model.ItemElastic;
import com.ecommerce.ddd.SearchService.model.ItemList;
import com.ecommerce.ddd.SearchService.repo.ElasticSearchQuery;
import com.ecommerce.ddd.SearchService.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class SearchController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ElasticSearchQuery elasticSearchQuery;


    @GetMapping("/getDocument/{itemId}/{userId}/{recommendation}")
    public ResponseEntity<Object> getDocumentById(@PathVariable String itemId,@PathVariable String userId,@PathVariable boolean recommendation) throws IOException {
        ItemElastic itemElastic =  elasticSearchQuery.getDocumentById(itemId,userId,recommendation);
        return new ResponseEntity<>(itemElastic, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDocument")
    public ResponseEntity<Object> deleteDocumentById(@RequestParam String itemId) throws IOException {
        String response =  elasticSearchQuery.deleteDocumentById(itemId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*@GetMapping("/searchDocument")
    public ResponseEntity<Object> searchAllDocument() throws IOException {
        List<ItemElastic> products = elasticSearchQuery.searchAllDocuments();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }*/

    @GetMapping("/findByName/{name}/{userId}")
    public ItemList findByName(@PathVariable String name, @PathVariable String userId) throws IOException {
        return elasticSearchQuery.findByName(name,userId);
    }

    @GetMapping("/findByCategory/{category}/{userId}")
    public ItemList findByCategory(@PathVariable String category,@PathVariable String userId){
        return elasticSearchQuery.findByCategory(category, userId);
    }

    @GetMapping("/findByBrand/{brand}/{userId}")
    public ItemList findByBrand(@PathVariable String brand,@PathVariable String userId){
        return elasticSearchQuery.findByBrand(brand,userId);
    }
}
