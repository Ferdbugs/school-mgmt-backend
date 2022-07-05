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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.mgmt.exception.ResourceNotFoundException;
import com.school.mgmt.model.Subject;
import com.school.mgmt.repository.SubjectRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class SubjectController {

	@Autowired
	private SubjectRepository subjectRepository;
	

	//get all subjects
	
	@GetMapping("/subject/all")
	public List<Subject> getAllsubjects(){
		return subjectRepository.findAll();
	}
	
	//create subject
	
	@PostMapping("/subject/create")
	public Subject createsubject(@RequestBody Subject subject) {
		return subjectRepository.save(subject);
	}
	
	//get subject by id rest api
	
	@GetMapping("/subject/{id}")
	public ResponseEntity<Subject> getsubjectById(@PathVariable Long id) {
		Subject subject = subjectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("subject not found"));
		
		return ResponseEntity.ok(subject);
	}
	
	//delete subject
	
	@DeleteMapping("/subject/{id}")
	public ResponseEntity<Map<String, Boolean>> deletesubject(@PathVariable Long id){

		Subject subject = subjectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("subject not found"));
		
		subjectRepository.delete(subject);
		
		Map<String,Boolean> response = new HashMap <>();
		response.put("deleted", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
	}
}
