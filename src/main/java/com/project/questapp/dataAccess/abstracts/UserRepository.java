package com.project.questapp.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questapp.entities.User;

import lombok.Data;

public interface UserRepository extends JpaRepository<User,Integer> {
	public User findByUserName(String username);
}
