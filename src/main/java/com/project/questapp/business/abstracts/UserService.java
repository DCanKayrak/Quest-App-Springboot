package com.project.questapp.business.abstracts;

import java.util.List;

import com.project.questapp.entities.User;

public interface UserService {
	List<User> getAll();
	User getUser(int id);
	User findByUserName(String username);
	User addUser(User user);
	User updateUser(int id,User newUser);
	void deleteUser(int id);
}
