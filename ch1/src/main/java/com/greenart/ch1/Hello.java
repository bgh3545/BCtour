package com.greenart.ch1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {
	int iv = 10;
	static int cv = 20;
	
	@RequestMapping("/hello")
	public void main() {
		System.out.println("hello-instance ");
		System.out.println(cv);
		System.out.println(iv);
	}
	@RequestMapping("/hello2")
	public static void main2() {
		System.out.println(cv);
	}
}
