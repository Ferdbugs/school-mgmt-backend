package com.school.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.mgmt.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
	
}
