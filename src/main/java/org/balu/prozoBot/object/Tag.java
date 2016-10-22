package org.balu.prozoBot.object;

public class Tag {

	private String Name;
	private String Attribute;

	public Tag(String name, String attribute) {
		Name = name;
		Attribute = attribute;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAttribute() {
		return Attribute;
	}

	public void setAttribute(String attribute) {
		Attribute = attribute;
	}
	
}
