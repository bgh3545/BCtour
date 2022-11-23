package com.greenart.ch1;

import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestMessage {
	@RequestMapping("/requestMessage")
	public static void main(HttpServletRequest request)throws Exception {
		String requestLine = request.getMethod();
		requestLine +=" "+request.getRequestURI();
		
		String queryString = request.getQueryString();
		requestLine += queryString == null ? "":"?"+queryString;
		requestLine += " "+request.getProtocol();
		System.out.println(requestLine);
		
		Enumeration<String> e = request.getHeaderNames();
		
		while(e.hasMoreElements()) {
			String name = e.nextElement();
			System.out.println(name+":"+request.getHeader(name));
		}
		
		final int CONTENT_LENGTH = request.getContentLength();
		
		if(CONTENT_LENGTH>0) {
			byte[] content = new byte[CONTENT_LENGTH];
			
			InputStream in = request.getInputStream();
			in.read(content,0,CONTENT_LENGTH);
			
			System.out.println();
			System.out.println(new String(content,"utf-8"));
		}
	}

}
