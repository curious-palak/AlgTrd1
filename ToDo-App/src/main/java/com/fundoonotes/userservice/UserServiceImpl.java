package com.fundoonotes.userservice;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
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

   @Transactional
   public void registerUser(UserDTO userdto, String url)
   {

      User userModel = new User(userdto);

      userModel.setPassword(encoder.encode(userModel.getPassword()));

      String randomUUID = UUID.randomUUID().toString();

      userModel.setRandomUUId(randomUUID);

      User user1 = userDao.saveUser(userModel);
      if (user1 != null) {
         String to = user1.getEmail();
         String subject = ("Link to confirm registration..");
         String message = url + "/activateAccount/" + randomUUID;
         SendEmail.sendEmail(to, subject, message);
      }
   }

   @Transactional
   @Override
   public void activateAccount(String randomUUId, HttpServletRequest request)
   {
      User user = userDao.getUserByRandomId(randomUUId);
      user.setStatus(true);
      User userUpdate = userDao.activateStatus(user);
   }

   @Transactional
   @Override
   public UserDTO loginUser(UserDTO userDto)
   {
      User user = new User();
      user.setEmail(userDto.getEmail());
      user.setPassword(userDto.getPassword());

      User userObject = userDao.getUserByEmail(userDto.getEmail());

      if (userObject != null && userObject.isStatus() == true
            && BCrypt.checkpw(userDto.getPassword(), userObject.getPassword()) == true) {

         return userDto;

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

      String randomUUID = userFetch.getRandomUUId();

      if (userFetch != null) {

         String req = request.getRequestURL().toString();
         String url = req.substring(0, req.lastIndexOf("/")) + "/resetPassword/" + randomUUID;
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
   public User getEmailByUUID(String randomUUId)
   {
      User userEmail = userDao.fetchEmailByUUID(randomUUId);
      if (userEmail != null) {
         return userEmail;
      }
      return null;
   }

   @Transactional
   @Override
   public boolean resetPassword(User user, UserDTO userDto)
   {

      String newPassword = userDto.getPassword();
      user.setPassword(newPassword);

      // user.setPassword(encoder.encode(userDto.getPassword()));
      userDao.updatePassword(user);
      return true;

   }
}