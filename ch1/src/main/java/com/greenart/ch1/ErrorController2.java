package com.greenart.ch1;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.BAD_REQUEST)
class MyException extends RuntimeException{
	MyException(String msg){
		super(msg);
	}
	MyException(){}
}

@Controller
public class ErrorController2 {
	
	@RequestMapping("/ex3")
	public String main() throws Exception{
		throw new MyException("예외가 발생했습니다");
	}
	
	@RequestMapping("/ex4")
	public String main2() throws Exception{
		throw new FileNotFoundException("예외가 발생했습니다");
	}
}
