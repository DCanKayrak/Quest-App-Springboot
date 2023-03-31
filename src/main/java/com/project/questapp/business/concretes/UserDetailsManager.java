package com.project.questapp.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.questapp.dataAccess.abstracts.UserRepository;
import com.project.questapp.entities.User;
import com.project.questapp.security.JwtUserDetails;

@Service
public class UserDetailsManager implements UserDetailsService{

	private UserRepository theUserRepository;
	
	@Autowired
	public UserDetailsManager(UserRepository theUserRepository) {
		this.theUserRepository = theUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User theUser = this.theUserRepository.findByUserName(username);
		return JwtUserDetails.create(theUser);
	}
	
	public UserDetails loadUserById(int id){
		User theUser = this.theUserRepository.findById(id).get();
		return JwtUserDetails.create(theUser);
	}
	
}
