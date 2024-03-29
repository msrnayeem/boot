package com.visa.VisaServices_SpringBoot.repository;

import com.visa.VisaServices_SpringBoot.models.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
}
