package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import views.HomeView;

public class RoomTypesModel {
    private int id;
    private String type;
    private float price;
    
	String host ="jdbc:mysql://uywiohjkpxink6lw:22LfDvbA07QUq1XOKk4d@b0ufffjehz0mbockqctk-mysql.services.clever-cloud.com:3306/b0ufffjehz0mbockqctk";
	String user = "uywiohjkpxink6lw";
	String pass = "22LfDvbA07QUq1XOKk4d";
    
	 public int createRoomType(RoomType roomType) throws SQLException {
	        String sql = "INSERT INTO room_type (id_tariff, rooms_included, num_floor, room_type, image) VALUES (?, ?, ?, ?, ?)";
	        
	        try (Connection conn = DriverManager.getConnection(host, user, pass);
	             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
	            
	            stmt.setInt(1, roomType.getId_tariff());
	            stmt.setInt(2, roomType.getRooms_included());
	            stmt.setInt(3, roomType.getNum_floor());
	            stmt.setString(4, roomType.getRoom_type());
	            stmt.setBytes(5, roomType.getImage());
	            
	            stmt.executeUpdate();
	            
	            try (ResultSet rs = stmt.getGeneratedKeys()) {
	                if (rs.next()) {
	                    return rs.getInt(1);
	                }
	            }
	        }
	        throw new SQLException("No se pudo obtener el ID generado");
	    }
	 
	 public boolean deleteRoomType(RoomType rm) throws SQLException {
		   
		 String sql = "DELETE FROM room_type WHERE id_room_type = ?";
		 
		 try (Connection conn = DriverManager.getConnection(host, user, pass);
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, rm.getId_room_type());
		        int affected = stmt.executeUpdate();
		        return affected > 0;
		 }
	}

	public boolean updateRoomType(RoomType roomType) throws SQLException {
	    String sql = "UPDATE room_type SET id_tariff = ?, rooms_included = ?, num_floor = ?, room_type = ?, image = ? WHERE id_room_type = ?";
	    
	    try (Connection conn = DriverManager.getConnection(host, user, pass);
	    
	    		PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, roomType.getId_tariff());
	        stmt.setInt(2, roomType.getRooms_included());
	        stmt.setInt(3, roomType.getNum_floor());
	        stmt.setString(4, roomType.getRoom_type());
	        stmt.setBytes(5, roomType.getImage());
	    
	        int affected = stmt.executeUpdate();
	        return affected > 0;
	    }
	}

	    
    public List<Tariff> getAvailableTariffs() throws SQLException {
        List<Tariff> tariffs = new ArrayList<>();
        String sql = "SELECT id_tariff, id_room, price_per_night, capacity, tariff_type, refundable FROM tariff";
        
        try (Connection conn = DriverManager.getConnection(host, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Tariff tariff = new Tariff(
                    rs.getInt("id_tariff"),
                    rs.getInt("id_room"),
                    rs.getFloat("price_per_night"),
                    rs.getInt("capacity"),
                    rs.getString("tariff_type"),
                    rs.getBoolean("refundable")
                );
                tariffs.add(tariff);
            }
        }
        return tariffs;
    }
    public List<RoomType> getAvailableRoomType() throws SQLException {
        List<RoomType> roomTypes = new ArrayList<>();
        String sql = "SELECT id_room_type, id_tariff, rooms_included, num_floor, room_type, image FROM room_type";
        
        try (Connection conn = DriverManager.getConnection(host, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
            	RoomType roomType = new RoomType(
        			rs.getInt("id_room_type"),
        			rs.getInt("id_tariff"),
                    rs.getInt("rooms_included"),
                    rs.getInt("num_floor"),
                    rs.getString("room_type"),
                    rs.getBytes("image")
                );
            	roomTypes.add(roomType);
            }
        }
        return roomTypes;
    }
    
    public boolean tariffExists(int tariffId) throws SQLException {
        String sql = "SELECT 1 FROM tariff WHERE id_tariff = ?";
        try (Connection conn = DriverManager.getConnection(host, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tariffId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
    public boolean RoomTypeExists(int roomTypeId) throws SQLException {
        String sql = "SELECT 1 FROM room_type WHERE id_room_type = ?";
        try (Connection conn = DriverManager.getConnection(host, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, roomTypeId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}
