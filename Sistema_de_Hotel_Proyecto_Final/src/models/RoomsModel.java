package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionDB;

public class RoomsModel {
	public int createRoom(Room room) throws SQLException {
	    String sql = "INSERT INTO room (id_room_image, id_room_type, status, num_room, beds_qty, max_guest_qty, room_name, amenities) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = ConnectionDB.getDataSource().getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        if (room.getId_room_image() > 0) {
	            stmt.setInt(1, room.getId_room_image());
	        } else {
	            stmt.setNull(1, Types.INTEGER);
	        }
	        stmt.setInt(2, room.getId_room_type());
	        stmt.setBoolean(3, room.isStatus());
	        stmt.setInt(4, room.getNum_room());
	        stmt.setInt(5, room.getBeds_qty());
	        stmt.setInt(6, room.getMax_guest_qty());
	        stmt.setString(7, room.getRoom_name());
	        stmt.setString(8, room.getAmenities());

	        int filasInsertadas = stmt.executeUpdate();

	        if (filasInsertadas == 0) {
	            throw new SQLException("No se pudo insertar la habitación.");
	        }

	        try (ResultSet rs = stmt.getGeneratedKeys()) {
	            if (rs.next()) {
	                return rs.getInt(1);
	            } else {
	                throw new SQLException("No se obtuvo el ID generado.");
	            }
	        }
	    }
	}
	
	public boolean updateRoomType(Room room) throws SQLException {
	    String sql = "UPDATE room SET id_room_image = ?, id_room_type = ?, status = ?, num_room = ?, beds_qty = ?, max_guest_qty = ?, room_name = ?, amenities = ? WHERE id_room = ?";
	    
	    try (Connection conn = ConnectionDB.getDataSource().getConnection();
	    
	    		PreparedStatement stmt = conn.prepareStatement(sql)) {
	    	
	        stmt.setInt(1, room.getId_room_image());
	        stmt.setInt(2, room.getId_room_type());
	        stmt.setBoolean(3, room.isStatus());
	        stmt.setInt(4, room.getNum_room());
	        stmt.setInt(5, room.getBeds_qty());
	        stmt.setInt(6, room.getMax_guest_qty());
	        stmt.setString(7, room.getRoom_name());
	        stmt.setString(8, room.getAmenities());
	        stmt.setInt(9, room.getId_room());
	    
	        int affected = stmt.executeUpdate();
	        return affected > 0;
	    }
	}
	public boolean deleteRoom(Room r) throws SQLException {
		   
		 String sql = "DELETE FROM room WHERE id_room = ?";
		 
		 try (Connection conn = ConnectionDB.getDataSource().getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, r.getId_room());
		        int affected = stmt.executeUpdate();
		        return affected > 0;
		 }
	}
	
	public List<Room> getAvailableRoom() throws SQLException {
		List<Room> rooms = new ArrayList<>();
		String sql = "SELECT id_room, id_room_image, id_room_type, status, num_room, beds_qty, max_guest_qty, room_name, amenities FROM room";
		
		try (Connection conn = ConnectionDB.getDataSource().getConnection();
		     Statement stmt = conn.createStatement();
		     ResultSet rs = stmt.executeQuery(sql)) {
		    
		    while (rs.next()) {
		    	Room room = new Room(
		    	rs.getInt("id_room"),
				rs.getInt("id_room_image"),
		        rs.getInt("id_room_type"),
		        rs.getBoolean("status"),
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
	
	public Room getRoomByNum(int idRoom) throws SQLException {
	    String sql = "SELECT * FROM room WHERE num_room = ?";
	    try (Connection conn = ConnectionDB.getDataSource().getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, idRoom);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                Room room = new Room();
	                room.setId_room(rs.getInt("id_room"));
	                room.setRoom_name(rs.getString("room_name"));
	                room.setNum_room(rs.getInt("num_room"));
	                room.setId_room_type(rs.getInt("id_room_type"));
	                room.setId_room_image(rs.getInt("id_room_image"));
	                room.setBeds_qty(rs.getInt("beds_qty"));
	                room.setMax_guest_qty(rs.getInt("max_guest_qty"));
	                room.setAmenities(rs.getString("amenities"));

	                return room;
	            }
	        }
	    }
	    return null;
	}
	
	public Room getRoomById(int idRoom) throws SQLException {
	    String sql = "SELECT * FROM room WHERE id_room = ?";
	    try (Connection conn = ConnectionDB.getDataSource().getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, idRoom);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                Room room = new Room();
	                room.setId_room(rs.getInt("id_room"));
	                room.setRoom_name(rs.getString("room_name"));
	                room.setNum_room(rs.getInt("num_room"));
	                room.setId_room_type(rs.getInt("id_room_type"));
	                room.setId_room_image(rs.getInt("id_room_image"));
	                room.setBeds_qty(rs.getInt("beds_qty"));
	                room.setMax_guest_qty(rs.getInt("max_guest_qty"));
	                room.setAmenities(rs.getString("amenities"));

	                return room;
	            }
	        }
	    }
	    return null;
	}

	
	public boolean existsRoomNumber(int numRoom) {
	    String query = "SELECT COUNT(*) FROM room WHERE num_room = ?";
	    try (Connection conn = ConnectionDB.getDataSource().getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setInt(1, numRoom);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

}
