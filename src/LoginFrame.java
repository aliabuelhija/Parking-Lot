
import javax.swing.*;

import Components.ButtonCastom;
import MySqlFacde.HelperFacade;

import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class LoginFrame extends FrameStructure implements ActionListener{
	JLabel userLabel;
	JLabel passwordLabel;
	JTextField userTextField;
	JPasswordField passwordField;

	
	ButtonCastom loginButton;
	ButtonCastom resetButton;
	ButtonCastom back;
	
	ImageIcon background;
	JLabel backgroundLabel;

	public LoginFrame() {
		super("LOGIN");
	}

	void actionEvent() {
		loginButton.addActionListener(this);
		resetButton.addActionListener(this);
		back.addActionListener(this);
	}

	public void addComponentsToFrame() {
		this.add(backgroundLabel);
		backgroundLabel.add(userLabel);
		backgroundLabel.add(passwordLabel);
		backgroundLabel.add(userTextField);
		backgroundLabel.add(passwordField);
		backgroundLabel.add(loginButton);
		backgroundLabel.add(resetButton);
		backgroundLabel.add(back);

	}

	public void createWindow() {
		this.setTitle("Login Form");
		this.setBounds(40, 40, 612, 421);
		this.getContentPane().setBackground(Color.gray);
		this.getContentPane().setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void setLocationsAndSize() {
		userLabel = new JLabel("USERNAME");
		passwordLabel = new JLabel("PSSWROD");
		userTextField = new JTextField();
		passwordField = new JPasswordField();
		loginButton = new ButtonCastom("LOGIN",180, 235, 100, 30 );
		resetButton = new ButtonCastom("RESET",320, 235, 100, 30);
		back = new ButtonCastom("BACK",2, 350, 100, 30);
		background = new ImageIcon(this.getClass().getResource("background.jpg"));
		backgroundLabel = new JLabel(background);
		
		
		backgroundLabel.setSize(612, 421);
		userLabel.setBounds(200, 110, 70, 20);
		userTextField.setBounds(200, 130, 150, 30);
		passwordLabel.setBounds(200, 170, 70, 20);
		passwordField.setBounds(200, 190, 150, 30);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			String userText = userTextField.getText();
			String pwdText = passwordField.getText();
			try {
				ResultSet rs = HelperFacade.validateAccess("MYSQL", userText, pwdText);
				if (rs.next()) {
				JOptionPane.showMessageDialog(null, " Login Successfully!");
				this.dispose();
				//new Menu(new User(userText, rs.getInt("LIKES"), rs.getString("REGION")));
				FrameFactory.getFrame("MENU", new User(userText, rs.getInt("LIKES"), rs.getString("REGION")));
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect Password / Username");
			}
			} catch (SQLException | HeadlessException e2) {
				JOptionPane.showMessageDialog(null, "Problem Occured!");
			}
		}

		if (e.getSource() == resetButton) {
			userTextField.setText("");
			passwordField.setText("");
		}
		if (e.getSource() == back) {
			this.dispose();
			FrameFactory.getFrame("ACCESS", null);
			
		}

	}

}
