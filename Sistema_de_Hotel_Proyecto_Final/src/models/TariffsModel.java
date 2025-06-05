package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
