package com.ecommerce.userService.UserService.repository;

import com.ecommerce.userService.UserService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends
        JpaRepository<User, Long> {
}
