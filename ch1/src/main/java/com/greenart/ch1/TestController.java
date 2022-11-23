package com.greenart.ch1;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TestController {
	@GetMapping("/test")
	public String test(Model m, HttpSession session) {
		m.addAttribute("id",session.getId());
		return "test";
	}
	@PostMapping("/test")
	public String test2(Model m, HttpSession session) {
		m.addAttribute("id",session.getId());
		return "test2";
}
}