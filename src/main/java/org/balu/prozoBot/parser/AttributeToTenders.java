package org.balu.prozoBot.parser;

import java.util.ArrayList;
import java.util.List;

import org.balu.prozoBot.object.Tender;

public class AttributeToTenders {
	
	private final int TenderAtributeCount = 5;
	private int STEP = 0;

	public List<Tender> setAttributeToTenders(List<String> atrribute)
	{
		List<Tender> tenders = new ArrayList<>();
		STEP = (atrribute.size()/TenderAtributeCount);
		for (int i = 0; i < STEP; i++) 
		{
			setValueToTender(i, atrribute);
			tenders.add(setValueToTender(i, atrribute));
		}
		return tenders;
	}
	
	private Tender setValueToTender(int position, List<String> atrribute)
	{
		Tender tender = new Tender();
			tender.setName(atrribute.get(position));
			tender.setCompany(atrribute.get(position+STEP));
			tender.setID(atrribute.get(position+STEP*2));
			tender.setPrice(atrribute.get(position+STEP*3));
			tender.setURL(atrribute.get(position+STEP*4));
		return tender;
	}
}
