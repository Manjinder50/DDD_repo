package com.ecommerce.itemService.repository;

import com.ecommerce.itemService.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ItemsRepository extends MongoRepository<Item,String> {
    List<Item> findByCategory(String category);

    @Query("{brand: ?0 }")
    List<Item> findByBrand(String brand);
}
