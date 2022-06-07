import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public abstract class FrameStructure extends JFrame {
	int likes;
	String name;
	String region;

	FrameStructure(String name) {
		setLocationsAndSize();
		createWindow(name);
		addComponentsToFrame();
		actionEvent();

	}

	public void createWindow(String name) {

		this.setTitle(name);
		this.setBounds(40, 40, 612, 421);
		this.getContentPane().setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	abstract void setLocationsAndSize();

	abstract void addComponentsToFrame();

	abstract void actionEvent();

}
