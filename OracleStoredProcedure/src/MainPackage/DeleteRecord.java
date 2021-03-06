package MainPackage;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
 
public class DeleteRecord {
 
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER = "Bert";
	private static final String DB_PASSWORD = "Circuit1";
	
	
	public static void main(String[] argv) {
 
		try {
 
			DeleteRec();
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 		}
 	}
 
	
	private static void DeleteRec() throws SQLException {
 
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
 
		String getCustInfo = "{call PKGCUST.Cust_Delete(?)}";
 
		try {
			dbConnection = getDBConnection();
			callableStatement = dbConnection.prepareCall(getCustInfo);
 
			callableStatement.setInt(1,1045);
			callableStatement.executeUpdate();
 
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		} finally {
 
			if (callableStatement != null) {
				callableStatement.close();
			}
 
			if (dbConnection != null) {
				dbConnection.close();
			}
 		}
 	}
 
	private static Connection getDBConnection() {
 
		Connection dbConnection = null;
 
		try {
 
			Class.forName(DB_DRIVER);
 
		} catch (ClassNotFoundException e) {
 
			System.out.println(e.getMessage());
 
		}
 
		try {
 			dbConnection = DriverManager.getConnection(
				DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;
 		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 		}
 		return dbConnection;
 	}
}
