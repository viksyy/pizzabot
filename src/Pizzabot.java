import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Pizzabot {

	public static Integer INPUT_ROW;
	public static Integer INPUT_COL;
	public static StringBuilder sb = new StringBuilder();
	public static LinkedList<House> listDropHouses = new LinkedList<>();

	public static class House {
		public int x;
		public int y;

		House(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class SetPoint {
		public double key;
		public House house;

		SetPoint(double key, House house) {
			this.key = key;
			this.house = house;
		}
	}

	public static class Point {
		int i;
		int j;
		double f, g, h;

		Point() {
			this.i = -1;
			this.j = -1;
			this.f = Double.MAX_VALUE;
			this.g = Double.MAX_VALUE;
			this.h = Double.MAX_VALUE;
		}
	}

	public static boolean isInNeighborhood(int row, int col) {
		return (row >= 0) && (row < INPUT_ROW) && (col >= 0) && (col < INPUT_COL);
	}

	public static boolean isFinalHouse(int row, int col, House dest) {
		if (row == dest.x && col == dest.y) {
			return (true);
		} else {
			return (false);
		}
	}

	public static double euclideanHeuristic(int row, int col, House dest) {
		return ((double) Math.sqrt((row - dest.x) * (row - dest.x) + (col - dest.y) * (col - dest.y)));
	}

	public static void drawDeliveryPath(Point points[][], House dest) {
		int row = dest.x;
		int col = dest.y;
		Stack<House> Path = new Stack();
		
		while (!(points[row][col].i == row && points[row][col].j == col)) {
			House p = new House(row, col);
			Path.push(p);
			int temp_row = points[row][col].i;
			int temp_col = points[row][col].j;
			row = temp_row;
			col = temp_col;
		}
		House p1 = new House(row, col);
		Path.push(p1);
		
		while (!Path.empty()) {
			House pP = Path.pop();
			if (!Path.empty()) {
				House pN = Path.peek();

				if (pP.x < pN.x && pP.y == pN.y) {
					sb.append("E");
				}

				if (pP.x > pN.x && pP.y == pN.y) {
					sb.append("W");
				}

				if (pP.x == pN.x && pP.y < pN.y) {
					sb.append("N");
				}

				if (pP.x == pN.x && pP.y > pN.y) {
					sb.append("S");
				}
			} else {
				sb.append("D");
			}
		}

		return;
	}

	public static void astarAlgorithm(House src, House dest) {
		if (isInNeighborhood(src.x, src.y) == false) {
			sb = new StringBuilder();
			sb.append("Invalid source\n");
			return;
		}

		if (isInNeighborhood(dest.x, dest.y) == false) {
			sb = new StringBuilder();
			sb.append("Invalid destination\n");
			return;
		}

		if (isFinalHouse(src.x, src.y, dest) == true) {
			sb.append("D");
			return;
		}

		Point[][] points = new Point[INPUT_ROW][INPUT_COL];

		int i, j;

		for (i = 0; i < INPUT_ROW; i++) {
			for (j = 0; j < INPUT_COL; j++) {
				if (points[i][j] == null) {
					points[i][j] = new Point();
				}
			}
		}

		i = src.x;
		j = src.y;
		points[i][j].f = 0.0;
		points[i][j].g = 0.0;
		points[i][j].h = 0.0;
		points[i][j].i = i;
		points[i][j].j = j;

		LinkedList<SetPoint> listNeighbors = new LinkedList<>();
		listNeighbors.addLast(new SetPoint(0.0, new House(i, j)));

		boolean foundDest = false;

		while (!listNeighbors.isEmpty()) {
			SetPoint p = listNeighbors.getFirst();
			listNeighbors.remove(listNeighbors.getFirst());
			i = p.house.x;
			j = p.house.y;

			double gNew, hNew, fNew;

			// ----------- Move south ------------
			if (isInNeighborhood(i - 1, j) == true) {
				if (isFinalHouse(i - 1, j, dest) == true) {
					points[i - 1][j].i = i;
					points[i - 1][j].j = j;
					drawDeliveryPath(points, dest);
					foundDest = true;
					return;
				} else {
					gNew = points[i][j].g + 1.0;
					hNew = euclideanHeuristic(i - 1, j, dest);
					fNew = gNew + hNew;

					if (points[i - 1][j].f == Float.MAX_VALUE || points[i - 1][j].f > fNew) {
						listNeighbors.addLast(new SetPoint(fNew, new House(i - 1, j)));
						points[i - 1][j].f = fNew;
						points[i - 1][j].g = gNew;
						points[i - 1][j].h = hNew;
						points[i - 1][j].i = i;
						points[i - 1][j].j = j;
					}
				}
			}

			// ----------- Move north ------------
			if (isInNeighborhood(i + 1, j) == true) {
				if (isFinalHouse(i + 1, j, dest) == true) {
					points[i + 1][j].i = i;
					points[i + 1][j].j = j;
					drawDeliveryPath(points, dest);
					foundDest = true;
					return;
				} else {
					gNew = points[i][j].g + 1.0;
					hNew = euclideanHeuristic(i + 1, j, dest);
					fNew = gNew + hNew;

					if (points[i + 1][j].f == Float.MAX_VALUE || points[i + 1][j].f > fNew) {
						listNeighbors.addLast(new SetPoint(fNew, new House(i + 1, j)));
						points[i + 1][j].f = fNew;
						points[i + 1][j].g = gNew;
						points[i + 1][j].h = hNew;
						points[i + 1][j].i = i;
						points[i + 1][j].j = j;
					}
				}
			}

			// ----------- Move east ------------
			if (isInNeighborhood(i, j + 1) == true) {
				if (isFinalHouse(i, j + 1, dest) == true) {
					points[i][j + 1].i = i;
					points[i][j + 1].j = j;
					drawDeliveryPath(points, dest);
					foundDest = true;
					return;
				} else {
					gNew = points[i][j].g + 1.0;
					hNew = euclideanHeuristic(i, j + 1, dest);
					fNew = gNew + hNew;

					if (points[i][j + 1].f == Float.MAX_VALUE || points[i][j + 1].f > fNew) {
						listNeighbors.addLast(new SetPoint(fNew, new House(i, j + 1)));
						points[i][j + 1].f = fNew;
						points[i][j + 1].g = gNew;
						points[i][j + 1].h = hNew;
						points[i][j + 1].i = i;
						points[i][j + 1].j = j;
					}
				}
			}

			// ----------- Move west ------------
			if (isInNeighborhood(i, j - 1) == true) {
				if (isFinalHouse(i, j - 1, dest) == true) {
					points[i][j - 1].i = i;
					points[i][j - 1].j = j;
					drawDeliveryPath(points, dest);
					foundDest = true;
					return;
				} else {
					gNew = points[i][j].g + 1.0;
					hNew = euclideanHeuristic(i, j - 1, dest);
					fNew = gNew + hNew;

					if (points[i][j - 1].f == Float.MAX_VALUE || points[i][j - 1].f > fNew) {
						listNeighbors.addLast(new SetPoint(fNew, new House(i, j - 1)));
						points[i][j - 1].f = fNew;
						points[i][j - 1].g = gNew;
						points[i][j - 1].h = hNew;
						points[i][j - 1].i = i;
						points[i][j - 1].j = j;
					}
				}
			}
		}

		if (foundDest == false) {
			sb = new StringBuilder();
			sb.append("No final destination\n");
		}

		return;
	}

	public static boolean readFromString(boolean test, String str) throws IOException{
		if(!test) {
			System.out.println("Please input string for testing");
			System.out.println("Example: pizzabot \"2x2 (0, 0) (1, 1)\"");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			str = br.readLine();
		}
		str.trim();
		String[] pom = str.split("\"");
		if (pom[0].trim().contains("pizzabot")) {
			try {
				String[] matrixDetails = pom[1].split(" ");
				INPUT_ROW = Integer.parseInt(matrixDetails[0].split("x")[0]);
				INPUT_COL = Integer.parseInt(matrixDetails[0].split("x")[1]);

				String spl = INPUT_ROW + "x" + INPUT_COL + " ";
				String[] houses = pom[1].split(spl);
				try {
					String[] housesPoints = houses[1].substring(1, houses[1].length() - 1).split("\\) \\(");
					for (String s : housesPoints) {
						String[] parts = s.split(", ");
						int i = Integer.parseInt(parts[0]);
						int j = Integer.parseInt(parts[1]);
						if(i >= INPUT_ROW || j >= INPUT_COL) {
							return false;
						}
						else {
							listDropHouses.add(new House(i, j));	
						}
					}
					return true;
				} catch (Exception e) {
					return false;
				}

			} catch (Exception e) {
				return false;
			}

		} else {
			return false;
		}
	}

	public static boolean startAlgorithm(boolean test, String input) throws IOException {
		if (test) {
			if (!readFromString(true, input)) {
				sb.append("Is not valid input!");
				return false;
			}
		}
		int startI = 0;
		int startJ = 0;
		if (!listDropHouses.isEmpty()) {
			while (!listDropHouses.isEmpty()) {
				double min_value = Double.MAX_VALUE;
				int index = -1;
				for (int i = 0; i < listDropHouses.size(); i++) {

					double iValue = euclideanHeuristic(startI, startJ, listDropHouses.get(i));
					if (iValue < min_value) {
						min_value = iValue;
						index = i;
					}
				}
				House tmp = listDropHouses.get(index);
				astarAlgorithm(new House(startI, startJ), tmp);

				startI = tmp.x;
				startJ = tmp.y;
				listDropHouses.remove(index);
			}
			System.out.println(sb.toString());
			return true;
		} else {
			sb.append("We don't have any elements in the dropList!");
			return false;
		}
	}

	public static void main(String[] args) throws IOException {

		System.out.println("*****************************************");
		System.out.println("\t\tPIZZABOT\t\t");
		System.out.println("*****************************************");
		System.out.println("Choose the testing of this application:");
		System.out.println("Press (1) for automation testing !");
		System.out.println("Press (2) for custom manual testing !");
		System.out.println("Press (0) for exit... ");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		sb = new StringBuilder();
		System.out.println("Automation test active...!");
		
		Result result = JUnitCore.runClasses(AllTests.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if(result.wasSuccessful()) {
			System.out.println("Successful - PASSED ! ");			
		}
		else {
			System.out.println("Unsuccessful - FAILED ! ");
		}
		
		System.out.println();
		System.out.println("Choose the testing of this console application:");
		System.out.println("Press (1) for custom manual testing !");
		System.out.println("Press (0) for exit... ");
		
		while((s = br.readLine()) != "0") {
			switch(s) {
				case "1":
					sb = new StringBuilder();
					listDropHouses = new LinkedList<>();
					System.out.println(sb.toString());
					System.out.println("Manual testing...!");
					if(readFromString(false, "")) {
						startAlgorithm(false, "");
					}
					else {
						sb.append("Is not valid input!");
						System.out.println("Please enter the valid command input: \n"
								+ " 'pizzabot'_(space)\"NxM(number of rows and cols)_(space) list of points ex.(1,_(space)1)");
						System.out.println("Example: pizzabot \"2x2 (0, 0) (1, 1)\"");
					}
					
					System.out.println();
					System.out.println("Choose the testing of this console application:");
					System.out.println("Press (1) for custom manual testing !");
					System.out.println("Press (0) for exit... ");

					break;
				case "0":
					System.out.println("EXIT !");
					return;
				default:
					System.out.println("Invalid number pressed!");
					System.out.println("Choose the testing of this console application:");
					System.out.println("Press (1) for custom manual testing !");
					System.out.println("Press (0) for exit... ");
					break;
			}
		}
	}

}
