package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionDB;

public class ClientsModel {
	 public int createRoomType(Client client) throws SQLException {
        String sql = "INSERT INTO client (phone_number, client_name, email, birth_date, profile_picture) VALUES (?, ?, ?, ?, ?)";
        
        try(Connection conn = ConnectionDB.getDataSource().getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, client.getPhone_number());
            stmt.setString(2, client.getClient_name());
            stmt.setString(3, client.getEmail());
            stmt.setDate(4, client.getBirth_date());
            stmt.setBytes(5, client.getProfile_picture());
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        throw new SQLException("No se pudo obtener el ID generado");
    }
	 public List<Client> getAvailableRoomType() throws SQLException {
	        List<Client> clients = new ArrayList<>();
	        String sql = "SELECT id_client, phone_number, client_name, email, birth_date, profile_picture FROM client";
	        
	        try (Connection conn = ConnectionDB.getDataSource().getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {
	            
	            while (rs.next()) {
	            	Client client = new Client(
	        			rs.getInt("id_client"),
	        			rs.getInt("phone_number"),
	                    rs.getString("client_name"),
	                    rs.getString("email"),
	                    rs.getDate("birth_date"),
	                    rs.getBytes("profile_picture")
	                );
	            	clients.add(client);
	            }
	        }
	        return clients;
	    }
}
