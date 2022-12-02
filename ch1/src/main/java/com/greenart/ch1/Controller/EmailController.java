package com.greenart.ch1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
			
			String findId = "아이디를 찾기 위한 인증확인 링크입니다. 아래의 링크를 클릭해주세요."
					+ "\n\nhttp://localhost:8080/ch1/BCFind/BCFindingId?name2=" + name + "&email2=" + email;
			
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
	
	// reservationConfirm Email 
	@GetMapping("/rvConfirmEmail")
	@ResponseBody
	public String rvConfirmEmail(String mem_id, Model m) throws Exception {
	
		String memEmail = dao.selectUser(mem_id).getEmail(); // userEmail
		
		Integer dtoEmail = dao.confirmEmail(memEmail);
		
		if (dtoEmail==1) {
			
			System.out.println("############Send#############");

			String toAddr = memEmail; // user

			String fromAddr = "bctour88@gmail.com"; // admin

			String subject = "BCtour 예약완료 안내 이메일입니다."; // email title
			
			String rvConfirm = "예약이 완료되었습니다.\n\n정해진 요일과 시간에 맞춰 즐거운 여행 되시길 바랍니다.\n\n감사합니다.\n\nBCtour홈페이지 바로가기 : http://localhost:8080/ch1/";
			
			String body = rvConfirm; // email contents

			mailService.sendEmail(toAddr, fromAddr, subject, body);
			
			System.out.println("############Send End#############");
			return "emailSuccess";
		} else {
			return "emailFail";
		}
	}
	
	// reservationCancel Email 
	@GetMapping("/rvCancelEmail")
	@ResponseBody
	public String rvCencalEmail(String mem_id, Model m) throws Exception {
	
		String memEmail = dao.selectUser(mem_id).getEmail(); // userEmail
		
		Integer dtoEmail = dao.confirmEmail(memEmail);
		
		if (dtoEmail==1) {
			
			System.out.println("############Send#############");

			String toAddr = memEmail; // user

			String fromAddr = "bctour88@gmail.com"; // admin

			String subject = "BCtour 예약취소 안내 이메일입니다."; // email title
			
			String rvCancel = "예약이 취소되었습니다.\n\nBCtour홈페이지 바로가기 : http://localhost:8080/ch1/";
			
			String body = rvCancel; // email contents

			mailService.sendEmail(toAddr, fromAddr, subject, body);
			
			System.out.println("############Send End#############");
			return "emailSuccess";
		} else {
			return "emailFail";
		}
	}
	
}