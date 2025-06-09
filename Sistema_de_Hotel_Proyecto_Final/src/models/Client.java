package models;

import java.sql.Date;

public class Client {
	int id_client;
	int phone_number;
	String client_name;
	String email;
	Date birth_date;
	byte[] profile_picture;
	
	public Client() {
		
	}
	
	public Client(int id_client, int phone_number, String client_name, String email, Date birth_date, byte[] profile_picture) {
		this.id_client = id_client;
		this.phone_number = phone_number;
		this.client_name = client_name;
		this.email = email;
		this.birth_date = birth_date;
		this.profile_picture = profile_picture;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public int getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public byte[] getProfile_picture() {
		return profile_picture;
	}

	public void setProfile_picture(byte[] profile_picture) {
		this.profile_picture = profile_picture;
	}
	
}
