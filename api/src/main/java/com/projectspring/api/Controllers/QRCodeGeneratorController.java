package com.projectspring.api.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.projectspring.api.services.QRCodeGeneratorServiceImpl;

@RestController
@Slf4j
@RequiredArgsConstructor
public class QRCodeGeneratorController {

    private final QRCodeGeneratorServiceImpl qrCodeGeneratorService;

    @PostMapping("/qrcode/test")
    public String addMessage(@RequestBody String message) {
        log.info("Input Message is {} ", message);
        qrCodeGeneratorService.generateQRCode(message);
        return "Created QR Code";
    }
}
