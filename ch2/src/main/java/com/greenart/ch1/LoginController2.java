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
	// ���������� �������� �Ű������� ������ �ڵ����� �Է����� 
	public String logout(HttpSession session) {
		// 1. ������ ����
		session.invalidate();
		// 2. Ȩ���� �̵�
		return "redirect:/";
		
	}
	@PostMapping("/login2")
	public String login(String id, String pwd, String toURL, boolean rememberId, HttpServletResponse response, HttpServletRequest request) throws Exception {
		// 1. id�� pwd Ȯ��
		// 2-1. id�� pwd�� ��ġ���� ������, loginForm���� �̵�
		if (!loginCheck(id,pwd)) {
			String msg = URLEncoder.encode("id�� pw�� �ϳ��� ��ġ ���� ����","utf-8");
			// get������� �����
			return "redirect:/login2/login2?msg="+msg;
		}

		// 2-2. id�� pwd�� ��ġ�ϸ�,
		//���� ��ü�� id�� ����
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		
		
		if(rememberId) {
			// 	1. ��Ű�� ����
			Cookie cookie = new Cookie("id",id);
			//	2. ���信 ����
			response.addCookie(cookie);
		}
		else {
			// 	1. ��Ű�� ����
			Cookie cookie = new Cookie("id",id);
			//	2. ��ȿ�Ⱓ�� 0���� ����
			cookie.setMaxAge(0);
			//	3. ���信 ����
			response.addCookie(cookie);
		}

		//	3. null�̰ų� ���ڿ��̸� Ȩ���� �̵�, toURL���� �ִٸ� URL��ġ�� �̵�
		if(toURL==null || toURL.equals("")) toURL="/";
 
		return "redirect:"+ toURL;
	}
	// ���̵�� ��й�ȣ�� ��ġ�ϴٸ� return true
	private boolean loginCheck(String id, String pwd) {
		return "asdf".equals(id)&&"1234".equals(pwd);
	}
	
}