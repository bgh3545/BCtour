package com.greenart.ch1;

import java.io.UnsupportedEncodingException;
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
		
		HttpSession session = request.getSession();
		session.setAttribute("pwd",pwd);
		
		return "myPage_pwdCheck";
	}
	
	
	@GetMapping("/myPage_main")
	public String list_v1_1(HttpServletRequest request, HttpSession session, Model m) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		int quesCnt = quesService.q_getCount(writer);
		
		m.addAttribute("quesCnt", quesCnt);
		
		return "myPage_main";
	}
	
	@PostMapping("/myPage_personalInfo")
	public String list1_v1_2(HttpServletRequest request, String pwd, Model m) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		if(!pwdCheck(pwd)) {
			String msg= "비밀번호가 틀렸습니다. 다시 입력해주세요";
			m.addAttribute("msg", msg);
			return "myPage_pwdCheck";
		}
		
		return "myPage_personalInfo";
	}
	
	private boolean pwdCheck(String pwd) {
		return "1234".equals(pwd);
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
	public String myPage_questions1(HttpServletRequest request, HttpSession session, SearchCondition sc, Model m, QuestionsDto quesDto) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		try {
			String writer = (String)session.getAttribute("id");
			
			int mNATotalCnt = quesService.q_getSearchResultNoAnsManagerCnt(sc);
			int mTotalCnt = quesService.q_getSearchResultManagerCnt(sc);
			int totalCnt = quesService.q_getSearchResultCnt(sc, writer);
			PageHandler pageHandler = new PageHandler(totalCnt, sc);
			
			List<QuestionsDto> mNAQues = quesService.q_getSearchResultNoAnsManagerPage(sc);
			List<QuestionsDto> mQues = quesService.q_getSearchResultManagerPage(sc);
			List<QuestionsDto> ques = quesService.q_getSearchResultPage(sc,writer);
			List<AnswerDto> ans = quesService.a_getList();
			
			m.addAttribute("ans",ans);
			m.addAttribute("ques",ques);
			m.addAttribute("mQues",mQues);
			m.addAttribute("mNAQues",mNAQues);
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
	
	@PostMapping("/myPage_questions")
	public String myPage_questions2(HttpServletRequest request, HttpSession session, SearchCondition sc, Model m, QuestionsDto quesDto) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		try {
			String writer = (String)session.getAttribute("id");
			
			int mNATotalCnt = quesService.q_getSearchResultNoAnsManagerCnt(sc);
			int mTotalCnt = quesService.q_getSearchResultManagerCnt(sc);
			int totalCnt = quesService.q_getSearchResultCnt(sc, writer);
			PageHandler pageHandler = new PageHandler(totalCnt, sc);
			
			List<QuestionsDto> mQues = quesService.q_getSearchResultNoAnsManagerPage(sc);
			List<QuestionsDto> ques = quesService.q_getSearchResultPage(sc,writer);
			List<AnswerDto> ans = quesService.a_getList();
			
			m.addAttribute("ans",ans);
			m.addAttribute("ques",ques);
			m.addAttribute("mQues",mQues);
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
	public String write_question(HttpServletRequest request,Model m, Integer ques_num, SearchCondition sc) {
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
	
	@PostMapping("/write_question")
	public String write_1(HttpServletRequest request,Model m, QuestionsDto quesDto, HttpSession session, Integer ques_num, SearchCondition sc, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		quesDto.setQues_writer(writer);
		
		try {
			if(quesDto.getQues_title()!="") {
				int rowCnt = quesService.q_write(quesDto);
				if(rowCnt!=1) throw new Exception("write error");
				redatt.addFlashAttribute("msg", "write_ok");
				return "redirect:/myPage/myPage_questions";
				}
				m.addAttribute("msg", "notitle");
				return "write_question";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "write_question";
	}
	
	@GetMapping("/write_answer")
	public String write_answer1(HttpServletRequest request,Model m, Integer ques_num, SearchCondition sc, String ques_title) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		try {
			m.addAttribute("ques_title", ques_title);
			m.addAttribute("ques_num", ques_num);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "write_answer";
	}
	
	@PostMapping("/write_answer")
	public String write_answer2(HttpServletRequest request,Model m, Integer ans_num, SearchCondition sc, AnswerDto ansDto, HttpSession session, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		ansDto.setAns_writer(writer);
		
		try {
			if(ansDto.getAns_title()!="") {
			int rowCnt = quesService.a_write(ansDto);
			int upansbool = quesService.q_ansBool(ans_num);
			if(rowCnt!=1) throw new Exception("write error");
			redatt.addFlashAttribute("msg", "write_ok");
			return "redirect:/myPage/myPage_questions";
			}
			m.addAttribute("msg", "notitle");
			return "write_answer";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "write_answer";
	}
	
	@GetMapping("/read_answer")
	public String read_answer(HttpServletRequest request, SearchCondition sc, HttpSession session, Model m, Integer ans_num) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		try {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+ans_num);
			AnswerDto ansDto = quesService.a_read(ans_num);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+ansDto);
			
			m.addAttribute("ansDto", ansDto);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:/myPage/myPage_questions"+sc.getQueryString();
		}
		
		return "read_answer";
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