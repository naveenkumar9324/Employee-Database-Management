import java.sql.*;
import javax.swing.*;
public class EmployeeData {
	
	public static Connection ConnectDB()
	{
		try
		{
			Class.forName("org.sqlite.jdbc");
			Connection conn=DriverManager.getConnection
					("jdbc:sqlite:C:\\Users\\nk353\\eclipse-workspace\\EmployeeData\\employee.db");
					JOptionPane.showMessageDialog(null, "Connection made");
					return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Connection Error");
			return null;
		}
	}

}
