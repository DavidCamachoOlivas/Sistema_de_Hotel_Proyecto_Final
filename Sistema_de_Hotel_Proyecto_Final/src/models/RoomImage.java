package models;

public class RoomImage {
    private int id_room_image;
    private int id_room;
    private byte[] room_image;


    public RoomImage(int id_room_image, int id_room, byte[] room_image) {
        this.id_room_image = id_room_image;
        this.id_room = id_room;
        this.room_image = room_image;
    }

    public RoomImage() {
    	
    }

    public int getid_Room_image() {
        return id_room_image;
    }

    public void setIdRoomImage(int id_room_image) {
        this.id_room_image = id_room_image;
    }

    public int getId_Room() {
        return id_room;
    }

    public void setId_Room(int id_room) {
        this.id_room = id_room;
    }

    public byte[] getRoom_Image() {
        return room_image;
    }

    public void setRoom_Image(byte[] room_image) {
        this.room_image = room_image;
    }
    public String toString() {
    	return ""+getid_Room_image();
    }
}
