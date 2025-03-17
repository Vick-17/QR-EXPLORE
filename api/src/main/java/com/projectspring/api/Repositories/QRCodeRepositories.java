package com.projectspring.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.Entities.QRCode;

public interface QRCodeRepositories extends JpaRepository<QRCode, Integer> {
    
}
