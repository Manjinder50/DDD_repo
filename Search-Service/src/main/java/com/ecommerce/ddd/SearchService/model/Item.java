package com.ecommerce.ddd.SearchService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private String name;
    private String category;
    private String description;
    private String brand;
    private long price;
}
