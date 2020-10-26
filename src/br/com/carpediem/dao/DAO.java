package br.com.carpediem.dao;

import java.sql.Connection;
import java.sql.DriverManager;


public abstract class DAO {

	private static Connection connection;
	private static final String user = "root";
	private static final String password = "edvg33";
	private static final String host = "localhost";
	private static final String classForName = "com.mysql.jdbc.Driver";


	public static Connection getConnection() throws Exception {
		
		try {
				conecta();
				return connection;
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw ex;
				
		}
	}


	
	public static void conecta() throws Exception {
		try {
			Class.forName(classForName);
			connection = DriverManager.getConnection("jdbc:mysql://"+ host +":3306/carpediem", user, password);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;

		}
	}
	
}
