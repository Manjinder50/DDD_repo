package com.ecommerce.ddd.SearchService.repo;

import com.ecommerce.ddd.SearchService.model.ItemElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends ElasticsearchRepository<ItemElastic, String> {

    Optional<ItemElastic> findById(String id);
    List<ItemElastic> findByName(String name);


    List<ItemElastic> findByCategory(String category);


    List<ItemElastic> findByBrand(String brand);
}
