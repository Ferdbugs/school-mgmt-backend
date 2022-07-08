package com.school.mgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.mgmt.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	List<Student>findAllByGrade(Integer grade);
}
