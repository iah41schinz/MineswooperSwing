import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.*;
@SuppressWarnings("serial")
public class Game extends JFrame implements MouseListener{

	Cell[][] Cells;
	Random r = new Random();
	int XofRandom;
	int YofRandom;
	public Game(int x,int y,int numBombs) {
		JPanel Panel = new JPanel();
		Cells = new Cell[y][x];
		//The Cells are generated

		for (int i = 0; i < y - 1; i++) {
			for (int j = 0; j < x - 1; j++) {
				Cells[i][j]  = new Cell();
				Cells[i][j].addMouseListener(this);
			}
			
		}
		//Cells are randomly selected as Bombs
		for (int i = 0; i < numBombs; i++) {
			XofRandom = r.nextInt(x);
			YofRandom = r.nextInt(y);
			if(Cells[YofRandom][XofRandom].Bomb == true) {
				// if the randomly selected Cell is already a bomb
				// the loop is executed again
				i--;
			}
			else {
				Cells[YofRandom][XofRandom].Bomb = true;
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Cell EventSource;
		//get the index of JButton that was pressed
		if (e.getSource() instanceof Cell) {
			for (int i = 0; i < Cells.length; i++) {

				for (int j = 0 ;j < Cells[i].length; j++) {
					if(e.getSource() == Cells[i][j]) {
						EventSource = (Cell)e.getSource();
					}
				}
			}	
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
