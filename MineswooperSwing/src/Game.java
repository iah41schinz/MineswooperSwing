import java.awt.GridLayout;
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
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.numBombs = numBombs;
		JPanel Panel = new JPanel();
		this.setSize(400,400);
		this.setResizable(false);
		Panel.setLayout(new GridLayout(x,y));
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
Iteration:	for (int i = 0; i < Cells.length; i++) {

				for (int j = 0 ;j < Cells[i].length; j++) {
					if(e.getSource() == Cells[i][j]) {
						EventSource = (Cell)e.getSource();
						x = j;
						y = i;
						break Iteration;
					}
				}
			}	
			if(SwingUtilities.isRightMouseButton(e)) {
				//Flagging a flagged Cell removes the Flag
				if(Cells[y][x].Flagged == true) {
					if(Cells[y][x].Bomb == true) {
						defusedBombs--;
					}
					Cells[y][x].Flagged = false;
				}
				else if(Cells[y][x].Hidden == true)
					if(Cells[y][x].Bomb == true) {
						defusedBombs++;
					}
					Cells[y][x].Flagged = true;
				Cells[y][x].setIcon(Cells[y][x].getStateIcon());
				if(defusedBombs == numBombs) {
					//TODO WINSCREEN
				}
			}
			else if(SwingUtilities.isLeftMouseButton(e)) {
				//Digging a flagged Cell is not possible
				if(Cells[y][x].Flagged == false) {
					if(Cells[y][x].Bomb == true) {
						Cells[y][x].Hidden = false;
						Cells[y][x].setIcon(Cells[y][x].getStateIcon());
						//TODO GAMEOVERSCREEN
					}
					//Digging an open Cell is not possible
					if(Cells[y][x].Hidden == true) {
					openCell(x,y);
					}
				}

			}
		}
		
	}
//TODO Algorithm is almost working
	private int openCell(int x, int y) {
		//check if the coordinates exist
		if(x >= 0 && x <= Cells[0].length - 1 && y >= 0 && y <= Cells.length - 1) {
			Cells[y][x].Hidden = false;
			//if there is a Bomb at (x,y) stop the method
			if(Cells[y][x].Bomb == true) {
				return 0;
			}
			
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
						else if(Cells[tmpY][tmpX].Hidden == true){
							openCell(tmpX, tmpY);
						}
						
					}
					
				}
			}
			Cells[y][x].setIcon(Cells[y][x].getStateIcon());

		}
		return 0;
	}
	
	public void unveilCells() {
		for (int i = 0; i < Cells.length; i++) {

				for (int j = 0 ;j < Cells[i].length; j++) {
						Cells[i][j].Hidden = false;
						Cells[i][j].setIcon(Cells[i][j].getStateIcon());

				}
		}	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
