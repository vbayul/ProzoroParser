package org.balu.prozoBot.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.balu.prozoBot.object.Tender;

public class TenderObjectCreat {

	public Tender createTender(ResultSet resultSet)
	{
		Tender tender = new Tender();

		try {
			tender.setID(resultSet.getString(1));
			tender.setName(resultSet.getString(2));
			tender.setCompany(resultSet.getString(3));
			tender.setPrice(resultSet.getString(4));
			tender.setURL(resultSet.getString(5));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tender;
	}
}
