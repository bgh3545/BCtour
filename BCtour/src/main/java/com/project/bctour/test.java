package com.project.bctour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class test {
	
	@Autowired
	private BCUserDao dao;
	
	@RequestMapping("/asd")
	public void main(String[] args) throws Exception {
		System.out.println(dao);
		
		String email = "asd@asd"; 
		System.out.println(dao.confirmEmail(email));

	}

}
