package com.ecommerce.userService.UserService.client;

import com.ecommerce.userService.UserService.entity.ItemElastic;
import com.ecommerce.userService.UserService.model.ItemList;
import com.ecommerce.userService.UserService.model.RecommendedItemList;
import com.ecommerce.userService.UserService.model.RecommendedItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    RestTemplate restTemplate;

    private  String ROOT_URI = "http://localhost:8093/";

    private  String ROOT_URI1 = "http://localhost:8096/";

    public ItemElastic getSearchForUsersById(String itemId, String userId,boolean recommendation) {
        String uri = ROOT_URI+"getDocument/"+itemId+"/"+userId+"/"+recommendation;
        ResponseEntity<ItemElastic> response = restTemplate.getForEntity(uri,ItemElastic.class);

        ItemElastic itemElastic = response.getBody();

        return itemElastic;
    }

    public List<ItemElastic> getSearchForUsersByName(String name, String userId) {
        String uri = ROOT_URI+"findByName/"+name+"/"+userId;
        ResponseEntity<ItemList> response = restTemplate.getForEntity(uri,ItemList.class);

        List<ItemElastic> items = response.getBody().getItemElastics();
        return items;
    }

    public List<ItemElastic> getSearchForUsersByBrand(String brand, String userId) {
        String uri = ROOT_URI+"findByBrand/"+brand+"/"+userId;
        ResponseEntity<ItemList> response = restTemplate.getForEntity(uri,ItemList.class);

        List<ItemElastic> items = response.getBody().getItemElastics();
        return items;
    }

    public List<ItemElastic> getSearchForUsersByCategory(String category, String userId) {
        String uri = ROOT_URI+"findByCategory/"+category+"/"+userId;
        ResponseEntity<ItemList> response = restTemplate.getForEntity(uri,ItemList.class);

        List<ItemElastic> items = response.getBody().getItemElastics();
        return items;
    }

    public List<RecommendedItems> getRecommendationForUser(String userId) {
        String uri = ROOT_URI1+"recommendation/userId/"+userId;
        ResponseEntity<RecommendedItemList> response = restTemplate.getForEntity(uri,RecommendedItemList.class);


        return response.getBody().getRecommendedItems();
    }
}
