package com.ecommerce.Inbound.controller;

import com.ecommerce.Inbound.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inbound")
public class AddItemsController {

    @Autowired
    KafkaTemplate<String, Item> kafkaTemplate;

    private static final String TOPIC = "ItemTopicDDD";


    @PostMapping("/publishItems")
    public String publishItems(@RequestBody Item item){

        Message<Item> message = MessageBuilder
                .withPayload(item)
                .setHeader(KafkaHeaders.TOPIC, TOPIC)
                .build();
        //sending the items
        kafkaTemplate.send(message);

        return "Items sent successfully";
    }
}
