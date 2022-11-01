package com.greenart.ch1;

import org.springframework.stereotype.Component;

@Component
public class AopMyMath {
	public int add(int a, int b) {
		return a+b;
	}
	public int add(int a, int b, int c) {
		return a+b+c;
	}
	public int subtract(int a, int b) {
		return a-b;
	}
	public int multiply(int a, int b) {
		return a*b;
	}
}
