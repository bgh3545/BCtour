package com.greenart.ch1;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController_v1 {
//	@RequestMapping("/register/add")
//	@RequestMapping(value="/register/add", method= {RequestMethod.GET, RequestMethod.POST})
	
//	@GetMapping("/register/add")
//	public String register() {
//		return "RegisterFrom"; // WEB-INF/views/registerForm.jsp
//	}
	
//	@RequestMapping("/register/save")
//	@RequestMapping(value="/register/save", method=RequestMethod.POST)
	
	@RequestMapping(value="/register/add2", method= {RequestMethod.GET, RequestMethod.POST})
	public String register() {
		return "RegisterFrom2";
	}
	
	@PostMapping("/register/save2")
	public String save(User user, Model m) throws Exception {
		String msg = URLEncoder.encode("id를 잘못입력하셨습니다.","utf-8");
		if(!isValid(user)) {
			return "forward:/register/add2";
		}
		return "RegisterInfo";
	}

	private boolean isValid(User user) {
		return false;
	}
	
}
