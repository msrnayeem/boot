package com.visa.VisaServices_SpringBoot.service;



import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QRCodeService {

    private final ResourceLoader resourceLoader;

    @Autowired
    public QRCodeService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public byte[] generateQRCodeWithLogo(String qrData, String logoUrl) throws IOException, WriterException {
        int qrCodeSize = 102;
        int logoSize = 12;

        // Generate QR code
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrData, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize);

        // Create buffered image for the QR code
        BufferedImage qrCodeImage = new BufferedImage(qrCodeSize, qrCodeSize, BufferedImage.TYPE_INT_RGB);
        qrCodeImage.createGraphics();


// Set QR code colors
        Color qrCodeColor = new Color(46, 83, 150); // Custom color (RGB values: 46, 83, 150)
        Color qrCodeBackgroundColor = new Color(102, 119, 151, 216); // Custom background color (RGBA values: 102, 119, 151, 216)
        Color qrCodeOtherColor = new Color(102, 119, 151, 216); // Custom color for other pixels (RGBA values: 102, 119, 151, 216)

// Render the QR code
        for (int x = 0; x < qrCodeSize; x++) {
            for (int y = 0; y < qrCodeSize; y++) {
                if ((x >= 3 && x < 6) && (y >= 3 && y < 6)) {
                    qrCodeImage.setRGB(x, y, qrCodeColor.getRGB()); // Set custom color for the big dots
                } else {
                    int pixelColor = bitMatrix.get(x, y) ? qrCodeOtherColor.getRGB() : qrCodeBackgroundColor.getRGB();
                    qrCodeImage.setRGB(x, y, pixelColor);
                }
            }
        }



        // Load the logo image from the provided URL
        BufferedImage logoImage = ImageIO.read(new URL(logoUrl));

        // Resize the logo image
        BufferedImage resizedLogo = new BufferedImage(logoSize, logoSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedLogo.createGraphics();
        g2d.drawImage(logoImage, 0, 0, logoSize, logoSize, null);
        g2d.dispose();

        // Calculate logo position
        int logoX = (qrCodeSize - logoSize) / 2;
        int logoY = (qrCodeSize - logoSize) / 2;

        // Overlay the logo on the QR code
        Graphics2D graphics = qrCodeImage.createGraphics();
        graphics.drawImage(resizedLogo, logoX, logoY, null);
        graphics.dispose();

        // Convert the combined image to a byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrCodeImage, "png", baos);
        return baos.toByteArray();
    }
}
