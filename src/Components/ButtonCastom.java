package Components;
import java.awt.Color;
import javax.swing.JButton;


public class ButtonCastom extends JButton{
	
	public ButtonCastom(String name, int x, int y, int w, int h) {
		super(name);
		this.setBounds(x,y,w,h);
		this.setForeground(Color.WHITE);
		this.setBackground(Color.BLACK);
		this.setFocusPainted(false);
	}

}
