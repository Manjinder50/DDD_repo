package com.ecommerce.cartAndWishlist.CartAndWishlist.controller;

import com.ecommerce.cartAndWishlist.CartAndWishlist.entity.WishListEntity;
import com.ecommerce.cartAndWishlist.CartAndWishlist.service.WishListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor()
@RequestMapping("/wishlist/ddd")
public class WishlistController {

    @Autowired
    private WishListService wishListService;

    @PostMapping("/addWishList/{itemId}/{userId}/{recommendation}")
    public WishListEntity addItemToWishList(@PathVariable String itemId, @PathVariable String userId, @PathVariable boolean recommendation){
        return wishListService.addItemAsWishList(itemId,userId,recommendation);
    }
}
