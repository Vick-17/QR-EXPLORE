package com.projectspring.api.Controllers;

import com.projectspring.api.Dto.QRCodeDto;
import com.projectspring.api.Generic.GenericController;
import com.projectspring.api.Services.QRCodeService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qrCode")
public class QRCodeController extends GenericController<QRCodeDto, Long, QRCodeService> {

    public QRCodeController(QRCodeService service) {
        super(service);
    }
    
}
