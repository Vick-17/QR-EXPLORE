package com.projectspring.api.controllers;

import com.projectspring.api.dtos.QRCodeDto;
import com.projectspring.api.generic.GenericController;
import com.projectspring.api.services.QRCodeService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/QRcodes")
public class QRCodeController extends GenericController<QRCodeDto, QRCodeService> {

    public QRCodeController(QRCodeService service) {
        super(service);
    }
}
