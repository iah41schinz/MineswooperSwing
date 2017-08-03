import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Cell extends JButton{

	public boolean Bomb;
	ImageIcon hidden,flag,bomb,empty,num1,num2,num3,num4,num5,num6,num7,num8;
	public int neighboringBombs;
	public Cell() {
		Bomb = false;
		hidden = new ImageIcon(this.getClass().getResource("hidden.png"));
		flag = new ImageIcon(this.getClass().getResource("flag.png"));
		bomb = new ImageIcon(this.getClass().getResource("bomb.png"));
		empty = new ImageIcon(this.getClass().getResource("empty.png"));
		num1 = new ImageIcon(this.getClass().getResource("num1.png"));
		num2 = new ImageIcon(this.getClass().getResource("num2.png"));
		num3 = new ImageIcon(this.getClass().getResource("num3.png"));
		num4 = new ImageIcon(this.getClass().getResource("num4.png"));
		num5 = new ImageIcon(this.getClass().getResource("num5.png"));
		num6 = new ImageIcon(this.getClass().getResource("num6.png"));
		num7 = new ImageIcon(this.getClass().getResource("num7.png"));
		num8 = new ImageIcon(this.getClass().getResource("num8.png"));
		this.setIcon(hidden);
	}
}
