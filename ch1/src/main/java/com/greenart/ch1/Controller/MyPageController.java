package com.greenart.ch1.Controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greenart.ch1.PageHandlerAndSearchCondition.PageHandler;
import com.greenart.ch1.PageHandlerAndSearchCondition.ProductPageHandler;
import com.greenart.ch1.PageHandlerAndSearchCondition.ProductSearchCondition;
import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;
import com.greenart.ch1.Product.ProductDao;
import com.greenart.ch1.Product.ProductService;
import com.greenart.ch1.QuestionsAndAnswers.AnswerDto;
import com.greenart.ch1.QuestionsAndAnswers.QuestionsDao;
import com.greenart.ch1.QuestionsAndAnswers.QuestionsDto;
import com.greenart.ch1.QuestionsAndAnswers.QuestionsService;
import com.greenart.ch1.Reservation.ReservationDao;
import com.greenart.ch1.Reservation.ReservationDto;
import com.greenart.ch1.Reservation.ReservationService;
import com.greenart.ch1.User.BCUserDao;
import com.greenart.ch1.User.BCUserDto;
import com.greenart.ch1.WishList.WishDao;
import com.greenart.ch1.WishList.WishDto;
import com.greenart.ch1.WishList.WishService;

@Controller
@RequestMapping("/myPage")
public class MyPageController {

	@Autowired
	QuestionsDao quesDao;
	@Autowired
	QuestionsService quesService;
	@Autowired
	BCUserDao userDao;
	@Autowired
	WishService wishService;
	@Autowired
	WishDao wishDao;
	@Autowired
	ReservationDao reservationDao;
	@Autowired
	ReservationService reservationService;
	@Autowired
	ProductDao productDao;
	@Autowired
	ProductService productService;
	
	@GetMapping("/myPage_main")
	public String myPage_main(HttpServletRequest request, HttpSession session, Model m) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		int quesCnt = quesService.q_getCount(writer);
		int wishCnt = wishService.w_getCount(writer);
		int reservationCnt = reservationService.res_count(writer);
		
		m.addAttribute("quesCnt", quesCnt);
		m.addAttribute("wishCnt", wishCnt);
		m.addAttribute("reservationCnt", reservationCnt);
		
