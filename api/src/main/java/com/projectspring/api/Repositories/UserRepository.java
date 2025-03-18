package com.projectspring.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByUsername(String username);
}
