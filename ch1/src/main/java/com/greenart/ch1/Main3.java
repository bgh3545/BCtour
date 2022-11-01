package com.greenart.ch1;

import java.util.HashMap;
import java.util.Map;

class AppContext{
	Map map;
	AppContext() {
		map = new HashMap();
		map.put("car", new SportsCar());
		map.put("engine", new Engine());
	}
	Object getBean(String key) {
		return map.get(key);
	}
}

public class Main3 {

	public static void main(String[] args) {
		AppContext ac = new AppContext();
		Car car = (Car)ac.getBean("car");
		Engine engine = (Engine)ac.getBean("engine");
		System.out.println("car="+car);
		System.out.println("engine="+engine);
	}

}
