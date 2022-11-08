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
	public String findid2(Model m, String name, String tel) throws Exception { // 조건 작성 해야댐 ( 폼에서 들어올 때 조건을 만족 해야 찾음 )
		if (idCheck(name, tel) != null) {
			m.addAttribute("userfindid", idCheck(name, tel));
			return "BCFindId";
		}
		return "BCFindingId";
	}
	// 
	private BCUserDto idCheck(String name, String tel) throws Exception { // 전화번호 인증번호해서 추가
		BCUserDto user = bcUserDao.idToTelUser(name, tel);
		if (user.getName().equals(name) && user.getTel().equals(tel)) {
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
	public String findpwd2(Model m, String id, String name, String tel) throws Exception { // 조건 작성
		if (pwdCheck(id, name, tel) != null) {
			m.addAttribute("userfindpwd", pwdCheck(id, name, tel));
			return "BCFindPwd";
		}
		return "BCFingdingPwd";
	}

	private BCUserDto pwdCheck(String id, String name, String tel) throws Exception { // 전화번호 인증번호 추가
		BCUserDto user = bcUserDao.pwdToTelUser(id, name, tel);
		if (user.getId().equals(id) && user.getName().equals(name)
				&& user.getTel().equals(tel)) {
			return user;
		}
		return null;
	}
}