package com.fundoonotes.userservice;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

/**
 * Purpose: This is UserService Interface,contains defined methods, This layer
 * interact with controller.
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */
public interface IUserService
{
   /** Service to register user
    * @param user
    * @param url
    * @return
    */
   String registerUser(UserDTO user,String url);

   /** Service to login user
    * @param userDto
    * @return
    */
   String loginUser(UserDTO userDto);

   /** Service to get user by ID
    * @param userId
    * @return
    */
   User getUserById(int userId);

   /** Service to activate users account
    * @param token
    * @param request
    */
   void activateAccount(String token, HttpServletRequest request);

   /** Service to get user by email ID
    * @param user
    * @return
    */
   User getUserByEmail(User user);

   /** Service to send mail for forgot password
    * @param userDto
    * @param request
    * @return
    */
   boolean forgotPass(UserDTO userDto, HttpServletRequest request);

   /** Service to get email of user by token
    * @param token
    * @return
    */
   User getEmailByToken(String token);

   /** Service to reset password
    * @param user
    * @param userDTO
    * @return
    */
   boolean resetPassword(User user, UserDTO userDTO);

   /** Service to upload user profile Image
    * @param uploadProfileImage
    * @param userId
    * @throws SerialException
    * @throws SQLException
    */
   void uploadImage(MultipartFile uploadProfileImage, int userId) throws SerialException, SQLException;
}
