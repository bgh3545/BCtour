package com.greenart.ch1;

import java.net.URLEncoder;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController_v1 {
	
	@InitBinder
	public void toDate(WebDataBinder binder) {
		//SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		//binder.registerCustomEditor(Date.class, new CustomDateEditor(df,false));
		binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#"));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
		//binder.addValidators(new UserValidator());
		List<Validator>validatorList = binder.getValidators();
		System.out.println(validatorList);
	}
	
	@RequestMapping(value="/register/add2",method= {RequestMethod.GET})
	public String register() {
		return "registerForm2";
	}
	
	@PostMapping("/register/add2")
	public String save(Model m, @Valid User user, BindingResult result) throws Exception{
		m.addAttribute("cnt", result.getErrorCount());
		System.out.println( "cnt="+result.getErrorCount());
		String msg = URLEncoder.encode("id를 잘못 입력하셨습니다.","utf-8");
		
		//UserValidator userValidator = new UserValidator();
		//userValidator.validate(user,result);
		
		if(result.hasErrors()) {
			return "registerForm2";
		}
		return "registerInfo2";
		
		//if(!isValid(user))
			//return "redirect:/register/add2";
		//return"registerInfo2";
	}
	
	
	
	//private boolean isValid(User user) {
		//return true;
	//}
}
