package com.ecommerce.itemService.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "items-ddd")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    private String id;
    private String name;
    private String category;
    private String description;
    private String brand;
    private long price;
}
