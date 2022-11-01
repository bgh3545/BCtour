package com.greenart.di;

import java.io.FileReader;
import java.util.Properties;

//class Car{}
//class Truck extends Car{}
//class SportsCar extends Car{}
//class Engine{}

public class Main0 {
			// getCar���� ���ܸ� ȣ�������� �Ѱܼ� main������ ���ܸ� �ѱ�
	public static void main(String[] args) throws Exception {
		Car car = (Car)getObject("car");
		Engine engine = (Engine)getObject("engine");
		System.out.println("car = " + car);
		System.out.println("Engine = " + engine);
	}
	
	static Object getObject(String key) throws Exception {
		// config.txt������ �о Properties�� ����
		Properties p = new Properties();
		p.load(new FileReader("config.txt"));
		// Ŭ���� ������ ��
		Class clazz = Class.forName(p.getProperty(key));
		// ��ü ����
		return clazz.newInstance();
	}

}
