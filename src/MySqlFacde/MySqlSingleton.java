package MySqlFacde;
import java.awt.HeadlessException;
import java.sql.*;
// https://www.journaldev.com/1557/facade-design-pattern-in-java

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MySqlSingleton {
	
    // Static variable reference of single_instance
    // of type Singleton
    private static MySqlSingleton single_instance = null;
 
    // Declaring a variable of type String
    public Connection con;
 
    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private MySqlSingleton()
    {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/parking", "root","ali123456789");
			
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}
    }
 
    public static MySqlSingleton getInstance()
    {
        if (single_instance == null)
            single_instance = new MySqlSingleton();
 
        return single_instance;
    }
	

	
	public static ResultSet validateAccess(Connection con, String userName, String password) {
		try {
			Statement stat = con.createStatement();
			String sql = "Select * from users Where USERNAME='" + userName + "' and PASSWRD='" + password + "'";
			ResultSet rs = stat.executeQuery(sql);
			return rs;	
		}
		catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}
	
	public static boolean userAlreadyExist(Connection con, String userName) {
		try {
			Statement stat = con.createStatement();
			String sql = "Select * from users Where USERNAME='" + userName + "'";
			ResultSet rs = stat.executeQuery(sql);
			boolean result = rs.next();
			return result;
			
		}catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}
	
	static boolean addressAlreadyExist(Connection con, String address, String region) {
	try {
		Statement stat = con.createStatement();
		String sql = "Select * from lots Where ADDRESS='" + address + "' And AVAILABLE=1 And REGION='"+ region +"'";
		ResultSet rs = stat.executeQuery(sql);
		boolean result = rs.next();
		return result;
		
	} catch (SQLException e1) {
		e1.printStackTrace();

	}
	return false;
}
	
	public static void addUser(Connection con, String userName, String password,String  confirmPassword, String region) {
		try {
			PreparedStatement Pstatement = con
			.prepareStatement("insert into users values(?,?,?,?,?)");
			Pstatement.setString(1, userName);
			Pstatement.setString(2, password);
			Pstatement.setString(3, confirmPassword);
			Pstatement.setInt(4, 0);
			Pstatement.setString(5, region);
			Pstatement.execute();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void addLot(Connection con, String name, String region,String  address, String vtime) {
		try {
			PreparedStatement Pstatement = con.prepareStatement("insert into lots values(?,?,?,?,?)");
			Pstatement.setString(1, name);
			Pstatement.setString(2, region);
			Pstatement.setString(3, address);
			Pstatement.setBoolean(4, true);
			Pstatement.setString(5, vtime);
			Pstatement.execute();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void updateAvailability(Connection con, String address) {
		try {
			Statement stat = con.createStatement();
			String sql = "UPDATE lots SET AVAILABLE=0 Where ADDRESS='" + address + "' And AVAILABLE=1";
			stat.executeUpdate(sql);
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void updateLikes(Connection con, String address) {
		try {
			Statement stat = con.createStatement();
			String sql = "UPDATE users SET likes =likes+1 Where username = (Select USERNAME from lots Where ADDRESS='"
					+ address + "' And AVAILABLE=1)";
			stat.executeUpdate(sql);
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void getUserLots(Connection con, String userName, DefaultTableModel model) {
		try {

			Statement stat = con.createStatement();
			String sql = "Select date , ADDRESS from lots Where USERNAME ='" + userName + "'";
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				String date = rs.getString("date");
				String ADDRESS = rs.getString("ADDRESS");
				String[] data = { date, ADDRESS };
				// this.model.asddRow(arr);
				model.addRow(data);

			}
		}

		catch (SQLException | HeadlessException e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "Problem Occured!");
		}

	}
	
	public static void getSearchLots(Connection con, DefaultTableModel model, String user, String region, String address) {
		try {
			Statement stat = con.createStatement();
			String sql = "Select  * from lots Where AVAILABLE=1 And USERNAME!='" +user +"' And REGION='" + region + "' And ADDRESS Like '" + address + "%'";
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				String[] arr = { rs.getString("date"), rs.getString("ADDRESS") };
				model.addRow(arr);
			}
		} catch (SQLException | HeadlessException e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "Problem Occured!");
		}
		
	}
}

