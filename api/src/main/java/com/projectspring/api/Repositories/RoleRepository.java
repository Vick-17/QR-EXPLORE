package com.projectspring.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.Entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
    
}
