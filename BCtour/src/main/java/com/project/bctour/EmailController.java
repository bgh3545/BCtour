package com.project.bctour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmailController {

	@Autowired
	private MailService mailService;
	@Autowired
	private BCUserDao dao;

	@RequestMapping("/noticeMail")
	public ModelAndView sendEmail(BCUserDto bcDto, Model m) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		System.out.println("email = " + bcDto.getEmail());
		System.out.println("name = " + bcDto.getName());
		Integer dtoEmail = dao.confirmEmail(bcDto.getEmail());
		
		System.out.println("dtoEmail = " + dtoEmail);
		if (dtoEmail==1) {
			System.out.println("실행");

			String toAddr = bcDto.getEmail(); // 받는사람

			String fromAddr = "bctour88@gmail.com"; // 보내는 사람

			String subject = "BCtour 인증번호입니다."; // 제목

			String asd = "http://localhost:8080/bctour/BCFind/BCFindingId";
			String body = asd; // 내용 ( 인증번호 )

			mailService.sendEmail(toAddr, fromAddr, subject, body);

			mv.setViewName("BCFindingId");
			System.out.println("실행 끝");
			m.addAttribute("bcDto", bcDto);
			return mv;
		} else {
			mv.setViewName("BCFindingId");
			return mv;
		}
	}
}