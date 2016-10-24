package org.balu.prozoBot;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.balu.prozoBot.bot.TelegramBot;
import org.balu.prozoBot.dao.ConnectDB;
import org.balu.prozoBot.dao.TenderDAO;
import org.balu.prozoBot.dao.TenderDAOImp;
import org.balu.prozoBot.object.Tag;
import org.balu.prozoBot.object.Tender;
import org.balu.prozoBot.parser.AttributeToTenders;
import org.balu.prozoBot.parser.ParserPage;
import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;

public class Begin {

	private static String URL = "https://prozorro.gov.ua/tender/search/?region=49-53&status=active.tendering";

	public static void main(String[] args) 
	{
		ParserPage parserPage = new ParserPage();

		List<Tender> tenders = new ArrayList<Tender>();
		List<Tender> tendersToSend = new ArrayList<Tender>();
		List<Tag> tags = new ArrayList<Tag>();

		tags.add(new Tag("span", "cell"));
		tags.add(new Tag("div", "items-list-item-description"));
		tags.add(new Tag("div", "items-list--tem-id"));
		tags.add(new Tag("div", "items-list--item--price"));
		tags.add(new Tag("a", "items-list--header"));
		
		Parser page = null;
		try 
		{
			page = new Parser(URL);
		}
		catch (ParserException e) 
		{
		}
		
		List<String> attributesValue = new ArrayList<String>();
		if (page != null)
		{
			for (Tag tag : tags) 
			{
			attributesValue.addAll(parserPage.getTagValue(page, tag));
			page.reset();	
			}
		}
		
		AttributeToTenders atrributeToTender = new AttributeToTenders();
		tenders = atrributeToTender.setAttributeToTenders(attributesValue);
     
		ConnectDB connectionDB = new ConnectDB();
		
		try (Connection connection = connectionDB.getConnection())
		{
			TenderDAO tenderDAO = new TenderDAOImp(connection);
			tenderDAO.addTenders(tenders);
			tendersToSend = tenderDAO.getNewTenders();
		}
		catch (SQLException e) 
		{
		}
		
		TelegramBot telegramBot = new TelegramBot();
		
		for (Tender tender : tendersToSend) {
			telegramBot.sendMessage(tender);
		}

		try (Connection connection = connectionDB.getConnection())
		{
			TenderDAO tenderDAO = new TenderDAOImp(connection);
			tenderDAO.setTendersStatusSend(tendersToSend);
		}
		catch (SQLException e) 
		{
		}
	}
}
