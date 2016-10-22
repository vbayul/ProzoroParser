package org.balu.prozoBot.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.balu.prozoBot.object.Tender;

public class TenderDAOImp implements TenderDAO{

	private Connection connection;
	
	public TenderDAOImp(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void saveTenders(List<Tender> tenders)  {
		
		String sql = "";
		
		try (Statement statement = connection.createStatement())
		{
			statement.executeUpdate(sql);
			//statement.setString(parameterIndex, x);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}  
	}

	@Override
	public List<Tender> getNewTenders() {
		
		String sql ="";
		ResultSet resultSet;
		
		try (Statement statement = connection.createStatement())
		{
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				resultSet.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void setStatusSend(Tender tender) {
		
		String sql = "";
		
		try (Statement statement = connection.createStatement())
		{
			statement.executeUpdate(sql);
			//statement.setString(parameterIndex, x);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}