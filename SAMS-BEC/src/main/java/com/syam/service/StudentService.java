package com.syam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syam.entityes.Achievement;
import com.syam.entityes.Student;
import com.syam.repository.AchievementRepository;
import com.syam.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	private AchievementRepository achievementRepository;

	
	
	public StudentService(StudentRepository studentRepository, AchievementRepository achievementRepository) {
		this.studentRepository = studentRepository;
		this.achievementRepository = achievementRepository;
	}

	public void saveData(Student student, Achievement achievement) {
		studentRepository.save(student);
		achievementRepository.save(achievement);
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
}
