package org.balu.prozoBot;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.balu.prozoBot.bot.TelegramBot;
import org.balu.prozoBot.bot.TelegramBotImp;

import org.balu.prozoBot.dao.ConnectDB;
import org.balu.prozoBot.dao.TenderDAO;
import org.balu.prozoBot.dao.TenderDAOImp;
import org.balu.prozoBot.object.Tag;
import org.balu.prozoBot.object.Tender;
import org.balu.prozoBot.parser.AttributeToTenders;
import org.balu.prozoBot.parser.ParserPage;
import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;

public class Main {

	public static void main(String[] args) 
	{
		GetSetting getSettings = new GetSetting();
		AttributeToTenders atrributeToTender = new AttributeToTenders();
		TelegramBot telegramBot = new TelegramBotImp(getSettings);
		ParserPage parserPage = new ParserPage();
		ConnectDB connectionDB = new ConnectDB();
		Parser page = null;
		
		List<String> attributesValue = new ArrayList<String>();
		List<Tender> tenders = new ArrayList<Tender>();
		List<Tender> tendersToSend = new ArrayList<Tender>();
		List<Tag> tags = new ArrayList<Tag>();

		String URL =  getSettings.getURL();

		tags.add(new Tag("span", "cell"));
		tags.add(new Tag("div", "items-list-item-description"));
		tags.add(new Tag("div", "items-list--tem-id"));
		tags.add(new Tag("div", "items-list--item--price"));
		tags.add(new Tag("a", "items-list--header"));

		try 
		{
			page = new Parser(URL);
		}
		catch (ParserException e) 
		{
		}

		if (page != null)
		{
			for (Tag tag : tags) 
			{
			attributesValue.addAll(parserPage.getTagValue(page, tag));
			page.reset();	
			}
		}

		tenders = atrributeToTender.setAttributeToTenders(attributesValue);
		
		try (Connection connection = connectionDB.getConnection())
		{
			TenderDAO tenderDAO = new TenderDAOImp(connection);
			tenderDAO.addTenders(tenders);
			tendersToSend = tenderDAO.getNewTenders();
		}
		catch (SQLException e) 
		{
		}

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
