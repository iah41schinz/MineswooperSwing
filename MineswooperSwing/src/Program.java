import java.util.Scanner;

public class Program {
	static final int maxGameSize = 1000;
	static final int minGameSize = 25;
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x,y,numBombs;
		int numCells = getIntfromPanelInput(minGameSize,maxGameSize,"Number of Cells","Only " + minGameSize + " - " + maxGameSize + " allowed");
		x = y = (int) Math.sqrt(numCells);
		numBombs = getIntfromPanelInput(1,x*y,"Number of bombs","There can not be more bombs than Cells");
		new Game(x,y,numBombs);
	}

	private static int getIntfromConsoleInput(int min, int max,String message) {
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
	
	static int getIntfromPanelInput(int min, int max,String ValueName, String message) {
		int i;
		javax.swing.JOptionPane InputWindow = new javax.swing.JOptionPane();
		do {
			try {
				i = Integer.parseInt(InputWindow.showInputDialog( ValueName ));

				if (i >= min && i <= max) {
					return i;
				}
				else {
					javax.swing.JOptionPane.showMessageDialog(InputWindow, message);
				}
			} catch (NumberFormatException e) {
				javax.swing.JOptionPane.showMessageDialog(InputWindow, "Error: The entered number is not a valid integer.\nTry again:");
			}
		} while (true);
	}
}
