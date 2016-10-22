package org.balu.prozoBot.dao;

import java.util.List;
import org.balu.prozoBot.object.Tender;

public interface TenderDAO {

	public void saveTenders(List<Tender> tenders);
	
	public List<Tender> getNewTenders();
	
	public void setStatusSend(Tender tender);
}
