package com.greenart.ch1.Controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import com.greenart.ch1.User.User;
import com.greenart.ch1.User.UserDao;

@Controller
public class RegisterController {
	
	@Autowired
	UserDao userDao;
	
	@GetMapping("/register/add")
	public String register() {
		return"loginAndRegist/registerForm";
    }
	
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
	
	@PostMapping("/register/save")
	public String save(User user, Model m, BindingResult result) throws Exception{
		
		if(!isValid(user)) {
			String msg = URLEncoder.encode("이미 존재하는 id입니다","utf-8");
			return "redirect:/register/add?msg="+msg;
		}
		if(!isValid2(user)) {
			String msg = URLEncoder.encode("이미 존재하는 email입니다","utf-8");
			return "redirect:/register/add?msg="+msg;
		}
		ArrayList<User> user1 = userDao.SelectAll();
		m.addAttribute("list",user1);
		
		if(!result.hasErrors()) {
			int rowCnt=userDao.insertUser(user);
			return "loginAndRegist/registerInfo";
		}
		
		return "loginAndRegist/registerForm";
	}

	private boolean isValid(User user) {
		user =userDao.SelectUser(user.getId());
		if(user!=null) return false;
		return true;
	}
	private boolean isValid2(User user) {
		user =userDao.SelectUserEmail(user.getEmail());
		if(user!=null) return false;
		return true;
	}
	

}
	
