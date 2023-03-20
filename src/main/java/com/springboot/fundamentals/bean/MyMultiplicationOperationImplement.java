package com.springboot.fundamentals.bean;

public class MyMultiplicationOperationImplement implements MyOperation{
	@Override
	public int operation(int number) {
		return number * 8;
	}
}
