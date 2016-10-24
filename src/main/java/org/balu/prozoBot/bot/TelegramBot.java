package org.balu.prozoBot.bot;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.balu.prozoBot.object.Tender;

public class TelegramBot {
	
	private String BOTID;
	private String TOKEN;
	private String CHATID;
	
	public TelegramBot() 
	{
		Properties properties = new Properties();
		
		try (InputStream in = Files.newInputStream(Paths.get("telegram.properties")))
		{
			properties.load(in);
		}
		catch (Exception e) {
			e.getStackTrace();
		}
		
		BOTID = properties.getProperty("BOTID");
		TOKEN = properties.getProperty("TOKEN");
		CHATID = properties.getProperty("CHATID");
	}

	// https://api.telegram.org/botID:TOKEN/sendMessage?chat_id=CHATID&text=test
	public void sendMessage(Tender tender)
	{
		//"https://api.telegram.org/botID:TOKEN/sendMessage?chat_id=CHATID&text=test"

		String url = "https://api.telegram.org/bot"
				+BOTID+ ":" + TOKEN +"/sendMessage?chat_id=" 
				+ CHATID + "&text=";
		try
		{
		URL obj = new URL(url+tender.toString());
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		//con.setRequestProperty("User-Agent", "Test");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url+tender.toString());
		System.out.println("Response Code : " + responseCode);
/*
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		*/
		}
		catch (Exception ex)
		{
			ex.getStackTrace();
		}
	}
}
