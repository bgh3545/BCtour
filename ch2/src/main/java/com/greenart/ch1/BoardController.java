package com.greenart.ch1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	// 3-1 ) 로그인을 하지 않으면, 게시판 목록을 보여주지 않음
	@GetMapping("/list")
	public String list(HttpServletRequest request) {
		if(!loginCheck(request)) {
			// 로그인을 안했으면 로그인 화면으로 이동
			return "redirect:/login1/login1?toURL=" + request.getRequestURL();
		}
		// 로그인을 한 상태라면, 게시판 화면으로 이동
		return "board";
	}
	
	// 로그인 상태 확인 함수
	private boolean loginCheck(HttpServletRequest request) {
		// 1. 세션을 열어서
		HttpSession session = request.getSession();
		// 2. 세션 id가 있는지 확인
		if(session.getAttribute("id") != null) {
			return true;
		} else {
			return false;
		}
		// 한줄로 표현 가능 return session.getAttribute("id")!=null;
	}

}
