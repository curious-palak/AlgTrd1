package com.todo.users.service;

import com.todo.users.model.User;

public interface IUserService {

	void registerUser(User user);

	User loginUser(User user);

}
