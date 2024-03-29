package com.visa.VisaServices_SpringBoot.repository;

import com.visa.VisaServices_SpringBoot.models.UploadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadRepo extends JpaRepository<UploadModel, Long> {

  @Query(nativeQuery = true, value = "SELECT * FROM upload_model WHERE holder_passport_no = ? AND holder_date_of_birth = ? AND holder_nationality = ?")
    public List<UploadModel> searchUploadedVisa(String holderPassportNo, String holderDateOfBirth, String holderNationality);

}
