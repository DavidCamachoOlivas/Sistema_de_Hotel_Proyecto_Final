package models;

public class Room {
	int id_room;
	int id_room_image;
	int id_room_type;
	boolean status;
	int num_room;
	int beds_qty;
	int max_guest_qty;
	String room_name;
	String amenities;
	
	public Room(int id_room, int id_room_image, int id_room_type, boolean status, int num_room, int beds_qty, int max_guest_qty, String room_name, String amenities){
		
		this.id_room = id_room;
		this.id_room_image = id_room_image;
		this.id_room_type = id_room_type;
		this.status = status;
		this.num_room = num_room;
		this.beds_qty = beds_qty;
		this.max_guest_qty = max_guest_qty;
		this.room_name = room_name;
		this.amenities = amenities;
		
	}
	public Room() {
		
	}
	public int getId_room() {
		return id_room;
	}

	public void setId_room(int id_room) {
		this.id_room = id_room;
	}

	public int getId_room_image() {
		return id_room_image;
	}

	public void setId_room_image(int id_room_image) {
		this.id_room_image = id_room_image;
	}

	public int getId_room_type() {
		return id_room_type;
	}

	public void setId_room_type(int id_room_type) {
		this.id_room_type = id_room_type;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getNum_room() {
		return num_room;
	}

	public void setNum_room(int num_room) {
		this.num_room = num_room;
	}

	public int getBeds_qty() {
		return beds_qty;
	}

	public void setBeds_qty(int beds_qty) {
		this.beds_qty = beds_qty;
	}

	public int getMax_guest_qty() {
		return max_guest_qty;
	}

	public void setMax_guest_qty(int max_guest_qty) {
		this.max_guest_qty = max_guest_qty;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}
	
}
