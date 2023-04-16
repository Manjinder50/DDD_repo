package com.ecommerce.userService.UserService.controller;

import com.ecommerce.userService.UserService.client.SearchService;
import com.ecommerce.userService.UserService.entity.User;
import com.ecommerce.userService.UserService.model.RecommendedItemList;
import com.ecommerce.userService.UserService.model.RecommendedItems;
import com.ecommerce.userService.UserService.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users/ddd")
public class UserController {

    private UserService userService;

    @Autowired
    private SearchService searchService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // build get user by id REST API
    // http://localhost:8080/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId){
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/recommendation/{userId}")
    public List<RecommendedItems> getRecommendations(@PathVariable String userId) {
        return searchService.getRecommendationForUser(userId);
    }
}
