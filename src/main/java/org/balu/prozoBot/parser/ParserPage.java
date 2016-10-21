package org.balu.prozoBot.parser;


import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class ParserPage {

	private StringManipulation manipulation = new StringManipulation();
	
	public void/*List<Tender>*/ getTagValue(Parser parser, Tag tag)
	{
		try
		{
		// получаем ссылкуна статистику и название сайта
			NodeFilter filter = new AndFilter(new TagNameFilter(tag.getName()), 
        	    new HasAttributeFilter("class", tag.getAttribute()));
			
			NodeList nodes = parser.parse(filter);

        	for(int i=0; i<nodes.size(); i++) 
        	{
            	TagNode node = (TagNode) nodes.elementAt(i);
            	String Attribute = getTagTextValue(node, tag.getAttribute());

            	
            	System.out.println(Attribute);
			} 
		}
		catch (ParserException e) {
        	e.printStackTrace();
    	}
	}

	private String getTagTextValue(TagNode node, String attribute)
	{
		String nodeText = "";
		
    	if (attribute.equals("items-list--header"))
    	{
    		nodeText = "https://prozorro.gov.ua" + node.getAttribute("href");
    	}
    	else
    	{
    		nodeText= node.toPlainTextString();
    		nodeText = manipulation.clearStringFromTags(nodeText);
    	}
    	
		return nodeText;
	}
	public void/*List<Tender>*/ getURL(Parser parser)
	{
		try
		{
		// получаем ссылкуна статистику и название сайта
			NodeFilter filter = new AndFilter(new TagNameFilter("a"), 
        	    new HasAttributeFilter("class", "items-list--header"));
			
			NodeList nodes = parser.parse(filter);

        	for(int i=0; i<nodes.size(); i++) 
        	{
            	TagNode node = (TagNode) nodes.elementAt(i);
            	String URL = node.getAttribute("href");
            	//URL = manipulation.clearStringFromTags(URL);
            	System.out.println("https://prozorro.gov.ua" + URL);
			} 
		}
		catch (ParserException e) {
        	e.printStackTrace();
    	}
	}
}
