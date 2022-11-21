package com.greenart.ch1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greenart.ch1.User.BCUserDao;
import com.greenart.ch1.User.BCUserDto;

@Controller
@RequestMapping("/BCFind")
public class EmailController {

	@Autowired
	private MailService mailService;
	@Autowired
	private BCUserDao dao;

	// ���̵� Email
	@RequestMapping("/emailGetId")
	public ModelAndView idsendEmail(BCUserDto bcDto, Model m) throws Exception {
		ModelAndView mv = new ModelAndView();
	
		Integer dtoEmail = dao.confirmEmail(bcDto.getEmail());
		if (dtoEmail==1) {
			String email = bcDto.getEmail();
			String name = bcDto.getName();
			
			System.out.println("����");

			String toAddr = bcDto.getEmail(); // �޴»��

			String fromAddr = "bctour88@gmail.com"; // ������ ���

			String subject = "BCtour �̸��� ����Ȯ���Դϴ�."; // ����
			
			
			String findId = "http://localhost:8080/ch1/BCFind/BCFindingId?name2=" + name + "&email2=" + email;
			String body = findId; // ���� ( ����Ȯ�� ��ũ )

			mailService.sendEmail(toAddr, fromAddr, subject, body);
			
			mv.setViewName("BCFindingId");
			System.out.println("���� ��");
//			m.addAttribute("bcDto", bcDto);
			return mv;
		} else {
			mv.setViewName("BCFindingId");
			return mv;
		}
	}
	
	// �н����� Email
	@RequestMapping("/emailGetPwd")
	public ModelAndView pwdsendEmail(BCUserDto bcDto, Model m) throws Exception {
		ModelAndView mv = new ModelAndView();
	
		Integer dtoEmail = dao.confirmEmail(bcDto.getEmail());
		if (dtoEmail==1) {
			String id = bcDto.getId();
			String name = bcDto.getName();
			String email = bcDto.getEmail();
			
			
			System.out.println("����");

			String toAddr = bcDto.getEmail(); // �޴»��

			String fromAddr = "bctour88@gmail.com"; // ������ ���

			String subject = "BCtour �̸��� ����Ȯ���Դϴ�."; // ����
			
			String findId = "http://localhost:8080/ch1/BCFind/BCFindingPwd?id2=" + id + "&name2=" + name + "&email2=" + email;
			
			String body = findId; // ���� ( ����Ȯ�� ��ũ )

			mailService.sendEmail(toAddr, fromAddr, subject, body);
			
			mv.setViewName("BCFindingPwd");
			System.out.println("���� ��");
//			System.out.println("���ڵ� : "); 
//			System.out.println(java.net.URLEncoder.encode(findId));
//			String de = java.net.URLEncoder.encode(findId);
//			System.out.println("���ڵ� : ");
//			System.out.println(java.net.URLDecoder.decode( de ));
			return mv;
		} else {
			mv.setViewName("BCFindingPwd");
			return mv;
		}
	}
	
}