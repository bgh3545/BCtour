package com.greenart.ch1;

import java.io.FileReader;
import java.util.Properties;

import org.springframework.stereotype.Component;


@Component class SportsCar extends Car{}
@Component class Door{}
class Truck extends Car{}
@Component class Engine{}


public class Main0 {

	public static void main(String[] args) throws Exception {
		Car car = (Car)getObject("car");
		Engine engine = (Engine)getObject("engine");
		System.out.println("car="+car);
		System.out.println("engine="+engine);
	}
	static Object getObject(String key) throws Exception{
		Properties p = new Properties();
		p.load(new FileReader("config.txt"));
		Class clazz = Class.forName(p.getProperty(key));
		
		return clazz.newInstance();
	}

}
