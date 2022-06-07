package Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import MySqlFacde.HelperFacade;

public class CustomTableModel{
	public DefaultTableModel model = new DefaultTableModel();
	JFrame f;
	public JTable jt;
	
	public CustomTableModel(JFrame f) {
		jt= new JTable(model) ;
		model.addColumn("ADDRESS");
		model.addColumn("DATE");
		this.jt.setPreferredScrollableViewportSize(new Dimension(300, 180));
		this.f = f;
		this.jt.setOpaque(false);
		this.jt.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		this.jt.getTableHeader().setBackground(new Color(32,136,203));
		this.jt.getTableHeader().setForeground(new Color(255,255,255));
		this.jt.setRowHeight(25);
	}


	public void pickSpotOption() {
		
		int a = JOptionPane.showConfirmDialog(f, "Do you want to take that spot ?");
		int state = 0;
		if (a == JOptionPane.YES_OPTION) {
			
			int b = JOptionPane.showConfirmDialog(f, "Do you want to give a like to the reporter user?");
			String address = (String) this.jt.getModel().getValueAt(this.jt.getSelectedRow(), 1);
			
			if (b == JOptionPane.YES_OPTION) {
				String user = "";
					HelperFacade.updateTable("MYSQL", "LIKES", address);
				}

			HelperFacade.updateTable("MYSQL", "AVAILABLE", address);
			this.model.removeRow(this.jt.getSelectedRow());
			}	

		}
//		}

	public void addListener() {		
	this.jt.addMouseListener(new MouseAdapter() {
		  public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 1) {
		      pickSpotOption();
		    }
		  }
		});

}

}
