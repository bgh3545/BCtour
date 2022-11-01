package com.greenart.di;

import java.util.HashMap;
import java.util.Map;

class AppContext {
	Map map; // 객체 저장소
	AppContext() { //생성자
		map = new HashMap();
		// 하드코딩된 부분으로 변경 필요
		map.put("car", new SportsCar());
		map.put("engine", new Engine());
	}
	Object getBean(String key) {
		return map.get(key);
	}
}

public class Main3 {

	public static void main(String[] args) throws Exception {
		AppContext ac = new AppContext();
		Car car = (Car)ac.getBean("car");
		Engine engine = (Engine)ac.getBean("engine");
		System.out.println("car = " + car);
		System.out.println("engine = " + engine);
	}
}
