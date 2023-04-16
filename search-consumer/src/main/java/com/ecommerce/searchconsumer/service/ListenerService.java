package com.ecommerce.searchconsumer.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.ecommerce.searchconsumer.model.Item;
import com.ecommerce.searchconsumer.model.ItemElastic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.UUID;

@Service
public class ListenerService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    private final String indexName = "itemsddd";

    @KafkaListener(topics = "ItemTopicDDD", containerFactory = "itemListener")
    public String consume(Item item) throws IOException {
        System.out.println("Item Name " + item.getName());

        ItemElastic itemElastic = new ItemElastic();
        itemElastic.setName(item.getName());
        itemElastic.setCategory(item.getCategory());
        itemElastic.setBrand(item.getBrand());
        itemElastic.setPrice(item.getPrice());
        itemElastic.setDescription(item.getDescription());

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId((UUID.randomUUID().toString().split("-")[0]))
                .withObject(itemElastic).build();

        if(indexQuery!=null){
            String documentId = elasticsearchOperations
                    .index(indexQuery, IndexCoordinates.of(indexName));
            return new StringBuilder("Document has been successfully created with id."+documentId).toString();
        }
        else {
            return new StringBuilder("Error while performing the operation.").toString();
        }

    }
}