package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.AuthController;
import controllers.ClientsController;
import controllers.HomeController;
import controllers.RentalsController;
import controllers.RoomTypesController;
import controllers.RoomsController;
import models.AuthModel;
import models.HomeModel;

public class HomeView extends JFrame{

	private JFrame frame;
	private HomeModel functions;
	public HomeView() {
		functions = new HomeModel();
	}
	
	public void home() {
		frame = new JFrame();
		frame.setTitle("Hotel Ancla de Paz");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);
		frame.setBounds(0,0,1400,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setBackground(Color.decode("#FFFCF7"));//FBF3E6
		panel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Inicio");
		lblTitle.setBounds(100,50,450,70);
		lblTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 64));
		lblTitle.setForeground(Color.decode("#071A2B"));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(Color.green);
		panel.add(lblTitle);
		
		JButton btnLogout = new JButton("Cerrar sesión");
		btnLogout.setBounds(1100,50,350,70);
		btnLogout.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnLogout.setForeground(Color.decode("#FFFFFF"));
		btnLogout.setBackground(Color.decode("#071A2B"));
		panel.add(btnLogout);
		
		btnLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AuthController auth = new AuthController();
				frame.dispose();
				auth.login();
			}
			
		});
		
		JButton btnRooms = new JButton("Habitaciones");
		btnRooms.setBounds(100,200,350,70);
		btnRooms.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnRooms.setForeground(Color.decode("#FFFFFF"));
		btnRooms.setBackground(Color.decode("#071A2B"));
		panel.add(btnRooms);
		
		btnRooms.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomsController rooms = new RoomsController();
				frame.dispose();
				rooms.rooms();
			}
			
		});
		
		JButton btnRoomTypes = new JButton("Tipos de habitaciones");
		btnRoomTypes.setBounds(500,200,350,70);
		btnRoomTypes.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnRoomTypes.setForeground(Color.decode("#FFFFFF"));
		btnRoomTypes.setBackground(Color.decode("#071A2B"));
		panel.add(btnRoomTypes);
		
		btnRoomTypes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomTypesController rooms = new RoomTypesController();
				frame.dispose();
				rooms.roomTypes();
			}
			
		});
		
		JButton btnRentals = new JButton("Rentas");
		btnRentals.setBounds(1000,200,350,70);
		btnRentals.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnRentals.setForeground(Color.decode("#FFFFFF"));
		btnRentals.setBackground(Color.decode("#071A2B"));
		panel.add(btnRentals);
		
		btnRentals.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RentalsController rental = new RentalsController();
				frame.dispose();
				rental.rentals();
			}
			
		});
		
		JButton btnClients = new JButton("Clientes");
		btnClients.setBounds(100,300,350,70);
		btnClients.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnClients.setForeground(Color.decode("#FFFFFF"));
		btnClients.setBackground(Color.decode("#071A2B"));
		panel.add(btnClients);
		
		btnClients.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ClientsController client = new ClientsController();
				frame.dispose();
				client.clients();
			}
			
		});
		
		JButton btnTariffs = new JButton("Tarifas");
		btnTariffs.setBounds(500,300,350,70);
		btnTariffs.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnTariffs.setForeground(Color.decode("#FFFFFF"));
		btnTariffs.setBackground(Color.decode("#071A2B"));
		panel.add(btnTariffs);
	}
}
