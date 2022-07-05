package com.school.mgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.mgmt.model.Student;
import com.school.mgmt.repository.StudentRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	//get all students
	
	@GetMapping("/student/all")
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	@PostMapping("/student/create")
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
}
