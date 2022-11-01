package com.greenart.ch1;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.BAD_REQUEST) // 기본 500번 -> 400번 변경
class MyException extends RuntimeException {
	MyException(String msg) {
		super(msg);
	}
	MyException() {}
}

@Controller
public class ExceptionController2 {
	@RequestMapping("/ex3")
	public String main() throws Exception {
			throw new MyException("예외가 발생하였습니다.");
	}
	
	@RequestMapping("/ex4")
	public String main2() throws Exception {
			throw new NullPointerException("예외가 발생하였습니다.");
	}
}
