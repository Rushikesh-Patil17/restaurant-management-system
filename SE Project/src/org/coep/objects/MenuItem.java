package org.coep.objects;

public class MenuItem {
	int id;
	String name;
	int price;
	int type;
	boolean isAvailable;
	
	public MenuItem(int id, String name, int price, int type, boolean isAvailable) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
		this.isAvailable = isAvailable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}	
}