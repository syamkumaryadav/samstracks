package com.syam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.syam.entity.StudentEntity;
import com.syam.service.MyService;

@Controller
public class MyController {
	
	
	@Autowired
	private MyService myService;
	
	
	@GetMapping("/")
	public String saveStudents(@ModelAttribute("ref") StudentEntity studentEntity ) {
		return "addStudent";
		
	}
	
	
	@PostMapping("/submit")
	public String getStudent(@ModelAttribute("ref") StudentEntity studentEntity, Model model) {
		myService.saveStudent(studentEntity);
		List<StudentEntity> allStudents = myService.getAllStudents();
		model.addAttribute("allStudents",allStudents);
		return "Studentlist";
	}
	
	
	
	

}
