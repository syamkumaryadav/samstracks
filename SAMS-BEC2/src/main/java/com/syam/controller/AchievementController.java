package com.syam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.syam.entityes.Student;
import com.syam.service.StudentService;

@Controller
public class AchievementController {
	@Autowired
	private final StudentService studentService;

	public AchievementController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/")
	public String saveStudent(@ModelAttribute("ref") Student student) {
		return "addStudent";

	}

	@PostMapping("/submit")
	public String saveStudent(@ModelAttribute("ref") Student student, Model model) {
		studentService.saveData(student);
		return "success";
	}

	@GetMapping("/getStudents")
	public String getStudents( Model model) {
		List<Student> allStudents = studentService.getAllStudents();
		model.addAttribute("allStudents", allStudents);
		return "Studentlist";
	}

}
