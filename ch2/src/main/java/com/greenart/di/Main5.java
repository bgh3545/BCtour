package com.greenart.di;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.common.reflect.ClassPath;

//@Component class Car{}
//@Component class Truck extends Car{}
//@Component class SportsCar extends Car{}
//@Component class Engine{}

class AppContext3 {
	Map map;
	AppContext3() {
		map = new HashMap();
		doComponentScan();
	}
	private void doComponentScan() {
		try {
			// 1. 패키지내의 클래스 목록을 가져온다.
			// 2. 반복문으로 클래스를 하나씩 읽어와서 @Component이 붙어 있는지 확인
			// 3. @Component가 붙어있으면 객체를 생성해서 map에 저장
			ClassLoader classloader = AppContext3.class.getClassLoader(); // 로드 할 수 있는 클래스 정보를 저장
			ClassPath classPath = ClassPath.from(classloader); // 로드 할 수 있는 모든 클래스 경로를 저장
			Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.greenart.di"); // 같은 패키지의 클래스 이름을 저장
			
			for(ClassPath.ClassInfo classInfo : set) {
				System.out.println("classInfo = " + classInfo);
				Class clazz = classInfo.load(); // 클래스 정보를 얻어옴
				Component component = (Component)clazz.getAnnotation(Component.class);
				if(component != null) { // @Component가 있다면
					// 패키지 제외한 이름을 소문자로 변경
					String id = StringUtils.uncapitalize(classInfo.getSimpleName());
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
}

public class Main5 {

	public static void main(String[] args) throws Exception {
		AppContext3 ac = new AppContext3();
		Car car = (Car)ac.getBean("car");
		Engine engine = (Engine)ac.getBean("engine");
		System.out.println("car = " + car);
		System.out.println("engine = " + engine);
	}
}
