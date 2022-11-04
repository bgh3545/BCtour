package com.greenart.ch1;

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
	
	@GetMapping("/list_v1_1")
	public String list_v1_1(HttpServletRequest request,SearchCondition sc, Model m) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		try {
			int totalCnt = boardService.getCount();
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
		return "board_v1_1";
	}
	
	@GetMapping("/write_1")
	public String write_1(HttpServletRequest request,Model m, Integer bno, SearchCondition sc) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		try {
			BoardDto boardDto = boardService.read(bno);
			m.addAttribute("boardDto", boardDto);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "write_1";
	}
	
	@GetMapping("/read_1")
	public String read_1(HttpServletRequest request,Model m, Integer bno, SearchCondition sc) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		try {
			BoardDto boardDto = boardService.read(bno);
			m.addAttribute("boardDto", boardDto);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:/board/list_v1_1"+sc.getQueryString();
		}
		return "read_1";
	}
	
	@PostMapping("/remove_1")
	public String remove_1(HttpServletRequest request,Model m, Integer bno, SearchCondition sc, HttpSession session, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		
		try {
			String writer = (String)session.getAttribute("id");
			int rowCnt= boardService.remove(bno, writer);
			if(rowCnt==1) {
				redatt.addFlashAttribute("msg", "del");
				return "redirect:/board/list_v1_1"+sc.getQueryString();
			}
			else {
				throw new Exception("board remove error");
			}
		}catch(Exception e){
			e.printStackTrace();
			redatt.addFlashAttribute("msg", "error");
		}
		return "redirect:/board/list_v1_1"+sc.getQueryString();
	}
	
	@PostMapping("/write_1")
	public String write_1(HttpServletRequest request,Model m, BoardDto boardDto, HttpSession session, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		boardDto.setWriter(writer);
		
		try {
			int rowCnt = boardService.writer(boardDto);
			if(rowCnt!=1) throw new Exception("write error");
			redatt.addFlashAttribute("msg", "write_ok");
			return "redirect:/board/list_v1_1";
		}catch(Exception e) {
			e.printStackTrace();
			m.addAttribute("boardDto", boardDto);
			m.addAttribute("msg", "write_error");
			
			return "write_1";
		}
	}
	
	@GetMapping("/modify_1")
	public String modify_1(HttpServletRequest request,Integer bno,SearchCondition sc, Model m) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		BoardDto modi = boardDao.select(bno);
		m.addAttribute("modi", modi);
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		return "modify_1";
	}
	
	@PostMapping("/modify_1") 
	public String modify_1(HttpServletRequest request,Model m, BoardDto boardDto, HttpSession session, SearchCondition sc, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		boardDto.setWriter(writer);
		
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		
		try {
			int rowCnt = boardService.modify(boardDto);
			if(rowCnt!=1) throw new Exception("modify error");
			redatt.addFlashAttribute("msg", "modify_ok");
			return "redirect:/board/list_v1_1?page="+sc.getQueryString();
		}catch(Exception e){
			e.printStackTrace();
			m.addAttribute("boardDto", boardDto);
			m.addAttribute("msg", "modify_error");
			
			return "read_1";
		}
	}
	
	@GetMapping("/list_v1_2")
	public String list_v1_2(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		return "board_v1_2";
	}
	
	@GetMapping("/list_v1_3")
	public String list_v1_3(HttpServletRequest request, SearchCondition sc, Model m, CommunityDto commDto) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		try {
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
		return "board_v1_3";
	}
	
	@GetMapping("/write_3")
	public String write_3(HttpServletRequest request,Model m, Integer comm_num, SearchCondition sc) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		try {
			CommunityDto commDto = commService.c_read(comm_num);
			m.addAttribute("commDto", commDto);
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "write_3";
	}
	
	@PostMapping("/read_3")
	public String post_read_3(HttpServletRequest request,Model m,comm_commentDto comm_commDto, Integer comm_num, SearchCondition sc, HttpSession session) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
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
		return "read_3";
	}
	
	@GetMapping("/read_3")
	public String get_read_3(HttpServletRequest request,Model m,comm_commentDto comm_commDto,Integer comm_comm_num, Integer comm_num, SearchCondition sc, HttpSession session) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
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
		return "read_3";
	}
	
	@PostMapping("/remove_3")
	public String remove_3(HttpServletRequest request,Model m, Integer comm_num, SearchCondition sc, HttpSession session, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		
		try {
			String writer = (String)session.getAttribute("id");
			int rowCnt= commService.c_remove(comm_num, writer);
			if(rowCnt==1) {
				redatt.addFlashAttribute("msg", "del");
				return "redirect:/board/list_v1_3";
			}
			else {
				throw new Exception("board remove error");
			}
		}catch(Exception e){
			e.printStackTrace();
			redatt.addFlashAttribute("msg", "error");
		}
		return "redirect:/board/list_v1_3";
	}
	
	@GetMapping("/removecomm_3")
	public String removecomm_3(HttpServletRequest request,Model m,Integer comm_comm_num, Integer comm_num, SearchCondition sc, HttpSession session, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		try {
			
			int commCnt = commService.c_decreaseCommCnt(comm_num);
			String writer = (String)session.getAttribute("id");
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
	
	@PostMapping("/write_3")
	public String write_3(HttpServletRequest request,Model m, CommunityDto commDto, HttpSession session, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		commDto.setComm_writer(writer);
		
		try {
			int rowCnt = commService.c_writer(commDto);
			if(rowCnt!=1) throw new Exception("write error");
			redatt.addFlashAttribute("msg", "write_ok");
			return "redirect:/board/list_v1_3";
		}catch(Exception e) {
			e.printStackTrace();
			m.addAttribute("commDto", commDto);
			m.addAttribute("msg", "write_error");
			
			return "write_3";
		}
	}
	
	@GetMapping("/modify_3")
	public String modify_3(HttpServletRequest request,Integer comm_num, SearchCondition sc, Model m) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		CommunityDto modi = commDao.c_select(comm_num);
		m.addAttribute("modi", modi);
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		return "modify_3";
	}
	
	@PostMapping("/modify_3")
	public String modify_3(HttpServletRequest request,Model m,Integer comm_num, CommunityDto commDto, HttpSession session, SearchCondition sc, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
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
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
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
			
			return "read_3";
		}
	}
	
	@PostMapping("/modify2comm_3")
	public String modify2comm_3(HttpServletRequest request,Model m,Integer comm_num, Integer comm_comm_num, comm_commentDto comm_commDto, HttpSession session, SearchCondition sc, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		String writer = (String)session.getAttribute("id");
		comm_commDto.setComm_comm_writer(writer);
		
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		
		try {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+comm_commDto);
			int rowCnt = comm_commService.cm_modify(comm_commDto);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+rowCnt);
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
