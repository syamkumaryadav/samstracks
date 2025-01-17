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

    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        
        // Convert image data to Base64
        students.forEach(student -> {
            if (student.getData() != null) {
                String base64Image = Base64.getEncoder().encodeToString(student.getData());
                student.setFileName(base64Image);
            }
        });

        model.addAttribute("students", students);
        return "students";
    }

    @PostMapping("/upload")
    public String uploadStudent(@ModelAttribute Student student, @RequestParam("file") MultipartFile file) {
        try {
            student.setFileName(file.getOriginalFilename());
            student.setFileType(file.getContentType());
            student.setData(file.getBytes());
            studentService.saveStudent(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/students";
    }

    @GetMapping("/search")
    public String searchStudents(@RequestParam String name, Model model) {
        List<Student> students = studentService.searchStudentsByName(name);

        // Convert image data to Base64
        students.forEach(student -> {
            if (student.getData() != null) {
                String base64Image = Base64.getEncoder().encodeToString(student.getData());
                student.setFileName(base64Image);
            }
        });

        model.addAttribute("students", students);
        return "students";
    }
}

