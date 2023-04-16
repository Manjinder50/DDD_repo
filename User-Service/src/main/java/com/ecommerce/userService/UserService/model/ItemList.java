package com.ecommerce.userService.UserService.model;

import com.ecommerce.userService.UserService.entity.ItemElastic;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Data
public class ItemList {

    private List<ItemElastic> itemElastics;
}
