package com.school.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.mgmt.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
