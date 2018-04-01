package com.fundoonotes.userservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fundoonotes.utility.RegistrationValidation;

/**
 * Purpose:
 * @author SANA SHAIKH
 * @since 21Mar 2018
 *
 */
@RestController
public class UserController {

	@Autowired
	IUserService userService;

	@Autowired
	RegistrationValidation registrationValidation;

	/**
	 * This method is to register user
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)

	public ResponseEntity<String> registerUser(@RequestBody User user, BindingResult bindingResult,
			HttpServletRequest request) {
		registrationValidation.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {

			return new ResponseEntity<String>("Invalid user details.", HttpStatus.CONFLICT);
		}
		String url=request.getRequestURL().toString().substring(0,request.getRequestURL().lastIndexOf("/"));
		userService.registerUser(user, url);
		
		return new ResponseEntity<String>("Successfully registered.", HttpStatus.OK);
	}

	/**
	 * This method is to login existing user
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<?> loginUser(@RequestBody User user, HttpServletRequest request) {

		String email = user.getEmail();
		String password = user.getPassword();
		System.out.println("mail->" + email);
		System.out.println("pass->" + password);
		User us=userService.getUserByEmail(email);
		System.out.println("Details.."+us);
		System.out.println("password match" + BCrypt.checkpw(password, user.getPassword()));
		
		try {
			User user2 = userService.loginUser(user);
			if (user2 != null) {
				HttpSession session = request.getSession();
				session.setAttribute("userId", user2);
				return new ResponseEntity<>(user, HttpStatus.OK);
			}
		} catch (Exception e) {

			return new ResponseEntity<String>("User Not Found..", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * This method is to get user by Id
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "getUser/{userId}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("userId") int userId) {

		System.out.println(userId);
		User user = userService.getUserById(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/**
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "forgetPassword", method = RequestMethod.POST)
	public ResponseEntity<?> forgotPassword(@RequestBody User user, HttpServletRequest request) {
		String user1=user.getEmail();
		String url=request.getRequestURL().toString().substring(0,request.getRequestURL().lastIndexOf("/"));
		try {
			if (userService.forgotPass(user.getEmail(), request)) {
				return new ResponseEntity<>(HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="resetPassword",method=RequestMethod.POST)
	public ResponseEntity<String> resetPassword(@RequestBody User user,HttpServletRequest request){
	
		userService.resetPassword(user);
		return new ResponseEntity<>("Password reset.." ,HttpStatus.OK);
	}
}
