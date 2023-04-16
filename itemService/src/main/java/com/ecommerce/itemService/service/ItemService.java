package com.ecommerce.itemService.service;

import com.ecommerce.itemService.model.Item;
import com.ecommerce.itemService.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {

    @Autowired
    private ItemsRepository itemsRepository;

    //CRUD
    public Item addItem(Item item){
        item.setId(UUID.randomUUID().toString().split("-")[0]);
        return itemsRepository.save(item);
    }

    public List<Item> findAllItems(){
        return itemsRepository.findAll();
    }

    public Item findItemById(String id){
        return itemsRepository.findById(id).get();
    }

    public List<Item> findItemsByCategory(String category){
        return itemsRepository.findByCategory(category);
    }

    public List<Item> findItemsByBrand(String brand){

        return itemsRepository.findByBrand(brand);
    }

    public Item updateItem(Item updatedItem){
        //get the existing document from db

        Item existingItem = itemsRepository.findById(updatedItem.getId()).get();

        existingItem.setBrand(updatedItem.getBrand());
        existingItem.setCategory(updatedItem.getCategory());
        existingItem.setName(updatedItem.getName());
        existingItem.setDescription(updatedItem.getDescription());
        existingItem.setPrice(updatedItem.getPrice());

       return itemsRepository.save(existingItem);
        //populate new value from request to existing object
    }

    public String deleteItem(String itemId){
        Item item = itemsRepository.findById(itemId).get();
        itemsRepository.deleteById(itemId);
        return "item "+item.getName()+" deleted";
    }

    @KafkaListener(topics = "ItemTopicDDD", containerFactory = "itemListener")
    public void consume(Item item) {
        System.out.println("Item Name "+item.getName());
        item.setId(UUID.randomUUID().toString().split("-")[0]);
        itemsRepository.save(item);
    }
}
