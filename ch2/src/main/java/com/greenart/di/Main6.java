package com.greenart.di;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.common.reflect.ClassPath;

class AppContext4 {
	Map map;
	AppContext4() {
		map = new HashMap();
		doComponentScan();
	}
	private void doComponentScan() {
		try {
			ClassLoader classloader = AppContext4.class.getClassLoader();
			ClassPath classPath = ClassPath.from(classloader);
			// 패키지내의 모든 클래스를 읽어서 set에 저장
			Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.greenart.di");
			
			//패키지 내의 @Component붙은 클래스 찾기
			for(ClassPath.ClassInfo classInfo : set) {
				Class clazz = classInfo.load();
				Component component = (Component)clazz.getAnnotation(Component.class);
				if(component != null) {
					String id = StringUtils.uncapitalize(classInfo.getSimpleName());
					// 객체를 생성해서 map에 저장
					map.put(id, clazz.newInstance());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("map = " + map);
	}
	Object getBean(String key) {
		return map.get(key);
	}
	Object getBean(Class clazz) {
		for(Object obj : map.values()) {
			System.out.println("obj = " + obj);
			if(clazz.isInstance(obj)) return obj;
		}
		return null;
	}
}
//@Component class Car{}
//class Truck extends Car{}
//@Component class SportsCar extends Car{}
//@Component class Engine{}

public class Main6 {
	public static void main(String[] args) throws Exception {
		AppContext4 ac = new AppContext4();
		Car car = (Car)ac.getBean("car");		// byName으로 확인
		Car car2 = (Car)ac.getBean(Car.class);  // byType으로 확인
		System.out.println("car = " + car);
		System.out.println("car2 = " + car2);
	}
}
