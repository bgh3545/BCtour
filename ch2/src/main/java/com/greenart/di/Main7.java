package com.greenart.di;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.common.reflect.ClassPath;

//@Component class Car {
//	@Autowired Engine engine;
//	@Autowired Door door;
////	Engine engine;
////	Door door;
//	
//	@Override
//	public String toString() {
//		return "Car [engine = " + engine + ", door = " + door + "]";
//	}
//}

//@Component class Door {}
//class Truck extends Car {}
//@Component class SportsCar extends Car {}
//@Component class Engine {}

class AppContext5 {
	Map map;
	AppContext5() {
		map = new HashMap();
		doComponentScan();
		doAutoWired();
	}
	private void doComponentScan() {
		try {
			ClassLoader classloader = AppContext5.class.getClassLoader();
			ClassPath classPath = ClassPath.from(classloader);
			// ��Ű������ ��� Ŭ������ �о set�� ����
			Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.greenart.di");
			
			//��Ű�� ���� @Component���� Ŭ���� ã��
			for(ClassPath.ClassInfo classInfo : set) {
				Class clazz = classInfo.load();
				Component component = (Component)clazz.getAnnotation(Component.class);
				if(component != null) {
					String id = StringUtils.uncapitalize(classInfo.getSimpleName());
					// ��ü�� �����ؼ� map�� ����
					map.put(id, clazz.newInstance());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println("map = " + map);
	}
	Object getBean(String key) {
		return map.get(key);
	}
	Object getBean(Class clazz) {
		for(Object obj : map.values()) {
//			System.out.println("obj = " + obj);
			if(clazz.isInstance(obj)) return obj;
		}
		return null;
	}
	
	private void doAutoWired() {
		// map�� ����� ��ü �ν��Ͻ� ���� �� @Autowired�� �پ� ������
		// map���� �ν��Ͻ� ������ Ÿ�Կ� �´� ��ü�� ã�Ƽ� ����(��ü�� �ּҸ� �ν��Ͻ� ������ ����)
		try {
			for(Object bean : map.values()) {
				for(Field fld : bean.getClass().getDeclaredFields()) {// byType
					System.out.println("fld = " + fld);
				if(fld.getAnnotation(Autowired.class) != null)
					fld.set(bean, getBean(fld.getType())); // car.engine = obj;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class Main7 {

	public static void main(String[] args) throws Exception {
		AppContext5 ac = new AppContext5();
		Car car = (Car)ac.getBean("car"); // byName���� Ȯ��
		Engine engine = (Engine)ac.getBean("engine");
		Door door = (Door)ac.getBean("door");

		// �������� ����
//		car.engine = engine;
//		car.door = door;
		System.out.println("car = " + car);
		System.out.println("engine = " + engine);
		System.out.println("door = " + door);
	}
	
}