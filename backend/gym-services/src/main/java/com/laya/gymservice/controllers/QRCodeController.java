package com.laya.gymservice.controllers;

import com.google.zxing.WriterException;
import com.laya.gymservice.componets.GenerateQRCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/qrcode")
@RequiredArgsConstructor
public class QRCodeController {

    private final GenerateQRCode generateQRCode;

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQRCode(@RequestParam("memberId") Long memberId,
                                                 @RequestParam("finishedDate") LocalDate finishedDate) throws IOException, WriterException {

        BufferedImage qrImage = generateQRCode.createQRImage(memberId, finishedDate);
        byte[] imageBytes = generateQRCode.getImageInByte(qrImage);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
    }
}
