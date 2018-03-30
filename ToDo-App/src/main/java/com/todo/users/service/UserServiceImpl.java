package com.todo.users.service;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.users.dao.IUserDao;
import com.todo.users.model.User;
import com.todo.utility.SendEmail;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Transactional
	public void registerUser(User user, String url) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));

		String randomUUID = UUID.randomUUID().toString();
		// System.out.println(randomUUID);

		User user1 = userDao.saveUser(user);
		if (user1 != null) {
			String to = user1.getEmail();
			String subject = ("Link to confirm registration..");
			String message = url + "/ConfirmRegistration/" + randomUUID;
			SendEmail.sendEmail(to, subject, message);
		}
	}

	@Override
	public User loginUser(User user) {

		return userDao.fetchUser(user);
	}

	@Override
	public User getUserById(int userId) {
		return userDao.getUserById(userId);
	}

	public User getUserByEmail(String email) {

		return userDao.getUserByEmail(email);

	}

	@Override
	public boolean forgotPass(String email, HttpServletRequest request) {
		User userFetch = userDao.getUserByEmail(email);
		if (userFetch != null) {
			String req = request.getRequestURL().toString();
			String url = req.substring(0, req.lastIndexOf("/")) + "/resetPassword/";
			String mailTo = userFetch.getEmail();
			String subject = "Link to Reset password";
			String message = "Visit the link to reset your password  \n" + url;
			SendEmail.sendEmail(mailTo, subject,message);
			return true;
		}
		return false;
	}

	@Override
	public void resetPassword(User user) {

	}
}