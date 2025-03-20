package com.projectspring.api.mappers;

import com.projectspring.api.dtos.QRCodeDto;
import com.projectspring.api.entities.QRCode;
import com.projectspring.api.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface QRCodeMapper extends GenericMapper<QRCodeDto, QRCode> {

}
