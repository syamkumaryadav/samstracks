package com.syam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syam.entity.StudentEntity;
import com.syam.repo.MyRepository;


@Service
public class MyService {
	
	@Autowired
	private MyRepository myRepository;
	
	
	public void saveStudent(StudentEntity studentEntity) {
		myRepository.save(studentEntity);
	}
	
	public List<StudentEntity> getAllStudents(){
		return myRepository.findAll();
		
	}

}
