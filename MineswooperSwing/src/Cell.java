import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Cell extends JButton{

	public boolean Bomb;
	ImageIcon flag,bomb,empty,num1,num2,num3,num4,num5,num6,num7,num8;
	public int neighboringBombs;
	public Cell() {
		Bomb = false;
	}
}
