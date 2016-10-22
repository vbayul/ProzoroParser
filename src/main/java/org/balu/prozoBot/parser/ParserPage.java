package org.balu.prozoBot.parser;

import java.util.ArrayList;
import java.util.List;

import org.balu.prozoBot.object.Tag;
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
	
	public List<String> getTagValue(Parser parser, Tag tag)
	{
		List<String> attributesValue = new ArrayList<>();
		try
		{
			NodeFilter filter = new AndFilter(new TagNameFilter(tag.getName()), 
        	    new HasAttributeFilter("class", tag.getAttribute()));
			
			NodeList nodes = parser.parse(filter);

        	for(int i=0; i<nodes.size(); i++) 
        	{
            	TagNode node = (TagNode) nodes.elementAt(i);
            	String AttributeValue = getTagTextValue(node, tag.getAttribute());
            	attributesValue.add(AttributeValue);
            	//System.out.println(AttributeValue);
			} 
		}
		catch (ParserException e) {
        	e.printStackTrace();
    	}	
		return attributesValue;
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
    		nodeText = node.toPlainTextString();
    		nodeText = manipulation.clearStringFromTags(nodeText);
    	}
		return nodeText;
	}
}
