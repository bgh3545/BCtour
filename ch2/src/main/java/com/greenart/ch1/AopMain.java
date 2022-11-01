package com.greenart.ch1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AopMain {

	public static void main(String[] args) {
		ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
		AopMyMath mm = ac.getBean("aopMyMath", AopMyMath.class);
		System.out.println("mm.add(3,5)= " + mm.add(3, 5));
		System.out.println("mm.mutiply(3,5)= " + mm.multiply(3, 5));
	}
}


//<<[start]add[3, 5]
//result =8
//[end]>>13ms
//mm.add(3,5)= 8
//mm.mutiply(3,5)= 15
