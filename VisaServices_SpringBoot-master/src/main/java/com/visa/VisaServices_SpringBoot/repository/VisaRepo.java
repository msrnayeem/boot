package com.visa.VisaServices_SpringBoot.repository;


import com.visa.VisaServices_SpringBoot.models.VisaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisaRepo extends JpaRepository<VisaModel, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM visa_model WHERE holder_passport_no = ? AND holder_date_of_birth = ? AND holder_nationality = ?")
    public List<VisaModel> searchVisa(String holderPassportNo, String holderDateOfBirth, String holderNationality);

    @Query(nativeQuery = true, value = "SELECT * FROM visa_model WHERE visa_number = ? AND holdermoireference = ? AND holder_passport_no = ?")
    public List<VisaModel> searchVisaForApp(String visaNumber, String moiRef, String holderPassportNo);

}
