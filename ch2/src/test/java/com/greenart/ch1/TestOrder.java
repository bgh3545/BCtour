package com.greenart.ch1;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestOrder {

	@Test
	public void A() {
		System.out.println("A�޼ҵ� ����");
		assertTrue(true);
	}
	@Test
	public void B() {
		System.out.println("B�޼ҵ� ����");
		assertTrue(true);
	}
	@Test
	public void C() {
		System.out.println("C�޼ҵ� ����");
		assertTrue(true);
	}
	
}