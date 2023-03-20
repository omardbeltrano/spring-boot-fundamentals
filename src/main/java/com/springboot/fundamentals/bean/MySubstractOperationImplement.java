package com.springboot.fundamentals.bean;

public class MySubstractOperationImplement implements MyOperation{

	@Override
	public int operation(int number) {
		return number - 10;
	}

	
}
