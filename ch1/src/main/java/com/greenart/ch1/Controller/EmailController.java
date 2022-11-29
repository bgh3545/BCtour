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

	// userId Email
	@RequestMapping("/emailGetId")
	public ModelAndView idsendEmail(BCUserDto bcDto, Model m) throws Exception {
		ModelAndView mv = new ModelAndView();
	
		Integer dtoEmail = dao.confirmEmail(bcDto.getEmail());
		if (dtoEmail==1) {
			String email = bcDto.getEmail();
			String name = bcDto.getName();
			
			System.out.println("############Send#############");

			String toAddr = bcDto.getEmail(); // user

			String fromAddr = "bctour88@gmail.com"; // admin

			String subject = "BCtour 이메일 인증확인입니다."; // email title
			
			String findId = "아이디를 찾기 위한 인증확인 링크입니다. 아래의 링크를 클릭해주세요.\n\nhttp://localhost:8080/ch1/BCFind/BCFindingId?name2=" + name + "&email2=" + email;
			
			String body = findId; // email contents

			mailService.sendEmail(toAddr, fromAddr, subject, body);
			
			mv.setViewName("loginAndRegist/BCFindingId");
			System.out.println("############Send End#############");
			return mv;
		} else {
			mv.setViewName("loginAndRegist/BCFindingId");
			return mv;
		}
	}
	
	// userPwd Email
	@RequestMapping("/emailGetPwd")
	public ModelAndView pwdsendEmail(BCUserDto bcDto, Model m) throws Exception {
		ModelAndView mv = new ModelAndView();
	
		Integer dtoEmail = dao.confirmEmail(bcDto.getEmail());
		if (dtoEmail==1) {
			String id = bcDto.getId();
			String name = bcDto.getName();
			String email = bcDto.getEmail();
			
			
			System.out.println("############Send#############");

			String toAddr = bcDto.getEmail(); // user

			String fromAddr = "bctour88@gmail.com"; // admin

			String subject = "BCtour 이메일 인증확인입니다."; // email title
			
			String findPwd = "비밀번호를 찾기 위한 인증확인 링크입니다. 아래의 링크를 클릭해주세요.\n\nhttp://localhost:8080/ch1/BCFind/BCFindingPwd?id2=" + id + "&name2=" + name + "&email2=" + email;
			
			String body = findPwd; // email contents

			mailService.sendEmail(toAddr, fromAddr, subject, body);
			
			mv.setViewName("loginAndRegist/BCFindingPwd");
			System.out.println("############Send End#############");
			return mv;
		} else {
			mv.setViewName("loginAndRegist/BCFindingPwd");
			return mv;
		}
	}
	
}