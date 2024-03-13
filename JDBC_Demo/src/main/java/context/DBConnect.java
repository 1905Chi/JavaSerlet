package context;

import java.sql.Connection;
import java.sql.DriverManager;



public class DBConnect {
	
	private final String serverName = "localhost";
	private final String dbName = "ShopOnline";
	private final String portNumber = "1433";
	private final String instance = "";
	private final String userID = "sa";
	private final String password = "19052002";

	public Connection getConnection() throws Exception {
		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName;
		if (instance == null || instance.trim().isEmpty())
			url = "jdbc:sqlserver://" + serverName  + ";databaseName =" + dbName;
		// Windows Authentication
			//url = "jdbc:sqlserver://" + serverName + ":" + portNumber + 
				//";integratedSecurity=true;databaseName=" + dbName;

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, userID, password);
		//return DriverManager.getConnection(url);
	}

	
	public static void main(String[] args) {
		try {
			System.out.print(new DBConnect().getConnection());
		}catch(Exception ex) {
			System.out.print(ex);
		}
	}
}
