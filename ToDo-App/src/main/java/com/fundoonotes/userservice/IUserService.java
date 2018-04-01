package com.fundoonotes.userservice;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {

	void registerUser(User user,String url);

	User loginUser(User user);

	public User getUserById(int userId);

	User getUserByEmail(String email);

	boolean forgotPass(String string, HttpServletRequest url);

	void resetPassword(User user);

}
