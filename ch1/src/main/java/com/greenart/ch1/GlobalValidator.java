package com.greenart.ch1;

import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class GlobalValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}
	@Override
	public void validate(Object target, org.springframework.validation.Errors errors) {
		System.out.println("GlobalValidator.validat() is called");
		User user = (User)target;
		String id = user.getId();
		String pwd = user.getPwd();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");
		if((id!=null&&id.length()<5)||id.length()>12) {
			errors.rejectValue("id", "invalidLength",new String[] {"5","12"},null);
		}
		if((pwd!=null&&pwd.length()<5)||pwd.length()>12) {
			errors.rejectValue("pwd", "invalidLength",new String[] {"5","12"},null);
		}
	}
}
