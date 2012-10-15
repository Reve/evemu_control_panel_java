

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBConnect {
	
	private static Connection con = null;
	
	public static void TestConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://"+ Settings.server + "/" + Settings.dbName, Settings.user,
					Settings.pass);
			if(!con.isClosed())
				JOptionPane.showMessageDialog(null, "Connected to " + Settings.dbName, "Test Connection ", JOptionPane.INFORMATION_MESSAGE);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Exception: " + e.getMessage(), "Error ", JOptionPane.ERROR_MESSAGE);
			System.err.println("Exception: " + e.getMessage());
		}
		finally {
		    try {
		        if(con != null)
		          con.close();
		    } 
		    catch(SQLException e) {}
	    }
	}
}