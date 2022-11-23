package com.greenart.ch1;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YoilTellerMVC2 {
@RequestMapping("/getYoilMVC2")
	public void main(int year,int month, int day, Model model)throws IOException {
		
	char yoil = getYoil(year,month,day);
	model.addAttribute("year",year);
	model.addAttribute("month",month);
	model.addAttribute("day",day);
	model.addAttribute("yoil",yoil);
	}

private char getYoil(int year, int month, int day) {
	Calendar cal = Calendar.getInstance();
	cal.set(year, month-1,day);
	
	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	return " 일월화수목금토".charAt(dayOfWeek);
}

}
