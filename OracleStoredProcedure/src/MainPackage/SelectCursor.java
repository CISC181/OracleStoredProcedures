package MainPackage;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

public class SelectCursor {

	private static dbSetup dbs = new dbSetup("local");

	public static void main(String[] argv) {

		try {

			getRecords();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

	}

	private static void getRecords() throws SQLException {
 
		
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
 
		String getCustInfo = "begin :1 := PKGCUST.Cust_GetAllCust; end;";
 
		try {
			dbConnection = getDBConnection();
			callableStatement = dbConnection.prepareCall(getCustInfo);
			
			callableStatement.registerOutParameter(1,OracleTypes.CURSOR); 
			callableStatement.execute(); 
		      ResultSet rset = (ResultSet)callableStatement.getObject(1); 

		      while (rset.next ()) 
		      {
		        System.out.println("CustID: " +  rset.getString (1) );
		      System.out.println("Cust Name: " +  rset.getString (2) );
		      System.out.println("Gender: " +  rset.getString (3) );
		      }
		      callableStatement.close(); 

 
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
					dbs.getDB_USER(), dbs.getDB_PASSWORD());
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}

}