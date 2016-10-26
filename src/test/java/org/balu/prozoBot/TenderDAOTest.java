package org.balu.prozoBot;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.balu.prozoBot.dao.ConnectDB;
import org.balu.prozoBot.dao.TenderDAO;
import org.balu.prozoBot.dao.TenderDAOImp;
import org.balu.prozoBot.object.Tender;
import org.junit.Before;
import org.junit.Test;

public class TenderDAOTest {

	
	private List<Tender> tenders = new ArrayList<Tender>();
	private Tender tender = new Tender();
	@Before
	public void createArrayTenders()
	{
		tender.setID("TestID");
		tender.setName("TestName");
		tender.setCompany("TestCompany");
		tender.setPrice("TestPrice");
		tender.setURL("TestURL");
		
		tenders.add(tender);
	}
	
	@Test
	public void testDB()
	{
		ConnectDB connectionDB = new ConnectDB();
		
		try (Connection connection = connectionDB.getConnection())
		{
			TenderDAO tenderDAO = new TenderDAOImp(connection);		
			tenderDAO.addTenders(tenders);
			//tenders = tenderDAO.getNewTenders();
			//Assert.assertEquals(tender.getID(), tenders.get(0).getID());
		} 
		catch (SQLException es) {
			// TODO: handle exception
			es.getStackTrace();
		}

	}
}
