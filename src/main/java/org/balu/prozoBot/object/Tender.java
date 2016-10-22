package org.balu.prozoBot.object;

public class Tender {

	private String ID;
	private String Name;
	private String Company;
	private String Price;
	private String URL;
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCompany() {
		return Company;
	}

	public void setCompany(String company) {
		Company = company;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ID + System.lineSeparator() 
				+ "Копания - " + Company + System.lineSeparator() 
				+ "Тендер - " + Name + System.lineSeparator() 
				+ "Цена - " + Price + System.lineSeparator()
				+ "Ссылка - " + URL + System.lineSeparator();
	}
}
