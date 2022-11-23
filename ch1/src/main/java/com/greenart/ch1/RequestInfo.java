package com.greenart.ch1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class RequestInfo {
	@RequestMapping("/requestInfo")
	public static void main(HttpServletRequest request) {
		System.out.println("reqest.getMethod()="+request.getMethod());
		System.out.println("reqest.getProtocol()="+request.getProtocol());
		System.out.println("reqest.getScheme()="+request.getScheme());
		
		System.out.println("reqest.getServerName()="+request.getServerName());
		System.out.println("reqest.getServerPort()="+request.getServerPort());
		System.out.println("reqest.getRequestURL()="+request.getRequestURL());
		System.out.println("reqest.getRequestURI()="+request.getRequestURI());
		
		System.out.println("reqest.getContextPath()="+request.getContextPath());
		System.out.println("reqest.getServletPath()="+request.getServletPath());
		System.out.println("reqest.getQueryString()="+request.getQueryString());
		

	}

}
