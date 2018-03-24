package com.todo.users.dao;

import com.todo.users.model.User;

public interface IUserDao {

	public void saveUser(User user);

	public User fetchUser(User user);

}
