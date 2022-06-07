package Components;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class MenuLabel extends JLabel{
	
	public MenuLabel(String name) {
		super(name);
		this.setFont(new Font("", Font.BOLD, 20));
		this.setForeground(Color.WHITE);
	}

}
