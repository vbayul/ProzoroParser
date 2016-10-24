package org.balu.prozoBot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.balu.prozoBot.object.Tender;

public class TenderDAOImp implements TenderDAO{

	private Connection connection;
	
	public TenderDAOImp(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void addTenders(List<Tender> tenders)  {
		
		String sql = "INSERT INTO [balu].[dbo].[tenders] "
				+" ([id],[name],[company],[price],[url],[status]) "
				+" VALUES(?,?,?,?,?,?)";
		
		try (PreparedStatement statement = connection.prepareStatement(sql))
		{
			for (Tender tender : tenders) {
				statement.setString(1, tender.getID());
				statement.setString(2, tender.getName());
				statement.setString(3, tender.getCompany());
				statement.setString(4, tender.getPrice());
				statement.setString(5, tender.getURL());
				statement.setInt(6, 0);
				
				statement.executeUpdate();
			}
		} 
		catch (SQLException e) 
		{
		}  
	}

	@Override
	public List<Tender> getNewTenders() {
		
		List<Tender> tenders = new ArrayList<>();
		
		String sql = "SELECT [id],[name],[company],[price],[url] "
				+" FROM [balu].[dbo].[tenders] WHERE [status] = 0";
		ResultSet resultSet;
		
		
		try (PreparedStatement statement = connection.prepareStatement(sql))
		{
			resultSet = statement.executeQuery();
			
			TenderObjectCreat createTender = new TenderObjectCreat();
			
			while (resultSet.next()) 
			{
				tenders.add(createTender.createTender(resultSet));
			}
		} 
		catch (SQLException e) 
		{
		}
		return tenders;
	}

	@Override
	public void setTendersStatusSend(List<Tender> tenders) {
		
		String sql = "UPDATE [balu].[dbo].[tenders] "
				+ "SET [status] = 1 "
				+ " WHERE [id] = ?";
		
		try (PreparedStatement statement = connection.prepareStatement(sql))
		{
			for (Tender tender : tenders) {
				statement.setString(1, tender.getID());
				statement.executeUpdate();
			}
		} 
		catch (SQLException e)
		{
		}  
	}
}