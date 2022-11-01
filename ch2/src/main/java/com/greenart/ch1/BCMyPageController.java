package com.greenart.ch1;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BCMyPageController {
	@Autowired
	BCUserDao bcuserDao;
	
	@RequestMapping("/mypage")
	public String mypage() { 
		return "BCmypage";
	}
	
	@PostMapping("/remove")
	public String remove(Model m, BCUser user, HttpSession session, RedirectAttributes redatt) {
		try {
			redatt.addAttribute("bcuser", user);
			int rowCnt = bcuserDao.deleteUser(user.getId());
			if(rowCnt == 1) {
				redatt.addFlashAttribute("msg", "del");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return "BCmypage";
	}
}
