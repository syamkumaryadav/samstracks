package com.syam.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "stt")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    
    @Column(length = 30)
    @NotEmpty(message = "Uname is required") 
    @Size(min = 3, max = 8, message = "Uname should be 3 to 8 characters") 
    private String name;
    
    @Column(length = 5)
    private String branch;
    
    @Column(length = 9)
    private String registrationNo;
    
    @Column(length = 8)
    private String achievementType;
    
    @Column(length = 25)
    private String achievementDetails;
    
    @Column(length = 15)
    private String prize;
    
    @Column(length = 30)
    private String event;
    
    @Column(length = 7)
    private String academicYear;
    
    private LocalDate achievementDate;
    
    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;
   

} 