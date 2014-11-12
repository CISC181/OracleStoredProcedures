package MainPackage;


import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
 
public class SelectSingleRecord {
 
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER = "Bert";
	private static final String DB_PASSWORD = "Circuit1";
	
	
	public static void main(String[] argv) {
 
		try {
 
			callOracleStoredProcOUTParameter();
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		}
 
	}
 
	
	
	
	private static void callOracleStoredProcOUTParameter() throws SQLException {
 
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
 
		String getCustInfo = "{call getCustInfo(?,?,?)}";
 
		try {
			dbConnection = getDBConnection();
			callableStatement = dbConnection.prepareCall(getCustInfo);
 
			callableStatement.setInt(1, 1021);
			callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(3, java.sql.Types.CHAR);
 
			callableStatement.executeUpdate();
 
			String custName = callableStatement.getString(2).trim();
			String Gender = callableStatement.getString(3).trim();
	
			System.out.println("Gender : " + Gender);

			System.out.println("custName : " + custName);
 
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