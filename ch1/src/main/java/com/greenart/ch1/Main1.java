package com.greenart.ch1;

import java.lang.reflect.Method;

public class Main1 {

	public static void main(String[] args) throws Exception {
		Class hc = Class.forName("com.greenart.ch1.Hello");
		Hello hello = (Hello)hc.newInstance();
		Method main = hc.getDeclaredMethod("main");
		main.setAccessible(true);
		
		main.invoke(hello);

	}

}

