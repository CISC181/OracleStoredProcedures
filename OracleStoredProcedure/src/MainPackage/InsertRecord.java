package MainPackage;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
 
public class InsertRecord {

	private static dbSetup dbs = new dbSetup("local");
	public static void main(String[] argv) {
 
		try {
			InsertRec();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
 		}
 	}
	
	private static void InsertRec() throws SQLException {
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
 
		String getCustInfo = "{? = call PKGCUST.Cust_Insert(?,?)}";
 
		try {
			dbConnection = getDBConnection();
			callableStatement = dbConnection.prepareCall(getCustInfo);
 
			callableStatement.registerOutParameter(1, Types.INTEGER);
			callableStatement.setString(2,"Jim");
			callableStatement.setString(3,"M");
 
			callableStatement.executeUpdate();
 
			int iCustID = callableStatement.getInt(1);
			System.out.println("CustID Created: " + iCustID);
 
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
 
			Class.forName(dbs.getDB_DRIVER());
 
		} catch (ClassNotFoundException e) {
 
			System.out.println(e.getMessage());
 
		}
 
		try {
 			dbConnection = DriverManager.getConnection(dbs.getDB_CONNECTION(), 
 					dbs.getDB_USER(), 
 					dbs.getDB_PASSWORD());
 			
			return dbConnection;
 		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 		}
 		return dbConnection;
 	}
 
}