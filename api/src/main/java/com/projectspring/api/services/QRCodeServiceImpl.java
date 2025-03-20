package com.projectspring.api.services;

import com.projectspring.api.dtos.QRCodeDto;
import org.springframework.stereotype.Service;

import com.projectspring.api.entities.QRCode;
import com.projectspring.api.generic.GenericServiceImpl;
import com.projectspring.api.mappers.QRCodeMapper;
import com.projectspring.api.repositories.QRCodeRepository;

@Service
public class QRCodeServiceImpl
        extends GenericServiceImpl<
        QRCode,
        QRCodeDto,
        QRCodeRepository,
        QRCodeMapper> implements QRCodeService {

    public QRCodeServiceImpl(QRCodeRepository repository, QRCodeMapper mapper) {
        super(repository, mapper);
    }
    
}
