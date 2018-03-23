package com.todo.User.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todo.User.model.User;

@RestController
public class LoginController {

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ResponseEntity<String> LoginUser(@RequestBody User user) {

		return new ResponseEntity<String>("Login Successfully..", HttpStatus.OK);
	}
}
