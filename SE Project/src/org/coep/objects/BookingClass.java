package org.coep.objects;

public class BookingClass {
	private String name, mobile, email, date;
	
	public BookingClass(String name, String mobile, String email, String date) {
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public String getMobile() {
		return mobile;
	}


	public String getEmail() {
		return email;
	}


	public String getDate() {
		return date;
	}
}
