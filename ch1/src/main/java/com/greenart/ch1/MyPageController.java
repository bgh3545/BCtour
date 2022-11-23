package com.greenart.ch1;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myPage")
public class MyPageController {

	@GetMapping("/myPage")
	public String list(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		return "myPage";
	}
	
	@GetMapping("/myPage1")
	public String list1(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		return "myPage1";
	}
	
	@GetMapping("/myPage2")
	public String list2(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		return "myPage2";
	}
	
	@GetMapping("/myPage3")
	public String list3(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		return "myPage3";
	}
	
	@GetMapping("/myPage4")
	public String list4(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		return "myPage4";
	}
	
	@GetMapping("/myPage_v1")
	public String list_v1(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		return "myPage_v1";
	}
	
	@PostMapping("/myPage_v1")
	public String login(String pwd, HttpServletResponse response, HttpServletRequest request, String toURL) throws Exception {
		
		if(!pwdCheck(pwd)) {
			String msg= URLEncoder.encode("비밀번호가 틀렸습니다","utf-8");
			return "redirect:/myPage/myPage_v1?msg="+msg;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("pwd",pwd);
		
		return "redirect:/myPage/myPage_v1_2";
	}
	
	private boolean pwdCheck(String pwd) {
		return "1234".equals(pwd);
	}
	
	@GetMapping("/myPage_v1_1")
	public String list_v1_1(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		return "myPage_v1_1";
	}
	
	@GetMapping("/myPage_v1_2")
	public String list1_v1_2(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		return "myPage_v1_2";
	}
	
	@GetMapping("/myPage_v1_3")
	public String list_v1_3(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		return "myPage_v1_3";
	}
	
	@GetMapping("/myPage_v1_4")
	public String list_v1_4(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		return "myPage_v1_4";
	}
	
	@GetMapping("/myPage_v1_5")
	public String list_v1_5(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		return "myPage_v1_5";
	}
	
	private boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("id")!=null) {
			return true;
		}
		else {
			return false;
		}
	}
}