import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class InputCommandTest {

	@Test
	public void testCommandReadEmpty() throws Exception {
		Pizzabot pb = new Pizzabot();
		System.out.println("-------testCommandReadEmpty-------");
		boolean result = pb.readFromString(true, "");
		System.out.println("INPUT: ''");
		System.out.println("Exptected: false | Result: " + result);
		System.out.println();
	    assertEquals(false, result);
	}
	
	@Test
	public void testReadWrongInputCommandRowCol() throws IOException {
		Pizzabot pb = new Pizzabot();
		System.out.println("-------testReadWrongInputCommandRowCol-------");
		String test = "pizzaboT \"axw (1, 2)\"";
		System.out.println("INPUT: "+test);
		boolean result = pb.readFromString(true, test);
		System.out.println("Exptected: false | Result: " + result);
		System.out.println();
	    assertEquals(false, result);
	}
	
	@Test
	public void testReadWrongInputCommandPointsAndRowCol() throws IOException {
		Pizzabot pb = new Pizzabot();
		System.out.println("-------testReadWrongInputCommandPointsAndRowCol-------");
		String test = "pizzaboT \"axw (1, 2\"";
		System.out.println("INPUT: "+test);
		boolean result = pb.readFromString(true, test);
		System.out.println("Exptected: false | Result: " + result);
		System.out.println();
	    assertEquals(false, result);
	}
	
	@Test
	public void testReadWrongInputCommandPoints() throws IOException{
		Pizzabot pb = new Pizzabot();
		System.out.println("-------testReadWrongInputCommandPoints-------");
		String test = "pizzaboT \"5x5 (1, 2\"";
		System.out.println("INPUT: "+test);
		boolean result = pb.readFromString(true, test);
		System.out.println("Exptected: false | Result: " + result);
		System.out.println();
	    assertEquals(false, result);
	}
	
	@Test
	public void testReadWrongInputPoints() throws IOException {
		Pizzabot pb = new Pizzabot();
		System.out.println("-------testReadWrongInputCommandPoints-------");
		String test = "pizzabot \"5x5 (1, 2\"";
		System.out.println("INPUT: "+test);
		boolean result = pb.readFromString(true, test);
		System.out.println("Exptected: false | Result: " + result);
		System.out.println();
	    assertEquals(false, result);
	}
	
	
	@Test
	public void testReadWrongInputOuthOfNeighborhood() throws IOException{
		Pizzabot pb = new Pizzabot();
		System.out.println("-------testReadWrongInputOuthOfNeighborhood-------");
		String test = "pizzaboT \"5x5 (10, 10)\"";
		System.out.println("INPUT: "+test);
		boolean result = pb.readFromString(true, test);
		System.out.println("Exptected: false | Result: " + result);
		System.out.println();
	    assertEquals(false, result);
	}
	

}

