package com.greenart.ch1;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public class YoilTeller {
	
	public static void main(String[] args) {
		String year = args[0];
		String month = args[1];
		String day = args[2];
		
		int yyyy = Integer.parseInt(year);
		int mm = Integer.parseInt(month);
		int dd = Integer.parseInt(day);
		
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm-1,dd);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		char yoil = " �Ͽ�ȭ�������".charAt(dayOfWeek);
		
		System.out.println(year+"��"+month+"��"+day+"����");
		System.out.println(yoil+"���� �Դϴ�");

	}

}