package com.greenart.ch1;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class naiTeller {
	Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR);
	
@RequestMapping("/getNai")
	public String main(int birth, Model model)throws IOException {
	if(!isValid(birth)) { 
		return "naiError";}
	
	int nai = getNai(birth);
	model.addAttribute("nai",nai);
	return "getNai";
	}

private boolean isValid(int birth) {
	if(birth==-1 || birth<1900 || birth>year)
		return false;
		
	return true;
}

private int getNai(int birth) {

	return year-birth+1; 
}

}