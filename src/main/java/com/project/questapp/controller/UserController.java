package com.project.questapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.business.abstracts.UserService;
import com.project.questapp.entities.User;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public List<User> getAll(){
		return this.userService.getAll();
	}
	
	@GetMapping("/{userId}")
	public User getUser(@PathVariable int userId) {
		return this.userService.getUser(userId);
	}
	
	@PostMapping
	public User addUser(@RequestBody User user) {
		return this.userService.addUser(user);
	}
	
	@PutMapping("/{userId}")
	public User updateUser(@PathVariable int userId,@RequestBody User newUser) {
		return this.userService.updateUser(userId, newUser);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable int userId) {
		this.userService.deleteUser(userId);
	}
}
