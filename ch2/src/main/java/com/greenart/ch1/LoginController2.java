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
@RequestMapping("/login2")
public class LoginController2 {
	@GetMapping("/login2")
	public String loginForm() {
		return "loginForm";
	}
	@GetMapping("/logout")
	// 스프링에서 세션으로 매개변수를 받으면 자동으로 입력해줌 
	public String logout(HttpSession session) {
		// 1. 세션을 종료
		session.invalidate();
		// 2. 홈으로 이동
		return "redirect:/";
		
	}
	@PostMapping("/login2")
	public String login(String id, String pwd, String toURL, boolean rememberId, HttpServletResponse response, HttpServletRequest request) throws Exception {
		// 1. id와 pwd 확인
		// 2-1. id와 pwd가 일치하지 않으면, loginForm으로 이동
		if (!loginCheck(id,pwd)) {
			String msg = URLEncoder.encode("id와 pw중 하나가 일치 하지 않음","utf-8");
			// get방식으로 진행됨
			return "redirect:/login2/login2?msg="+msg;
		}

		// 2-2. id와 pwd가 일치하면,
		//세션 객체에 id를 저장
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		
		
		if(rememberId) {
			// 	1. 쿠키를 생성
			Cookie cookie = new Cookie("id",id);
			//	2. 응답에 저장
			response.addCookie(cookie);
		}
		else {
			// 	1. 쿠키를 생성
			Cookie cookie = new Cookie("id",id);
			//	2. 유효기간을 0으로 설정
			cookie.setMaxAge(0);
			//	3. 응답에 저장
			response.addCookie(cookie);
		}

		//	3. null이거나 빈문자열이면 홈으로 이동, toURL값이 있다면 URL위치로 이동
		if(toURL==null || toURL.equals("")) toURL="/";
 
		return "redirect:"+ toURL;
	}
	// 아이디와 비밀번호가 일치하다면 return true
	private boolean loginCheck(String id, String pwd) {
		return "asdf".equals(id)&&"1234".equals(pwd);
	}
	
}