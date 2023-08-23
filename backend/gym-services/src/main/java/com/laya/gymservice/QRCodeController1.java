package com.laya.gymservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.WriterException;
import com.laya.gymservice.componets.GenerateQRCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("qrcode2")
public class QRCodeController1 {

    @Autowired
    private GenerateQRCode generateQRCode;

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getQRCodeImage() throws IOException, WriterException {
        // Obtener la imagen del código QR desde el sistema de archivos o la ubicación donde se almacena
//        Path qrCodePath = Paths.get("ruta/donde/se/almacena/el/codigo/qr/" + "434" + ".png");
//        byte[] qrCodeBytes = Files.readAllBytes(qrCodePath);
//        String qrCodeText = "https://example.com";
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setName("se");
        contactInfo.setEmail("sfdf");
        contactInfo.setPhoneNumber("234234");
        String vCardText = convertToJson(contactInfo);
        int size = 300;
//        BufferedImage qrImage = generateQRCode.createQRImage(vCardText, size);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(qrImage, "png", baos);

        byte[] imageBytes = baos.toByteArray();

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
    }

    public String convertToVCard(ContactInfo contactInfo) {
        String vCard = "BEGIN:VCARD\n"
                + "VERSION:2.1\n"
                + "FN:" + contactInfo.getName() + "\n"
                + "EMAIL:" + contactInfo.getEmail() + "\n"
                + "TEL:" + contactInfo.getPhoneNumber() + "\n"
                + "END:VCARD";
        return vCard;
    }

    public String convertToJson(ContactInfo contactInfo) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(contactInfo);
    }



}
