
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Components.ButtonCastom;
import Components.CustomTableModel;
import Components.HintTextField;
import Components.MenuLabel;
import MySqlFacde.HelperFacade;

import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Menu extends FrameStructure implements ActionListener {

	ButtonCastom searchButton;
	ButtonCastom addButton;
	ButtonCastom profileButton;
	ButtonCastom addSpotButton;
	ButtonCastom searchSpot;
	

	int likes;
	String name;
	String region;

	JPanel menuButtons;
	JPanel infoPanel;
	JPanel lotsPanel;
	JPanel addLotPanel;

	JPanel searchLotPanel;

	ImageIcon background;

	JLabel backgroundLabel;

	JTextField addressTextField;
	JTextField searchAddressTextField;
	CustomTableModel profileTable;
	CustomTableModel searchResultTable;


	Menu(User user) {
		super("MENU");
		this.likes = user.likes;
		this.name = user.userName;
		this.region = user.region;

		createInfo();
		createAddLot();
		createSearchLot();
		getUserLots();
	}

	public void createSearchLot() {
		searchLotPanel = new JPanel();
		searchLotPanel.setBounds(30, 40, 500, 250);
		searchLotPanel.setBackground(Color.gray);
		searchLotPanel.setOpaque(false);
		searchLotPanel.add(searchAddressTextField);
		searchLotPanel.add(searchSpot);
		JScrollPane sp_a = new JScrollPane(searchResultTable.jt);
		sp_a.setOpaque(false);
		sp_a.setBounds(30, 40, 100, 100);
		searchLotPanel.add(sp_a);
		searchResultTable.addListener();

		backgroundLabel.add(searchLotPanel);
		searchLotPanel.show(false);

	}

	public void createAddLot() {
		addLotPanel = new JPanel() {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(300, 300);
			};
		};
		addLotPanel.setBounds(30, 120, 500, 250);
		addLotPanel.setOpaque(false);
		addLotPanel.add(addressTextField);
		addLotPanel.add(addSpotButton);
		backgroundLabel.add(addLotPanel);
		addLotPanel.show(false);
	}

//	use Mysql;
//	create table lots(USERNAME varchar(30) not null, REGION varchar(30), ADDRESS varchar(30), AVAILABLE boolean);

	void getUserLots() {
		HelperFacade.fillTable("MYSQL","USERLOTS", this.name, "", "", profileTable.model);
	}

	void createInfo() {
		infoPanel = new JPanel();
		infoPanel.setBounds(30, 40, 500, 250);
		infoPanel.setBackground(Color.gray);
		infoPanel.setOpaque(false);
		
		MenuLabel userNameLabel = new MenuLabel("USER:");
		infoPanel.add(userNameLabel);

		JTextArea userText = new JTextArea(this.name, 1, 5);
		infoPanel.add(userText);
		MenuLabel likesLabel = new MenuLabel("LIKES:");

		infoPanel.add(likesLabel);
		JTextArea likesText = new JTextArea(Integer.toString(this.likes), 1, 5);
		infoPanel.add(likesText);
		MenuLabel regionLabel = new MenuLabel("REGION:");
		infoPanel.add(regionLabel);
		JTextArea regionText = new JTextArea(this.region, 1, 5);
		infoPanel.add(regionText);

		lotsPanel = new JPanel();
		JScrollPane sp = new JScrollPane(profileTable.jt);
		sp.setOpaque(false);
		lotsPanel.add(sp);
		infoPanel.add(lotsPanel);
		infoPanel.show(false);

		backgroundLabel.add(infoPanel);

	}

	public void actionEvent() {
		searchButton.addActionListener(this);
		addButton.addActionListener(this);
		profileButton.addActionListener(this);
		addSpotButton.addActionListener(this);
		searchSpot.addActionListener(this);
	}

	public void addComponentsToFrame() {
		backgroundLabel.add(searchButton);
		backgroundLabel.add(addButton);
		backgroundLabel.add(profileButton);
		
		backgroundLabel.setSize(612, 421);
	
	}

	public void setLocationsAndSize() {
		searchButton = new ButtonCastom("SEARCH", 453, 300, 85, 21 );
		addButton = new ButtonCastom("ADD", 47, 302, 85, 21);
		profileButton = new ButtonCastom("PROFILE", 249, 302, 100, 21);
		addSpotButton = new ButtonCastom("ADD NEW SPOT",47, 302, 85, 21 );
		searchSpot = new ButtonCastom("SEARCH SPOT", 453, 300, 85, 21);
		

		
		background = new ImageIcon(this.getClass().getResource("background.jpg"));

	    backgroundLabel = new JLabel(background);

		addressTextField = new HintTextField("Enter an address...");
		searchAddressTextField = new HintTextField("Enter an address...");
		profileTable = new CustomTableModel(this);
		searchResultTable = new CustomTableModel(this);
		
		this.add(backgroundLabel);


	}

	public void createWindow() {

super.createWindow("MENU");
	}

	void searchSpotHandler() {
			if ((searchAddressTextField.getText().equals(""))) {
				JOptionPane.showMessageDialog(null, "The input is invalid");
			} else {
				DefaultTableModel model = (DefaultTableModel) searchResultTable.jt.getModel();
				model.setRowCount(0);
				HelperFacade.fillTable("MYSQL", "SEARCHLOTS", this.name, this.region, this.searchAddressTextField.getText(), searchResultTable.model);
			}

	}

	boolean addressAlreadyExist(String address) {
		
		return HelperFacade.isAlreadyExist("MYSQL", "ADDRESS", address, this.region);
	}

	void addSpotHandler() {

			if ((addressTextField.getText().equals("") || addressAlreadyExist(addressTextField.getText()))) {
				JOptionPane.showMessageDialog(null, "The input is invalid OR duplicated");
			} else {

				LocalDateTime ltime = LocalDateTime.now();
				DateTimeFormatter ftime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				String vtime = ftime.format(ltime);
				HelperFacade.addRow("MYSQL", "LOTS", this.name, this.region, addressTextField.getText(), vtime);
				
				JOptionPane.showMessageDialog(null, "Lot has been added successfully... Thank you!");
				String arr[] = { vtime, addressTextField.getText() };
				profileTable.model.addRow(arr);
			}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == profileButton) {
			searchLotPanel.show(false);
			infoPanel.setVisible(false);
			addLotPanel.show(false);
			infoPanel.show(true);

		}

		if (e.getSource() == addButton) {
			searchLotPanel.show(false);
			infoPanel.show(false);
			addLotPanel.show(true);
		}

		if (e.getSource() == addSpotButton) {
			addSpotHandler();
		}

		if (e.getSource() == searchButton) {
			infoPanel.show(false);
			addLotPanel.show(false);
			searchLotPanel.show(true);
		}

		if (e.getSource() == searchSpot) {
			searchSpotHandler();
			
		}

	}

}
