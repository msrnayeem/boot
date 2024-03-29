package com.visa.VisaServices_SpringBoot.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VisaModel {
    @Id
    @GeneratedValue
    private Long id;


    private int visaNumber;
    private String visaTypeInArabic;
    private String visaType;
    private String visaPurposeInArabic;
    private String visaPurpose;
    private LocalDate dateOfIssue;
    private LocalDate dateOfExpiry;
    private String placeOfIssue;

    private String holderFullNameInArabic;
    private String holderFullName;
    private String holderMOIReference;
    private String holderNationality;
    private LocalDate holderDateOfIssue;
    private String holderGender;
    private String holderOccupation;
    private String holderOccupationInArabic;
    private LocalDate holderDateOfBirth;
    private String holderPassportNo;
    private String holderPlaceOfIssue;
    private String holderPassportType;
    private LocalDate holderExpiryDate;

    private String employerFullName;
    private String employerFullNameinArabic;
    private String employerMOIReference;
    private String employerMobileNumber;

    private boolean active;

    @CreationTimestamp
    private LocalDateTime createdDateTime;

    private String message;


}
