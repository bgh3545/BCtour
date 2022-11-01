package com.greenart.ch1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	// 3-1 ) �α����� ���� ������, �Խ��� ����� �������� ����
	@GetMapping("/list")
	public String list(HttpServletRequest request) {
		if(!loginCheck(request)) {
			// �α����� �������� �α��� ȭ������ �̵�
			return "redirect:/login1/login1?toURL=" + request.getRequestURL();
		}
		// �α����� �� ���¶��, �Խ��� ȭ������ �̵�
		return "board";
	}
	
	// �α��� ���� Ȯ�� �Լ�
	private boolean loginCheck(HttpServletRequest request) {
		// 1. ������ ���
		HttpSession session = request.getSession();
		// 2. ���� id�� �ִ��� Ȯ��
		if(session.getAttribute("id") != null) {
			return true;
		} else {
			return false;
		}
		// ���ٷ� ǥ�� ���� return session.getAttribute("id")!=null;
	}

}