		return "myPageMain/myPage_main";
	}
	
	@PostMapping("/myPage_pwdCheck")
	public String myPage_pwdCheck(HttpServletRequest request, HttpSession session, BCUserDto userDto, Model m) throws Exception {
		String id = (String)session.getAttribute("id");
		String pwd = userDto.getPwd();
		
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
	
	@GetMapping("/myPage_personalInfo")
	public String myPage_personalInfo1(HttpServletRequest request, HttpSession session, Model m) throws Exception {
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
	
	@GetMapping("/manage")
	public String myPage_manage(HttpServletRequest request, HttpSession session, Model m) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		int quesCnt = quesService.q_getCount(writer);
		
		m.addAttribute("quesCnt", quesCnt);
		
		return "personalInfo/manage_pwdCheck";
	}
	
	@PostMapping("/manage_pwdCheck")
	public String manage_pwdCheck(HttpServletRequest request, HttpSession session,String pwd, Model m) throws Exception {
		
		String id = (String)session.getAttribute("id"); // admin id
		
		if(!pwdCheck(pwd, id)) {
			String msg= "비밀번호가 틀렸습니다. 다시 입력해주세요";
			m.addAttribute("msg", msg);
			return "personalInfo/manage_pwdCheck";
		}
		
		List<BCUserDto> userAll = userDao.selectAll();
		m.addAttribute("userAll", userAll);
		
		return "personalInfo/manage_managerInfo";
	}
	
	@GetMapping("/manage_managerInfo")
	public String manage_managerInfo1(HttpServletRequest request, HttpSession session, Model m) throws Exception {
		if(!loginCheck(request)) {
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		}
		return "personalInfo/manage_pwdCheck";
	}
	
	@PostMapping("/manage_managerInfoDel")
	@ResponseBody
	public String manage_managerInfoDel(HttpServletRequest request, HttpSession session,@RequestBody BCUserDto user, Model m) throws Exception {
		if(!loginCheck(request)) {
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		}
		
		userDao.deleteUser(user.getId(), user.getPwd());
		
		return "adminUserInfoDel";
	}
	
	@GetMapping("/myPage_reservation")
	public String myPage_reservation(HttpServletRequest request, SearchCondition sc, HttpSession session, Model m) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String mem_id = (String)session.getAttribute("id");

		List<ReservationDto> reslist = reservationService.res_reservationSelect(mem_id, sc);
		
		int totalCnt = reservationService.res_count(mem_id);
		PageHandler ph = new PageHandler(totalCnt,sc);
		
		m.addAttribute("reslist", reslist);
		m.addAttribute("page", sc.getPage());
		m.addAttribute("ph", ph);
		
		
		return "reservation/myPage_reservation";
	}
	
	@GetMapping("/cancle_request")
	public String cancle_request(HttpServletRequest request, Integer pd_num, HttpSession session) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		String mem_id = (String)session.getAttribute("id");
		int cancleRequest = reservationService.res_deleteRequest(mem_id, pd_num);
		return "redirect:/myPage/myPage_reservation";
	}
	
	@GetMapping("/manage_reservation")
	public String manage_reservation(HttpServletRequest request, SearchCondition sc, Model m) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		try {
			List<ReservationDto> mrlist = reservationService.res_reservationSelectManage(sc);
			
			int totalCnt = reservationService.res_countManage();
			PageHandler ph = new PageHandler(totalCnt, sc);
			
			m.addAttribute("mrlist", mrlist);
			m.addAttribute("ph", ph);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("reservationFilter","manage_reservation");
			m.addAttribute("wait_btn","reservation_confirm");
			m.addAttribute("cancle_btn","reservation_cancle");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "reservation/manage_reservation";
	}
	
	@GetMapping("/reservation_confirm")
	public String reservation_confirm(HttpServletRequest request, Integer pd_num, HttpSession session,String mem_id) throws Exception {
		if(!loginCheck(request))
			return "redirect:/";
		int reservationConfirm = reservationService.res_reservation(mem_id, pd_num);
		int increaseBuyCnt = productService.increaseBuyCnt(pd_num);
		return "redirect:/myPage/manage_reservation";
	}
	
	@GetMapping("/reservation_cancle")
	public String reservation_cancle(HttpServletRequest request, Integer pd_num, HttpSession session,String mem_id) throws Exception {
		if(!loginCheck(request))
			return "redirect:/";
		int reservationCancle = reservationService.res_delete(mem_id, pd_num);
		int decreaseBuyCnt = productService.decreaseBuyCnt(pd_num);
		return "redirect:/myPage/manage_reservation";
	}
	
	@GetMapping("/wait_reservation_confirm")
	public String wait_reservation_confirm(HttpServletRequest request, Integer pd_num, HttpSession session,String mem_id) throws Exception {
		if(!loginCheck(request))
			return "redirect:/";
		int reservationConfirm = reservationService.res_reservation(mem_id, pd_num);
		int increaseBuyCnt = productService.increaseBuyCnt(pd_num);
		return "redirect:/myPage/reservationWait";
	}
	
	@GetMapping("/wait_reservation_cancle")
	public String wait_reservation_cancle(HttpServletRequest request, Integer pd_num, HttpSession session,String mem_id) throws Exception {
		if(!loginCheck(request))
			return "redirect:/";
		int reservationCancle = reservationService.res_delete(mem_id, pd_num);
		int decreaseBuyCnt = productService.decreaseBuyCnt(pd_num);
		return "redirect:/myPage/reservationWait";
	}
	
	@GetMapping("/cancle_reservation_confirm")
	public String cancle_reservation_confirm(HttpServletRequest request, Integer pd_num, HttpSession session,String mem_id) throws Exception {
		if(!loginCheck(request))
			return "redirect:/";
		int reservationConfirm = reservationService.res_reservation(mem_id, pd_num);
		int increaseBuyCnt = productService.increaseBuyCnt(pd_num);
		return "redirect:/myPage/cancleRequest";
	}
	
	@GetMapping("/cancle_reservation_cancle")
	public String cancle_reservation_cancle(HttpServletRequest request, Integer pd_num, HttpSession session,String mem_id) throws Exception {
		if(!loginCheck(request))
			return "redirect:/";
		int reservationCancle = reservationService.res_delete(mem_id, pd_num);
		int decreaseBuyCnt = productService.decreaseBuyCnt(pd_num);
		return "redirect:/myPage/cancleRequest";
	}
	
	@GetMapping("/reservationWait")
	public String reservationWait(HttpServletRequest request, Model m, HttpSession session, SearchCondition sc) throws Exception {
		if(!loginCheck(request))
			return "redirect:/";
		List<ReservationDto> mrlist = reservationService.res_reservationRequestManage(sc);
		
		int totalCnt = reservationService.res_countReservationRequest();
		PageHandler ph = new PageHandler(totalCnt, sc);
		
		m.addAttribute("mrlist", mrlist);
		m.addAttribute("ph", ph);
		m.addAttribute("page", sc.getPage());
		m.addAttribute("reservationFilter","reservationWait");
		m.addAttribute("wait_btn","wait_reservation_confirm");
		m.addAttribute("cancle_btn","wait_reservation_cancle");
		return "reservation/manage_reservation";
	}
	
	@GetMapping("/cancleRequest")
	public String reservationCancle(HttpServletRequest request, Model m, HttpSession session, SearchCondition sc) throws Exception {
		if(!loginCheck(request))
			return "redirect:/";
		List<ReservationDto> mrlist = reservationService.res_reservationCancleRequestManage(sc);
		
		int totalCnt = reservationService.res_countCancleRequest();
		PageHandler ph = new PageHandler(totalCnt, sc);
		
		m.addAttribute("mrlist", mrlist);
		m.addAttribute("ph", ph);
		m.addAttribute("page", sc.getPage());
		m.addAttribute("reservationFilter","cancleRequest");
		m.addAttribute("wait_btn","cancle_reservation_confirm");
		m.addAttribute("cancle_btn","cancle_reservation_cancle");
		return "reservation/manage_reservation";
	}
	
	@GetMapping("/myPage_wishList")
	public String myPage_wishList(HttpServletRequest request,HttpSession session,ProductSearchCondition psc, Model m) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String id = (String)session.getAttribute("id");
		List<WishDto> wish = wishService.w_getWishPage(id, psc);
		System.out.println("#####################" + wish);
		
		int totalCnt = wishService.w_getCount(id);
		
		ProductPageHandler pph = new ProductPageHandler(totalCnt,psc);
		
		m.addAttribute("seoulList", wish);
		m.addAttribute("ph", pph);
		
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
			return "redirect:/myPage/manage_questions";
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
	
	private boolean managerCheck(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("id")=="admin") {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean pwdCheck(String pwd, String id) throws Exception {
		BCUserDto user = userDao.selectUser(id);
		return user.getPwd().equals(pwd);
	}
	
}