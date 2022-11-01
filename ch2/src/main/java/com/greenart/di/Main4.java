package com.greenart.di;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class AppContext2 {
	Map map;
	AppContext2() {
		try {
			// �ܺ����Ͽ��� ������ ���� �о�� �� �ֵ���
			Properties p = new Properties();
			p.load(new FileReader("config.txt"));
			// Properties�� ����� ������ Map�� ����
			map = new HashMap(p);
			// �ݺ������� Ŭ���� �̸��� �� ��ü�� �����ؼ� �ٽ� map�� ����
			for(Object key : map.keySet()) {
				Class clazz = Class.forName((String)map.get(key));
				map.put(key, clazz.newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace(); // FileReader����Ϸ��� ����ó���ʿ�
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
