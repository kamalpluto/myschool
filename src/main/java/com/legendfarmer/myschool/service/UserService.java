package com.legendfarmer.myschool.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.legendfarmer.myschool.domain.User;
import com.legendfarmer.myschool.domain.security.UserRole;

@Service
public interface UserService {
	
	User createUser(User user, Set<UserRole> userRoles);
}
