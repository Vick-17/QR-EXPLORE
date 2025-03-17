package com.projectspring.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.Entities.Role;

public interface RoleRepositories extends JpaRepository<Role, Integer> {
    Role findByName(String name);
    
}
