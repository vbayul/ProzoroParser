package org.balu.prozoBot;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class GetSetting {
	
	private String URL;
	private String BOTID;
	private String TOKEN;
	private String CHATID;
	
	public GetSetting() 
	{
		Properties props = new Properties();
	    try (InputStream in = Files.newInputStream(Paths.get("setting.properties")))
	    {
	    	props.load(in);
	    } catch (IOException e) {
			e.printStackTrace();
		}
	    
	    URL= props.getProperty("URL");
	    BOTID = props.getProperty("BOTID");
		TOKEN = props.getProperty("TOKEN");
		CHATID = props.getProperty("CHATID");
	}

	public String getBOTID() {
		return BOTID;
	}

	public String getTOKEN() {
		return TOKEN;
	}

	public String getCHATID() {
		return CHATID;
	}
	
	public String getURL()
	{
		return URL;
	}
}
