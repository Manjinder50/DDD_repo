package com.ecommerce.cartAndWishlist.CartAndWishlist.service;

import com.ecommerce.cartAndWishlist.CartAndWishlist.entity.CartEntity;
import com.ecommerce.cartAndWishlist.CartAndWishlist.entity.ItemElastic;
import com.ecommerce.cartAndWishlist.CartAndWishlist.entity.WishListEntity;
import com.ecommerce.cartAndWishlist.CartAndWishlist.repository.CartRepository;
import com.ecommerce.cartAndWishlist.CartAndWishlist.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CartService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CartRepository cartRepository;

    private String ROOT_URI = "http://localhost:8094/";

    public CartEntity addItemToCart(String itemId, String userId, boolean recommendation) {
        String uri = ROOT_URI+"search/getDocument/"+itemId+"/"+userId+"/"+recommendation;

        ResponseEntity<ItemElastic> response = restTemplate.getForEntity(uri,ItemElastic.class);

        ItemElastic itemElastic = response.getBody();

        //Transform ItemElastic to WishListEntity

        CartEntity cartEntity = new CartEntity();
        cartEntity.setUserId(userId);
        cartEntity.setBrand(itemElastic.getBrand());
        cartEntity.setCategory(itemElastic.getCategory());
        cartEntity.setDescription(itemElastic.getDescription());
        cartEntity.setName(itemElastic.getName());
        cartEntity.setPrice(itemElastic.getPrice());

        cartRepository.save(cartEntity);

        return cartEntity;
    }
}
