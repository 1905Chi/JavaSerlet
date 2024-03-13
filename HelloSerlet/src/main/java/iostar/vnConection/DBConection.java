package iostar.vnConection;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConection{

	private final String serverName = "ADMIN";
	private final String dbName = "ShopOnline";
	private final String portNumber = "1433";
	private final String instance = "";// MSSQLSERVER LEAVE THIS ONE
	
	//private final String userID = "";
	//private final String password = "";
	
	public Connection getConnectionW() throws Exception {
		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber
		+ "\\" + instance + ";integratedSecurity=true;databaseName=" +
		dbName;
		if (instance == null || instance.trim().isEmpty())
		   url = "jdbc:sqlserver://" + serverName + ":" + portNumber +	";integratedSecurity=true;databaseName=" + dbName;
		//DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url);
		}
	public static void main(String[] args) {
		try {
		System.out.println(new DBConection().getConnectionW());
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
}

