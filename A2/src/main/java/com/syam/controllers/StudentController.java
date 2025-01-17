package com.syam.controllers;

import java.io.IOException; 
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.syam.entity.Student;
import com.syam.services.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/home")
    public String homePage(Model model) {
        return "home"; // Maps to home.html
    }

    @GetMapping("/contact")
    public String contactPage(Model model) {
        return "contact"; // Maps to contact.html
    }

    @GetMapping("/testimonials")
    public String testimonialsPage(Model model) {
        return "testimonials"; // Maps to testimonials.html
    }

    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        convertImagesToBase64(students);
        model.addAttribute("students", students);
        return "students"; // Maps to students.html
    }
    
	/* @GetMapping("/list")
	public String listStudents(Model model) {
	    List<Student> students = studentService.getAllStudents();
	    convertImagesToBase64(students);
	    
	    // Compute counts
	    long firstCount = students.stream().filter(s -> "First".equalsIgnoreCase(s.getPrize())).count();
	    long secondCount = students.stream().filter(s -> "Second".equalsIgnoreCase(s.getPrize())).count();
	    long certificationCount = students.stream().filter(s -> "Certification".equalsIgnoreCase(s.getAchievementType())).count();
	    long academicCount = students.stream().filter(s -> "Academic".equalsIgnoreCase(s.getAchievementType())).count();
	    long sportsCount = students.stream().filter(s -> "Sports".equalsIgnoreCase(s.getAchievementType())).count();
	    long culturalCount = students.stream().filter(s -> "Cultural".equalsIgnoreCase(s.getAchievementType())).count();
	    long otherCount = students.stream().filter(s -> "Other".equalsIgnoreCase(s.getAchievementType())).count();
	    
	    model.addAttribute("students", students);
	    model.addAttribute("firstCount", firstCount);
	    model.addAttribute("secondCount", secondCount);
	    model.addAttribute("certificationCount", certificationCount);
	    model.addAttribute("academicCount", academicCount);
	    model.addAttribute("sportsCount", sportsCount);
	    model.addAttribute("culturalCount", culturalCount);
	    model.addAttribute("otherCount", otherCount);
	    return "students"; // Maps to students.html
	}*/


    @GetMapping("/search")
    public String searchStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String registrationNo,
            @RequestParam(required = false) String academicYear,
            @RequestParam(required = false) String achievementType,
            Model model) {

        List<Student> students = studentService.searchStudents(name, registrationNo, academicYear, achievementType);
        convertImagesToBase64(students);
        model.addAttribute("students", students);
        return "search"; // Maps to search.html
    }

    // GET mapping for displaying the form
    @GetMapping("/saveStudent")
    public String showStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "students"; // Maps to student-form.html
    }

    // POST mapping for saving student data
    @PostMapping("/upload")
    public String uploadStudent(@ModelAttribute Student student, @RequestParam("file") MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                student.setFileName(file.getOriginalFilename());
                student.setFileType(file.getContentType());
                student.setData(file.getBytes());
            } else {
                System.out.println("File is empty or not selected");
            }

            // Save the student details
            studentService.saveStudent(student);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/students?error=upload_failed"; // Redirect to error page if needed
        }

        return "redirect:/students"; // Redirect to the students list page
    }


    // Helper method to convert image data to Base64
    private void convertImagesToBase64(List<Student> students) {
        students.forEach(student -> {
            if (student.getData() != null) {
                String base64Image = Base64.getEncoder().encodeToString(student.getData());
                student.setFileName(base64Image);
            }
        });
    }
}
