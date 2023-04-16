package com.ecommerce.cartAndWishlist.CartAndWishlist.service;

import com.ecommerce.cartAndWishlist.CartAndWishlist.entity.ItemElastic;
import com.ecommerce.cartAndWishlist.CartAndWishlist.entity.WishListEntity;
import com.ecommerce.cartAndWishlist.CartAndWishlist.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WishListService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WishListRepository wishListRepository;

    private String ROOT_URI = "http://localhost:8094/";

    public WishListEntity addItemAsWishList(String itemId, String userId,boolean recommendation) {
        String uri = ROOT_URI+"search/getDocument/"+itemId+"/"+userId+"/"+recommendation;

        ResponseEntity<ItemElastic> response = restTemplate.getForEntity(uri,ItemElastic.class);

        ItemElastic itemElastic = response.getBody();

        //Transform ItemElastic to WishListEntity

        WishListEntity wishListEntity = new WishListEntity();
        wishListEntity.setUserId(userId);
        wishListEntity.setBrand(itemElastic.getBrand());
        wishListEntity.setCategory(itemElastic.getCategory());
        wishListEntity.setDescription(itemElastic.getDescription());
        wishListEntity.setName(itemElastic.getName());
        wishListEntity.setPrice(itemElastic.getPrice());

        wishListRepository.save(wishListEntity);

        return wishListEntity;
    }
}
