package com.school.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.mgmt.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String name);
}
