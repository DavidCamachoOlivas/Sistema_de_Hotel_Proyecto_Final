package models;

public class RoomImage {
    private int id_room_image;
    private byte[] room_image;


    public RoomImage(int id_room_image, byte[] room_image) {
        this.id_room_image = id_room_image;
        this.room_image = room_image;
    }

    public RoomImage() {
    	
    }

    public int getId_room_image() {
        return id_room_image;
    }

    public void getId_room_image(int id_room_image) {
        this.id_room_image = id_room_image;
    }

    public byte[] getRoom_image() {
        return room_image;
    }

    public void setRoom_image(byte[] room_image) {
        this.room_image = room_image;
    }
    public String toString() {
    	return ""+getId_room_image();
    }
}
