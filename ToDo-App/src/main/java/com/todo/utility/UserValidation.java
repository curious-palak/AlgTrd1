package com.todo.utility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.todo.users.model.User;

@Component
public class UserValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "name", "user.name.empty");
		
	}
}
