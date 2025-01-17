package com.syam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syam.entityes.Student;
import com.syam.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;

	
	
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public void saveData(Student student) {
		studentRepository.save(student);
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
}
