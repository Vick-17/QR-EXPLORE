package com.projectspring.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.Entities.User;

public interface UserRepositories extends JpaRepository<User, Integer> {
    
    User findByUsername(String username);
}
