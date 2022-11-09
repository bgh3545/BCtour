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

	// ���̵� ã��
	@GetMapping("/BCFindingId")
	public String findid() {
		return "BCFindingId";
	}

	@PostMapping("/BCFindingId")
	public String findid2(Model m, String name, String email) throws Exception { // ���� �ۼ� �ؾߴ� ( ������ ���� �� ������ ���� �ؾ� ã�� )
		if (idCheck(name, email) != null) {
			m.addAttribute("userfindid", idCheck(name, email));
			return "BCFindId";
		}
		return "BCFindingId";
	}
	
	private BCUserDto idCheck(String name, String email) throws Exception { // ��ȭ��ȣ ������ȣ�ؼ� �߰�
		BCUserDto user = bcUserDao.idToEmail(name, email);
		if (user.getName().equals(name) && user.getEmail().equals(email)) {
			return user;
		}
		return null;
	}

	// ��й�ȣ ã��
	@GetMapping("/BCFindingPwd")
	public String findpwd() {
		return "BCFindingPwd";
	}

	@PostMapping("/BCFindingPwd")
	public String findpwd2(Model m, String id, String name, String email) throws Exception { // ���� �ۼ�
		if (pwdCheck(id, name, email) != null) {
			m.addAttribute("userfindpwd", pwdCheck(id, name, email));
			return "BCFindPwd";
		}
		return "BCFingdingPwd";
	}

	private BCUserDto pwdCheck(String id, String name, String email) throws Exception { // ��ȭ��ȣ ������ȣ �߰�
		BCUserDto user = bcUserDao.pwdToEmail(id, name, email);
		if (user.getId().equals(id) && user.getName().equals(name)
				&& user.getEmail().equals(email)) {
			return user;
		}
		return null;
	}
}