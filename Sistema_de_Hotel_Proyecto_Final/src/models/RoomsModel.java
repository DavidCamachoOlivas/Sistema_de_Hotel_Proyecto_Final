package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionDB;

public class RoomsModel {
	 public int createRoom(Room room) throws SQLException {
        String sql = "INSERT INTO `room` (`id_room`, `id_room_image`, `id_room_type`, `num_room`, `beds_qty`, `max_guest_qty`, `room_name`, `amenities`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
       
        try (Connection conn = ConnectionDB.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, room.getId_room());
            stmt.setInt(2, room.getId_room_image());
            stmt.setInt(3, room.getId_room_type());
            stmt.setInt(4, room.getNum_room());
            stmt.setInt(5, room.getMax_guest_qty());
            stmt.setString(6, room.getRoom_name());
            stmt.setString(7, room.getAmenities());
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        throw new SQLException("No se pudo obtener el ID generado");
    }
	 public List<RoomType> getAvailableRoomType() throws SQLException {
	        List<RoomType> roomTypes = new ArrayList<>();
	        String sql = "SELECT id_room_type, id_tariff, rooms_included, num_floor, room_type, image, description FROM room_type";
	        
	        try (Connection conn = ConnectionDB.getDataSource().getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {
	            
	            while (rs.next()) {
	            	RoomType roomType = new RoomType(
	        			rs.getInt("id_room_type"),
	        			rs.getInt("id_tariff"),
	                    rs.getInt("rooms_included"),
	                    rs.getInt("num_floor"),
	                    rs.getString("room_type"),
	                    rs.getBytes("image"),
	                    rs.getString("description")
	                );
	            	roomTypes.add(roomType);
	            }
	        }
	        return roomTypes;
	    }
	
	public List<Room> getAvailableRoom() throws SQLException {
		List<Room> rooms = new ArrayList<>();
		String sql = "SELECT id_room, id_room_image, id_room_type, num_room, beds_qty, max_guest_qty, room_name, amenities FROM room";
		
		try (Connection conn = ConnectionDB.getDataSource().getConnection();
		     Statement stmt = conn.createStatement();
		     ResultSet rs = stmt.executeQuery(sql)) {
		    
		    while (rs.next()) {
		    	Room room = new Room(
		    	rs.getInt("id_room"),
				rs.getInt("id_room_image"),
		        rs.getInt("id_room_type"),
				rs.getInt("num_room"),
		        rs.getInt("beds_qty"),
		        rs.getInt("max_guest_qty"),
		        rs.getString("room_name"),
		        rs.getString("amenities")
		        );
		    	rooms.add(room);
		    }
		}
		return rooms;
    }
	public List<RoomImage> getAvailableRoomImage() throws SQLException {
		List<RoomImage> roomsImages = new ArrayList<>();
		String sql = "SELECT id_room_image, id_room, room_image FROM room_image";
		
		try (Connection conn = ConnectionDB.getDataSource().getConnection();
		     Statement stmt = conn.createStatement();
		     ResultSet rs = stmt.executeQuery(sql)) {
		    
		    while (rs.next()) {
		    	RoomImage roomImage = new RoomImage(
		    	rs.getInt("id_room_image"),
				rs.getInt("id_room"),
		        rs.getBytes("room_image")
		        );
		    	roomsImages.add(roomImage);
		    }
		}
		return roomsImages;
    }
	
	public List<Tariff> getAvailableTariffs() throws SQLException {
        List<Tariff> tariffs = new ArrayList<>();
        String sql = "SELECT id_tariff, id_room, price_per_night, capacity, tariff_type, refundable, description FROM tariff";
        
        try (Connection conn = ConnectionDB.getDataSource().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Tariff tariff = new Tariff(
                    rs.getInt("id_tariff"),
                    rs.getInt("id_room"),
                    rs.getFloat("price_per_night"),
                    rs.getInt("capacity"),
                    rs.getString("tariff_type"),
                    rs.getBoolean("refundable"),
                    rs.getString("description")
                );
                tariffs.add(tariff);
            }
        }
        return tariffs;
	}
}
