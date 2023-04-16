package com.ecommerce.cartAndWishlist.CartAndWishlist.controller;

import com.ecommerce.cartAndWishlist.CartAndWishlist.entity.CartEntity;
import com.ecommerce.cartAndWishlist.CartAndWishlist.entity.WishListEntity;
import com.ecommerce.cartAndWishlist.CartAndWishlist.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor()
@RequestMapping("/cart/ddd")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addToCart/{itemId}/{userId}/{recommendation}")
    public CartEntity addItemToCart(@PathVariable String itemId, @PathVariable String userId, @PathVariable boolean recommendation){
        return cartService.addItemToCart(itemId,userId,recommendation);
    }
}
