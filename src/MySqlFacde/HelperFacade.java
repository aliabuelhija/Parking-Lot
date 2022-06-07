package MySqlFacde;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class HelperFacade {

	public static Connection MysqlCon = MySqlSingleton.getInstance().con;
	
	public static ResultSet validateAccess(String dbType, String userName, String password){
		switch (dbType){
		case "MYSQL": 

			return MySqlSingleton.validateAccess(MysqlCon, userName, password);			
		}	
		return null;	
	}
	
	public static boolean isAlreadyExist(String dbType, String field, String value, String region){
		switch (dbType){
		case "MYSQL":

			switch (field){
			case "USER": 
				return MySqlSingleton.userAlreadyExist(MysqlCon, value);
			case "ADDRESS": 
				return MySqlSingleton.addressAlreadyExist(MysqlCon, value, region);
			}		
		}	
		return false;	
	}
		
	
	public static void addRow(String dbType, String table, String field_1, String field_2, String field_3, String field_4){
		switch (dbType){
		case "MYSQL": 

			
			switch (table){
			case "USERS": 
				MySqlSingleton.addUser(MysqlCon, field_1, field_2, field_3, field_4);
				break;
			case "LOTS": 
				MySqlSingleton.addLot(MysqlCon, field_1, field_2, field_3, field_4);
				break;
			}		
			break;
		}		
	}
	
	public static void fillTable(String dbType, String table, String userName, String region, String address, DefaultTableModel model){
		switch (dbType){
		case "MYSQL": 

			
			switch (table){
			case "USERLOTS": 
				MySqlSingleton.getUserLots(MysqlCon, userName, model);
				break;
			case "SEARCHLOTS": 
				MySqlSingleton.getSearchLots(MysqlCon, model, userName, region, address);
				break;
			}		
		}
	}
	
	public static void updateTable(String dbType, String field, String address){
		switch (dbType){
		case "MYSQL": 

			switch (field){
			case "AVAILABLE": 
				MySqlSingleton.updateAvailability(MysqlCon, address);
				break;
			case "LIKES": 
				MySqlSingleton.updateLikes(MysqlCon, address);
				break;
			}		
		}
	}
}
