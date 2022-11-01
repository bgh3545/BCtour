package com.greenart.ch1;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RegisterController {
	@Autowired
	UserDao userDao;
	
	@InitBinder
	public void dateBinder (WebDataBinder binder) {
		binder.registerCustomEditor(LocalDate.class, "birth", new PropertyEditorSupport() {

			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(LocalDate.parse(text));
			}
			
		});
	}
	
	@InitBinder
	public void toDate(WebDataBinder binder) { // 
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));  
		binder.registerCustomEditor(String[].class,"hobby", new StringArrayPropertyEditor("#"));
//		binder.setValidator(new UserValidator()); 
//		binder.addValidators(new UserValidator()); 
		
		List<Validator> validatorList = binder.getValidators();
		System.out.println(validatorList);
		binder.setValidator(new UserValidator());
	}
	
//	@RequestMapping("/register/add")
//	@RequestMapping(value="/register/add", method= {RequestMethod.GET, RequestMethod.POST})
	
	@GetMapping(value="/register/add")
	public String register() {
		return "RegisterForm"; // WEB-INF/views/registerForm.jsp
	}
	
//	@RequestMapping("/register/save")
//	@RequestMapping(value="/register/save", method=RequestMethod.POST)
	@PostMapping("/register/add")
	public String save(Model m, @Valid DBUser user, BindingResult result) throws Exception {
		System.out.println("result = " + result);
		System.out.println("user = " + user);
		m.addAttribute("user", user);
		
		if(userDao.insertUser(user)==1) {
			return "RegisterInfo";
		}
		
		// User
		if(result.hasErrors()) {
			return "RegisterForm";
		}
		// 2. 
		return "RegisterInfo";
	}
	
	
	
	@RequestMapping("/DBinput")
	public String input(Model m, @Valid DBUser user, BindingResult result) throws Exception {
		System.out.println("user = " + user);
		if(true) {
			ArrayList<DBUser> user1 = userDao.selectAll();
			m.addAttribute("user1", user1);
			return "DBinput";
		}
		
		return "RegisterInfo";
	}
}
