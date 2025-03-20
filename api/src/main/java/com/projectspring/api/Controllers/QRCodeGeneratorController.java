package com.projectspring.api.Controllers;

import com.projectspring.api.Services.QRCodeGeneratorServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
