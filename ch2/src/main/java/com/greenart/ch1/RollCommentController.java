package com.greenart.ch1;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RollCommentController {
	@Autowired
	RollCommentDao rollCommentDao;
	
	@GetMapping("/cmt")
	public String rolling() {
		return "rollingpaper";
	}
	
	@PostMapping("/cmt")
	public String save(Model m, RollComment cmt) {
		String msg = null;
		int rowcnt = rollCommentDao.insertCmt(cmt);
		
		if(rowcnt!=0) {
			msg = "��ϼ���";
			m.addAttribute("msg", msg);
		}
		return "rollingpaper";
	}
	// ���
	@RequestMapping("/info")
	public String info(Model m, RollComment cmt) {
		int cnt = rollCommentDao.cnt();
		m.addAttribute("cnt", cnt);
		
		ArrayList<RollComment> cmts = new ArrayList<>();
		cmts = rollCommentDao.selectAll();
		m.addAttribute("cmts", cmts);
		
		System.out.println(cmts); // Ȯ��
		
		return "rollingpaperinfo";
	}
	// ����
	@RequestMapping("/del")
	public String del(Model m, RollComment cmt) throws Exception {
		rollCommentDao.deleteAll();
		
		int cnt = rollCommentDao.cnt();
		m.addAttribute("cnt", cnt);
		
		return "rollingpaperinfo";
		
	}

}
