package com.appointmentmgmtsyst.services;

import java.util.Optional;

import com.appointmentmgmtsyst.dao.UserDaoImpl;
import com.appointmentmgmtsyst.model.User;
import com.appointmentmgmtsyst.util.passwordManager;

public class UserServiceImpl implements UserService{

	UserDaoImpl userDao = new UserDaoImpl();
	
	@Override
	public int addUser(User user){
		String key = passwordManager.getNewKey();
		user.setSalt(key);
		String encryptedPassword = (passwordManager.getEncryptedPassword(user.getPassword(), key));
		user.setPassword(encryptedPassword);
		return userDao.addUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public boolean deleteUser(User user) {
		return userDao.deleteUser(user);
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		return Optional.ofNullable(userDao.getUserByEmail(email));
	}

	@Override
	public Optional<User> getUserById(int id) {
		return Optional.ofNullable(userDao.getUserById(id));
	}

	@Override
	public Optional<User> getUserByName(String name) {
		return Optional.ofNullable(userDao.getUserByName(name));
	}
	
	public boolean loginUser(User user){
		System.out.println(user.toString());
		User u = userDao.getUserByEmail(user.getEmail());
		System.out.println(u.toString());
		if(passwordManager.verifyUserPassword(user.getPassword(), u.getPassword(), u.getSalt()))
			return true;
		else
			return false;
	}

}
