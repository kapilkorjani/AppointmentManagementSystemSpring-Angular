package com.appointmentmgmtsyst.services;

import java.util.Optional;

import com.appointmentmgmtsyst.model.User;

public interface UserService {

	public int addUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(User user);
	public Optional<User> getUserByEmail(String email);
	public Optional<User> getUserById(int id);
	public Optional<User> getUserByName(String name);
	
}
