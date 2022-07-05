package com.school.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.mgmt.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{

}
