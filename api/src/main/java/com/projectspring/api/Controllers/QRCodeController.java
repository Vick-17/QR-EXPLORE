package com.projectspring.api.controllers;

import com.projectspring.api.dtos.QRCodeDto;
import com.projectspring.api.generic.GenericController;
import com.projectspring.api.services.QRCodeService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/qrcodes")
public class QRCodeController extends GenericController<QRCodeDto, QRCodeService> {

    public QRCodeController(QRCodeService service) {
        super(service);
    }

    @PostMapping("generate/test")
    public String addMessage(@RequestBody String message) {
        log.info("Input Message is {} ", message);
        service.generateQRCode(message);
        return "Created QR Code";
    }

}
