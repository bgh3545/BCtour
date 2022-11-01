package com.greenart.ch1;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class YoilTellerMVC4 {
	@RequestMapping("/getYoilMVC4")
	public String main(MyDate date, Model model) throws IOException{
		if(!isValid(date)) return"yoilError";
		char yoil = getYoil(date);
		model.addAttribute("myDate",date);
		model.addAttribute("yoil",yoil);
		return "yoil2";
	}
	
	private boolean isValid(MyDate date) {
		return isValid(date.getYear(),date.getMonth(),date.getDay());
	}
	private boolean isValid(int year, int month, int day) {
		if(year ==-1 || month ==-1 || day ==-1)
			return false;
		return(1<=month&& month<=12)&&(1<=day&&day<=31);
	}
	private char getYoil(MyDate date) {
		return getYoil(date.getYear(),date.getMonth(),date.getDay());
	}
	private char getYoil(int year,int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return " 일월화수목금토".charAt(dayOfWeek);
	}
}
