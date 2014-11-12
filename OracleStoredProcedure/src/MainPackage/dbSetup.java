package MainPackage;

import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

public class dbSetup {

	private String DB_DRIVER;
	private String DB_CONNECTION;
	private String DB_USER;
	private String DB_PASSWORD;
	
	public String getDB_DRIVER() {
		return DB_DRIVER;
	}

	public void setDB_DRIVER(String dB_DRIVER) {
		DB_DRIVER = dB_DRIVER;
	}

	public String getDB_CONNECTION() {
		return DB_CONNECTION;
	}

	public void setDB_CONNECTION(String dB_CONNECTION) {
		DB_CONNECTION = dB_CONNECTION;
	}

	public String getDB_USER() {
		return DB_USER;
	}

	public void setDB_USER(String dB_USER) {
		DB_USER = dB_USER;
	}

	public String getDB_PASSWORD() {
		return DB_PASSWORD;
	}

	public void setDB_PASSWORD(String dB_PASSWORD) {
		DB_PASSWORD = dB_PASSWORD;
	}

	public dbSetup(String env) {

		String config = "config//" + env + ".properties";

		Properties configProps = new Properties();
		InputStream inStream = null;
		try {
			FileReader reader = new FileReader(config);
			configProps.load(reader);
			DB_DRIVER = (String) configProps.get("DB_DRIVER");
			DB_CONNECTION = (String) configProps.get("DB_CONNECTION");
			DB_USER = (String) configProps.get("DB_USER");
			DB_PASSWORD = (String) configProps.get("DB_PASSWORD");

			System.out.println(DB_DRIVER);
			System.out.println(DB_CONNECTION);
			System.out.println(DB_USER);
			System.out.println(DB_PASSWORD);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}


}
