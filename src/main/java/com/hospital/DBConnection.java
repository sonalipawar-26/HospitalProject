package com.hospital;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	private static String driver_class="";
	private static String database_url="";
	private static String database_name="";
	private static String database_username="";
	private static String database_password="";
	private static String file_name="db_info.properties";
	private static Connection connection=null;
	
	private static void readDatabaseInfo() {
		Properties properties=new Properties();
		FileInputStream fileInputStream=null;
		try {
			fileInputStream=new FileInputStream(file_name);
			properties.load(fileInputStream);
			driver_class=properties.getProperty("driver_class");
			database_url=properties.getProperty("database_url");
			database_name=properties.getProperty("database_name");
			database_username=properties.getProperty("database_username");
			database_password=properties.getProperty("database_password");
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}catch(IOException e) {
			System.out.println(e);
		}
		
	}
	static Connection getConnection() {
		readDatabaseInfo();
		try {
			Class.forName(driver_class);
			connection=DriverManager.getConnection(database_url+database_name, database_username, database_password);
			System.out.println("Connection Succusessfull...");
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}catch(SQLException e) {
			System.out.println(e);
		}
		return connection;
	}
	private static void closeConnection() {
		try{
			if(connection!=null) {
				connection.close();
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	public static void main(String[] args) {
		DBConnection.getConnection();
	}
}
