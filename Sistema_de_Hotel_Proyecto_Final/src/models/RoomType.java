package models;

public class RoomType {
	int id_room_type;
	int id_tariff;
	int rooms_included;
	int num_floor;
	String room_type;
	private byte[] image;
	String description;
	
	public RoomType(int id_room_type, int id_tariff, int rooms_included, int num_floor, String room_type, byte[] image, String description) {
		this.id_room_type = id_room_type;
		this.id_tariff = id_tariff;
		this.num_floor = num_floor;
		this.rooms_included =  rooms_included;
		this.room_type = room_type;
		this.image = image;
		this.description = description;
	}
	public RoomType() {
	}
	
	public int getId_room_type() {
		return id_room_type;
	}

	public void setId_room_type(int id_room_type) {
		this.id_room_type = id_room_type;
	}

	public int getId_tariff() {
		return id_tariff;
	}

	public void setId_tariff(int id_tariff) {
		this.id_tariff = id_tariff;
	}

	public int getRooms_included() {
		return rooms_included;
	}

	public void setRooms_included(int rooms_included) {
		this.rooms_included = rooms_included;
	}

	public int getNum_floor() {
		return num_floor;
	}

	public void setNum_floor(int num_floor) {
		this.num_floor = num_floor;
	}

	public String getRoom_type() {
		return room_type;
	}

	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	
	public byte[] getImage() {
		return image; }
	
	public void setImage(byte[] image) { 
		this.image = image; }
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return room_type;
		
	}
	
}
