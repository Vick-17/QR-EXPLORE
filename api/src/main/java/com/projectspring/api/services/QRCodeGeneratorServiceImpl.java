package com.projectspring.api.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class QRCodeGeneratorServiceImpl {

    @Value("${qrcode.output.directory}")
    private String outputLocation;

    private static final String charset = "UTF-8";

    private static final String strDateFormat = "yyyyMMddhhmmss";

    public void generateQRCode(String message) {
        log.info("### Generating QRCode ###");

        log.info("Output directory - {}", outputLocation);
        try {
            String finalMessage = (StringUtils.isNotBlank(message)) ? message : "";
            log.info("Final Input Message - {}", finalMessage);
            processQRCode(finalMessage, prepareOutputFileName(), charset, 400, 400);

        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String prepareOutputFileName() {
        Date date = new Date();

        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(outputLocation).append("\\")
                .append("QRCode-").append(formattedDate).append(".png");
        log.info("Final output file - " + stringBuilder.toString());
        return stringBuilder.toString();
    }

    private void processQRCode(String data, String filePath, String charset, int height, int width) throws WriterException, IOException {
        // La BitMatrix représente la matrice 2D de bits.
        // MultiFormatWriter est une factory class qui trouve la sous-classe Writer appropriée
        // pour le BarcodeFormat renseigné et encode le code-barres avec le contenu fourni.
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, width, height);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), path);
    }
}
