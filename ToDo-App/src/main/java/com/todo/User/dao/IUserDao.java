package com.todo.User.dao;

import com.todo.User.model.User;

public interface IUserDao {

	public void saveUser(User user);

	public void loginUser(String name, String password);
}
