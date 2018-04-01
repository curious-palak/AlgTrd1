package com.fundoonotes.userservice;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.fundoonotes.utility.SendEmail;

@Service
public class UserServiceImpl implements IUserService
{

   @Autowired
   private IUserDao userDao;

   @Transactional
   public void registerUser(UserDTO userdto, String url)
   {

      User userModel = new User(userdto);

      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
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
      User user2 = userDao.activateStatus(user);
   }

   @Override
   public UserDTO loginUser(UserDTO userDto)
   {

      User userPass = userDao.getUserByEmail(userDto.getEmail());
      Boolean passwordMatch = BCrypt.checkpw(userDto.getPassword(), userPass.getPassword());

      if (userDto.getEmail() == null && passwordMatch != true) {
         return null;
      }

      return userDto;
   }

   @Override
   public User getUserById(int userId)
   {

      return userDao.getUserById(userId);
   }

   @Override
   public boolean forgotPass(String email, HttpServletRequest request)
   {
      User userFetch = userDao.getUserByEmail(email);
      if (userFetch != null) {
         String req = request.getRequestURL().toString();
         String url = req.substring(0, req.lastIndexOf("/")) + "/resetPassword/";
         String mailTo = userFetch.getEmail();
         String subject = "Link to Reset password";
         String message = "Visit the link to reset your password  \n" + url;
         SendEmail.sendEmail(mailTo, subject, message);
         return true;
      }
      return false;
   }

   @Override
   public void resetPassword(User user)
   {

   }
}