package com.basics;

public abstract class Shape {

	private String name;
	protected String version;
	
	public Shape(String name) {
//		this.name = name;
//		this.version = "0";
		this(name, "0");
	}
	
	public Shape(String name, String ver) {
		this.name = name;
		this.version = ver;
	}
	
	public String getName() {
		return name;
	}
	
	public void printMe(int a) {
		System.out.println("Int" + a);
	}
	
	public void printMe(long a) {
		System.out.println("Long :" + a);
	}	
	
	public void printMe(double a) {
		System.out.println("Doub" + a);
	}	
	
	public abstract double getArea();
	
}


