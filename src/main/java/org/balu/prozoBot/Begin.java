package org.balu.prozoBot;


import java.util.ArrayList;
import java.util.List;

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

		List<Tender> tenders = new ArrayList<>();
		List<Tag> tags = new ArrayList<>();

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
			e.printStackTrace();
		}

		List<String> attributesValue = new ArrayList<>();
		for (Tag tag : tags) 
		{
			attributesValue.addAll(parserPage.getTagValue(page, tag));
			page.reset();	
		}
		
		AttributeToTenders atrributeToTender = new AttributeToTenders();
		tenders = atrributeToTender.setAttributeToTenders(attributesValue);
		
		
		for (Tender tender : tenders) {
			System.out.println(tender.toString());
		}
	}
}
