package com.greenart.ch1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenart.ch1.User.BCUserDao;
import com.greenart.ch1.User.BCUserDto;

@Controller
@RequestMapping("/BCFind")
public class BCFindController {

	@Autowired
	BCUserDao bcUserDao;

	// userId finding
	@GetMapping("/BCFindingId")
	public String findid() {
		return "loginAndRegist/BCFindingId";
	}

	@PostMapping("/BCFindingId")
	public String findid2(Model m, String name, String email) throws Exception {
		if (idCheck(name, email) != null) {
			m.addAttribute("userfindid", idCheck(name, email));
			return "loginAndRegist/BCFindId";
		}
		return "loginAndRegist/BCFindingId";
	}
	
	// userId check
	private BCUserDto idCheck(String name, String email) throws Exception {
		BCUserDto user = bcUserDao.idToEmail(name, email);
		if (user.getName().equals(name) && user.getEmail().equals(email)) {
			return user;
		}
		return null;
	}

	// userPwd finding
	@GetMapping("/BCFindingPwd")
	public String findpwd() {
		return "loginAndRegist/BCFindingPwd";
	}

	@PostMapping("/BCFindingPwd")
	public String findpwd2(Model m, String id, String name, String email) throws Exception {
		if (pwdCheck(id, name, email) != null) {
			m.addAttribute("userfindpwd", pwdCheck(id, name, email));
			return "loginAndRegist/BCFindPwd";
		}
		return "loginAndRegist/BCFingdingPwd";
	}

	// userPwd check
	private BCUserDto pwdCheck(String id, String name, String email) throws Exception {
		BCUserDto user = bcUserDao.pwdToEmail(id, name, email);
		if (user.getId().equals(id) && user.getName().equals(name)
				&& user.getEmail().equals(email)) {
			return user;
		}
		return null;
	}
}