package com.project.bctour;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BCSignUpController {

	@Autowired
	BCUserDao bcUserDao;

	@GetMapping("/BCsignup")
	public String BCsignup() {
		return "BCsignup";
	}
	
	@PostMapping("/BCsignup")
	public String save(Model m, BCUserDto user, String pwd_check) throws Exception {
//		System.out.println(user);
//		m.addAttribute("signuser", user);

//		if(bcUserDao.selectIdCount(user.getId())!=1) {
//			System.out.println("사용가능아이디");
//			m.addAttribute("userId" , user.getId());
//			System.out.println("user = " + user);
//		}
//		
		if (!user.getPwd().equals(pwd_check)) {
			System.out.println("비밀번호 불일치 pwd_check = " + pwd_check);
			return "BCsignup";
		}
		if (bcUserDao.insertUser(user) == 1) {
			return "BCloginForm";
		}
		return "BCsignup";
	}
	
	@GetMapping("/checkId")
	@ResponseBody
	public String test(@RequestParam String id, Model m) throws Exception {
		System.out.println("아이디체크 =" + id);
		int cnt = bcUserDao.selectIdCount(id);
		System.out.println(cnt);
		if(cnt!=0) {
			return "true";
		}
		return "false";
	}

	// DB 전체 선택
	@RequestMapping("/BCinput")
	public String input(Model m, BCUserDto user) throws Exception {

		List<BCUserDto> bcuser = bcUserDao.selectAll();
		m.addAttribute("bcuser", bcuser);

		return "BCinput";
	}
}