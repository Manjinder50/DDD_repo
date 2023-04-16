package com.ecommerce.ddd.SearchService.service;

import com.ecommerce.ddd.SearchService.model.Item;
import com.ecommerce.ddd.SearchService.model.ItemElastic;
import com.ecommerce.ddd.SearchService.model.RecommendedItems;
import com.ecommerce.ddd.SearchService.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class ListenerService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    @Autowired
    private ItemRepository itemRepository;

    private final String indexName = "itemsddd";

    private final String indexNameRecommendation = "recommendationddd";


    @KafkaListener(topics = "RecommendationTopicDDD", containerFactory = "itemListenerRecommendation")
    public String consumeRecommendedItems(RecommendedItems item) throws IOException {
        System.out.println("Item Name " + item.getName());

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(item.getId())
                .withObject(item).build();

        if(indexQuery!=null){
            String documentId = elasticsearchOperations
                    .index(indexQuery, IndexCoordinates.of(indexNameRecommendation));
            return new StringBuilder("Document has been successfully created with id."+documentId).toString();
        }
        else {
            return new StringBuilder("Error while performing the operation.").toString();
        }

    }
}