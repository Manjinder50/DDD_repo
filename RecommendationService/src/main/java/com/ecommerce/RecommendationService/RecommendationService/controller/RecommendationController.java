package com.ecommerce.RecommendationService.RecommendationService.controller;

import com.ecommerce.RecommendationService.RecommendationService.model.RecommendedItemList;
import com.ecommerce.RecommendationService.RecommendationService.repo.ElasticSearchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

    @Autowired
    private ElasticSearchQuery elasticSearchQuery;

    @GetMapping("/userId/{userId}")
    public RecommendedItemList getRecommendationForUser(@PathVariable String userId){
        return elasticSearchQuery.findByBrand(userId);
    }
}
