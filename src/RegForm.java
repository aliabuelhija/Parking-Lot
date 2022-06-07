
import javax.swing.*;

import Components.ButtonCastom;
import MySqlFacde.HelperFacade;

import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class RegForm extends FrameStructure implements ActionListener{

	ImageIcon background;
	JLabel backgroundLabel;

	JLabel userNameLabel;
	JLabel passwordLabel;
	JLabel confirmPasswordLabel;
	JTextField userNameTextField;
	JPasswordField passwordField;
	JPasswordField confirmPasswordField;

	ButtonCastom registerButton;
	ButtonCastom restButton;
	ButtonCastom back;
	

	JLabel regions;
	JLabel regsionChoose;
	String[] reg;
	JComboBox regionsBox;



	RegForm() {
		super("REGISTRATION");
	}

	public void createWindow() {
		super.createWindow("REGISTRATION");

	}

	public void setLocationsAndSize() {
		
		userNameLabel = new JLabel("USER");
		passwordLabel = new JLabel("PASSWORD");
		confirmPasswordLabel = new JLabel("COFIRM");
		userNameTextField = new JTextField();
		passwordField = new JPasswordField();
		confirmPasswordField = new JPasswordField();


		restButton = new ButtonCastom("RESET", 261, 251, 85, 21);
		registerButton = new ButtonCastom("REGISTER", 497, 335, 111, 31);
		back = new ButtonCastom("BACK",0, 336, 100, 30);
		

		regions = new JLabel("REGIONS");
		regsionChoose = new JLabel("REGIONS");
		String[] reg = { "TELAVIV", "HAIFA", "ELIAT", "RAMTGAN" };
		regionsBox = new JComboBox(reg);


		
		
		
		background = new ImageIcon(this.getClass().getResource("background.jpg"));
		backgroundLabel = new JLabel("New label");
		backgroundLabel.setIcon(background);
		backgroundLabel.setBounds(-196, 0, 804, 393);

		userNameLabel.setBounds(141, 87, 87, 35);
		passwordLabel.setBounds(141, 120, 111, 35);
		regionsBox.setBounds(228, 195, 160, 23);

		confirmPasswordLabel.setBounds(141, 132, 140, 70);
		userNameTextField.setBounds(228, 93, 160, 23);
		passwordField.setBounds(228, 126, 160, 23);
		confirmPasswordField.setBounds(228, 162, 160, 23);

		regsionChoose.setBounds(141, 171, 160, 70);


	}

	public void addComponentsToFrame() {
		this.getContentPane().add(userNameLabel);
		this.getContentPane().add(passwordLabel);
		this.getContentPane().add(confirmPasswordLabel);
		this.getContentPane().add(userNameTextField);
		this.getContentPane().add(passwordField);
		this.getContentPane().add(confirmPasswordField);
		this.getContentPane().add(regionsBox);
		this.getContentPane().add(regsionChoose);
		this.getContentPane().add(back);
		this.getContentPane().add(restButton);
		this.getContentPane().add(registerButton);
		this.getContentPane().add(backgroundLabel);

	}

	public void actionEvent() {
		registerButton.addActionListener(this);
		restButton.addActionListener(this);
		back.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == registerButton) {

				if (!(passwordField.getText().equals(confirmPasswordField.getText()))) {
					JOptionPane.showMessageDialog(null, "Password did not matched");
				} else if (passwordField.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Password should contain more then one char!");
				}
					
				else {
					boolean rs = HelperFacade.isAlreadyExist("MYSQL", "USER", userNameTextField.getText(), "");
					if (rs) {
					JOptionPane.showMessageDialog(null,
							"User name already exist. Please choose another username! ");

					} else {
						HelperFacade.addRow("MYSQL", "USERS", userNameTextField.getText(), passwordField.getText() , confirmPasswordField.getText(), regionsBox.getSelectedItem().toString());
						JOptionPane.showMessageDialog(null, "User created successfully!");
						this.dispose();
						FrameFactory.getFrame("MENU", new User(userNameTextField.getText(), 0, regionsBox.getSelectedItem().toString()));
					}
				}

		}

		if (e.getSource() == restButton) {
			// Clear Data
			userNameTextField.setText("");
			passwordField.setText("");
			confirmPasswordField.setText("");

		}
		if (e.getSource() == back) {
			this.dispose();
			FrameFactory.getFrame("ACCESS", null);

		}

	}

}
