package com.greenart.ch1;

import java.lang.reflect.Method;

public class AopEx {
	public static void main(String[] args) throws Exception {
		MyAdvice myAdvice = new MyAdvice();
		Class myClass = Class.forName("com.greenart.ch1.MyClass");
		Object obj = myClass.newInstance();
		
		for(Method m : myClass.getDeclaredMethods()) {
			myAdvice.invoke(m, obj, null);
		}
	}
	
}
class MyAdvice {
	void invoke(Method m, Object obj, Object...args) throws Exception {
		System.out.println("[before]{");
		m.invoke(obj, args);
		System.out.println("}[after]");
	}
}

class MyClass {
	void aaa() {
		System.out.println("aaa() is called");
	}
	void bbb() {
		System.out.println("bbb() is called");
	}
	void ccc() {
		System.out.println("ccc() is called");
	}
}
