package com.greenart.ch1;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.common.reflect.ClassPath;

class AppContext3{
	Map map;
	AppContext3() {
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
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("map="+map);
	}
	
	Object getBean(String key) {
		return map.get(key);
	}
}

public class Main5 {

	public static void main(String[] args) {
		AppContext3 ac = new AppContext3();
		Car car = (Car)ac.getBean("car");
		Engine engine = (Engine)ac.getBean("engine");
		System.out.println("car="+car);
		System.out.println("engine="+engine);
	}

}
