package com.projectspring.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.api.entities.QRCode;

public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
    
}
