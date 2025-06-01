package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomTypesModel {
	
	String host ="jdbc:mysql://uywiohjkpxink6lw:22LfDvbA07QUq1XOKk4d@b0ufffjehz0mbockqctk-mysql.services.clever-cloud.com:3306/b0ufffjehz0mbockqctk";
	String user = "uywiohjkpxink6lw";
	String pass = "22LfDvbA07QUq1XOKk4d";
	
	public int createRoomType(RoomType roomType) throws SQLException {
		String sql = "INSERT INTO room_type (id_tariff, rooms_included, num_floor, room_type) VALUES (?, ?, ?, ?)";
		
		try (Connection conn = DriverManager.getConnection(host, user, pass);
		     PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
		    
		    stmt.setInt(1, roomType.getId_tariff());
		    stmt.setInt(2, roomType.getRooms_included());
		    stmt.setInt(3, roomType.getNum_floor());
		    stmt.setString(4, roomType.getRoom_type());
		    
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
}
