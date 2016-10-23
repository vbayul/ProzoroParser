package org.balu.prozoBot.dao;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectDB {

	private String url ;
	private String driver ;
	private String username ;
	private String password ;

	public ConnectDB()
	{
		//создаем объект настроек  
		Properties props = new Properties();
		// пробуе считать с файла настроек параметры настроек к базе
	    try (InputStream in = Files.newInputStream(Paths.get("database.properties")))
	    {
	    	props.load(in);
	    } catch (IOException e) {
			e.printStackTrace();
		}

	    // получаем к каждому полю свои настрокий
	    driver = props.getProperty("jdbc.drivers");
	    if (driver != null) System.setProperty("jdbc.drivers", driver);
	    url = props.getProperty("jdbc.url");
	    username = props.getProperty("jdbc.username");
	    password = props.getProperty("jdbc.password");

	}
	
	public Connection getConnection() throws SQLException
	{
		// регистриуем драйвера коннекта к базе
		try
		{
			Class.forName(driver);
		}
		catch (ClassNotFoundException ex)
		{
			ex.getStackTrace();
		}
		return DriverManager.getConnection(url, username, password);
	}
}
