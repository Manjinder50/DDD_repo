package com.ecommerce.cartAndWishlist.CartAndWishlist.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false, unique = true)
    private String description;
    @Column(nullable = false, unique = true)
    private String brand;
    @Column(nullable = false, unique = true)
    private long price;
    @Column(nullable = false, unique = true)
    private String userId;
}
