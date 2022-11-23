package com.greenart.ch1.Controller;

import java.net.http.HttpRequest;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greenart.ch1.PageHandlerAndSearchCondition.PageHandler;
import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;
import com.greenart.ch1.QuestionsAndAnswers.AnswerDto;
import com.greenart.ch1.QuestionsAndAnswers.QuestionsDao;
import com.greenart.ch1.QuestionsAndAnswers.QuestionsDto;
import com.greenart.ch1.QuestionsAndAnswers.QuestionsService;
import com.greenart.ch1.User.BCUserDao;
import com.greenart.ch1.User.BCUserDto;

@Controller
@RequestMapping("/myPage")
public class MyPageController {

	@Autowired
	QuestionsDao quesDao;
	@Autowired
	QuestionsService quesService;
	@Autowired
	BCUserDao userDao;
	
	@GetMapping("/myPage_main")
	public String myPage_main(HttpServletRequest request, HttpSession session, Model m) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		int quesCnt = quesService.q_getCount(writer);
		
		m.addAttribute("quesCnt", quesCnt);
		
		return "myPageMain/myPage_main";
	}
	
	@GetMapping("/manage")
	public String myPage_manage(HttpServletRequest request, HttpSession session, Model m) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		int quesCnt = quesService.q_getCount(writer);
		
		m.addAttribute("quesCnt", quesCnt);
		
		return "myPageMain/manage_main";
	}
	
	@PostMapping("/myPage_pwdCheck")
	public String myPage_pwdCheck(HttpServletRequest request, HttpSession session, String pwd, Model m) throws Exception {
		String id = (String)session.getAttribute("id");
		
		if(!pwdCheck(pwd,id)) {
			String msg= "비밀번호가 틀렸습니다. 다시 입력해주세요";
			m.addAttribute("msg", msg);
			return "personalInfo/myPage_pwdCheck";
		}
		session = request.getSession();
		session.setAttribute("pwd", pwd);
		
		BCUserDto myPageUser = userDao.selectUser(id);
		m.addAttribute("myPageUser", myPageUser);
		
		return "personalInfo/myPage_personalInfo";
	}
	
	@GetMapping("/manage_pwdCheck")
	public String manage_pwdCheck(HttpSession session) throws Exception {
		
		return "personalInfo/manage_pwdCheck";
	}
	
	@GetMapping("/myPage_personalInfo")
	public String myPage_personalInfo1(HttpServletRequest request, HttpSession session, String pwd, Model m) throws Exception {
		if(!loginCheck(request)) {
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		}
		String id = (String)session.getAttribute("id");
		BCUserDto user = userDao.selectUser(id);
		if( session.getAttribute("pwd") != null ) {
			if( session.getAttribute("pwd").equals(user.getPwd()) ){
				BCUserDto myPageUser = userDao.selectUser(id);
				m.addAttribute("myPageUser", myPageUser);
				return "personalInfo/myPage_personalInfo";
			}
		}
		return "personalInfo/myPage_pwdCheck";
	}
	
	@DeleteMapping("/infoDel")
	@ResponseBody
	public String infoDel(HttpSession session, BCUserDto userDto) throws Exception {
		String id = (String)session.getAttribute("id");
		String pwd = userDao.selectUser(id).getPwd();
		
		int cnt = userDao.deleteUser(id, pwd);
		
		if(cnt==1) {
			session.invalidate();
			return "infoDel";
		} else {
			throw new Exception("회원탈퇴 예외");
		}
	}
	
	@PatchMapping("/modifyPwd")
	@ResponseBody
	public String modifyPwd(HttpSession session, String pwd) {
		try{
			String id = (String)session.getAttribute("id");
			int cnt = userDao.updateUserPwd(id, pwd);
			if(cnt!=0) {
				return "modifyPwd";
			} else throw new Exception();
		} catch(Exception e) {
			e.printStackTrace();
			return "modifyPwdFail";
		}
	}
	@PatchMapping("/modifyEmail")
	@ResponseBody
	public String modifyEmail(HttpSession session, String email) throws Exception {
			String id = (String)session.getAttribute("id");
			int cnt = userDao.updateUserEmail(id, email);
			if(cnt!=0) {
				return "modifyEmail";
			} 
			return "modifyEmailFail";
			
	}
	
	@PatchMapping("/modifyTel")
	@ResponseBody
	public String modifyTel(HttpSession session, String tel) throws Exception{
			String id = (String)session.getAttribute("id");
			BCUserDto user = userDao.selectUser(id);
			if(tel.equals(user.getTel())) throw new Exception("번호가 일치함");
			if(tel.length() < 13) throw new Exception("길이가 부족함");
			
			int cnt = userDao.updateUserTel(id, tel);
			if(cnt!=1) throw new Exception("알수 없는 에러");
			
			return "modifyTel";
	}
	
	@GetMapping("/manage_managerInfo")
	public String manage_managerInfo1(HttpServletRequest request, HttpSession session, String pwd, Model m) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		return "personalInfo/manage_managerInfo";
	}
	
	@PostMapping("/manage_managerInfo")
	public String manage_managerInfo2(HttpServletRequest request, HttpSession session, String pwd, Model m) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String id = (String)session.getAttribute("id");
		
		if(!pwdCheck(pwd, id)) {
			String msg= "비밀번호가 틀렸습니다. 다시 입력해주세요";
			m.addAttribute("msg", msg);
			return "personalInfo/manage_pwdCheck";
		}
		
		session = request.getSession();
		session.setAttribute("pwd",pwd);
		
		return "personalInfo/manage_managerInfo";
	}
	
	private boolean pwdCheck(String pwd, String id) throws Exception {
		BCUserDto user = userDao.selectUser(id);
		return user.getPwd().equals(pwd);
	}
	
	@GetMapping("/myPage_reservation")
	public String myPage_reservation(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		return "reservation/myPage_reservation";
	}
	
	@GetMapping("/manage_reservation")
	public String manage_reservation(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		return "reservation/manage_reservation";
	}
	
	@GetMapping("/myPage_wishList")
	public String myPage_wishList(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		return "wishList/myPage_wishList";
	}
	
	@GetMapping("/myPage_questions")
	public String myPage_questions1(HttpServletRequest request, HttpSession session, SearchCondition sc, Model m, QuestionsDto quesDto) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			String writer = (String)session.getAttribute("id");
			
			int mTotalCnt = quesService.q_getSearchResultManagerCnt(sc);
			int totalCnt = quesService.q_getSearchResultCnt(sc, writer);
			PageHandler pageHandler = new PageHandler(totalCnt, sc);
			PageHandler mPageHandler = new PageHandler(mTotalCnt, sc);
			
			List<QuestionsDto> mNAQues = quesService.q_getSearchResultNoAnsManagerPage(sc);
			List<QuestionsDto> mQues = quesService.q_getSearchResultManagerPage(sc);
			List<QuestionsDto> ques = quesService.q_getSearchResultPage(sc,writer);
			List<AnswerDto> ans = quesService.a_getList();
			
			m.addAttribute("ans",ans);
			m.addAttribute("ques",ques);
			m.addAttribute("mQues",mQues);
			m.addAttribute("mNAQues",mNAQues);
			m.addAttribute("ph", pageHandler);
			m.addAttribute("mph", mPageHandler);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
			
			Date now = new Date();
			m.addAttribute("now",now);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "questionsAndAnswers/myPage_questions";
	}
	
	@GetMapping("/manage_questions")
	public String manage_questions(HttpServletRequest request, HttpSession session, SearchCondition sc, Model m, QuestionsDto quesDto) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			String writer = (String)session.getAttribute("id");
			
			int mTotalCnt = quesService.q_getSearchResultManagerCnt(sc);
			int totalCnt = quesService.q_getSearchResultCnt(sc, writer);
			PageHandler pageHandler = new PageHandler(totalCnt, sc);
			PageHandler mPageHandler = new PageHandler(mTotalCnt, sc);
			
			List<QuestionsDto> mNAQues = quesService.q_getSearchResultNoAnsManagerPage(sc);
			List<QuestionsDto> mQues = quesService.q_getSearchResultManagerPage(sc);
			List<AnswerDto> ans = quesService.a_getList();
			
			m.addAttribute("manage_questions","manage_questions");
			m.addAttribute("ans",ans);
			m.addAttribute("mQues",mQues);
			m.addAttribute("mNAQues",mNAQues);
			m.addAttribute("ph", pageHandler);
			m.addAttribute("mph", mPageHandler);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
			
			Date now = new Date();
			m.addAttribute("now",now);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "questionsAndAnswers/manage_questions";
	}
	
	@GetMapping("/manage_noAns")
	public String manage_questions2(HttpServletRequest request, HttpSession session, SearchCondition sc, Model m, QuestionsDto quesDto) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			String writer = (String)session.getAttribute("id");
			
			int mNATotalCnt = quesService.q_getSearchResultNoAnsManagerCnt(sc);
			PageHandler pageHandler = new PageHandler(mNATotalCnt, sc);
			
			List<QuestionsDto> mQues = quesService.q_getSearchResultNoAnsManagerPage(sc);
			List<QuestionsDto> ques = quesService.q_getSearchResultPage(sc,writer);
			List<AnswerDto> ans = quesService.a_getList();
			
			m.addAttribute("manage_questions","manage_noAns");
			m.addAttribute("ans",ans);
			m.addAttribute("ques",ques);
			m.addAttribute("mQues",mQues);
			m.addAttribute("mph", pageHandler);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
			
			Date now = new Date();
			m.addAttribute("now",now);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "questionsAndAnswers/manage_questions";
	}
	
	@GetMapping("/read_question")
	public String read_question(HttpServletRequest request, SearchCondition sc, HttpSession session, Model m, Integer ques_num) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
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
		
		return "questionsAndAnswers/read_question";
	}
	
	@GetMapping("/manage_read_question")
	public String read_manage_question(HttpServletRequest request, SearchCondition sc, HttpSession session, Model m, Integer ques_num) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			String writer = (String)session.getAttribute("id");
			
			QuestionsDto quesDto = quesService.q_read(ques_num);
			m.addAttribute("quesDto", quesDto);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:/myPage/manage_questions"+sc.getQueryString();
		}
		
		return "questionsAndAnswers/manage_read_question";
	}
	
	@PostMapping("/remove_question")
	public String remove_question(HttpServletRequest request,Model m, Integer ques_num, SearchCondition sc, HttpSession session, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
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
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			QuestionsDto quesDto = quesService.q_read(ques_num);
			m.addAttribute("quesDto", quesDto);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "questionsAndAnswers/write_question";
	}
	
	@PostMapping("/write_question")
	public String write_question(HttpServletRequest request,Model m, QuestionsDto quesDto, HttpSession session, Integer ques_num, SearchCondition sc, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		quesDto.setQues_writer(writer);
		
		try {
			
			if(quesDto.getQues_content()=="") {
				m.addAttribute("msg", "nocontent");
				return "questionsAndAnswers/write_question";
				}
			
			if(quesDto.getQues_title()=="") {
				m.addAttribute("msg", "notitle");
				return "questionsAndAnswers/write_question";
				}
			int rowCnt = quesService.q_write(quesDto);
			if(rowCnt!=1) throw new Exception("write error");
			redatt.addFlashAttribute("msg", "write_ok");
			return "redirect:/myPage/myPage_questions";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "questionsAndAnswers/write_question";
	}
	
	@GetMapping("/write_answer")
	public String write_answer1(HttpServletRequest request,Model m, Integer ques_num, SearchCondition sc, String ques_title) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			m.addAttribute("ques_title", ques_title);
			m.addAttribute("ques_num", ques_num);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "questionsAndAnswers/manage_write_answer";
	}
	
	@PostMapping("/write_answer")
	public String write_answer2(HttpServletRequest request,Model m, Integer ans_num, SearchCondition sc, AnswerDto ansDto, HttpSession session, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		ansDto.setAns_writer(writer);
		
		try {
			if(ansDto.getAns_title()!="") {
			int rowCnt = quesService.a_write(ansDto);
			int upansbool = quesService.q_ansBool(ans_num);
			if(rowCnt!=1) throw new Exception("write error");
			redatt.addFlashAttribute("msg", "write_ok");
			return "redirect:/myPage/manager_questions";
			}
			m.addAttribute("msg", "notitle");
			return "questionsAndAnswers/manage_write_answer";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "questionsAndAnswers/manage_write_answer";
	}
	
	@GetMapping("/read_answer")
	public String read_answer(HttpServletRequest request, SearchCondition sc, HttpSession session, Model m, Integer ans_num) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			AnswerDto ansDto = quesService.a_read(ans_num);
			
			m.addAttribute("ansDto", ansDto);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:/myPage/myPage_questions"+sc.getQueryString();
		}
		
		return "questionsAndAnswers/read_answer";
	}
	
	@GetMapping("/manage_read_answer")
	public String read_manage_answer(HttpServletRequest request, SearchCondition sc, HttpSession session, Model m, Integer ans_num) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			AnswerDto ansDto = quesService.a_read(ans_num);
			
			m.addAttribute("ansDto", ansDto);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:/myPage/manage_questions"+sc.getQueryString();
		}
		
		return "questionsAndAnswers/manage_read_answer";
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