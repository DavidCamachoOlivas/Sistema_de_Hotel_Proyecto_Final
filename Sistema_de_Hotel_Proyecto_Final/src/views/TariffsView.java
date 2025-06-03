package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import buttonCells.TableActionCellEditor2;
import buttonCells.TableActionCellRender2;
import buttonCells.TableActionEvent2;
import controllers.ClientsController;
import controllers.HomeController;
import controllers.RentalsController;
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
		
		JPanel header = new JPanel();
		header.setBounds(0, 0, 1266, 130);
		header.setBackground(Color.decode("#071A2B"));
		panel.add(header);
		header.setLayout(null);
		
		JButton btnHome = new JButton("");
		btnHome.setBounds(130, 60, 56, 56);
		header.add(btnHome);
		btnHome.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnHome.setForeground(Color.decode("#FFFFFF"));
		btnHome.setBorderPainted(false);
		btnHome.setBackground(null);
		ImageIcon btnHomeOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/btnHome.png"));
		Image btnHomeScaledImage = btnHomeOriginalIcon.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH);
		ImageIcon btnHomeScaledIcon = new ImageIcon(btnHomeScaledImage);
		btnHome.setIcon(btnHomeScaledIcon);
		
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HomeController home = new HomeController();
				frame.dispose();
				home.home();
			}
			
		});
		
		JLabel lblTitle = new JLabel("Tarifas");
		lblTitle.setBounds(200, 42, 250, 82);
		header.add(lblTitle);
		lblTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 44));
		lblTitle.setForeground(Color.decode("#FFFFFF"));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(null);
		
		JTextField textField = new JTextField();
		textField.setBounds(130, 208, 777, 58);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Ver");
		btnNewButton.setBounds(917, 207, 213, 58);
		btnNewButton.setFont(new Font("Inter_18pt Bold", Font.BOLD, 22));
		btnNewButton.setForeground(Color.decode("#FFFFFF"));
		btnNewButton.setBackground(new Color(7, 26, 43));
		panel.add(btnNewButton);
		
		String[] columnNames = {
				"Tarifas",
				"Precios",
				"Descripción",
				"Acciones"
		};
		
		Object [][] data = {
				{"Reembolsable", "$1,300","Cancelación gratuita hasta antes de 24hrs",""},
				{"Reembolsable", "$1,300","Cancelación gratuita hasta antes de 24hrs",""},
				{"Reembolsable", "$1,300","Cancelación gratuita hasta antes de 24hrs",""},
				{"Reembolsable", "$1,300","Cancelación gratuita hasta antes de 24hrs",""},
				
		};
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(130, 286, 1000, 300);
		panel.add(panel_1);
			DefaultTableModel model = new DefaultTableModel(data, columnNames);
			JTable clientsTable = new JTable(model);
			clientsTable.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 22));
			clientsTable.setRowHeight(30);
			clientsTable.getTableHeader().setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
			clientsTable.getTableHeader().setBackground(Color.decode("#071A2B"));
			clientsTable.getTableHeader().setForeground(Color.decode("#FFFFFF"));
			clientsTable.setDefaultEditor(Object.class,null);
			TableActionEvent2 event = new TableActionEvent2() {
	            @Override
	            public void onEdit(int row) {
	            	TariffsController rte = new TariffsController();
	            	frame.dispose();
	            	rte.editTariff();
	                System.out.println("Edit row : " + row);
	            }
	
	            @Override
	            public void onDelete(int row) {
	                if (clientsTable.isEditing()) {
	                	clientsTable.getCellEditor().stopCellEditing();
	                }
	                //model.removeRow(row);
	            }
	
	        };
	        clientsTable.getColumn("Acciones").setCellRenderer(new TableActionCellRender2());
	        clientsTable.getColumn("Acciones").setCellEditor(new TableActionCellEditor2(event));
			JScrollPane scrollPane = new JScrollPane(clientsTable);
			panel_1.setLayout(null);
			scrollPane.setBounds(0, 0, 1000, 300);
			panel_1.add(scrollPane);
		JButton btnNewButton1 = new JButton("Añadir");
		btnNewButton1.setBounds(917, 600, 213, 58);
		btnNewButton1.setFont(new Font("Inter_18pt Bold", Font.BOLD, 32));
		btnNewButton1.setForeground(Color.decode("#FFFFFF"));
		btnNewButton1.setBackground(new Color(7, 26, 43));
		panel.add(btnNewButton1);
		btnNewButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController rte = new TariffsController();
				frame.dispose();
				rte.createTariff();
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
		
		JPanel header = new JPanel();
		header.setBounds(0, 0, 1266, 130);
		header.setBackground(Color.decode("#071A2B"));
		panel.add(header);
		header.setLayout(null);
		
		JButton btnHome = new JButton("");
		btnHome.setBounds(130, 60, 56, 56);
		header.add(btnHome);
		btnHome.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnHome.setForeground(Color.decode("#FFFFFF"));
		btnHome.setBorderPainted(false);
		btnHome.setBackground(null);
		ImageIcon btnHomeOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/btnHome.png"));
		Image btnHomeScaledImage = btnHomeOriginalIcon.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH);
		ImageIcon btnHomeScaledIcon = new ImageIcon(btnHomeScaledImage);
		btnHome.setIcon(btnHomeScaledIcon);
		
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController tariffs = new TariffsController();
				frame.dispose();
				tariffs.tariffs();
			}
			
		});
		
		JLabel lblTitle = new JLabel("Añadir tarifas");
		lblTitle.setBounds(200, 42, 550, 82);
		header.add(lblTitle);
		lblTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 44));
		lblTitle.setForeground(Color.decode("#FFFFFF"));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(null);
		
		JLabel lblNewLabel = new JLabel("Tipo de tarifa");
		lblNewLabel.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		lblNewLabel.setBounds(130, 172, 202, 42);
		panel.add(lblNewLabel);
		
		JTextField textField = new JTextField();
		textField.setBounds(130, 224, 1000, 56);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Precio por noche");
		lblNewLabel_1.setBounds(130, 313, 250, 42);
		lblNewLabel_1.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		panel.add(lblNewLabel_1);
		
		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(130, 365, 1000, 56);
		panel.add(textField_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("¿Es reembolsable?");
		lblNewLabel_1_1.setBounds(130, 449, 250, 42);
		lblNewLabel_1_1.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		panel.add(lblNewLabel_1_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(130, 501, 1000, 56);
		panel.add(comboBox);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(947, 587, 183, 56);
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("Inter_18pt Bold", Font.BOLD, 22));
		btnNewButton.setForeground(Color.decode("#FFFFFF"));
		btnNewButton.setBackground(new Color(7, 26, 43));
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController home = new TariffsController();
				frame.dispose();
				home.tariffs();
			}
			
		});
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(732, 587, 183, 56);
		panel.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Inter_18pt Bold", Font.BOLD, 22));
		btnNewButton_1.setForeground(Color.decode("#FFFFFF"));
		btnNewButton_1.setBackground(Color.decode("#99592D"));
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController home = new TariffsController();
				frame.dispose();
				home.tariffs();
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
		
		JPanel header = new JPanel();
		header.setBounds(0, 0, 1266, 130);
		header.setBackground(Color.decode("#071A2B"));
		panel.add(header);
		header.setLayout(null);
		
		JButton btnHome = new JButton("");
		btnHome.setBounds(130, 60, 56, 56);
		header.add(btnHome);
		btnHome.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnHome.setForeground(Color.decode("#FFFFFF"));
		btnHome.setBorderPainted(false);
		btnHome.setBackground(null);
		ImageIcon btnHomeOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/btnHome.png"));
		Image btnHomeScaledImage = btnHomeOriginalIcon.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH);
		ImageIcon btnHomeScaledIcon = new ImageIcon(btnHomeScaledImage);
		btnHome.setIcon(btnHomeScaledIcon);
		
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController tariffs = new TariffsController();
				frame.dispose();
				tariffs.tariffs();
			}
			
		});
		
		JLabel lblTitle = new JLabel("Añadir tarifas");
		lblTitle.setBounds(200, 42, 550, 82);
		header.add(lblTitle);
		lblTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 44));
		lblTitle.setForeground(Color.decode("#FFFFFF"));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(null);
		
		JLabel lblNewLabel = new JLabel("Tipo de tarifa");
		lblNewLabel.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		lblNewLabel.setBounds(130, 172, 202, 42);
		panel.add(lblNewLabel);
		
		JTextField textField = new JTextField("Estandar");
		textField.setBounds(130, 224, 1000, 56);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Precio por noche");
		lblNewLabel_1.setBounds(130, 313, 250, 42);
		lblNewLabel_1.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		panel.add(lblNewLabel_1);
		
		JTextField textField_1 = new JTextField("$2,000");
		textField_1.setColumns(10);
		textField_1.setBounds(130, 365, 1000, 56);
		panel.add(textField_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("¿Es reembolsable?");
		lblNewLabel_1_1.setBounds(130, 449, 250, 42);
		lblNewLabel_1_1.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		panel.add(lblNewLabel_1_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(130, 501, 1000, 56);
		panel.add(comboBox);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(947, 587, 183, 56);
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("Inter_18pt Bold", Font.BOLD, 22));
		btnNewButton.setForeground(Color.decode("#FFFFFF"));
		btnNewButton.setBackground(new Color(7, 26, 43));
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController home = new TariffsController();
				frame.dispose();
				home.tariffs();
			}
			
		});
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(732, 587, 183, 56);
		panel.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Inter_18pt Bold", Font.BOLD, 22));
		btnNewButton_1.setForeground(Color.decode("#FFFFFF"));
		btnNewButton_1.setBackground(Color.decode("#99592D"));
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController home = new TariffsController();
				frame.dispose();
				home.tariffs();
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
