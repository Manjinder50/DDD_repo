package com.ecommerce.searchconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

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
