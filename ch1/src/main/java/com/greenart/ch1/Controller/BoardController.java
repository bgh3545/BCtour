package com.greenart.ch1.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greenart.ch1.Board.BoardDao;
import com.greenart.ch1.Board.BoardDto;
import com.greenart.ch1.Board.BoardService;
import com.greenart.ch1.Community.CommunityDao;
import com.greenart.ch1.Community.CommunityDto;
import com.greenart.ch1.Community.CommunityService;
import com.greenart.ch1.Community.comm_commentDao;
import com.greenart.ch1.Community.comm_commentDto;
import com.greenart.ch1.Community.comm_commentService;
import com.greenart.ch1.PageHandlerAndSearchCondition.CommPageHandler;
import com.greenart.ch1.PageHandlerAndSearchCondition.CommSearchCondition;
import com.greenart.ch1.PageHandlerAndSearchCondition.PageHandler;
import com.greenart.ch1.PageHandlerAndSearchCondition.ProductSearchCondition;
import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;
import com.greenart.ch1.Product.ProductDao;
import com.greenart.ch1.Product.ProductDto;
import com.greenart.ch1.Product.ProductService;
import com.greenart.ch1.Recommend.RecommendDao;
import com.greenart.ch1.Recommend.RecommendDto;
import com.greenart.ch1.Recommend.RecommendService;
import com.greenart.ch1.Recommend.rec_commentDao;
import com.greenart.ch1.Recommend.rec_commentDto;
import com.greenart.ch1.Recommend.rec_commentService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardDao boardDao;
	@Autowired
	BoardService boardService;
	@Autowired
	CommunityDao commDao;
	@Autowired
	CommunityService commService;
	@Autowired
	comm_commentDao comm_commDao;
	@Autowired
	comm_commentService comm_commService;
	@Autowired
	RecommendDao recDao;
	@Autowired
	RecommendService recService;
	@Autowired
	rec_commentDao rec_commDao;
	@Autowired
	rec_commentService rec_commService;
	@Autowired
	ProductDao productDao;
	@Autowired
	ProductService productService;
	
	@GetMapping("/list_1")
	public String list_1(HttpServletRequest request,SearchCondition sc, Model m,String keyword) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			if(keyword == null) {
				keyword = "";
			}
			sc.setKeyword(keyword);
			int totalCnt = boardService.getSearchResultCnt(sc);
			PageHandler pageHandler = new PageHandler(totalCnt, sc);
			
			Map map = new HashMap();
			map.put("offset", sc.getOffset());
			map.put("pageSize", sc.getPageSize());
			
			List<BoardDto> board = boardService.getSearchResultPage(sc);
			List<BoardDto> notice = boardService.getNotice(map);
			m.addAttribute("notice", notice);
			m.addAttribute("board",board);
			m.addAttribute("ph", pageHandler);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
			
			Date now = new Date();
			m.addAttribute("now",now);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "notice/board_1";
	}
	
	@GetMapping("/write_1")
	public String write_1(HttpServletRequest request,Model m, Integer bno, SearchCondition sc) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			BoardDto boardDto = boardService.read(bno);
			m.addAttribute("boardDto", boardDto);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "notice/write_1";
	}
	
	@PostMapping("/write_1")
	public String write_1(HttpServletRequest request,Model m, BoardDto boardDto, HttpSession session, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		boardDto.setWriter(writer);
		
		try {
			if(boardDto.getTitle() =="") {
				m.addAttribute("msg", "notitle");
				return "write_1";
			}
			if(boardDto.getContent() =="") {
				m.addAttribute("msg", "nocontent");
				return "write_1";
			}
			int rowCnt = boardService.writer(boardDto);
			if(rowCnt!=1) throw new Exception("write error");
			redatt.addFlashAttribute("msg", "write_ok");
			return "redirect:/board/list_1";
		}catch(Exception e) {
			e.printStackTrace();
			m.addAttribute("boardDto", boardDto);
			m.addAttribute("msg", "write_error");
			
			return "notice/write_1";
		}
	}
	
	@GetMapping("/read_1")
	public String read_1(HttpServletRequest request,Model m, Integer bno, SearchCondition sc) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			BoardDto boardDto = boardService.read(bno);
			m.addAttribute("boardDto", boardDto);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:/board/list_1"+sc.getQueryString();
		}
		return "notice/read_1";
	}
	
	@PostMapping("/remove_1")
	public String remove_1(HttpServletRequest request,Model m, Integer bno, SearchCondition sc, HttpSession session, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		
		try {
			String writer;
			if(session.getAttribute("id").equals("admin")) {
				BoardDto boardDto = boardService.read(bno);
				writer = boardDto.getWriter();
			}
			else {
				writer = (String)session.getAttribute("id");
			}
			int rowCnt= boardService.remove(bno, writer);
			if(rowCnt==1) {
				redatt.addFlashAttribute("msg", "del");
				return "redirect:/board/list_1"+sc.getQueryString();
			}
			else {
				throw new Exception("board remove error");
			}
		}catch(Exception e){
			e.printStackTrace();
			redatt.addFlashAttribute("msg", "error");
		}
		return "redirect:/board/list_1"+sc.getQueryString();
	}
	
	@GetMapping("/modify_1")
	public String modify_1(HttpServletRequest request,Integer bno,SearchCondition sc, Model m) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		BoardDto modi = boardDao.select(bno);
		m.addAttribute("modi", modi);
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		return "notice/modify_1";
	}
	
	@PostMapping("/modify_1") 
	public String modify_1(HttpServletRequest request,Model m, BoardDto boardDto, HttpSession session, SearchCondition sc, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		boardDto.setWriter(writer);
		
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		
		try {
			int rowCnt = boardService.modify(boardDto);
			if(rowCnt!=1) throw new Exception("modify error");
			redatt.addFlashAttribute("msg", "modify_ok");
			return "redirect:/board/list_1"+sc.getQueryString();
		}catch(Exception e){
			e.printStackTrace();
			m.addAttribute("boardDto", boardDto);
			m.addAttribute("msg", "modify_error");
			
			return "notice/read_1";
		}
	}
	
	@GetMapping("/list_2")
	public String list_2(HttpServletRequest request, SearchCondition sc, Model m, RecommendDto recDto) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			
			int totalCnt = recService.r_getSearchResultCnt(sc);
			PageHandler pageHandler = new PageHandler(totalCnt, sc);
			
			Map map = new HashMap();
			map.put("offset", sc.getOffset());
			map.put("pageSize", sc.getPageSize());
			
			List<RecommendDto> rec = recService.r_getSearchResultPage(sc);
			List<BoardDto> notice = boardService.getNotice(map);
			
			m.addAttribute("addr", "list_2");
			m.addAttribute("notice", notice);
			m.addAttribute("rec",rec);
			m.addAttribute("ph", pageHandler);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
			
			Date now = new Date();
			m.addAttribute("now",now);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "recommend/board_2";
	}
	
	@GetMapping("/rec10")
	public String rec10(HttpServletRequest request, SearchCondition sc, Model m, RecommendDto recDto, String keyword) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			if(keyword == null) {
				keyword="";
			}
			sc.setKeyword(keyword);
			
			int totalCnt = recService.r_getSearchResultRecommendCnt(sc);
			PageHandler pageHandler = new PageHandler(totalCnt, sc);
			
			Map map = new HashMap();
			map.put("offset", sc.getOffset());
			map.put("pageSize", sc.getPageSize());
			
			List<RecommendDto> rec = recService.r_getSearchResultRecommendPage(sc);
			List<BoardDto> notice = boardService.getNotice(map);
			
			m.addAttribute("addr", "rec10");
			m.addAttribute("notice", notice);
			m.addAttribute("rec",rec);
			m.addAttribute("ph", pageHandler);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
			
			Date now = new Date();
			m.addAttribute("now",now);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "recommend/board_2";
	}
	
	@GetMapping("/write_2")
	public String write_2(HttpServletRequest request,Model m, Integer rec_num, SearchCondition sc, HttpSession session, Integer pd_num) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			ProductDto productDto = productDao.select(pd_num);
			
			m.addAttribute("list", productDto);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "recommend/write_2";
	}
	
	@PostMapping("/write_2")
	public String write_2(HttpServletRequest request,Model m, RecommendDto recDto, HttpSession session, RedirectAttributes redatt, Integer pd_scorePoint, Integer pd_num) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		recDto.setRec_writer(writer);
		
		try {
			if(recDto.getRec_title()=="") {
				m.addAttribute("msg", "notitle");
				return "write_2";
			}
			if(recDto.getRec_content()=="") {
				m.addAttribute("msg", "nocontent");
				return "recommend/write_2";
			}
			int rowCnt = recService.r_writer(recDto);
			int setScore = productService.setScore(pd_num, writer, pd_scorePoint);
			int addScore = productService.addScore(pd_num, pd_scorePoint);
			if(rowCnt!=1) throw new Exception("write error");
			redatt.addFlashAttribute("msg", "write_ok");
			return "redirect:/board/list_2";
		}catch(Exception e) {
			e.printStackTrace();
			m.addAttribute("recDto", recDto);
			m.addAttribute("msg", "write_error");
			
			return "recommend/write_2";
		}
	}
	
	@PostMapping("/read_2")
	public String post_read_2(HttpServletRequest request,Model m,rec_commentDto rec_commDto, Integer rec_num, SearchCondition sc, CommSearchCondition csc, HttpSession session) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			
			String rec_writer = (String)session.getAttribute("id");
			rec_commDto.setRec_comm_writer(rec_writer);
			
			
			int commCnt = recService.r_increaseCommCnt(rec_num);
			int rowCnt = rec_commService.rm_write(rec_commDto);
			
			Date now = new Date();
			m.addAttribute("now",now);
			
			List<rec_commentDto> r_comment = rec_commService.rm_getList(rec_num,csc);
			RecommendDto recDto = recService.r_read(rec_num);
			
			m.addAttribute("r_comment",r_comment);
			m.addAttribute("recDto", recDto);
			m.addAttribute("commPage", csc.getCommPage());
			m.addAttribute("commPageSize", csc.getCommPageSize());
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "recommend/read_2";
	}
	
	@GetMapping("/read_2")
	public String get_read_2(HttpServletRequest request, Model m, rec_commentDto rec_commDto,RecommendDto recDto, Integer rec_comm_num, Integer rec_num, ProductSearchCondition psc, SearchCondition sc, CommSearchCondition csc, HttpSession session) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String mem_id= (String)session.getAttribute("id");
		recDto.setMem_id(mem_id);
		try {
			RecommendDto recBoolDto = recService.rb_read(rec_num, mem_id);
			if(recBoolDto==null) {
				int recBoolCnt = recService.rb_write(recDto);
			}
			
			Date now = new Date();
			m.addAttribute("now",now);
			
			int totalCnt = rec_commService.rm_getCount(rec_num);
			CommPageHandler cPageHandler = new CommPageHandler(totalCnt,csc);
			m.addAttribute("cph", cPageHandler);
			
			List<rec_commentDto> r_comment = rec_commService.rm_getList(rec_num, csc);
			recDto = recService.r_read(rec_num);
			
			ProductDto productDto = productDao.select(recDto.getPd_num());
			m.addAttribute("list", productDto);
			
			m.addAttribute("r_comment",r_comment);
			m.addAttribute("recDto", recDto);
			m.addAttribute("commPage", csc.getCommPage());
			m.addAttribute("commPageSize", csc.getCommPageSize());
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "recommend/read_2";
	}
	
	@PostMapping("/remove_2")
	public String remove_2(HttpServletRequest request,Model m, Integer rec_num, SearchCondition sc, HttpSession session, RedirectAttributes redatt, Integer pd_num) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		
		try {
			String writer;
			if(session.getAttribute("id").equals("admin")) {
				RecommendDto recDto = recService.r_read(rec_num);
				writer = recDto.getRec_writer();
			}
			else {
				writer = (String)session.getAttribute("id");
			}
			ProductDto proDto = productService.pd_scoreSelect(pd_num, writer);
			int deleteScore = productService.deleteScore(pd_num, proDto.getPd_scorePoint());
			int rowCnt= recService.r_remove(rec_num, writer);
			if(rowCnt==1) {
				redatt.addFlashAttribute("msg", "del");
				return "redirect:/board/list_2";
			}
			else {
				throw new Exception("board remove error");
			}
		}catch(Exception e){
			e.printStackTrace();
			redatt.addFlashAttribute("msg", "error");
		}
		return "redirect:/board/list_2";
	}
	
	@GetMapping("/removecomm_2")
	public String removecomm_2(HttpServletRequest request,Model m,Integer rec_comm_num, Integer rec_num, SearchCondition sc, HttpSession session, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			String writer;
			if(session.getAttribute("id").equals("admin")) {
				rec_commentDto rec_commDto = rec_commService.rm_read(rec_comm_num);
				writer = rec_commDto.getRec_comm_writer();
			}
			else {
				writer = (String)session.getAttribute("id");
			}
			
			int commCnt = recService.r_decreaseCommCnt(rec_num);
			int rowCnt= rec_commService.rm_remove(rec_comm_num, writer);
			
			if(rowCnt==1) {
				redatt.addFlashAttribute("msg", "del");
			}
			
			else {
				throw new Exception("comment remove error");
			}
		}catch(Exception e){
			e.printStackTrace();
			redatt.addFlashAttribute("msg", "error");
		}
		return "redirect:/board/read_2?rec_num="+rec_num+"&page="+sc.getPage()+"&pageSize="+sc.getPageSize();
	}
	
	@GetMapping("/modify_2")
	public String modify_2(HttpServletRequest request,Integer rec_num,Integer pd_num, SearchCondition sc, Model m, HttpSession session) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		String id = (String)session.getAttribute("id");
		RecommendDto modi = recDao.r_selectReview(pd_num,id);
		ProductDto productDto = productDao.select(pd_num);
		ProductDto proDto = productService.pd_scoreSelect(pd_num, id);
		
		m.addAttribute("score", proDto);
		m.addAttribute("list", productDto);
		m.addAttribute("modi", modi);
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		return "recommend/modify_2";
	}
	
	@PostMapping("/modify_2")
	public String modify_2(HttpServletRequest request,Model m,Integer rec_num, RecommendDto recDto, HttpSession session, SearchCondition sc, RedirectAttributes redatt, Integer pd_num, Integer pd_scorePoint) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		recDto.setRec_writer(writer);
		
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		
		try {
			ProductDto proDto = productService.pd_scoreSelect(pd_num, writer);
			int deleteScore = productService.deleteScore(pd_num, proDto.getPd_scorePoint());
			int setScore = productService.setScore(pd_num, writer, pd_scorePoint);
			int addScore = productService.addScore(pd_num, pd_scorePoint);
			int rowCnt = recService.r_modify(recDto);
			if(rowCnt!=1) throw new Exception("modify error");
			redatt.addFlashAttribute("msg", "modify_ok");
			return "redirect:/board/read_2?rec_num="+rec_num+"&page="+sc.getPage()+"&pageSize="+sc.getPageSize();
		}catch(Exception e){
			e.printStackTrace();
			m.addAttribute("recDto", recDto);
			m.addAttribute("msg", "modify_error");
			
			return "recommend/read_2";
		}
	}
	
	@GetMapping("/modifycomm_2")
	public String modifycomm_2(HttpServletRequest request,Model m,Integer rec_num, Integer rec_comm_num, rec_commentDto rec_commDto, HttpSession session, SearchCondition sc, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		rec_commDto.setRec_comm_writer(writer);
		m.addAttribute("cNum", rec_comm_num);
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		
		try {
			rec_commentDto commModi = rec_commDao.rm_select(rec_comm_num);
			m.addAttribute("commModi", commModi);
			
			return "redirect:/board/read_2?rec_num="+rec_num+"&page="+sc.getPage()+"&pageSize="+sc.getPageSize()+"&cNum="+rec_comm_num;
		}catch(Exception e){
			e.printStackTrace();
			
			return "recommend/read_2";
		}
	}
	
	@PostMapping("/modify2comm_2")
	public String modify2comm_2(HttpServletRequest request,Model m,Integer rec_num, Integer rec_comm_num, rec_commentDto rec_commDto, HttpSession session, SearchCondition sc, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		rec_commDto.setRec_comm_writer(writer);
		
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		
		try {
			int rowCnt = rec_commService.rm_modify(rec_commDto);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return "redirect:/board/read_2?rec_num="+rec_num+"&page="+sc.getPage()+"&pageSize="+sc.getPageSize();
	}
	
	@PostMapping("/recBtn_2")
	public String incRec_2(HttpServletRequest request,RecommendDto recDto, Integer rec_num, SearchCondition sc, Model m, HttpSession session) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String recommender = (String)session.getAttribute("id");
		recDto.setMem_id(recommender);
		
		RecommendDto recBool = recService.rb_read(rec_num, recommender);
		
		if(recBool.getRecbool()==0) {
		int recCnt = recService.r_increaseRecCnt(rec_num);
		int recBoolCnt = recService.r_increaseRecBool(rec_num, recommender);
		}
		if(recBool.getRecbool()==1) {
		int recCnt = recService.r_decreaseRecCnt(rec_num);
		int recBoolCnt = recService.r_decreaseRecBool(rec_num, recommender);
		}
		return "redirect:/board/read_2?rec_num="+rec_num+"&page="+sc.getPage()+"&pageSize="+sc.getPageSize();
	}
	
	@GetMapping("/list_3")
	public String list_3(HttpServletRequest request, SearchCondition sc, Model m, CommunityDto commDto, String keyword) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			if(keyword == null) {
				keyword = "";
			}
			sc.setKeyword(keyword);
			
			int totalCnt = commService.c_getSearchResultCnt(sc);
			PageHandler pageHandler = new PageHandler(totalCnt, sc);
			
			Map map = new HashMap();
			map.put("offset", sc.getOffset());
			map.put("pageSize", sc.getPageSize());
			
			List<CommunityDto> comm = commService.c_getSearchResultPage(sc);
			List<BoardDto> notice = boardService.getNotice(map);
			
			m.addAttribute("notice", notice);
			m.addAttribute("comm",comm);
			m.addAttribute("ph", pageHandler);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
			
			Date now = new Date();
			m.addAttribute("now",now);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "community/board_3";
	}
	
	@GetMapping("/write_3")
	public String write_3(HttpServletRequest request,Model m, Integer comm_num, SearchCondition sc) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			CommunityDto commDto = commService.c_read(comm_num);
			m.addAttribute("commDto", commDto);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "community/write_3";
	}
	
	@PostMapping("/write_3")
	public String write_3(HttpServletRequest request,Model m, CommunityDto commDto, HttpSession session, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		commDto.setComm_writer(writer);
		
		try {
			if(commDto.getComm_title() == "") {
				m.addAttribute("msg", "notitle");
				return "community/write_3";
			}
			if(commDto.getComm_content() == "") {
				m.addAttribute("msg", "nocontent");
				return "community/write_3";
			}
			int rowCnt = commService.c_writer(commDto);
			if(rowCnt!=1) throw new Exception("write error");
			redatt.addFlashAttribute("msg", "write_ok");
			return "redirect:/board/list_3";
		}catch(Exception e) {
			e.printStackTrace();
			m.addAttribute("commDto", commDto);
			m.addAttribute("msg", "write_error");
			
			return "community/write_3";
		}
	}
	
	@PostMapping("/read_3")
	@ResponseBody
	public String post_read_3(HttpServletRequest request,Model m,comm_commentDto comm_commDto, Integer comm_num, SearchCondition sc, HttpSession session) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			
			String comm_writer = (String)session.getAttribute("id");
			comm_commDto.setComm_comm_writer(comm_writer);
			
			
			int commCnt = commService.c_increaseCommCnt(comm_num);
			int rowCnt = comm_commService.cm_write(comm_commDto);
			
			Date now = new Date();
			m.addAttribute("now",now);
			
			List<comm_commentDto> c_comment = comm_commService.cm_getList(comm_num);
			CommunityDto commDto = commService.c_read(comm_num);
			
			m.addAttribute("c_comment",c_comment);
			m.addAttribute("commDto", commDto);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "ok";
	}
	
	@GetMapping("/read_3")
	public String get_read_3(HttpServletRequest request,Model m,comm_commentDto comm_commDto,Integer comm_comm_num, Integer comm_num, SearchCondition sc, HttpSession session) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			
			Date now = new Date();
			m.addAttribute("now",now);
			
			List<comm_commentDto> c_comment = comm_commService.cm_getList(comm_num);
			CommunityDto commDto = commService.c_read(comm_num);
			
			m.addAttribute("c_comment",c_comment);
			m.addAttribute("commDto", commDto);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "community/read_3";
	}
	
	@PostMapping("/remove_3")
	public String remove_3(HttpServletRequest request,Model m, Integer comm_num, SearchCondition sc, HttpSession session, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		
		try {
			String writer;
			if(session.getAttribute("id").equals("admin")) {
				CommunityDto commDto = commService.c_read(comm_num);
				writer = commDto.getComm_writer();
			}
			else {
				writer = (String)session.getAttribute("id");
			}
			
			int rowCnt= commService.c_remove(comm_num, writer);
			if(rowCnt==1) {
				redatt.addFlashAttribute("msg", "del");
				return "redirect:/board/list_3";
			}
			else {
				throw new Exception("board remove error");
			}
		}catch(Exception e){
			e.printStackTrace();
			redatt.addFlashAttribute("msg", "error");
		}
		return "redirect:/board/list_3";
	}
	
	@GetMapping("/removecomm_3")
	public String removecomm_3(HttpServletRequest request,Model m,Integer comm_comm_num, Integer comm_num, SearchCondition sc, HttpSession session, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		try {
			String writer;
			if(session.getAttribute("id").equals("admin")) {
				comm_commentDto comm_commDto = comm_commService.cm_read(comm_comm_num);
				writer = comm_commDto.getComm_comm_writer();
			}
			else {
				writer = (String)session.getAttribute("id");
			}
			
			int commCnt = commService.c_decreaseCommCnt(comm_num);
			int rowCnt= comm_commService.cm_remove(comm_comm_num, writer);
			
			if(rowCnt==1) {
				redatt.addFlashAttribute("msg", "del");
			}
			
			else {
				throw new Exception("comment remove error");
			}
		}catch(Exception e){
			e.printStackTrace();
			redatt.addFlashAttribute("msg", "error");
		}
		return "redirect:/board/read_3?comm_num="+comm_num+"&page="+sc.getPage()+"&pageSize="+sc.getPageSize();
	}
	
	@GetMapping("/modify_3")
	public String modify_3(HttpServletRequest request,Integer comm_num, SearchCondition sc, Model m) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		CommunityDto modi = commDao.c_select(comm_num);
		m.addAttribute("modi", modi);
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		return "community/modify_3";
	}
	
	@PostMapping("/modify_3")
	public String modify_3(HttpServletRequest request,Model m,Integer comm_num, CommunityDto commDto, HttpSession session, SearchCondition sc, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		commDto.setComm_writer(writer);
		
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		
		try {
			int rowCnt = commService.c_modify(commDto);
			if(rowCnt!=1) throw new Exception("modify error");
			redatt.addFlashAttribute("msg", "modify_ok");
			return "redirect:/board/read_3?comm_num="+comm_num+"&page="+sc.getPage()+"&pageSize="+sc.getPageSize();
		}catch(Exception e){
			e.printStackTrace();
			m.addAttribute("commDto", commDto);
			m.addAttribute("msg", "modify_error");
			
			return "redirect:/board/read_3?comm_num="+comm_num+"&page="+sc.getPage()+"&pageSize="+sc.getPageSize();
		}
	}
	
	@GetMapping("/modifycomm_3")
	public String modifycomm_3(HttpServletRequest request,Model m,Integer comm_num, Integer comm_comm_num, comm_commentDto comm_commDto, HttpSession session, SearchCondition sc, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		comm_commDto.setComm_comm_writer(writer);
		m.addAttribute("cNum", comm_comm_num);
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		
		try {
			comm_commentDto commModi = comm_commDao.cm_select(comm_comm_num);
			m.addAttribute("commModi", commModi);
			
			return "redirect:/board/read_3?comm_num="+comm_num+"&page="+sc.getPage()+"&pageSize="+sc.getPageSize()+"&cNum="+comm_comm_num;
		}catch(Exception e){
			e.printStackTrace();
			
			return "community/read_3";
		}
	}
	
	@PostMapping("/modify2comm_3")
	public String modify2comm_3(HttpServletRequest request,Model m,Integer comm_num, Integer comm_comm_num, comm_commentDto comm_commDto, HttpSession session, SearchCondition sc, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		comm_commDto.setComm_comm_writer(writer);
		
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		
		try {
			int rowCnt = comm_commService.cm_modify(comm_commDto);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return "redirect:/board/read_3?comm_num="+comm_num+"&page="+sc.getPage()+"&pageSize="+sc.getPageSize();
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
