
import javax.swing.*;

import Components.ButtonCastom;

import java.awt.event.*;
import java.awt.*;
import java.sql.*;


public class Access extends FrameStructure implements ActionListener {

	ButtonCastom loginButton;
	ButtonCastom registerButton;;
	ImageIcon background;
	JLabel backgroundLabel;

	Access() {
		super("PARKING");
	}
	
	public void actionEvent() {
		loginButton.addActionListener(this);
		registerButton.addActionListener(this);	
	}
	
	public void addComponentsToFrame () {
		this.add(backgroundLabel);
		backgroundLabel.add(loginButton);
		backgroundLabel.add(registerButton);
	}

	public void setLocationsAndSize() {

		loginButton = new ButtonCastom("LOGIN", 200, 150, 165, 30);
		registerButton = new ButtonCastom("REGISTER", 200, 200, 165, 30);
		background = new ImageIcon(this.getClass().getResource("background.jpg"));
		backgroundLabel = new JLabel(background);
		
		backgroundLabel.setSize(612, 421);

		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			this.dispose();
			FrameFactory.getFrame("LOGIN", null);
		}
		
		if (e.getSource() == registerButton) {
			this.dispose();
			FrameFactory.getFrame("REG", null);
		}
	
	}

	@Override
	public void createWindow(String name) {
		super.createWindow(name);

	}

	
}
