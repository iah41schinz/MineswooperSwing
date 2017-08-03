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
	int defusedBombs;
	int numBombs;


	public Game(int x,int y,int numBombs) {
		this.numBombs = numBombs;
		JPanel Panel = new JPanel();
		this.setSize(400,400);
		Panel.setSize(400,400);
		Cells = new Cell[y][x];
		//The Cells are generated

		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				Cells[i][j]  = new Cell();
				Cells[i][j].addMouseListener(this);
				Panel.add(Cells[i][j]);
			}
			
		}
		this.add(Panel);
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
		this.setVisible(true);
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
		Cell EventSource = null;
		int x = 0;
		int y = 0;
		//get the index of the JButton that was pressed
		if (e.getSource() instanceof Cell) {
			for (int i = 0; i < Cells.length; i++) {

				for (int j = 0 ;j < Cells[i].length; j++) {
					if(e.getSource() == Cells[i][j]) {
						EventSource = (Cell)e.getSource();
						x = j;
						y = i;
					}
				}
			}	
			if(SwingUtilities.isRightMouseButton(e)) {
				if(EventSource.getIcon() == EventSource.flag) {
					EventSource.setIcon(null);
					if(EventSource.Bomb == true)
						defusedBombs--;
				}
				else if(EventSource.getIcon() == null) {
					if(EventSource.Bomb == true)
						defusedBombs++;
					EventSource.setIcon(EventSource.flag);
					if(defusedBombs == numBombs) {
						//TODO Winningscreen
					}
				}
			}
			else if(SwingUtilities.isLeftMouseButton(e)) {
				if(EventSource.Bomb == true) {
					//TODO Gameoverscreen
				}
				else if(EventSource.getIcon() == null) {
					OpenCell(y,x);
				}


			}
		}
		
	}

	private void OpenCell(int y, int x) {
		//Cascade through all neighbor Cells:
		//First count the bombs around the selected Cell
		for (int tmpY = y - 1; tmpY < y + 2; tmpY++) {
xIteration : 	for (int tmpX = x - 1; tmpX < x + 2; tmpX++) {
				if (tmpX == x && tmpY == y || tmpX < 0 || tmpX > Cells[0].length - 1 || tmpY < 0 || tmpY > Cells.length - 1) {
					continue xIteration;
				}
				else if(Cells[tmpY][tmpX].Bomb == true){
					Cells[y][x].neighboringBombs++;		
				}
				
			}
			
		}
		//Then, if there are no bombs 
		//open all surrounding Cells
		if(Cells[y][x].neighboringBombs == 0) {
			for (int tmpY = y - 1; tmpY < y + 2; tmpY++) {
				for (int tmpX = x - 1; tmpX < x + 2; tmpX++) {
					if (tmpX == x && tmpY == y || tmpX < 0 || tmpX > Cells[0].length - 1 || tmpY < 0 || tmpY > Cells.length - 1) {
						continue;
					}
					else if(Cells[tmpY][tmpX].getIcon() == null){
						OpenCell(tmpX, tmpY);
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
