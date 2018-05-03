package com.fundoonotes.userservice;

import java.io.IOException;
import java.util.logging.Logger;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fundoonotes.exception.CustomResponse;
import com.fundoonotes.utility.JwtTokenUtility;
import com.fundoonotes.utility.SendEmail;

/**
 * Purpose: This class contains implementation of userService interface and
 * contains business logic.
 * 
 * @author SANA SHAIKH
 * @since 21Mar 2018
 */
@Service
public class UserServiceImpl implements IUserService
{

   @Autowired
   private IUserDao userDao;

   BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

   CustomResponse response = new CustomResponse();

   private static Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

   @Transactional
   public String registerUser(UserDTO userdto, String url)
   {
      User userModel = new User(userdto);

      /*
       * if (userModel.getEmail() == userdto.getEmail()) { throw new
       * EmailAlreadyExistsException(); }
       */

      userModel.setPassword(encoder.encode(userModel.getPassword()));

      User userDb = userDao.saveUser(userModel);
      if (userDb != null) {
         int id = userDb.getUserId();
         String token = JwtTokenUtility.generateToken(id);
         logger.info("Token generated->>" + token);

         String to = userDb.getEmail();
         String subject = ("Link to confirm registration..");
         String message = url + "/activateaccount/" + token;
         SendEmail.sendEmail(to, subject, message);
      }
      return null;
   }

   @Transactional
   @Override
   public void activateAccount(String token, HttpServletRequest request)
   {
      logger.info("Get token to activate account->>" + token);
      int id = JwtTokenUtility.verifyToken(token);

      User user = userDao.getUserById(id);
      user.setStatus(true);
      User userUpdate = userDao.activateStatus(user);
   }

   @Transactional
   @Override
   public String loginUser(UserDTO userDto)
   {
      logger.info("In login user Service..");
      User user = new User();
      user.setEmail(userDto.getEmail());
      user.setPassword(userDto.getPassword());

      User userObject = userDao.getUserByEmail(userDto.getEmail());

      if (userObject != null && userObject.isStatus() == true
            && BCrypt.checkpw(user.getPassword(), userObject.getPassword())) {
         int id = userObject.getUserId();
         String token = JwtTokenUtility.generateToken(id);

         logger.info("Token generated->>" + token);
         return token;
      }
      return null;
   }

   @Transactional
   @Override
   public User getUserByEmail(User user)
   {
      User userFetch = userDao.getUserByEmail(user.getEmail());
      return user;
   }

   @Transactional
   @Override
   public User getUserById(int userId)
   {

      return userDao.getUserById(userId);
   }

   @Transactional
   @Override
   public boolean forgotPass(UserDTO userDto, HttpServletRequest request)
   {
      User userFetch = userDao.getUserByEmail(userDto.getEmail());

      int userId = userFetch.getUserId();
      logger.info("userID->>" + userId);

      String token = JwtTokenUtility.generateToken(userId);
      logger.info("token->>" + token);

      if (userFetch != null) {

         String req = request.getRequestURL().toString();
         String url = req.substring(0, req.lastIndexOf("/")) + "/resetpassword/" + token;
         String mailTo = userFetch.getEmail();
         String subject = "Link to Reset password";
         String message = "Visit the link to reset your password  \n" + url;

         SendEmail.sendEmail(mailTo, subject, message);
         return true;
      }
      return false;
   }

   @Transactional
   @Override
   public User getEmailByToken(String token)
   {
      int id = JwtTokenUtility.verifyToken(token);
      logger.info("Getting user Id by token->>" + id);

      User user = userDao.getUserById(id);

      if (user != null) {
         return user;
      }
      return null;
   }

   @Transactional
   @Override
   public boolean resetPassword(User user, UserDTO userDto)
   {

      String newPassword = userDto.getPassword();
      user.setPassword(newPassword);

      // user.setPassword(encoder.encode(userDto.getPassword())); --for reset
      // password encryption
      userDao.updatePassword(user);
      return true;
   }

   @Transactional
   @Override
   public void uploadImage(MultipartFile uploadProfileImage, int userId)
   {
      User user = userDao.getUserById(userId);
      user.setUserId(userId);
      System.out.println("User->>" + user);

      try {
         if (uploadProfileImage.getBytes() != null) {
            user.setUserProfile(uploadProfileImage.getBytes());
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
      userDao.updatePassword(user);
   }
}