package com.projectspring.api.Services;

import org.springframework.stereotype.Service;

import com.projectspring.api.Dto.QRCodeDto;
import com.projectspring.api.Entities.QRCode;
import com.projectspring.api.Generic.GenericService;
import com.projectspring.api.Generic.GenericServiceImpl;
import com.projectspring.api.Mappers.QRCodeMapper;
import com.projectspring.api.Repositories.QRCodeRepository;

@Service
public class QRCodeService extends GenericServiceImpl<QRCode, Integer, QRCodeDto, QRCodeRepository, QRCodeMapper> implements GenericService<QRCodeDto, Integer> {

    public QRCodeService(QRCodeRepository repository, QRCodeMapper mapper) {
        super(repository, mapper);
    }
    
}
