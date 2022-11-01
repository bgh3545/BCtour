package com.greenart.ch1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet")
public class HelloServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		System.out.println("[HelloServlet init() is called");
	}
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1 ) throws ServletException, IOException{
		System.out.println("[HelloServlet] service() is called");
	}
	@Override
	public void destroy() {
		System.out.println("[HelloServlet] destroy() is called");
	}

}
