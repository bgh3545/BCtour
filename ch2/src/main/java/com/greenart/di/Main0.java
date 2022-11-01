package com.greenart.di;

import java.io.FileReader;
import java.util.Properties;

//class Car{}
//class Truck extends Car{}
//class SportsCar extends Car{}
//class Engine{}

public class Main0 {
			// getCar에서 예외를 호출쪽으로 넘겨서 main에서도 예외를 넘김
	public static void main(String[] args) throws Exception {
		Car car = (Car)getObject("car");
		Engine engine = (Engine)getObject("engine");
		System.out.println("car = " + car);
		System.out.println("Engine = " + engine);
	}
	
	static Object getObject(String key) throws Exception {
		// config.txt파일을 읽어서 Properties에 저장
		Properties p = new Properties();
		p.load(new FileReader("config.txt"));
		// 클래서 정보를 얻어서
		Class clazz = Class.forName(p.getProperty(key));
		// 객체 생성
		return clazz.newInstance();
	}

}
