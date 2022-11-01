package com.greenart.ch1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DiceRoll {
	@RequestMapping("/rollDice")
	public static void main(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out =response.getWriter();
		int r1 = (int)(Math.random()*6+1);
		int r2 = (int)(Math.random()*6+1);
		
		
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<img src = 'resources/img/dice"+r1+".png'>");
		out.println("<img src = 'resources/img/dice"+r2+".png'>");
		out.println("</body");
		out.println("</html>");

	}

}
