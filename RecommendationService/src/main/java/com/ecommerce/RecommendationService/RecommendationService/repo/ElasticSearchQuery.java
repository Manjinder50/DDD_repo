package com.ecommerce.RecommendationService.RecommendationService.repo;

import co.elastic.clients.elasticsearch.core.DeleteRequest;

import com.ecommerce.RecommendationService.RecommendationService.model.RecommendedItemList;
import com.ecommerce.RecommendationService.RecommendationService.model.RecommendedItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import org.springframework.stereotype.Repository;

import java.io.IOException;

import java.util.List;
import java.util.UUID;

@Repository
public class ElasticSearchQuery {


    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    private static final String TOPIC = "RecommendationTopic";

    public RecommendedItemList findByBrand(String userId) {
        List<RecommendedItems> recommendedItems = itemRepository.findByUserId(userId);

        RecommendedItemList itemsList = new RecommendedItemList();
        itemsList.setRecommendedItems(recommendedItems);

        return itemsList;
    }
}
