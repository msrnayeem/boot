package com.visa.VisaServices_SpringBoot.controllers;


import com.visa.VisaServices_SpringBoot.models.VisaModel;
import com.visa.VisaServices_SpringBoot.service.QRCodeService;
import com.visa.VisaServices_SpringBoot.service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;


@RestController
@RequestMapping("api/v1/")
public class VisaController {

    @Autowired
    private VisaService visaService;

    @Autowired
    private QRCodeService qrCodeService;


    @GetMapping("visa")
    public List<VisaModel> getAllVisa() {
        return visaService.getAllVisa_();
    }

    @GetMapping("visa/{id}")
    public VisaModel getVisaById(@PathVariable Long id){
        return visaService.getVisaById_(id);
    }

    @PostMapping("visa")
    public void saveVisa(@RequestBody VisaModel visaModel) {
        visaService.saveVisa(visaModel);
    }

    @PutMapping("visa")
    public void updateVisa(@RequestBody VisaModel visaModel){
        visaService.saveVisa(visaModel);
    }

    @DeleteMapping("visa/{id}")
    public void deleteVisa(@PathVariable Long id){
        visaService.deleteVisa(id);
    }



    @GetMapping("visa/{holderPassportNumber}/{holderDateOfBirth}/{holderNationality}")
    public List<VisaModel> searchVis(@PathVariable String holderPassportNumber, @PathVariable String holderDateOfBirth, @PathVariable String holderNationality){
        return visaService.searchVisa_(holderPassportNumber, holderDateOfBirth, holderNationality);
    }

    @GetMapping("app-visa/{visaNumber}/{moiRef}/{holderPassportNumber}")
    public List<VisaModel> searchVisaForApp(@PathVariable String visaNumber, @PathVariable String moiRef, @PathVariable String holderPassportNumber){
        return visaService.searchVisaForApp(visaNumber, moiRef, holderPassportNumber);
    }


    @GetMapping("/qrCode/{path}")
    public ResponseEntity<byte[]> getQrCode(@PathVariable String path) {
        try {
            byte[] imageBytes = qrCodeService.generateQRCodeWithLogo("http://localhost:4200/eVisa/verify/" + path, "https://live.staticflickr.com/65535/52974974959_4726f09725_m.jpg");

            // Set the appropriate HTTP headers for the response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            // Return the ResponseEntity with the image byte[] and headers
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/qrCode/{path}")
//    public ResponseEntity<Resource> getQrCode(@PathVariable String path) {
//        try {
//            byte[] imageBytes = qrCodeService.generateQRCodeWithLogo("http://localhost:4200/eVisa/verify/" + path, "https://live.staticflickr.com/65535/52974974959_4726f09725_m.jpg");
//
//            // Convert byte[] to an ArrayBuffer
//            ByteArrayResource resource = new ByteArrayResource(imageBytes);
//
//            // Set the appropriate HTTP headers for the response
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.IMAGE_PNG);
//            headers.setContentLength(imageBytes.length);
//            headers.setContentDispositionFormData("attachment", "image.png");
//
//            // Return the ResponseEntity with the resource and headers
//            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }


//    }


    }
