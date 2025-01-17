package com.syam.entityes;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long achievementId;
    
    private String registrationNo;
    private String achievementType;
    private String achievementDetails;
    private String prize;
    private String event;
    private String academicYear;
    private LocalDate achievementDate;
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

}

