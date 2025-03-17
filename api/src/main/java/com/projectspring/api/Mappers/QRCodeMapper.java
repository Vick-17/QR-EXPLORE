package com.projectspring.api.Mappers;

import org.mapstruct.Mapper;

import com.projectspring.api.Dto.QRCodeDto;
import com.projectspring.api.Entities.QRCode;
import com.projectspring.api.Generic.GenericMapper;

@Mapper
public interface QRCodeMapper extends GenericMapper<QRCode, QRCodeDto> {
    
}
