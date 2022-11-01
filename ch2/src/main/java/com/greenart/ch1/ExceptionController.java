package com.greenart.ch1;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ExceptionController {
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 200 -> 500 변경
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex, Model m) {
//		m.addAttribute("ex", ex);
		System.out.println("ExceptionHandler-e");
		return "error";
	}
	
	@ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
	public String catcher2(Exception ex, Model m) {
		System.out.println("model=" + m);
		m.addAttribute("ex", ex);
		return "error";
	}
	
	@RequestMapping("/ex")
	public String main() throws Exception {
			throw new Exception("예외가 발생하였습니다.");
	}
	
	@RequestMapping("/ex2")
	public String main2(Model m) throws Exception {
		m.addAttribute("msg","message from ExceptionController.main2()");
			throw new NullPointerException("예외가 발생하였습니다.");
	}
}
