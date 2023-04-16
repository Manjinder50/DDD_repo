package com.ecommerce.userService.UserService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName="items",shards=1,replicas=0, refreshInterval="1s",createIndex = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemElastic {

    @Id
    private String id;
    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Text, name = "category")
    private String category;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Field(type = FieldType.Text, name = "brand")
    private String brand;

    @Field(type = FieldType.Long, name = "price")
    private long price;
}
