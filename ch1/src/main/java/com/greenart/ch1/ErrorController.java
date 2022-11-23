package com.greenart.ch1;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ErrorController {
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex, Model m) {
		//m.addAttribute("ex",ex);
		return "error";
	}
	
	@RequestMapping("/ex")
	public String main() throws Exception{
		throw new Exception("���ܰ� �߻��߽��ϴ�");
	}
	
	@ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
	public String catcher2(Exception ex, Model m) {
		//m.addAttribute("ex",ex);
		System.out.println("m="+m);
		return "error";
	}
	@RequestMapping("/ex2")
	public String main2(Model m) throws Exception{
		m.addAttribute("msg", "message from ExceptionController.main()");
		throw new NullPointerException("���ܰ� �߻��߽��ϴ�");
	}
}
