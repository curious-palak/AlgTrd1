package com.todo.User.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todo.User.model.User;
import com.todo.User.service.IUserService;

@RestController
public class UserController {

	@Autowired
	IUserService userService;

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ResponseEntity<String> registerUser(@RequestBody User user) {

		userService.registerUser(user);
		return new ResponseEntity<String>("Successfully registered.", HttpStatus.OK);
	}

	/*@DeleteMapping(value = "delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId) {

		return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);

	}*/
}
