package com.visa.VisaServices_SpringBoot.controllers;

import com.visa.VisaServices_SpringBoot.models.UploadModel;
import com.visa.VisaServices_SpringBoot.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @GetMapping("uploadVisa")
    public List<UploadModel> getAllUploadedFile_(){
        return uploadService.getAllUploadedFile();
    }


    @GetMapping("uploadVisa/{holderPassportNumber}/{holderDateOfBirth}/{holderNationality}")
    public List<UploadModel> serchUploaded(@PathVariable String holderPassportNumber, @PathVariable String holderDateOfBirth, @PathVariable String holderNationality){
        return uploadService.searchUploadedVisa_(holderPassportNumber, holderDateOfBirth, holderNationality);
    }

    @PostMapping("uploadVisa")
    public void addToUploadedFile(@RequestBody UploadModel uploadModel){
        uploadService.saveUploadedFile(uploadModel);
    }

    @PutMapping("uploadVisa")
    public void updateToUploadedFile(@RequestBody UploadModel uploadModel){
        uploadService.saveUploadedFile(uploadModel);
    }

    @DeleteMapping("uploadVisa/{id}")
    public void deleteUploadedFile_(@PathVariable Long id){
        uploadService.deleteUploadedFile(id);
    }

    @GetMapping("uploadVisa/{id}")
    public UploadModel getUploadedFileById_(@PathVariable Long id){
        return uploadService.findUploadedFileById(id);
    }
}
