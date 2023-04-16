package com.ecommerce.RecommendationService.RecommendationService.repo;

import com.ecommerce.RecommendationService.RecommendationService.model.RecommendedItemList;
import com.ecommerce.RecommendationService.RecommendationService.model.RecommendedItems;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends ElasticsearchRepository<RecommendedItems, String> {

    List<RecommendedItems> findByUserId(String userId);

}
