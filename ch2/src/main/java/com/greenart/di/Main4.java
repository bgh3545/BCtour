package com.greenart.di;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class AppContext2 {
	Map map;
	AppContext2() {
		try {
			// 외부파일에서 저장한 값을 읽어올 수 있도록
			Properties p = new Properties();
			p.load(new FileReader("config.txt"));
			// Properties에 저장된 내용을 Map에 저장
			map = new HashMap(p);
			// 반복문으로 클래스 이름을 얻어서 객체를 생성해서 다시 map에 저장
			for(Object key : map.keySet()) {
				Class clazz = Class.forName((String)map.get(key));
				map.put(key, clazz.newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace(); // FileReader사용하려면 예외처리필요
		}
	}
	Object getBean(String key) {
		return map.get(key);
	}
}

public class Main4 {

	public static void main(String[] args) throws Exception {
		AppContext2 ac = new AppContext2();
		Car car = (Car)ac.getBean("car");
		Engine engine = (Engine)ac.getBean("engine");
		System.out.println("car = " + car);
		System.out.println("engine = " + engine);
	}
}
