package com.ecommerce.ddd.SearchService.config;

import com.ecommerce.ddd.SearchService.model.RecommendedItems;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${kafka.groupId2}")
    private String groupId1;

    @Bean
    public ProducerFactory<String, RecommendedItems> producerFactoryRecommendation() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        config.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        config.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate kafkaTemplate1() {
        return new KafkaTemplate<>(producerFactoryRecommendation());
    }

    @Bean
    public ConsumerFactory<String, RecommendedItems> itemConsumerRecommendation()
    {

        // HashMap to store the configurations
        Map<String, Object> map
                = new HashMap<>();

        // put the host IP in the map
        map.put(ConsumerConfig
                        .BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);

        // put the group ID of consumer in the map
        map.put(ConsumerConfig
                        .GROUP_ID_CONFIG,
                groupId1);
        map.put(ConsumerConfig
                        .KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        map.put(ConsumerConfig
                        .VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);

        JsonDeserializer<RecommendedItems> jsonDeserializer = new JsonDeserializer<>(RecommendedItems.class);
        jsonDeserializer.setRemoveTypeHeaders(false);
        jsonDeserializer.addTrustedPackages("*");
        jsonDeserializer.setUseTypeMapperForKey(true);
        // return message in JSON formate
        return new DefaultKafkaConsumerFactory<>(
                map, new StringDeserializer(),
                jsonDeserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,RecommendedItems> itemListenerRecommendation()
    {
        ConcurrentKafkaListenerContainerFactory<String,
                RecommendedItems>
                factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(itemConsumerRecommendation());
        return factory;
    }
}
