package org.coep.objects;

public class OrderClass {
	private String name, mobile;
	private int itemCount;
	
	public OrderClass(String name, String mobile, int itemCount) {
		this.name = name;
		this.mobile = mobile;
		this.itemCount = itemCount;
	}
	
	public String getName() {
		return name;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public int getItemCount() {
		return itemCount;
	}

}
