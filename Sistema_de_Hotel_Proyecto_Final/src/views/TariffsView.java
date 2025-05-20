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

import controllers.ClientsController;
import controllers.HomeController;
import controllers.RoomsController;
import controllers.TariffsController;
import models.ClientsModel;
import models.TariffsModel;

public class TariffsView {

	private JFrame frame;
	private TariffsModel functions;
	public TariffsView() {
		functions = new TariffsModel();
	}
	
	public void tariff() {
		frame = new JFrame();
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
		
		
		JLabel lblTitle = new JLabel("Tarifas");
		lblTitle.setBounds(100,50,450,70);
		lblTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 64));
		lblTitle.setForeground(Color.decode("#071A2B"));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(Color.green);
		panel.add(lblTitle);
		
		JButton btnHome = new JButton("Regresar");
		btnHome.setBounds(900,50,300,70);
		btnHome.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnHome.setForeground(Color.decode("#FFFFFF"));
		btnHome.setBackground(Color.decode("#071A2B"));
		panel.add(btnHome);
		
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HomeController home = new HomeController();
				frame.dispose();
				home.home();
			}
			
		});
		
		
		JButton btnCreate = new JButton("Crear");
		btnCreate.setBounds(100,250,300,70);
		btnCreate.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnCreate.setForeground(Color.decode("#FFFFFF"));
		btnCreate.setBackground(Color.decode("#071A2B"));
		panel.add(btnCreate);
		
		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController tariff = new TariffsController();
				frame.dispose();
				tariff.createClient();
			}
			
		});
		
		JButton btnEdit = new JButton("Editar");
		btnEdit.setBounds(500,250,300,70);
		btnEdit.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnEdit.setForeground(Color.decode("#FFFFFF"));
		btnEdit.setBackground(Color.decode("#071A2B"));
		panel.add(btnEdit);
		
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController tariff = new TariffsController();
				frame.dispose();
				tariff.editClient();
			}
			
		});
		
		JButton btnConsult = new JButton("Detalles");
		btnConsult.setBounds(900,250,300,70);
		btnConsult.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnConsult.setForeground(Color.decode("#FFFFFF"));
		btnConsult.setBackground(Color.decode("#071A2B"));
		panel.add(btnConsult);
		
		btnConsult.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController tariff = new TariffsController();
				frame.dispose();
				tariff.consultClient();
			}
			
		});
		
		JButton btnDelete = new JButton("Eliminar");
		btnDelete.setBounds(100,350,300,70);
		btnDelete.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnDelete.setForeground(Color.decode("#FFFFFF"));
		btnDelete.setBackground(Color.decode("#071A2B"));
		panel.add(btnDelete);
		
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController tariff = new TariffsController();
				tariff.deleteClient();
			}
			
		});
	}
	
	public void createTariff() {
		frame = new JFrame();
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
		
		
		JLabel lblTitle = new JLabel("Añadir tarifa");
		lblTitle.setBounds(100,50,450,70);
		lblTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 64));
		lblTitle.setForeground(Color.decode("#071A2B"));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(Color.green);
		panel.add(lblTitle);
		
		JButton btnHome = new JButton("Regresar");
		btnHome.setBounds(900,50,300,70);
		btnHome.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnHome.setForeground(Color.decode("#FFFFFF"));
		btnHome.setBackground(Color.decode("#071A2B"));
		panel.add(btnHome);
		
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController tariff = new TariffsController();
				frame.dispose();
				tariff.tariffs();
			}
			
		});
	}
	
	public void editTariff() {
		frame = new JFrame();
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
		
		
		JLabel lblTitle = new JLabel("Editar tarifa");
		lblTitle.setBounds(100,50,450,70);
		lblTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 64));
		lblTitle.setForeground(Color.decode("#071A2B"));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(Color.green);
		panel.add(lblTitle);
		
		JButton btnHome = new JButton("Regresar");
		btnHome.setBounds(900,50,300,70);
		btnHome.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnHome.setForeground(Color.decode("#FFFFFF"));
		btnHome.setBackground(Color.decode("#071A2B"));
		panel.add(btnHome);
		
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController tariff = new TariffsController();
				frame.dispose();
				tariff.tariffs();
			}
			
		});
	}
	
	public void consultTariff() {
		frame = new JFrame();
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
		
		
		JLabel lblTitle = new JLabel("Detalles");
		lblTitle.setBounds(100,50,450,70);
		lblTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 64));
		lblTitle.setForeground(Color.decode("#071A2B"));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(Color.green);
		panel.add(lblTitle);
		
		JButton btnHome = new JButton("Regresar");
		btnHome.setBounds(900,50,300,70);
		btnHome.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnHome.setForeground(Color.decode("#FFFFFF"));
		btnHome.setBackground(Color.decode("#071A2B"));
		panel.add(btnHome);
		
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController tariff = new TariffsController();
				frame.dispose();
				tariff.tariffs();
			}
			
		});
		
		JButton lblDownload = new JButton("Descargar .pdf");
		lblDownload.setBounds(900,500,300,70);
		lblDownload.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		lblDownload.setForeground(Color.decode("#FFFFFF"));
		lblDownload.setBackground(Color.decode("#071A2B"));
		panel.add(lblDownload);
		
		lblDownload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController tariff = new TariffsController();
				frame.dispose();
				tariff.successDownload();
			}
			
		});
	}
	
	public void deleteConfirm() {
		frame = new JFrame();
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setBackground(Color.decode("#FFFCF7"));//FBF3E6
		panel.setLayout(null);
		
		JLabel title = new JLabel("Confirmar eliminación ");
		title.setBounds(100,100,400,70);
		title.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		title.setVisible(true);
		panel.add(title);
		
		JButton accept = new JButton("Aceptar");
		accept.setBounds(350,350,300,70);
		accept.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		accept.setForeground(Color.decode("#FFFFFF"));
		accept.setBackground(Color.decode("#071A2B"));
		panel.add(accept);
		
		accept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController tariff = new TariffsController();
				frame.dispose();
				tariff.successDelete();
			}
			
		});
		
		JButton deny = new JButton("Cancelar");
		deny.setBounds(50,350,300,70);
		deny.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		deny.setForeground(Color.decode("#FFFFFF"));
		deny.setBackground(Color.decode("#071A2B"));
		panel.add(deny);
		
		deny.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController tariff = new TariffsController();
				frame.dispose();
				tariff.errorDelete();
			}
			
		});
	}
	
	public void successDelete() {
		frame = new JFrame();
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setBackground(Color.decode("#FFFCF7"));//FBF3E6
		panel.setLayout(null);
		
		JLabel title = new JLabel("Tarifa eliminado con exito");
		title.setBounds(50,100,600,70);
		title.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		title.setVisible(true);
		panel.add(title);
		
	}
	
	public void succesDownload() {
		frame = new JFrame();
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setBackground(Color.decode("#FFFCF7"));//FBF3E6
		panel.setLayout(null);
		
		JLabel title = new JLabel("PDF descargado con exito");
		title.setBounds(50,100,600,70);
		title.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		title.setVisible(true);
		panel.add(title);
	}
	
	public void errorDelete() {
		frame = new JFrame();
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setBackground(Color.decode("#FFFCF7"));//FBF3E6
		panel.setLayout(null);
		
		JLabel title = new JLabel("Hubo un error en la eliminación");
		title.setBounds(50,100,600,70);
		title.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		title.setVisible(true);
		panel.add(title);
	}
}
