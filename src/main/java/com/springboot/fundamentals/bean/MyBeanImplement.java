package com.springboot.fundamentals.bean;

public class MyBeanImplement implements MyBean {
	
	@Override
	public void print() {
		System.out.println("Hello again, but from my own bean!");
	}
}
