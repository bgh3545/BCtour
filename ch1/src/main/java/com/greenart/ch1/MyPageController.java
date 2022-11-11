package com.greenart.ch1;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/myPage")
public class MyPageController {

	@Autowired
	QuestionsDao quesDao;
	@Autowired
	QuestionsService quesService;
	
	@GetMapping("/myPage_pwdCheck")
	public String login(String pwd, HttpServletResponse response, HttpServletRequest request, String toURL) throws Exception {
		
		if(!pwdCheck(pwd)) {
			String msg= URLEncoder.encode("비밀번호가 틀렸습니다","utf-8");
			return "redirect:/myPage/myPage_pwdCheck?msg="+msg;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("pwd",pwd);
		
		return "redirect:/myPage/myPage_personalInfo";
	}
	
	private boolean pwdCheck(String pwd) {
		return "1234".equals(pwd);
	}
	
	@GetMapping("/myPage_main")
	public String list_v1_1(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		return "myPage_main";
	}
	
	@GetMapping("/myPage_personalInfo")
	public String list1_v1_2(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		return "myPage_personalInfo";
	}
	
	@GetMapping("/myPage_reservation")
	public String list_v1_3(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		return "myPage_reservation";
	}
	
	@GetMapping("/myPage_wishList")
	public String list_v1_4(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		return "myPage_wishList";
	}
	
	@GetMapping("/myPage_questions")
	public String list_v1_5(HttpServletRequest request, HttpSession session, SearchCondition sc, Model m, QuestionsDto quesDto) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		try {
			String writer = (String)session.getAttribute("id");
			
			int totalCnt = quesService.q_getSearchResultCnt(sc, writer);
			PageHandler pageHandler = new PageHandler(totalCnt, sc);
			
			List<QuestionsDto> ques = quesService.q_getSearchResultPage(sc,writer);
			
			m.addAttribute("ques",ques);
			m.addAttribute("ph", pageHandler);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
			
			Date now = new Date();
			m.addAttribute("now",now);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "myPage_questions";
	}
	
	@GetMapping("/read_question")
	public String read_question(HttpServletRequest request, SearchCondition sc, HttpSession session, Model m, Integer ques_num) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		try {
			String writer = (String)session.getAttribute("id");
			
			QuestionsDto quesDto = quesService.q_read(ques_num);
			m.addAttribute("quesDto", quesDto);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:/myPage/myPage_questions"+sc.getQueryString();
		}
		
		return "read_question";
	}
	
	@PostMapping("/remove_question")
	public String remove_1(HttpServletRequest request,Model m, Integer ques_num, SearchCondition sc, HttpSession session, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		
		try {
			String writer = (String)session.getAttribute("id");
			int rowCnt= quesService.q_remove(ques_num, writer);
			if(rowCnt==1) {
				redatt.addFlashAttribute("msg", "del");
				return "redirect:/myPage/myPage_questions"+sc.getQueryString();
			}
			else {
				throw new Exception("questions remove error");
			}
		}catch(Exception e){
			e.printStackTrace();
			redatt.addFlashAttribute("msg", "error");
		}
		return "redirect:/myPage/myPage_questions"+sc.getQueryString();
	}
	
	@GetMapping("/write_question")
	public String write_2(HttpServletRequest request,Model m, Integer ques_num, SearchCondition sc) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		try {
			QuestionsDto quesDto = quesService.q_read(ques_num);
			m.addAttribute("quesDto", quesDto);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "write_question";
	}
	
	private boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("id")!=null) {
			return true;
		}
		else {
			return false;
		}
	}
}