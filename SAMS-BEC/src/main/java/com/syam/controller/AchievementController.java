package com.syam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.syam.entityes.Achievement;
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
	public String saveStudent(@ModelAttribute("ref") Student student, @ModelAttribute("ref") Achievement achievement) {
		return "addStudent";

	}

	@PostMapping("/submit")
	public String getStudent(@ModelAttribute("ref") Student student, @ModelAttribute("ref") Achievement achievement,
			Model model) {
		studentService.saveData(student, achievement);
		List<Student> allStudents = studentService.getAllStudents();
		model.addAttribute("allStudents", allStudents);
		return "Studentlist";
	}

	/* @PostMapping("/save")
	public ResponseEntity<String> saveData(@RequestBody SaveDTO saveRequestDTO) {
		Student student = new Student();
		student.setName(saveRequestDTO.getName());
		student.setBranch(saveRequestDTO.getBranch());
		student.setRegNo(saveRequestDTO.getRegNo());
	
	   Achievement achievement = new Achievement();
	   achievement.setRegistrationNo(saveRequestDTO.getRegistrationNo());
	   achievement.setAchievementType(saveRequestDTO.getAchievementType());
	   achievement.setAchievementDetails(saveRequestDTO.getAchievementDetails());
	   achievement.setPrize(saveRequestDTO.getPrize());
	   achievement.setEvent(saveRequestDTO.getEvent());
	   achievement.setAcademicYear(saveRequestDTO.getAcademicYear());
	   achievement.setAchievementDate(saveRequestDTO.getAchievementDate());
	   achievement.setImagePath(saveRequestDTO.getImagePath());
	
	   studentService.saveData(student, achievement);
	
	    return ResponseEntity.ok("Data saved successfully!");
	}*/

}
