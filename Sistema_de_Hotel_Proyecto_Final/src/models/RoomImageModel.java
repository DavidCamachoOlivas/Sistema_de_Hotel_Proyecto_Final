package models;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Connection.ConnectionDB;
import controllers.AuthController;
import controllers.RoomsController;
import views.AuthView;

public class RoomImageModel {
	static byte[] imageBytes;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		cargarImagen();
	}
	
	public static int createRoomImage(RoomImage roomImage) throws SQLException {
	    String sql = "INSERT INTO `room_image` (`id_room`, `room_image`) VALUES (?, ?)";
	   
	    try (Connection conn = ConnectionDB.getDataSource().getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

	        if (roomImage.getId_Room() == 0) {
	            stmt.setNull(1, java.sql.Types.INTEGER);
	        } else {
	            stmt.setInt(1, roomImage.getId_Room());
	        }

	        stmt.setBytes(2, roomImage.getRoom_Image());
	        
	        stmt.executeUpdate();
	        
	        try (ResultSet rs = stmt.getGeneratedKeys()) {
	            if (rs.next()) {
	                return rs.getInt(1);
	            }
	        }
	    }
	    throw new SQLException("No se pudo obtener el ID generado");
	}
	
	public boolean updateRoomImageReference(int oldImageId, int newImageId, int roomId) throws SQLException {
	    try (Connection conn = ConnectionDB.getDataSource().getConnection()) {
	        conn.setAutoCommit(false);  // Transacción manual

	        try {
	            // 1. Liberar la imagen anterior
	            if (oldImageId > 0) {
	                try (PreparedStatement stmt = conn.prepareStatement(
	                        "UPDATE room_image SET id_room = NULL WHERE id_room_image = ?")) {
	                    stmt.setInt(1, oldImageId);
	                    stmt.executeUpdate();
	                }
	            }

	            // 2. Asignar la nueva imagen
	            if (newImageId > 0) {
	                try (PreparedStatement stmt = conn.prepareStatement(
	                        "UPDATE room_image SET id_room = ? WHERE id_room_image = ?")) {
	                    stmt.setInt(1, roomId);
	                    stmt.setInt(2, newImageId);
	                    stmt.executeUpdate();
	                }
	            }

	            conn.commit();
	            return true;

	        } catch (SQLException e) {
	            conn.rollback();
	            throw e;
	        }
	    }
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
	
	public static void cargarImagen() {
		JFrame frame = new JFrame();
		frame.setTitle("Hotel Ancla de Paz");
		frame.setResizable(false);
		frame.setBounds(0,0,1280,720);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setBackground(Color.decode("#FFFCF7"));//FBF3E6
		panel.setLayout(null);
	
		JPanel header = new JPanel();
		header.setBounds(0, 0, 1266, 130);
		header.setBackground(Color.decode("#071A2B"));
		panel.add(header);
		header.setLayout(null);
		
		
		JLabel lblTitle = new JLabel("Añadir Imagen");
		lblTitle.setBounds(200, 42, 550, 82);
		header.add(lblTitle);
		lblTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 44));
		lblTitle.setForeground(Color.decode("#FFFFFF"));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(null);
		
		JLabel lblImg = new JLabel();
		lblImg.setBounds(130,150,510,200);
		lblImg.setText(null);
		lblImg.setBorder(BorderFactory.createLineBorder(Color.black));
		
		ImageIcon imagenPredeterminada = new ImageIcon("src/images/subir.png");
		Image scaledPredeterminada = imagenPredeterminada.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		
		
		lblImg.setHorizontalAlignment(JLabel.CENTER);
		lblImg.setVerticalAlignment(JLabel.CENTER);
		lblImg.setIcon(new ImageIcon(scaledPredeterminada));
		panel.add(lblImg);
		
		JButton btnAddImage = new JButton("Agregar imagen");
		btnAddImage.setBounds(700,300,460,60);
		btnAddImage.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnAddImage.setForeground(Color.decode("#FFFFFF"));
		btnAddImage.setBackground(Color.decode("#071A2B"));
		panel.add(btnAddImage);
		
		btnAddImage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			    JFileChooser fileChooser = new JFileChooser();
			    fileChooser.setDialogTitle("Selecciona una imagen");
			    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
			    int resultado = fileChooser.showOpenDialog(null);
			    if (resultado == JFileChooser.APPROVE_OPTION) {
			        File imagenSeleccionada = fileChooser.getSelectedFile();
			
			        try (FileInputStream fis = new FileInputStream(imagenSeleccionada)) {
			        	imageBytes = fis.readAllBytes();
			        	
			            	ImageIcon icon = new ImageIcon(imageBytes);
			            	Image scaled = icon.getImage().getScaledInstance(510, 510, Image.SCALE_SMOOTH);
			            	lblImg.setIcon(new ImageIcon(scaled));
			            	
			        } catch (IOException e1) {
			            e1.printStackTrace();
			        }
			    }
			}			
		});
		
		
		JButton btnCreate = new JButton("Guardar");
		btnCreate.setBounds(970,600,200,70);
		btnCreate.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnCreate.setForeground(Color.decode("#FFFFFF"));
		btnCreate.setBackground(Color.decode("#071A2B"));
		panel.add(btnCreate);
		
		btnCreate.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        RoomImage roomimage = new RoomImage();

		        roomimage.setId_Room(0);
		        roomimage.setRoom_Image(imageBytes);

		        try {
		            createRoomImage(roomimage);
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		    }
		});


		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(760,600,200,70);
		btnCancel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnCancel.setForeground(Color.decode("#FFFFFF"));
		btnCancel.setBackground(new Color(153, 89, 45));
		panel.add(btnCancel);
		
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomsController home = new RoomsController();
				frame.dispose();
				home.rooms();
			}
			
		});
	}

}
