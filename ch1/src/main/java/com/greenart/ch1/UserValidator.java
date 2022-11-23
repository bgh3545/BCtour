package com.greenart.ch1;

import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}
	@Override
	public void validate(Object target, org.springframework.validation.Errors errors) {
		System.out.println("UserValidator.validat() is called");
		User user = (User)target;
		String id = user.getId();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");
		if(id==null||id.length()<5||id.length()>12) {
			errors.rejectValue("id", "invalidLength");
		}
	}
}
