package com.school.mgmt.service;

import java.util.List;

import com.school.mgmt.domain.Role;
import com.school.mgmt.domain.User;

public interface UserService {
	User saveUser(User user);
	Role saveRole(Role role);
	void addRoleToUser(String username, String roleName);
	User getUser(String username);
	List<User>getUsers();
}
