import java.util.ArrayList;

import com.basics.Circle;
import com.basics.Shape;
import com.basics.Square;


public class Test {

	public enum WeekDay {
		Monday, Tuesday, Wednesday, Thy, Fri, Sat, Sun
	}
	
	static void task(WeekDay weekday) {
		
		System.out.println(weekday.ordinal());
		if(weekday.ordinal() > 5) {
			System.out.println("Sorry, today is holiday.");
		}
		System.out.println("Done");
	}
	
	static void m1(Integer input) throws Exception {
		if(input > 100) {
			throw new Exception("Val > 100");
		}
	}
	
	static void m2(int input) throws Exception {
		if(input > 100) {
			throw new Exception("Val > 100");
		}
	}	
	
	public static void main(String[] args) throws Exception {
		task(WeekDay.Monday);
		task(WeekDay.Sun);
		
		Shape s1 = new Square("Square");
		Shape s2 = new Circle("Circle");
		
		m1(1);
		m2(new Integer(1));
		
//		s1.printMe(1);
//		s1.printMe(1l);
//		s1.printMe(Integer.MAX_VALUE + 1);
//		s1.printMe(0xffffffff);
		
//		m1(s1);
//		m1(s2);
		System.out.println(s1.getName());
		System.out.println(s2.getName());
		
		ArrayList<String> list = new ArrayList<String>();
	}
	
	static void m1(Shape s) {
		System.out.println(s.getArea());
	}
}
