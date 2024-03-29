package com.visa.VisaServices_SpringBoot.service;

import com.visa.VisaServices_SpringBoot.models.VisaModel;
import com.visa.VisaServices_SpringBoot.repository.VisaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisaService {

    @Autowired
    private VisaRepo visaRepo;


    public List<VisaModel> getAllVisa_(){
        return visaRepo.findAll();
    }

    public VisaModel getVisaById_(Long id){
        return visaRepo.findById(id).orElse(new VisaModel());
    }

    public void saveVisa(VisaModel visaModel){
        visaRepo.save(visaModel);
    }

    public void deleteVisa(Long id){
        visaRepo.deleteById(id);
    }

    public List<VisaModel> searchVisa_(String holderPassportNo, String holderDateOfBirth, String holderNationality){
        return visaRepo.searchVisa(holderPassportNo, holderDateOfBirth, holderNationality);
    }

    public List<VisaModel> searchVisaForApp(String visaNumber, String moiRef, String holderPassportNumber){
        return visaRepo.searchVisaForApp(visaNumber, moiRef, holderPassportNumber);
    }


   // QR Code Services

}
