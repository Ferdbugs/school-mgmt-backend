package com.school.mgmt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.mgmt.exception.ResourceNotFoundException;
import com.school.mgmt.model.Student;
import com.school.mgmt.model.Subject;
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
	
	//create student
	
	@PostMapping("/student/create")
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	//get student by id rest api
	
	@GetMapping("/student/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found"));
		
		return ResponseEntity.ok(student);
	}
	
	@PutMapping("/student/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id,@RequestBody Student studentDetails){
		
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found"));
		
		student.setFirstName(studentDetails.getFirstName());
		student.setLastName(studentDetails.getLastName());
		student.setEmail(studentDetails.getEmail());
		student.setGrade(studentDetails.getGrade());
		student.setType(studentDetails.getType());
		
		Student updatedStudent = studentRepository.save(student);
		
		return ResponseEntity.ok(updatedStudent);
	}
	
	
	//delete student
	
	@DeleteMapping("/student/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Long id){

		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found"));
		
		studentRepository.delete(student);
		
		Map<String,Boolean> response = new HashMap <>();
		response.put("deleted", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/studentbygrade/{grade}")
	public List<Student> getsubjectById(@PathVariable Integer grade) {
		return studentRepository.findAllByGrade(grade);
	}
	
}
