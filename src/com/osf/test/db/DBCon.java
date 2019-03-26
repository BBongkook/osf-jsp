package com.osf.test.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.osf.test.io.ReadFile;

public class DBCon {
	private static final String URL;
	private static final String USER;
	private static final String PASSWORD;
	private static final String DRIVER;
	public static final String CLIENID;
	public static final String CLIENTSECRET;
	public static final String NAVERURL;
	public static final String METHOD;
	private static Connection con = null;
	static {
		InputStream is = ReadFile.class.getResourceAsStream("/com/osf/test/config/db.properties");
		Properties prop = new Properties();
			try {
				prop.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			URL = prop.getProperty("url");
			USER = prop.getProperty("user");
			PASSWORD = prop.getProperty("password");
			DRIVER = prop.getProperty("classname");
			CLIENID = prop.getProperty("clientId");
			CLIENTSECRET = prop.getProperty("clientSecret");
			NAVERURL = prop.getProperty("naverUrl");
			METHOD = prop.getProperty("method");
	}	
	
	
	public static Connection openCon() {
		if(con==null) {
			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return con;
	}
	public static void close() {
		if(con!=null) {
			try {
				if(!con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		con = null;
	}
}
