package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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

import buttonCells.TableActionCellEditor;
import buttonCells.TableActionCellRender;
import buttonCells.TableActionEvent;
import controllers.ClientsController;
import controllers.HomeController;
import controllers.RentalsController;
import controllers.RoomTypesController;
import controllers.RoomsController;
import models.ClientsModel;
import models.RentalsModel;

public class RentalsView {

	private JFrame frame;
	private RentalsModel functions;
	public RentalsView() {
		RentalsModel functions = new RentalsModel();
	}
	
	public void rentals() {
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
		
		JLabel lblTitle = new JLabel("Rentas");
		lblTitle.setBounds(200, 42, 250, 82);
		header.add(lblTitle);
		lblTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 44));
		lblTitle.setForeground(Color.decode("#FFFFFF"));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(null);
		
		JTextField textField = new JTextField();
		textField.setBounds(100, 160, 659, 58);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Ver");
		btnNewButton.setBounds(764, 160, 180, 58);
		btnNewButton.setBackground(Color.decode("#071A2B"));
		btnNewButton.setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
		btnNewButton.setForeground(Color.decode("#FFFFFF"));
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Nueva renta");
		btnNewButton_1.setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
		btnNewButton_1.setBackground(Color.decode("#071A2B"));
		btnNewButton_1.setForeground(Color.decode("#FFFFFF"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RentalsController rental = new RentalsController();
				frame.dispose();
				rental.createRental();
			}
		});
		btnNewButton_1.setBounds(948, 160, 252, 58);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(100, 228, 1100, 165);
		panel_1.setBackground(Color.decode("#FFFFFF"));
		panel_1.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Filtros de busqueda");
		lblNewLabel.setBounds(10, 10, 240, 35);
		lblNewLabel.setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cliente");
		lblNewLabel_1.setBounds(100, 45, 93, 35);
		lblNewLabel_1.setFont(new Font("Inter_18pt Bold", Font.BOLD, 16));
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Habitación");
		lblNewLabel_1_1.setBounds(325, 45, 117, 35);
		lblNewLabel_1_1.setFont(new Font("Inter_18pt Bold", Font.BOLD, 16));
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Fecha inicio");
		lblNewLabel_1_2.setBounds(557, 45, 131, 35);
		lblNewLabel_1_2.setFont(new Font("Inter_18pt Bold", Font.BOLD, 16));
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Fecha fin");
		lblNewLabel_1_3.setBounds(788, 45, 117, 35);
		lblNewLabel_1_3.setFont(new Font("Inter_18pt Bold", Font.BOLD, 16));
		panel_1.add(lblNewLabel_1_3);
		
		JTextField textField_1 = new JTextField();
		textField_1.setBounds(100, 83, 200, 35);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_3_1 = new JButton("Limpiar filtros");
		btnNewButton_3_1.setBounds(557, 125, 200, 30);
		btnNewButton_3_1.setFont(new Font("Inter_18pt Bold", Font.BOLD, 18));
		btnNewButton_3_1.setBackground(new Color(153, 89, 45));
		btnNewButton_3_1.setForeground(Color.WHITE);
		panel_1.add(btnNewButton_3_1);
		
		JButton btnNewButton_3_1_1 = new JButton("Aplicar filtros");
		btnNewButton_3_1_1.setBounds(788, 125, 200, 30);
		btnNewButton_3_1_1.setForeground(Color.WHITE);
		btnNewButton_3_1_1.setBackground(new Color(7, 26, 43));
		btnNewButton_3_1_1.setFont(new Font("Inter_18pt Bold", Font.BOLD, 18));
		panel_1.add(btnNewButton_3_1_1);
		
		JTextField textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(325, 83, 200, 35);
		panel_1.add(textField_2);
		
		JTextField textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(557, 83, 200, 35);
		panel_1.add(textField_3);
		
		JTextField textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(788, 83, 200, 35);
		panel_1.add(textField_4);
		String[] columnNames = {
				"ID renta",
				"ID cliente",
				"Habitación",
				"Check-in",
				"Check-out",
				"Total",
				"Acciones"
		};
		
		Object [][] data = {
				{"1", "26","101","12/07/24","12/07/24","$2000",""},
				{"2", "12","102","12/07/24","12/07/24","$2000",""},
				{"3", "1","103","12/07/24","12/07/24","$2000",""},
				{"4", "3","104","12/07/24","12/07/24","$2000",""},
				
		};
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(100, 403, 1100, 270);
		panel.add(panel_2);
		panel_2.setLayout(null);
				DefaultTableModel model = new DefaultTableModel(data, columnNames);
				JTable clientsTable = new JTable(model);
				clientsTable.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 22));
				clientsTable.setRowHeight(30);
				clientsTable.getTableHeader().setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
				clientsTable.getTableHeader().setBackground(Color.decode("#071A2B"));
				clientsTable.getTableHeader().setForeground(Color.decode("#FFFFFF"));
				clientsTable.getColumnModel().getColumn(4).setPreferredWidth(150);
				clientsTable.setDefaultEditor(Object.class,null);
				TableActionEvent event = new TableActionEvent() {
		            @Override
		            public void onEdit(int row) {
		                System.out.println("Edit row : " + row);
		            }

		            @Override
		            public void onDelete(int row) {
		                if (clientsTable.isEditing()) {
		                	clientsTable.getCellEditor().stopCellEditing();
		                }
		                model.removeRow(row);
		            }

		            @Override
		            public void onView(int row) {
		                System.out.println("View row : " + row);
		            }
		        };
		        clientsTable.getColumn("Acciones").setCellRenderer(new TableActionCellRender());
		        clientsTable.getColumn("Acciones").setCellEditor(new TableActionCellEditor(event));
				JScrollPane scrollPane = new JScrollPane(clientsTable);
				scrollPane.setBounds(0, 0, 1100, 270);
				panel_2.add(scrollPane);
	}
	
	public void createRental() {
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
		
		JLabel lblTitle = new JLabel("Añadir renta");
		lblTitle.setBounds(200, 42, 350, 82);
		header.add(lblTitle);
		lblTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 44));
		lblTitle.setForeground(Color.decode("#FFFFFF"));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(null);
		
		JLabel lblNewLabel = new JLabel("Nombre del cliente");
		lblNewLabel.setBounds(117, 175, 178, 34);
		lblNewLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(117, 219, 344, 44);
		panel.add(comboBox);
		
		JButton btnNewButton = new JButton("Nuevo cliente");
		btnNewButton.setBounds(481, 219, 146, 44);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(7, 26, 43));
		btnNewButton.setFont(new Font("Inter_18pt Bold", Font.BOLD, 16));
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ClientsController client = new ClientsController();
				frame.dispose();
				client.createClient();
			}
			
		});
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(117, 314, 510, 44);
		panel.add(comboBox_1);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de habitación");
		lblNewLabel_1.setBounds(117, 270, 178, 34);
		lblNewLabel_1.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Check in");
		lblNewLabel_2.setBounds(691, 175, 178, 34);
		lblNewLabel_2.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel_2);
		
		JTextField textField = new JTextField();
		textField.setBounds(691, 219, 424, 44);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Check out");
		lblNewLabel_2_1.setBounds(691, 270, 178, 34);
		lblNewLabel_2_1.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel_2_1);
		
		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(691, 314, 424, 44);
		panel.add(textField_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Precio por noche");
		lblNewLabel_2_1_1.setBounds(691, 368, 178, 34);
		lblNewLabel_2_1_1.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel_2_1_1);
		
		JTextField textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(691, 412, 424, 44);
		panel.add(textField_2);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Total");
		lblNewLabel_2_1_1_1.setBounds(691, 466, 178, 34);
		lblNewLabel_2_1_1_1.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel_2_1_1_1);
		
		JTextField textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(691, 510, 424, 44);
		panel.add(textField_3);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(691, 601, 188, 51);
		btnNewButton_1.setBackground(new Color(153, 89, 45));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Inter_18pt Bold", Font.BOLD, 22));
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Aceptar");
		btnNewButton_1_1.setBounds(927, 601, 188, 51);
		btnNewButton_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1.setBackground(new Color(7, 26, 43));
		btnNewButton_1_1.setFont(new Font("Inter_18pt Bold", Font.BOLD, 22));
		panel.add(btnNewButton_1_1);
		
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RentalsController rent = new RentalsController();
				frame.dispose();
				rent.rentals();
			}
			
		});
	}
	
	public void editRental() {
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
		
		
		JLabel lblTitle = new JLabel("Editar renta");
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
				RentalsController rental = new RentalsController();
				frame.dispose();
				rental.rentals();
			}
			
		});
	}
	
	public void consultRental() {
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
				RentalsController rental = new RentalsController();
				frame.dispose();
				rental.rentals();
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
				RentalsController rental = new RentalsController();
				rental.successDownload();
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
				RentalsController rooms = new RentalsController();
				frame.dispose();
				rooms.successDelete();
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
				RentalsController rooms = new RentalsController();
				frame.dispose();
				rooms.errorDelete();
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
		
		JLabel title = new JLabel("Renta eliminada con exito");
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
