import java.util.Scanner;

public class Program {
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x,y,numBombs;
		System.out.println("Specify the games size:");
		System.out.print("X:");
		x = getIntfromInput(1, 100, "Only 1 - 100 allowed");
		System.out.print("Y:");
		y = getIntfromInput(1, 100, "Only 1 - 100 allowed");
		System.out.println("Specify the number of bombs:");
		numBombs = getIntfromInput(1, x*y, "The number of Bombs has to be within the size of the field");
		new Game(x,y,numBombs);
	}

	private static int getIntfromInput(int min, int max,String message) {
		int i;
		do {
			try {
				i = Integer.parseInt(scan.nextLine());
				if (i >= min && i <= max) {
					return i;
				}
				else {
					System.out.println(message);
				}
			} catch (NumberFormatException e) {
				System.out.print("Error: The entered number is not a valid integer.\nTry again:");
			}
		} while (true);
	}
}
