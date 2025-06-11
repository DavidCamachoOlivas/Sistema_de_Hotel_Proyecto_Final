package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionDB;

public class RentalsModel {
	public int createRental(Rental rental) throws SQLException {
	    String sql = "INSERT INTO rental ( id_room, id_client, check_in, check_out, total, status) " +
	    			 "VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection conn = ConnectionDB.getDataSource().getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        stmt.setInt(1, rental.getId_room());
	        stmt.setInt(2, rental.getId_client());
	        stmt.setDate(3, rental.getCheck_in());
	        stmt.setDate(4, rental.getCheck_out());
	        stmt.setLong(5, rental.getDias_totales());
	        stmt.setBoolean(6, rental.isStatus());

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
	public boolean updateRental(Rental rental) throws SQLException {
	    String sql = "UPDATE rental SET id_room = ?, id_client = ?, check_in = ?, check_out = ?, total = ?, status = ? WHERE id_rental = ?";
	    
	    try (Connection conn = ConnectionDB.getDataSource().getConnection();
	    
	    		PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, rental.getId_room());
	        stmt.setInt(2, rental.getId_client());
	        stmt.setDate(3, rental.getCheck_in());
	        stmt.setDate(4, rental.getCheck_out());
	        stmt.setLong(5, rental.getDias_totales());
	        stmt.setBoolean(6, rental.isStatus());
	        stmt.setInt(7, rental.getId_rental());
	    
	        int affected = stmt.executeUpdate();
	        return affected > 0;
	    }
	}
	
	public boolean deleteRental(Rental r) throws SQLException {
		   
		 String sql = "DELETE FROM rental WHERE id_rental = ?";
		 
		 try (Connection conn = ConnectionDB.getDataSource().getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, r.getId_rental());
		        int affected = stmt.executeUpdate();
		        return affected > 0;
		 }
	}
	
	public Rental getRentalById(int idRental) throws SQLException {
	    String sql = "SELECT * FROM rental WHERE id_rental = ?";
	    try (Connection conn = ConnectionDB.getDataSource().getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, idRental);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                Rental rental = new Rental();
	                rental.setId_rental(rs.getInt("id_rental"));
	                rental.setId_room(rs.getInt("id_room"));
	                rental.setId_client(rs.getInt("id_client"));
	                rental.setCheck_in(rs.getDate("check_in"));
	                rental.setCheck_out(rs.getDate("check_out"));
	                rental.setDias_totales(rs.getLong("total"));
	                rental.setStatus(rs.getBoolean("status"));
	               
	                return rental;
	            }
	        }
	    }
	    return null;
	}
	
	public List<Rental> getAvailableRental() throws SQLException {
		List<Rental> rentals = new ArrayList<>();
		String sql = "SELECT id_rental, id_room, id_client, check_in, check_out, total, status FROM rental";
		
		try (Connection conn = ConnectionDB.getDataSource().getConnection();
		     Statement stmt = conn.createStatement();
		     ResultSet rs = stmt.executeQuery(sql)) {
		    
		    while (rs.next()) {
		    	Rental rental = new Rental(
		    	rs.getInt("id_rental"),
				rs.getInt("id_room"),
		        rs.getInt("id_client"),
		        rs.getDate("check_in"),
				rs.getDate("check_out"),
		        rs.getLong("total"),
		        rs.getBoolean("status")
		        );
		    	rentals.add(rental);
		    }
		}
		return rentals;
    }
}
