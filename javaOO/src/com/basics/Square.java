package com.basics;

public class Square extends Shape {

	public Square(String name) {
		super(name);
	}
	
	public String getName() {
		return "My Name is:" + super.getName() ;
	}
	
	
	@Override
	public double getArea() {
		return 0;
	}
	
}