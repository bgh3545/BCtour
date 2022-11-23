package com.greenart.ch1;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.StringJoiner;

public class Methodinfo {

	public static void main(String[] args) throws Exception {
		Class clazz = Class.forName("com.greenart.ch1.YoilTellerMVC2");
		Object obj = clazz.newInstance();
		
		Method[] methodArr = clazz.getDeclaredMethods();
		
		for(Method m : methodArr) {
			String name = m.getName();
			Parameter[] paramArr = m.getParameters();
			Class returnType = m.getReturnType();
			
			StringJoiner paramList = new StringJoiner(",","(",")");
			
			for(Parameter param: paramArr) {
				String paramName = param.getName();
				Class paramType = param.getType();
				paramList.add(paramType.getName()+" "+paramName);
			}
			System.out.printf("%s %s%s%n",returnType.getName(),name,paramList);
		}

	}

}
