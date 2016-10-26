package org.balu.prozoBot.bot;

import java.net.HttpURLConnection;
import java.net.URL;
import org.balu.prozoBot.GetSetting;
import org.balu.prozoBot.object.Tender;

public class TelegramBotImp implements TelegramBot {
	
	private String BOTID;
	private String TOKEN;
	private String CHATID;
	
	public TelegramBotImp(GetSetting getSettings) 
	{		
		BOTID = getSettings.getBOTID();
		TOKEN = getSettings.getTOKEN();
		CHATID = getSettings.getCHATID();
	}

	// https://api.telegram.org/botID:TOKEN/sendMessage?chat_id=CHATID&text=test
	public void sendMessage(Tender tender)
	{
		String url = "https://api.telegram.org/bot"
				+BOTID+ ":" + TOKEN +"/sendMessage?chat_id=" 
				+ CHATID + "&text=";
		try
		{
		URL obj = new URL(url+tender.toString());
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		/*
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url+tender.toString());
		System.out.println("Response Code : " + responseCode);
		*/
		}
		catch (Exception ex)
		{
			ex.getStackTrace();
		}
	}
}
