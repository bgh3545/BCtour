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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardDao boardDao;
	@Autowired
	BoardService boardService;
	
	@GetMapping("/list_v1_1")
	public String list_v1_1(HttpServletRequest request,Integer page, Integer pageSize, Model m) throws Exception {
		if(page==null) page=1;
		if(pageSize==null) pageSize=10;
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		try {
			int totalCnt = boardService.getCount();
			PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);
			
			Map map = new HashMap();
			map.put("offset", (page-1)*pageSize);
			map.put("pageSize", pageSize);
			
			List<BoardDto> board = boardService.getPage(map);
			List<BoardDto> notice = boardService.getNotice(map);
			m.addAttribute("notice", notice);
			m.addAttribute("board",board);
			m.addAttribute("ph", pageHandler);
			m.addAttribute("page", page);
			m.addAttribute("pageSize", pageSize);
			
			Date now = new Date();
			m.addAttribute("now",now);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "board_v1_1";
	}
	
	@GetMapping("/write_1")
	public String write_1(HttpServletRequest request,Model m, Integer bno, Integer pageSize, Integer page) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		try {
			BoardDto boardDto = boardService.read(bno);
			m.addAttribute("boardDto", boardDto);
			m.addAttribute("page", page);
			m.addAttribute("pageSize", pageSize);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "write_1";
	}
	
	@GetMapping("/read_1")
	public String read_1(HttpServletRequest request,Model m, Integer bno, Integer pageSize, Integer page) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		try {
			BoardDto boardDto = boardService.read(bno);
			m.addAttribute("boardDto", boardDto);
			m.addAttribute("page", page);
			m.addAttribute("pageSize", pageSize);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "read_1";
	}
	
	@PostMapping("/remove_1")
	public String remove_1(HttpServletRequest request,Model m, Integer bno, Integer pageSize, Integer page, HttpSession session, RedirectAttributes redatt) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		
		m.addAttribute("page", page);
		m.addAttribute("pageSize", pageSize);
		
		try {
			String writer = (String)session.getAttribute("id");
			int rowCnt= boardService.remove(bno, writer);
			if(rowCnt==1) {
				redatt.addFlashAttribute("msg", "del");
				return "redirect:/board/list_v1_1";
			}
			else {
				throw new Exception("board remove error");
			}
		}catch(Exception e){
			e.printStackTrace();
			redatt.addFlashAttribute("msg", "error");
		}
		return "redirect:/board/list_v1_1";
	}
	
	@GetMapping("/list_v1_2")
	public String list_v1_2(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		return "board_v1_2";
	}
	@GetMapping("/list_v1_3")
	public String list_v1_3(HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/logIn1/logIn1?toURL="+request.getRequestURL();
		return "board_v1_3";
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
