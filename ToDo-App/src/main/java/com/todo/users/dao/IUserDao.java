package com.todo.users.dao;

import com.todo.users.model.User;

public interface IUserDao {

	public User saveUser(User user);

	public User fetchUser(User user);

	public User getUserById(int userId);
	
	public User getUserByEmail(String email);
}
