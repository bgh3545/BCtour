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
@RequestMapping("/login1")
public class LoginController {

	@Autowired
	UserDao userDao;
	
	@GetMapping("/login1")
	public String loginForm() {
		return "loginForm";
	}
	
	@PostMapping("/login1")
	public String login(String id, String pwd, boolean rememberId, HttpServletResponse response, HttpServletRequest request, String toURL) throws Exception{
		System.out.println("id=" + id);
		System.out.println("pwd=" + pwd);
		System.out.println("rememberId=" + rememberId);
		//1. id�� pwd ��ġ�ϴ��� Ȯ��
		//2-1. ��ġ���� ������ loginForm�̵�
		if(!loginCheck(id, pwd)) {
			String msg = URLEncoder.encode("id�Ǵ� pwd�� ��ġ���� �ʽ��ϴ�.", "utf-8");
			return "redirect:/login1/login1?msg=" + msg;
		}
		//2-2. ��ġ�ϸ� home���� �̵�
		// ���� ��ü�� id�� ����
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		
		if(rememberId) {
			// 1. ��Ű�� ����
			Cookie cookie = new Cookie("id", id);
			// 2. ���信 ����
			response.addCookie(cookie);
		} else {
			// 1. ��Ű�� ����
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(0);
			// 2. ���信 ����
			response.addCookie(cookie);
		}
		if(toURL==null || toURL.equals("")) { 
			toURL = "/"; 
		}
		return "redirect:"+toURL;
	}
	@GetMapping("/logout")
	// ���������� �������� �Ű������� ������ �ڵ����� �Է�����
	public String logout(HttpSession session) {
		// 1. ������ ����
		session.invalidate();
		// 2. Ȩ���� �̵�
		return "redirect:/";
	}
	
	private boolean loginCheck(String id, String pwd) throws Exception {
		DBUser user = userDao.selectUser(id);
		if(user==null) return false;
		
		return user.getPwd().equals(pwd);
	}
	
}
