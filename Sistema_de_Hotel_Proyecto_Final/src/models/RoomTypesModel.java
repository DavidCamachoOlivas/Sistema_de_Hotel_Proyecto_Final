package models;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Connection.ConnectionDB;
import controllers.RoomTypesController;
import views.HomeView;

public class RoomTypesModel {
    
	 public int createRoomType(RoomType roomType) throws SQLException {
	        String sql = "INSERT INTO room_type (id_tariff, rooms_included, num_floor, room_type, description, image) VALUES (?, ?, ?, ?, ?, ?)";
	        
	        try(Connection conn = ConnectionDB.getDataSource().getConnection();
	        		PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
	            
	            stmt.setInt(1, roomType.getId_tariff());
	            stmt.setInt(2, roomType.getRooms_included());
	            stmt.setInt(3, roomType.getNum_floor());
	            stmt.setString(4, roomType.getRoom_type());
	            stmt.setString(5, roomType.getDescription());
	            stmt.setBytes(6, roomType.getImage());
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
		 
		 try (Connection conn = ConnectionDB.getDataSource().getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, rm.getId_room_type());
		        int affected = stmt.executeUpdate();
		        return affected > 0;
		 }
	}

	public boolean updateRoomType(RoomType roomType) throws SQLException {
	    String sql = "UPDATE room_type SET id_tariff = ?, rooms_included = ?, num_floor = ?, room_type = ?, image = ?, description = ? WHERE id_room_type = ?";
	    
	    try (Connection conn = ConnectionDB.getDataSource().getConnection();
	    
	    		PreparedStatement stmt = conn.prepareStatement(sql)) {
	    	
	        stmt.setInt(1, roomType.getId_tariff());
	        stmt.setInt(2, roomType.getRooms_included());
	        stmt.setInt(3, roomType.getNum_floor());
	        stmt.setString(4, roomType.getRoom_type());
	        stmt.setBytes(5, roomType.getImage());
	        stmt.setString(6, roomType.getDescription());
	        stmt.setInt(7, roomType.getId_room_type());
	    
	        int affected = stmt.executeUpdate();
	        return affected > 0;
	    }
	}

	    
    public List<Tariff> getAvailableTariffs() throws SQLException {
        List<Tariff> tariffs = new ArrayList<>();
        String sql = "SELECT id_tariff, id_room, price_per_night, capacity, tariff_type, refundable FROM tariff";
        
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
                    rs.getBoolean("refundable")
                );
                tariffs.add(tariff);
            }
        }
        return tariffs;
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
    
    public boolean tariffExists(int tariffId) throws SQLException {
        String sql = "SELECT 1 FROM tariff WHERE id_tariff = ?";
        try (Connection conn = ConnectionDB.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tariffId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
    public boolean RoomTypeExists(int roomTypeId) throws SQLException {
        String sql = "SELECT 1 FROM room_type WHERE id_room_type = ?";
        try (Connection conn = ConnectionDB.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, roomTypeId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
    public static void exportarTablaPDF(JTable table, String rutaArchivo) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo));
            document.open();

            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());

            TableModel model = table.getModel();
            for (int i = 0; i < model.getColumnCount(); i++) {
                pdfTable.addCell(new PdfPCell(new Phrase(model.getColumnName(i))));
            }

            for (int rows = 0; rows < model.getRowCount(); rows++) {
                for (int cols = 0; cols < model.getColumnCount(); cols++) {
                	 Object valor = model.getValueAt(rows, cols);
                	 if (valor != null) {
                		    pdfTable.addCell(valor.toString());
                	 }
                	 else {
                		 pdfTable.addCell("");
                	 }
                }
            }

            document.add(pdfTable);
            RoomTypesController rtc = new RoomTypesController();
            rtc.successDownload();
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}