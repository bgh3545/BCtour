package com.greenart.ch1;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.common.reflect.ClassPath;

class AppContext5{
	Map map;
	AppContext5() {
		map = new HashMap();
		doComponentScan();
		doAutoWired();
	}
	
	private void doAutoWired() {
		try {
			for(Object bean: map.values()) {
				for(Field fld :bean.getClass().getDeclaredFields()) {
					System.out.println("bean="+bean);
					if(fld.getAnnotation(Autowired.class) !=null)
						fld.set(bean, getBean(fld.getType()));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
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



public class Main7 {

	public static void main(String[] args) throws Exception {
		AppContext5 ac = new AppContext5();
		Car car = (Car)ac.getBean("car");
		Engine engine = (Engine)ac.getBean("engine");
		Door door = (Door)ac.getBean("door");
		
		//car.engine=engine;
		//car.door=door;
		
		System.out.println("car="+car);
		System.out.println("engine="+engine);
		System.out.println("door="+door);
	}

}