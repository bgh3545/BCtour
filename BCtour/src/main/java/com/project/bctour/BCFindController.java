package com.project.bctour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/BCFind")
public class BCFindController {

	@Autowired
	BCUserDao bcUserDao;

	// 아이디 찾기
	@GetMapping("/BCFindingId")
	public String findid() {
		return "BCFindingId";
	}

	@PostMapping("/BCFindingId")
	public String findid2(Model m, String name, String email) throws Exception {
		if (idCheck(name, email) != null) {
			m.addAttribute("userfindid", idCheck(name, email));
			return "BCFindId";
		}
		return "BCFindingId";
	}
	
	// 아이디 체크
	private BCUserDto idCheck(String name, String email) throws Exception {
		BCUserDto user = bcUserDao.idToEmail(name, email);
		if (user.getName().equals(name) && user.getEmail().equals(email)) {
			return user;
		}
		return null;
	}

	// 비밀번호 찾기
	@GetMapping("/BCFindingPwd")
	public String findpwd() {
		return "BCFindingPwd";
	}

	@PostMapping("/BCFindingPwd")
	public String findpwd2(Model m, String id, String name, String email) throws Exception { // 조건 작성
		if (pwdCheck(id, name, email) != null) {
			m.addAttribute("userfindpwd", pwdCheck(id, name, email));
			return "BCFindPwd";
		}
		return "BCFingdingPwd";
	}

	// 비밀번호 체크
	private BCUserDto pwdCheck(String id, String name, String email) throws Exception { // 전화번호 인증번호 추가
		BCUserDto user = bcUserDao.pwdToEmail(id, name, email);
		if (user.getId().equals(id) && user.getName().equals(name)
				&& user.getEmail().equals(email)) {
			return user;
		}
		return null;
	}
}