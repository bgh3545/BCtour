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
			// 1. ��Ű������ Ŭ���� ����� �����´�.
			// 2. �ݺ������� Ŭ������ �ϳ��� �о�ͼ� @Component�� �پ� �ִ��� Ȯ��
			// 3. @Component�� �پ������� ��ü�� �����ؼ� map�� ����
			ClassLoader classloader = AppContext3.class.getClassLoader(); // �ε� �� �� �ִ� Ŭ���� ������ ����
			ClassPath classPath = ClassPath.from(classloader); // �ε� �� �� �ִ� ��� Ŭ���� ��θ� ����
			Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.greenart.di"); // ���� ��Ű���� Ŭ���� �̸��� ����
			
			for(ClassPath.ClassInfo classInfo : set) {
				System.out.println("classInfo = " + classInfo);
				Class clazz = classInfo.load(); // Ŭ���� ������ ����
				Component component = (Component)clazz.getAnnotation(Component.class);
				if(component != null) { // @Component�� �ִٸ�
					// ��Ű�� ������ �̸��� �ҹ��ڷ� ����
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
