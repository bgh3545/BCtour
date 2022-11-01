package com.greenart.ch1;

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
	BCUserDao bcuserDao;

	// required�� ���� ���ܳ����� redirect ���ص� ��
	// ���̵� ã��
	@GetMapping("/BCFindingId")
	public String findid() {
		return "BCFindingId";
	}

	@PostMapping("/BCFindingId")
	public String findid2(Model m, BCUser bcuser) throws Exception { // ���� �ۼ� �ؾߴ� ( ������ ���� �� ������ ���� �ؾ� ã�� )
		System.out.println("findid2 = " + bcuser);
		if (idCheck(bcuser) != null) {
			m.addAttribute("userfindid", idCheck(bcuser));
			return "BCFindId";
		}
		return "BCFindingId";
	}

	// ��й�ȣ ã��
	@GetMapping("/BCFindingPwd")
	public String findpwd() {
		return "BCFindingPwd";
	}

	@PostMapping("/BCFindingPwd")
	public String findpwd2(Model m, BCUser bcuser) throws Exception { // ���� �ۼ�
		System.out.println("findpwd2 = " + bcuser);
		if (pwdCheck(bcuser) != null) {
			m.addAttribute("userfindpwd", pwdCheck(bcuser));
			return "BCFindPwd";
		}
		return "BCFingdingPwd";
	}

	private BCUser idCheck(BCUser bcuser) throws Exception { // ��ȭ��ȣ ������ȣ�ؼ� �߰�
		System.out.println("bcuser name, tel = " + bcuser.getName() + " , " + bcuser.getTel());
		BCUser user = bcuserDao.idToTelUser(bcuser);
		System.out.println("idCheck = " + user);
		if (user.getName().equals(bcuser.getName()) &&
			user.getTel().equals(bcuser.getTel())) {
			return user;
		}
		return null;
	}

	private BCUser pwdCheck(BCUser bcuser) throws Exception { // ��ȭ��ȣ ������ȣ �߰�
		BCUser user = bcuserDao.pwdToTelUser(bcuser);
		System.out.println("pwdCheck = " + user);
		if (user.getId().equals(bcuser.getId()) &&
			user.getName().equals(bcuser.getName()) &&
			user.getTel().equals(bcuser.getTel())) {
			return user;
		}
		return null;
	}
}