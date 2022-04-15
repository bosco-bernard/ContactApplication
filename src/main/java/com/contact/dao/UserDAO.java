package com.contact.dao;

import java.util.List;

import com.contact.model.User;


public interface UserDAO {
	
	public int save(User user);
	
	public int update(User user);
	
	public User getUserById(int id);
	
	public int delete(int id);
	
	public List<User> getUsers();

}
