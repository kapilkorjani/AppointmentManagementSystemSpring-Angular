package com.appointmentmgmtsyst.dao;

import com.appointmentmgmtsyst.model.User;

public interface UserDao {

	public User getUserByName(String name);
	
	public User getUserById(int id);
	
	public User getUserByEmail(String email);
	
	public int addUser(User user);
	
	public boolean updateUser(User user);
	
	public boolean deleteUser(User user);
	
}
