package com.greenart.ch1;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// 특정 Class가 어떤 클래스/인터페이스를 상속/구현했는지 체크
		return DBUser.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("UserValidator.validate() is called");
		DBUser user = (DBUser)target;
		String id = user.getId();
		
//		System.out.println(user);
		
		// if(id=null || "".equals(id.trim()) errors.rejectValue("id","required");
		// ValidationUtils : validate() 메소드를 편하게 사용하기 위해 만든 클래스
		ValidationUtils.rejectIfEmpty(errors, "id", "required");
		ValidationUtils.rejectIfEmpty(errors, "pwd", "required");
		if(id==null || id.length()<5 || id.length()>12) {
			errors.rejectValue("id", "invalidLength");
		}

	}

}
