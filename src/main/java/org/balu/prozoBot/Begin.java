package org.balu.prozoBot;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.balu.prozoBot.parser.ParserPage;
import org.balu.prozoBot.parser.Tag;
import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;

public class Begin {

	//private List<Tender> tenders = new ArrayList<Tender>();
	private static String URL = "https://prozorro.gov.ua/tender/search/?region=49-53&status=active.tendering";
	
	public static void main(String[] args) {
		ParserPage parserPage = new ParserPage();
		try
		{
			Parser page = new Parser(URL);
			List<Tag> tags = new ArrayList<>();
			
			tags.add(new Tag("span", "cell"));
			tags.add(new Tag("div", "items-list-item-description"));
			tags.add(new Tag("div", "items-list--tem-id"));
			tags.add(new Tag("div", "items-list--item--price"));
			tags.add(new Tag("a", "items-list--header"));
			
			for (Tag tag : tags) {
				parserPage.getTagValue(page, tag);
				page.reset();				
			}
			
		}
		catch (ParserException ex)
		{
			ex.printStackTrace();
		}
		
		// запись в базу
		
		// отправка сообщений из базы
		
		
	}

}
