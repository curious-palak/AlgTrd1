package com.fundoonotes.userservice;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {

	void registerUser(UserDTO user,String url);

	UserDTO loginUser(UserDTO userDto);

	public User getUserById(int userId);

	//User getUserByEmail(String email);

	boolean forgotPass(String string, HttpServletRequest url);

	void resetPassword(User user);

   void activateAccount(String randomUUId, HttpServletRequest request);
}
