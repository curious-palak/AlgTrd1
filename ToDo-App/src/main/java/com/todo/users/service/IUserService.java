package com.todo.users.service;

import javax.servlet.http.HttpServletRequest;

import com.todo.users.model.User;

public interface IUserService {

	void registerUser(User user,String url);

	User loginUser(User user);

	public User getUserById(int userId);

	User getUserByEmail(String email);

	boolean forgotPass(String string, HttpServletRequest url);

	void resetPassword(User user);

}
