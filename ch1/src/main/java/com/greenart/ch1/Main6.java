package com.greenart.ch1;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.common.reflect.ClassPath;

class AppContext4{
	Map map;
	AppContext4() {
		map = new HashMap();
		doComponentScan();
	}
	
	private void doComponentScan() {
		try {
			ClassLoader classloader = AppContext3.class.getClassLoader();
			ClassPath classPath = ClassPath.from(classloader);
			Set<ClassPath.ClassInfo>set = classPath.getTopLevelClasses("com.greenart.ch1");
			
			for(ClassPath.ClassInfo classInfo : set) {
				Class clazz = classInfo.load();
				Component component = (Component)clazz.getAnnotation(Component.class);
				if(component != null) {
					String id = StringUtils.uncapitalize(classInfo.getSimpleName());
					map.put(id, clazz.newInstance());
				}
			}
			System.out.println("map="+map);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	Object getBean(String key) {
		return map.get(key);
	}
	Object getBean(Class clazz) {
		for(Object obj : map.values()) {
			System.out.println("obj="+obj);
			if(clazz.isInstance(obj)) return obj;
		}
		return null;
	}
}

public class Main6 {

	public static void main(String[] args) {
		AppContext4 ac = new AppContext4();
		Car car = (Car)ac.getBean("car");
		Car car2 = (Car)ac.getBean(Car.class);
		System.out.println("car="+car);
		System.out.println("car2="+car2);
	}

}
