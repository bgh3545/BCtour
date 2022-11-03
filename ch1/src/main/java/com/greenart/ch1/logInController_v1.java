package com.greenart.ch1;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logIn1")
public class logInController_v1 {
	@Autowired
	UserDao userDao;
	
	@GetMapping("/logIn1")
	public String logInForm() {
		return"logInForm_v1";
	}
	@PostMapping("/logIn1")
	public String login(String id, String pwd, boolean rememberId, HttpServletResponse response, HttpServletRequest request, String toURL) throws Exception {
		
		System.out.println("id="+id);
		System.out.println("pwd="+pwd);
		System.out.println("rememberId="+rememberId);
		
		if(!loginCheck(id,pwd)) {
			String msg= URLEncoder.encode("id 혹은 비밀번호를 확인해 주세요","utf-8");
			return "redirect:/logIn1/logIn1?msg="+msg;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("id",id);
		
		if(rememberId) {
			Cookie cookie = new Cookie("id",id);
			response.addCookie(cookie);
		}
		else {
			Cookie cookie = new Cookie("id",id);
			cookie.setMaxAge(0);
			
			response.addCookie(cookie);
		}
		
		if(toURL==null||toURL.equals("")) 
			toURL="/1";
		
		return "redirect:"+toURL;
	}
	
	@GetMapping("/logOut1")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/1";
	}
	
	private boolean loginCheck(String id, String pwd) throws Exception {
		User user = userDao.SelectUser(id);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@"+user);
		if(user==null) return false;
		
		return user.getPwd().equals(pwd);
	}

}
	