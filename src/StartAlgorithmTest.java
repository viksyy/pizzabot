import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class StartAlgorithmTest{
	
	@Test
	public void testReadWrongInputPoints() throws IOException {
		Pizzabot pb = new Pizzabot();
		System.out.println("-------testReadWrongInputCommandPoints-------");
		String test = "pizzabot \"5x5 (1, 2\"";
		System.out.println("INPUT: "+test);
		boolean result = pb.readFromString(true,test);
		System.out.println("Exptected: false | Result: " + result);
		System.out.println();
	    assertEquals(false, result);
	}
	
	
	@Test
	public void testStartAlgorithmFromString1() throws IOException{
		Pizzabot pb = new Pizzabot();
		System.out.println("-------testStartAlgorithmFromString1-------");
		pb.sb = new StringBuilder();
		String test1 = "pizzabot \"5x5 (1, 3) (4, 4)\"";
		System.out.println("INPUT: "+test1);
		boolean result = pb.startAlgorithm(true, test1);
		System.out.println("Exptected: ENNNDEEEND | Result: " + pb.sb.toString());
		System.out.println();
		assertEquals("ENNNDEEEND", pb.sb.toString());
		
	}
	
	@Test
	public void testStartAlgorithmFromString2() throws IOException{
		Pizzabot pb = new Pizzabot();
		System.out.println("-------testStartAlgorithmFromString2-------");
		pb.sb = new StringBuilder();
		String test2 = "pizzabot \"5x5 (0, 0) (1, 3) (4, 4) (4, 2) (4, 2) (0, 1) (3, 2) (2, 3) (4, 1)\"";
		System.out.println("INPUT: "+test2);
		boolean result = pb.startAlgorithm(true, test2);
		System.out.println("Exptected: DNDENNDEDESDEDDSDNNND | Result: " + pb.sb.toString());
		System.out.println();
		assertEquals("DNDENNDEDESDEDDSDNNND", pb.sb.toString());
		
	}
	
	@Test
	public void testStartAlgorithmFromString3() throws IOException{
		Pizzabot pb = new Pizzabot();
		System.out.println("-------testStartAlgorithmFromString3-------");
		pb.sb = new StringBuilder();
		String test3 = "pizzabot \"5x5 (0,0)\"";
		System.out.println("INPUT: "+test3);
		boolean result = pb.startAlgorithm(true, test3);
		System.out.println("Exptected: Is not valid input! | Result: " + pb.sb.toString());
		System.out.println();
		assertEquals("Is not valid input!", pb.sb.toString());
	}
	
	@Test
	public void testStartAlgorithmFromString4() throws IOException{
		Pizzabot pb = new Pizzabot();
		System.out.println("-------testStartAlgorithmFromString4-------");
		pb.sb = new StringBuilder();
		String test4 = "pizzabot \"5x5 (0, 0)\"";
		System.out.println("INPUT: "+test4);
		boolean result = pb.startAlgorithm(true, test4);
		System.out.println("Exptected: D | Result: " + pb.sb.toString());
		System.out.println();
		assertEquals("D", pb.sb.toString());
	}
	
	@Test
	public void testStartAlgorithmFromString5() throws IOException{
		Pizzabot pb = new Pizzabot();
		System.out.println("-------testStartAlgorithmFromString5-------");
		pb.sb = new StringBuilder();
		String test5 = "pizzabot \"10x10 (0, 0)\"";
		System.out.println("INPUT: "+test5);
		boolean result = pb.startAlgorithm(true, test5);
		System.out.println("Exptected: D | Result: " + pb.sb.toString());
		System.out.println();
		assertEquals("D", pb.sb.toString());
	}
	
	
	@Test
	public void testStartAlgorithmFromString6() throws IOException{
		Pizzabot pb = new Pizzabot();
		System.out.println("-------testStartAlgorithmFromString6-------");
		pb.sb = new StringBuilder();
		String test6 = "pizzabot \"10x10 (0, 0) (0, 0) (0, 1)\"";
		System.out.println("INPUT: "+test6);
		boolean result = pb.startAlgorithm(true, test6);
		System.out.println("Exptected: DDND | Result: " + pb.sb.toString());
		System.out.println();
		assertEquals("DDND", pb.sb.toString());
	}
	
	@Test
	public void testStartAlgorithmFromString7() throws IOException{
		Pizzabot pb = new Pizzabot();
		System.out.println("-------testStartAlgorithmFromString7-------");
		pb.sb = new StringBuilder();
		String test7 = "pizzabot \"10x10 (11, 11) (12, 12) (13, 1)\"";
		System.out.println("INPUT: "+test7);
		boolean result = pb.startAlgorithm(true, test7);
		System.out.println("Exptected: Is not valid input! | Result: " + pb.sb.toString());
		System.out.println();
		assertEquals("Is not valid input!", pb.sb.toString());
	}
	
	@Test
	public void testStartAlgorithmFromString8() throws IOException{
		Pizzabot pb = new Pizzabot();
		System.out.println("-------testStartAlgorithmFromString8-------");
		pb.sb = new StringBuilder();
		String test8 = "pizzabot \"10x10 (20, 11)\"";
		System.out.println("INPUT: "+test8);
		boolean result = pb.startAlgorithm(true, test8);
		System.out.println("Exptected: Is not valid input! | Result: " + pb.sb.toString());
		System.out.println();
		assertEquals("Is not valid input!", pb.sb.toString());
	}
	
	@Test
	public void testStartAlgorithmFromString9() throws IOException{
		Pizzabot pb = new Pizzabot();
		System.out.println("-------testStartAlgorithmFromString9-------");
		pb.sb = new StringBuilder();
		String test9 = "pizzabot \"3x3 (0, 0) (0, 1) (0, 2) (1, 0) (1, 1) (1, 2) (2, 0) (2, 1) (2, 2)\"";
		System.out.println("INPUT: "+test9);
		boolean result = pb.startAlgorithm(true, test9);
		System.out.println("Exptected: DNDNDEDSDSDEDNDND | Result: " + pb.sb.toString());
		System.out.println();
		assertEquals("DNDNDEDSDSDEDNDND", pb.sb.toString());
	}
	
	@Test
	public void testStartAlgorithmFromString10() throws IOException{
		Pizzabot pb = new Pizzabot();
		System.out.println("-------testStartAlgorithmFromString10-------");
		pb.sb = new StringBuilder();
		String test10 = "pizzabot \"3x3 (0, 0) (0, 1) (0, 2) (0, 3) (1, 0) (1, 1) (1, 2) (1, 3) (2, 0) (2, 1) (2, 2) (2, 3) (3, 0) (3, 1) (3, 2) (3, 3)\"";
		System.out.println("INPUT: "+test10);
		boolean result = pb.startAlgorithm(true, test10);
		System.out.println("Exptected: Is not valid input! | Result: " + pb.sb.toString());
		System.out.println();
		assertEquals("Is not valid input!", pb.sb.toString());
	}
	
	
}
