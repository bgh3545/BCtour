package com.greenart.ch1;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BCSignUpController {

	@Autowired
	BCUserDao bcUserDao;

	@GetMapping("/BCsignup")
	public String BCsignup() {
		return "BCsignup";
	}

	@PostMapping("/BCsignup")
	public String save(Model m, BCUser user, String pwd_check) {
		System.out.println(user);
		m.addAttribute("signuser", user);

		if (!user.getPwd().equals(pwd_check)) {
			System.out.println("��й�ȣ ����ġ pwd_check = " + pwd_check);
			return "BCsignup";
		}
		if (bcUserDao.insertUser(user) == 1) {
			return "BCsignupInfo";
		}
		return "BCsingup";
	}

	// DB ��ü ����
	@RequestMapping("/BCinput")
	public String input(Model m, BCUser user) {

		ArrayList<BCUser> bcuser = bcUserDao.selectAll();
		m.addAttribute("bcuser", bcuser);

		return "BCinput";
	}
}