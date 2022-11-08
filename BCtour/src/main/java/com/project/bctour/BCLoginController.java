package com.project.bctour;

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
@RequestMapping("/BClogin")
public class BCLoginController {
	
	@Autowired
	BCUserDao bcUserDao;
	
	@GetMapping("/BClogin")
	public String loginForm() {
		return "BCloginForm";
	}
	
	@PostMapping("/BClogin")
	public String login(String id,String pwd, boolean rememberId, HttpServletResponse response, HttpServletRequest request, String toURL) throws Exception{
		
		System.out.println("id=" + id);
		System.out.println("pwd=" + pwd);
		System.out.println("rememberId=" + rememberId);
		//1. id와 pwd 일치하는지 확인
		//2-1. 일치하지 않으면 loginForm이동
		if(!loginCheck(id,pwd)) {
			String msg = URLEncoder.encode("id또는 pwd가 일치하지 않습니다.", "utf-8");
			return "redirect:/BClogin/BClogin?msg=" + msg;
		}
		//2-2. 일치하면 home으로 이동
		// 세션 객체에 id를 저장
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		if(rememberId) {
			// 1. 쿠키를 생성
			Cookie cookie = new Cookie("id", id);
			// 2. 응답에 저장
			response.addCookie(cookie);
		} else {
			// 1. 쿠키를 생성
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(0);
			// 2. 응답에 저장
			response.addCookie(cookie);
		}
		if(toURL==null || toURL.equals("")) { 
			toURL = "/"; 
		}
		return "redirect:"+toURL;
	}
	@GetMapping("/BClogout")
	// 스프링에서 세션으로 매개변수를 받으면 자동으로 입력해줌
	public String logout(HttpSession session) {
		// 1. 세션을 종료
		session.invalidate();
		// 2. 홈으로 이동
		return "redirect:/";
	}
	
	private boolean loginCheck(String id, String pwd) throws Exception {
		System.out.println("id = " + id);
		System.out.println("pwd = " + pwd);
		BCUserDto user = bcUserDao.selectUser(id);
		if(user==null) return false;
		
		return user.getPwd().equals(pwd);
	}
	
}
