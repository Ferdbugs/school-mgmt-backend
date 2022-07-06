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
import com.school.mgmt.model.Teacher;
import com.school.mgmt.repository.TeacherRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class TeacherController {

	@Autowired
	private TeacherRepository teacherRepository;

	
	//get all teachers
	
	@GetMapping("/teacher/all")
	public List<Teacher> getAllteachers(){
		return teacherRepository.findAll();
	}
	
	//create teacher
	
	@PostMapping("/teacher/create")
	public Teacher createteacher(@RequestBody Teacher teacher) {
		return teacherRepository.save(teacher);
	}
	
	//get teacher by id rest api
	
	@GetMapping("/teacher/{id}")
	public ResponseEntity<Teacher> getteacherById(@PathVariable Long id) {
		Teacher teacher = teacherRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("teacher not found"));
		
		return ResponseEntity.ok(teacher);
	}
	
	@PutMapping("/teacher/{id}")
	public ResponseEntity<Teacher> updateteacher(@PathVariable Long id,@RequestBody Teacher teacherDetails){
		
		Teacher teacher = teacherRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("teacher not found"));
		
		teacher.setFirstName(teacherDetails.getFirstName());
		teacher.setLastName(teacherDetails.getLastName());
		teacher.setEmail(teacherDetails.getEmail());
		teacher.setSubjects(teacherDetails.getSubjects());
		
		Teacher updatedteacher = teacherRepository.save(teacher);
		
		return ResponseEntity.ok(updatedteacher);
	}
	
	
	//delete teacher
	
	@DeleteMapping("/teacher/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteteacher(@PathVariable Long id){

		Teacher teacher = teacherRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("teacher not found"));
		
		teacherRepository.delete(teacher);
		
		Map<String,Boolean> response = new HashMap <>();
		response.put("deleted", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
	}
}
