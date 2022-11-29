package com.greenart.ch1.Controller;

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

import com.greenart.ch1.User.BCUserDao;
import com.greenart.ch1.User.BCUserDto;

@Controller
public class BCSignUpController {

	@Autowired
	BCUserDao bcUserDao;

	@GetMapping("/BCsignup")
	public String BCsignup() {
		return "loginAndRegist/BCsignup";
	}
	
	@PostMapping("/BCsignup")
	public String save(Model m, BCUserDto user, String pwd_check) throws Exception {

		if (!user.getPwd().equals(pwd_check)) {
			System.out.println("비밀번호 불일치 pwd_check = " + pwd_check);
			return "loginAndRegist/BCsignup";
		}
		if (bcUserDao.insertUser(user) == 1) {
			return "loginAndRegist/BCloginForm";
		}
		return "loginAndRegist/BCsignup";
	}
	
	@GetMapping("/checkId")
	@ResponseBody
	public String test(@RequestParam String id, Model m) throws Exception {
		System.out.println("idCheck = " + id);
		int cnt = bcUserDao.selectIdCount(id);
		System.out.println(cnt);
		if(cnt!=0) {
			return "true";
		}
		return "false";
	}

	// DB selectAll
	@RequestMapping("/BCinput")
	public String input(Model m, BCUserDto user) throws Exception {

		List<BCUserDto> bcuser = bcUserDao.selectAll();
		m.addAttribute("bcuser", bcuser);

		return "loginAndRegist/BCinput";
	}
}