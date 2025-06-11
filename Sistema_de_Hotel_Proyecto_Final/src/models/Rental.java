package models;

import java.sql.Date;


public class Rental {
	int id_rental;
	int id_room;
	int id_client;
	Date check_in;
	Date check_out;
	long dias_totales;
	boolean status;
	
	public Rental(int id_rental, int id_room, int id_client,
			Date check_in, Date check_out, long dias_totales,
			boolean status) {
		this.id_rental = id_rental;
		this.id_room = id_room;
		this.id_client = id_client;
		this.check_in = check_in;
		this.check_out = check_out;
		this.dias_totales = dias_totales;
	}
	public Rental() {
		
	}

	public int getId_rental() {
		return id_rental;
	}

	public void setId_rental(int id_rental) {
		this.id_rental = id_rental;
	}

	public int getId_room() {
		return id_room;
	}

	public void setId_room(int id_room) {
		this.id_room = id_room;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public Date getCheck_in() {
		return check_in;
	}

	public void setCheck_in(Date check_in) {
		this.check_in = check_in;
	}

	public Date getCheck_out() {
		return check_out;
	}

	public void setCheck_out(Date check_out) {
		this.check_out = check_out;
	}

	public long getDias_totales() {
		return dias_totales;
	}

	public void setDias_totales(long dias_totales) {
		this.dias_totales = dias_totales;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
