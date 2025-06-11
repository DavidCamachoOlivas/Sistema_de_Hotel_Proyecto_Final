package models;

import java.io.FileOutputStream;
import java.sql.Connection;
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

public class ClientsModel {
	 public int createClient(Client client) throws SQLException {
        String sql = "INSERT INTO client (phone_number, client_name, email, birth_date, profile_picture) VALUES (?, ?, ?, ?, ?)";
        
        try(Connection conn = ConnectionDB.getDataSource().getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, client.getPhone_number());
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
	public boolean updateClient(Client client) throws SQLException {
	    String sql = "UPDATE client SET phone_number = ?, client_name = ?, email = ?, birth_date = ?, profile_picture = ? WHERE id_client = ?";
	    
	    try (Connection conn = ConnectionDB.getDataSource().getConnection();
	    
	    		PreparedStatement stmt = conn.prepareStatement(sql)) {
	    	
	    	stmt.setString(1, client.getPhone_number());
            stmt.setString(2, client.getClient_name());
            stmt.setString(3, client.getEmail());
            stmt.setDate(4, client.getBirth_date());
            stmt.setBytes(5, client.getProfile_picture());
	        stmt.setInt(6, client.getId_client());
	    
	        int affected = stmt.executeUpdate();
	        return affected > 0;
	    }
	}
	
	public Client getClientById(int idClient) throws SQLException {
	    String sql = "SELECT * FROM client WHERE id_client = ?";
	    try (Connection conn = ConnectionDB.getDataSource().getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, idClient);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	            	Client client = new Client();
	            	client.setId_client(rs.getInt("id_client"));
	            	client.setPhone_number(rs.getString("phone_number"));
	            	client.setClient_name(rs.getString("client_name"));
	            	client.setEmail(rs.getString("email"));
	            	client.setBirth_date(rs.getDate("birth_date"));
	            	client.setProfile_picture(rs.getBytes("profile_picture"));
	                return client;
	            }
	        }
	    }
	    return null;
	 }
	
	public boolean deleteClient(Client c) throws SQLException {
		   
		 String sql = "DELETE FROM client WHERE id_client = ?";
		 
		 try (Connection conn = ConnectionDB.getDataSource().getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, c.getId_client());
		        int affected = stmt.executeUpdate();
		        return affected > 0;
		 }
	}
	  
	public List<Client> getAvailableClient() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT id_client, phone_number, client_name, email, birth_date, profile_picture FROM client";
        
        try (Connection conn = ConnectionDB.getDataSource().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
            	Client client = new Client(
        			rs.getInt("id_client"),
        			rs.getString("phone_number"),
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
