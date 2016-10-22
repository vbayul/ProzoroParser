package org.balu.prozoBot.parser;

public class StringManipulation {

	public String clearStringFromTags(String stringWithTag)
	{
		String withoutTag = stringWithTag;
		
		withoutTag = withoutTag.replaceAll("\n", " ");

		withoutTag = withoutTag.replaceAll("	", "");	
		
		withoutTag = withoutTag.replaceAll("&quot;", "\"");		
		withoutTag = withoutTag.replaceAll("&laquo;", "\"");
		withoutTag = withoutTag.replaceAll("&raquo;", "\"");
		
		withoutTag = withoutTag.replaceAll("&ndash;", "-");
		withoutTag = withoutTag.replaceAll("&#039;", "'");
		
		withoutTag = withoutTag.replaceAll("ID: ", "");
		withoutTag = withoutTag.replaceAll("Компанія: ", "");
		withoutTag = withoutTag.replaceAll("Очікувана вартість ", "");	
		
		return withoutTag.trim();
	}
}
