package com.ecommerce.cartAndWishlist.CartAndWishlist.repository;

import com.ecommerce.cartAndWishlist.CartAndWishlist.entity.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends
        JpaRepository<WishListEntity, String> {
}
