package com.ecommerce.ddd.SearchService.repo;

import co.elastic.clients.elasticsearch.core.DeleteRequest;
import com.ecommerce.ddd.SearchService.model.ItemElastic;
import com.ecommerce.ddd.SearchService.model.ItemList;
import com.ecommerce.ddd.SearchService.model.RecommendedItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ElasticSearchQuery {


    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    KafkaTemplate<String, ItemElastic> kafkaTemplate;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    private final String indexName = "itemsddd";

    private static final String TOPIC = "RecommendationTopicDDD";

    public ItemElastic getDocumentById(String itemId,String userId,boolean recommendation) throws IOException {

        ItemElastic itemElastic = null;

        Optional<ItemElastic> itemElasticOptional = itemRepository.findById(itemId);
       if(itemElasticOptional!=null){
           itemElastic = itemElasticOptional.get();
       }
        if(itemElastic!=null && recommendation) {

            RecommendedItems recommendedItems = new RecommendedItems();
            recommendedItems.setUserId(userId);
            recommendedItems.setId(UUID.randomUUID().toString().split("-")[0]);
            recommendedItems.setBrand(itemElastic.getBrand());
            recommendedItems.setCategory(itemElastic.getCategory());
            recommendedItems.setDescription(itemElastic.getDescription());
            recommendedItems.setName(itemElastic.getName());
            recommendedItems.setPrice(itemElastic.getPrice());
            Message<RecommendedItems> message = MessageBuilder
                    .withPayload(recommendedItems)
                    .setHeader(KafkaHeaders.TOPIC, TOPIC)
                    .build();
            //sending the items
            kafkaTemplate.send(message);
        }
        //Put the itemElastic Data in the
        return itemElastic;
    }

    public String deleteDocumentById(String itemId) throws IOException {

        DeleteRequest request = DeleteRequest.of(d -> d.index(indexName).id(itemId));

        String deleteResponse = elasticsearchOperations.delete(itemId,ItemElastic.class);
        if(deleteResponse!=null){
            System.out.println("Item with id "+itemId+ " deleted");
        }else {
            System.out.println("Item not found");
        }
        return new StringBuilder("Item with id " + deleteResponse+" deleted.").toString();

    }

    /*public List<ItemElastic> searchAllDocuments() throws IOException {

        SearchRequest searchRequest =  SearchRequest.of(s -> s.index(indexName));
        SearchResponse searchResponse =  elasticsearchOperations.search(searchRequest, ItemElastic.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<ItemElastic> items = new ArrayList<>();
        for(Hit object : hits){

            System.out.print(((ItemElastic) object.source()));
            items.add((ItemElastic) object.source());

        }
        return items;
    }*/

    public ItemList findByName(String name, String userId) throws IOException {
        List<ItemElastic> itemElastics = itemRepository.findByName(name);


        ItemList itemsList = new ItemList();
        itemsList.setItemElastics(itemElastics);
        if(itemsList!=null && itemsList.getItemElastics().size()>0){
            for(ItemElastic itemElastic:itemsList.getItemElastics()){
                String category = itemElastic.getCategory();
                List<ItemElastic> sameCategory = itemRepository.findByCategory(category);
                for(ItemElastic itemWithCat : sameCategory){
                    RecommendedItems recommendedItems = new RecommendedItems();
                    recommendedItems.setUserId(userId);
                    recommendedItems.setId(UUID.randomUUID().toString().split("-")[0]);
                    recommendedItems.setBrand(itemWithCat.getBrand());
                    recommendedItems.setCategory(itemWithCat.getCategory());
                    recommendedItems.setDescription(itemWithCat.getDescription());
                    recommendedItems.setName(itemWithCat.getName());
                    recommendedItems.setPrice(itemWithCat.getPrice());
                    Message<RecommendedItems> message = MessageBuilder
                            .withPayload(recommendedItems)
                            .setHeader(KafkaHeaders.TOPIC, TOPIC)
                            .build();
                    //sending the items
                    kafkaTemplate.send(message);
                }
            }
        }

        return itemsList;
    }

    public ItemList findByCategory(String category, String userId) {
        List<ItemElastic> itemElastics = itemRepository.findByCategory(category);
        ItemList itemsList = new ItemList();
        itemsList.setItemElastics(itemElastics);

        if(itemsList!=null && itemsList.getItemElastics().size()>0){

            for(ItemElastic itemElastic:itemsList.getItemElastics()){
                RecommendedItems recommendedItems = new RecommendedItems();
                recommendedItems.setUserId(userId);
                recommendedItems.setId(UUID.randomUUID().toString().split("-")[0]);
                recommendedItems.setBrand(itemElastic.getBrand());
                recommendedItems.setCategory(itemElastic.getCategory());
                recommendedItems.setDescription(itemElastic.getDescription());
                recommendedItems.setName(itemElastic.getName());
                recommendedItems.setPrice(itemElastic.getPrice());
                Message<RecommendedItems> message = MessageBuilder
                        .withPayload(recommendedItems)
                        .setHeader(KafkaHeaders.TOPIC, TOPIC)
                        .build();
                //sending the items
                kafkaTemplate.send(message);
            }
        }

        return itemsList;

    }

    public ItemList findByBrand(String brand, String userId) {
        List<ItemElastic> itemElastics = itemRepository.findByBrand(brand);

        ItemList itemsList = new ItemList();
        itemsList.setItemElastics(itemElastics);

        if(itemsList!=null && itemsList.getItemElastics().size()>0) {
            for (ItemElastic itemElastic : itemsList.getItemElastics()) {
                String category = itemElastic.getCategory();
                List<ItemElastic> sameCategory = itemRepository.findByCategory(category);
                for (ItemElastic itemWithCat : sameCategory) {
                    RecommendedItems recommendedItems = new RecommendedItems();
                    recommendedItems.setUserId(userId);
                    recommendedItems.setBrand(itemElastic.getBrand());
                    recommendedItems.setId(UUID.randomUUID().toString().split("-")[0]);
                    recommendedItems.setCategory(itemWithCat.getCategory());
                    recommendedItems.setDescription(itemWithCat.getDescription());
                    recommendedItems.setName(itemWithCat.getName());
                    recommendedItems.setPrice(itemWithCat.getPrice());
                    Message<RecommendedItems> message = MessageBuilder
                            .withPayload(recommendedItems)
                            .setHeader(KafkaHeaders.TOPIC, TOPIC)
                            .build();
                    //sending the items
                    kafkaTemplate.send(message);
                }
            }
        }
        return itemsList;
    }
}
