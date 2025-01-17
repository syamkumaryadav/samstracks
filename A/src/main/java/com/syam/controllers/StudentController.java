package com.syam.controllers;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/list")
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        convertImagesToBase64(students);
        model.addAttribute("students", students);
        return "students"; // Maps to students.html
    }
    
	

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
    
    @GetMapping("/adminSearch")
    public String adminsearchStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String registrationNo,
            @RequestParam(required = false) String academicYear,
            @RequestParam(required = false) String achievementType,
            Model model) {

        List<Student> students = studentService.searchStudents(name, registrationNo, academicYear, achievementType);
        convertImagesToBase64(students);
        model.addAttribute("students", students);
        return "admin-search"; // Maps to search.html
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

        return "redirect:/students/success"; // Redirect to the students list page
    }
    
    @GetMapping("/success")
    public String successPage(Model model) {
        return "success"; // Maps to home.html
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
    
 // Update student record - GET request to display the form
    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "student-form"; // Maps to student-form.html for editing
    }

    // Update student record - POST request to save updated details
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student, @RequestParam("file") MultipartFile file) {
        try {
            // Fetch the existing student record
            Student existingStudent = studentService.getStudentById(student.getStudentId());

            if (existingStudent == null) {
                return "redirect:/students?error=not_found"; // Handle case when the student does not exist
            }

            // Update fields
            existingStudent.setName(student.getName());
            existingStudent.setBranch(student.getBranch());
            existingStudent.setRegistrationNo(student.getRegistrationNo());
            existingStudent.setAchievementType(student.getAchievementType());
            existingStudent.setAchievementDetails(student.getAchievementDetails());
            existingStudent.setPrize(student.getPrize());
            existingStudent.setEvent(student.getEvent());
            existingStudent.setAcademicYear(student.getAcademicYear());
            existingStudent.setAchievementDate(student.getAchievementDate());

            if (file != null && !file.isEmpty()) {
                existingStudent.setFileName(file.getOriginalFilename());
                existingStudent.setFileType(file.getContentType());
                existingStudent.setData(file.getBytes());
            }

            // Save the updated student
            studentService.saveStudent(existingStudent);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/students?error=update_failed";
        }
        return "redirect:/students/adminSearch";
    }


    // Delete student record
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students/adminSearch";
    }
    
    

}
