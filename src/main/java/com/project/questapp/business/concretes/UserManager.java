package com.project.questapp.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.questapp.business.abstracts.UserService;
import com.project.questapp.dataAccess.abstracts.UserRepository;
import com.project.questapp.entities.User;

@Service
public class UserManager implements UserService{

	private UserRepository userRepository;
	
	@Autowired
	public UserManager(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAll() {
		return this.userRepository.findAll();
	}

	@Override
	public User getUser(int id) {
		return this.userRepository.findById(id).orElse(null);
	}

	@Override
	public User addUser(User user) {
		return this.userRepository.save(user);
	}
	
	@Override
	public User updateUser(int id, User newUser) {
		Optional<User> theUser = this.userRepository.findById(id);
		
		if(theUser.isPresent()) {
			User foundUser = theUser.get();
			
			foundUser.setUsername(newUser.getUsername());
			foundUser.setPassword(newUser.getPassword());
			
			return this.userRepository.save(foundUser);
		}
		return null;
	}

	@Override
	public void deleteUser(int id) {
		this.userRepository.deleteById(id);
	}


	
}
