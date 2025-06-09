package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionDB;

public class TariffsModel {
	 
	public int createTariff(Tariff tariff) throws SQLException {
	    String sql = "INSERT INTO tariff (id_room, price_per_night, capacity, tariff_type, refundable) VALUES (?, ?, ?, ?, ?)";

	    try (Connection conn = ConnectionDB.getDataSource().getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

	        if (tariff.getId_room() == 0) {
	            stmt.setNull(1, java.sql.Types.INTEGER);
	        } 
	        else {
	            stmt.setInt(1, tariff.getId_room());
	        }
	        stmt.setDouble(2, tariff.getPrice_per_night());
	        stmt.setInt(3, tariff.getCapacity());
	        stmt.setString(4, tariff.getTariff_type());
	        stmt.setBoolean(5, tariff.isRefundable());

	        stmt.executeUpdate();

	        try (ResultSet rs = stmt.getGeneratedKeys()) {
	            if (rs.next()) {
	                return rs.getInt(1);
	            }
	        }
	    }
	    throw new SQLException("No se pudo obtener el ID generado");
	}
	public boolean deleteRoomType(Tariff t) throws SQLException {
		   
		 String sql = "DELETE FROM tariff WHERE id_tariff = ?";
		 
		 try (Connection conn = ConnectionDB.getDataSource().getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, t.getId_tariff());
		        int affected = stmt.executeUpdate();
		        return affected > 0;
		 }
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
	 public Tariff getTariffById(int idTariff) throws SQLException {
		    String sql = "SELECT * FROM tariff WHERE id_tariff = ?";
		    try (Connection conn = ConnectionDB.getDataSource().getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, idTariff);

		        try (ResultSet rs = stmt.executeQuery()) {
		            if (rs.next()) {
		                Tariff tariff = new Tariff();
		                tariff.setId_tariff(rs.getInt("id_tariff"));
		                tariff.setId_room(rs.getInt("id_room"));
		                tariff.setPrice_per_night(rs.getInt("price_per_night"));
		                tariff.setCapacity(rs.getInt("capacity"));
		                tariff.setTariff_type(rs.getString("tariff_type"));
		                tariff.setRefundable(rs.getBoolean("refundable"));
		                tariff.setDescription(rs.getString("description"));
		                return tariff;
		            }
		        }
		    }
		    return null;
		}
	 public boolean isTariffInUse(Tariff t) throws SQLException {
		    Connection conn = ConnectionDB.getDataSource().getConnection();
		    String sql = "SELECT COUNT(*) FROM room_type WHERE id_tariff = ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, t.getId_tariff());
		    ResultSet rs = stmt.executeQuery();
		    if (rs.next()) {
		        return rs.getInt(1) > 0;
		    }
		    return false;
		}

}
