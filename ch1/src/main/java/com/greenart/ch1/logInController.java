package com.greenart.ch1;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logIn")
public class logInController {
	@GetMapping("/logIn")
	public String logInForm() {
		return"logInForm";
	}
	@PostMapping("/logIn")
	public String login(String id, String pwd, boolean rememberId, HttpServletResponse response, HttpServletRequest request, String toURL) throws Exception {
		
		System.out.println("id="+id);
		System.out.println("pwd="+pwd);
		System.out.println("rememberId="+rememberId);
		
		if(!loginCheck(id,pwd)) {
			String msg= URLEncoder.encode(" id또는 비밀번호를 확인해주세요","utf-8");
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
			toURL="/";
		
		return "redirect:"+toURL;
	}
	
	@GetMapping("/logOut")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	private boolean loginCheck(String id, String pwd) {
		
		return "asdf".equals(id)&&"1234".equals(pwd);
	}
}