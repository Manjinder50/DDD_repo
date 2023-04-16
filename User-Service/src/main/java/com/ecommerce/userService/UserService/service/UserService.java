package com.ecommerce.userService.UserService.service;

import com.ecommerce.userService.UserService.entity.User;

public interface UserService {

    User createUser(User user);

    User getUserById(Long userId);
}
