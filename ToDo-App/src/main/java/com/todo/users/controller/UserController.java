package com.todo.users.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todo.users.model.User;
import com.todo.users.service.IUserService;

/**Purpose:
 * @author SANA SHAIKH
 * @since 21Mar 2018
 *
 */
@RestController
public class UserController {

	@Autowired
	IUserService userService;

	/**
	 * This method is to register user
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	
	public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {

		userService.registerUser(user);
		return new ResponseEntity<String>("Successfully registered.", HttpStatus.OK);
	}

	/**
	 * This method is to login existing user
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<?> loginUser(@RequestBody User user) {

		try {
			if (userService.loginUser(user) != null) {

				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
		} catch (Exception e) {

			return new ResponseEntity<String>("User Not Found..", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
