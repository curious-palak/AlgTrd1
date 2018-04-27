package com.fundoonotes.userservice;

import javax.servlet.http.HttpServletRequest;

/**
 * Purpose: This is UserService Interface,contains defined methods, This layer
 * interact with controller.
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */
public interface IUserService
{
   String registerUser(UserDTO user,String url);

   String loginUser(UserDTO userDto);

   User getUserById(int userId);

   void activateAccount(String token, HttpServletRequest request);

   User getUserByEmail(User user);

   boolean forgotPass(UserDTO userDto, HttpServletRequest request);

   User getEmailByToken(String token);

   boolean resetPassword(User user, UserDTO userDTO);
}
