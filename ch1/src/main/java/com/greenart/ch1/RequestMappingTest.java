package com.greenart.ch1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestMappingTest {
	@RequestMapping("/login/hello.do")
	public void test1() {
		System.out.println("urlpattern=/login/hello.do");
	}
	@RequestMapping("/login/*")
	public void test2() {
		System.out.println("urlpattern=/login/*");
	}
	@RequestMapping("/login/**/tmp/*.do")
	public void test3() {
		System.out.println("urlpattern=/login/**/tmp/*.do");
	}
	@RequestMapping("/login/??")
	public void test4() {
		System.out.println("urlpattern=/login/??");
	}
	@RequestMapping("*.do")
	public void test5() {
		System.out.println("urlpattern=*.do");
	}
	@RequestMapping("/*.???")
	public void test6() {
		System.out.println("urlpattern=/*.???");
	}
}
