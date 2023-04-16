package com.ecommerce.RecommendationService.RecommendationService.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class RecommendedItemList {

    private List<RecommendedItems> recommendedItems;
}
