package com.fundoonotes.utility;

import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.fundoonotes.userservice.User;

@Component
public class RegistrationValidation implements Validator {

	private static final Pattern NAME_REGEX = Pattern.compile("^[a-zA-z]{3,}");

	private static final Pattern EMAIL_REGEX = Pattern.compile("^[\\w\\d._-]+@[\\w\\d.-]+\\.[\\w\\d]{2,6}$");

	private static final Pattern MOBILE_REGEX = Pattern.compile("[0-9]{10}");

	private static final Pattern PASSWORD_REGEX = Pattern
			.compile("[a-z0-9]{3,}");

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz == User.class;
	}

	@Override
	public void validate(Object target, Errors errors) {

		User user = (User) target;
		if (user.getName()!=null && !NAME_REGEX.matcher(user.getName()).matches()) {
			errors.rejectValue("name", "User Name cannot be empty.. ");
		}

		if (user.getEmail() != null && !EMAIL_REGEX.matcher(user.getEmail()).matches()) {
			errors.rejectValue("email", "Email is invalid..");
		}

		if (user.getPassword() != null && !PASSWORD_REGEX.matcher(user.getPassword()).matches()) {
			errors.reject("password",
					"User Password cannot be empty contains letter and atleast 3digits..");
		}

		if (user.getMobileNo() != null && !MOBILE_REGEX.matcher(user.getMobileNo()).matches()) {
			errors.reject("mobileNo", "Mobile number contains 10digits..");
		}

		ValidationUtils.rejectIfEmpty(errors, "name", "User Name cannot be empty.");
		ValidationUtils.rejectIfEmpty(errors, "email", "Email is invalid..");
		ValidationUtils.rejectIfEmpty(errors, "password","User Password cannot be empty ");
		ValidationUtils.rejectIfEmpty(errors, "mobileNo", "Mobile number is invalid..");
	}
}
