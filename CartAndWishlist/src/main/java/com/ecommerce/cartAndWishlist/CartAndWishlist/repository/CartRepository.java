package com.ecommerce.cartAndWishlist.CartAndWishlist.repository;

import com.ecommerce.cartAndWishlist.CartAndWishlist.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends
        JpaRepository<CartEntity, String> {
}
