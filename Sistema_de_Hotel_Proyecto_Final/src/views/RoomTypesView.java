package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.ClientsController;
import controllers.HomeController;
import controllers.RoomTypesController;
import controllers.RoomsController;
import models.ClientsModel;
import models.RoomTypesModel;

public class RoomTypesView {

	private JFrame frame;
	private RoomTypesModel functions;
	public RoomTypesView() {
		functions = new RoomTypesModel();
	}
	
	public void roomTypes() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(new Rectangle(0, 0, 720, 1280));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 1280, 720);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 252, 247));
		contentPane.setBounds(new Rectangle(0, 0, 720, 1280));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(7, 26, 43));
		panel.setBounds(-26, 0, 1290, 130);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Tipo de habitaciones");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Inter_18pt Bold", Font.BOLD, 44));
		lblNewLabel_4.setBounds(200, 42, 500, 82);
		panel.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(130, 60, 56, 56);
		Image imgBtnHome = new ImageIcon("src/images/btnHome.png").getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH);
		btnNewButton.setIcon(new ImageIcon(imgBtnHome));
		btnNewButton.setBackground(null);
		btnNewButton.setBorder(null);
		panel.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HomeController home = new HomeController();
				frame.dispose();
				home.home();
			}
			
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(130, 169, 310, 381);
		contentPane.add(panel_1);
		panel_1.setBorder(BorderFactory.createLineBorder(Color.black));  
		panel_1.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		Image icon = new ImageIcon("src/images/cama.png").getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
		ImageIcon backgroundIcon = new ImageIcon(icon);
		JLabel backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setBounds(0, 0, 900, 500);
		panel_2.setOpaque(false);
		panel_2.setBounds(0, 0, 900, 500);

		backgroundLabel.add(panel_2);
		panel_1.add(backgroundLabel);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Habitación de lujo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(10, 31, 271, 46);
		panel_5.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("$2.000 MXN por noche");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(10, 77, 182, 30);
		panel_5.add(lblNewLabel_3);
		
		JButton btnNewButton2 = new JButton("Eliminar");
		btnNewButton2.setBackground(new Color(153, 89, 45));
		btnNewButton2.setForeground(Color.WHITE);
		btnNewButton2.setBounds(10, 118, 89, 49);
		panel_5.add(btnNewButton2);
		
		btnNewButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomTypesController rooms = new RoomTypesController();
				rooms.deleteRoomType();
			}
			
		});
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setBounds(110, 118, 89, 49);
		panel_5.add(btnEditar);
		
		btnEditar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomTypesController rooms = new RoomTypesController();
				frame.dispose();
				rooms.editRoomType();
			}
			
		});
		
		JButton btnDetalles = new JButton("Detalles");
		btnDetalles.setForeground(Color.WHITE);
		btnDetalles.setBackground(new Color(7, 26, 43));
		btnDetalles.setBounds(209, 118, 89, 49);
		panel_5.add(btnDetalles);
		
		btnDetalles.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomTypesController rooms = new RoomTypesController();
				frame.dispose();
				rooms.consultRoomType();
			}
			
		});
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(468, 169, 310, 381);
		contentPane.add(panel_1_1);
		panel_1_1.setBorder(BorderFactory.createLineBorder(Color.black));  
		panel_1_1.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_6 = new JPanel();
		Image icon2 = new ImageIcon("src/images/suite.png").getImage().getScaledInstance(500, 200, Image.SCALE_SMOOTH);
		ImageIcon backgroundIcon2 = new ImageIcon(icon2);
		JLabel backgroundLabel2 = new JLabel(backgroundIcon2);
		backgroundLabel2.setBounds(0, 0, 900, 500);
		panel_6.setOpaque(false);
		panel_6.setBounds(0, 0, 900, 500);
		
		backgroundLabel2.add(panel_6);	
		panel_1_1.add(backgroundLabel2);
		
		JPanel panel_3 = new JPanel();
		panel_1_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Habitación de lujo");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_1.setBounds(10, 29, 271, 46);
		panel_3.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("$2.000 MXN por noche");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3_1.setBounds(10, 74, 182, 30);
		panel_3.add(lblNewLabel_3_1);
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(153, 89, 45));
		btnNewButton_1.setBounds(10, 115, 89, 49);
		panel_3.add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomTypesController rooms = new RoomTypesController();
				rooms.deleteRoomType();
			}
			
		});
		
		JButton btnEditar_1 = new JButton("Editar");
		btnEditar_1.setBackground(Color.WHITE);
		btnEditar_1.setBounds(109, 115, 89, 49);
		panel_3.add(btnEditar_1);
		
		btnEditar_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomTypesController rooms = new RoomTypesController();
				frame.dispose();
				rooms.editRoomType();
			}
			
		});
		
		JButton btnDetalles_1 = new JButton("Detalles");
		btnDetalles_1.setForeground(Color.WHITE);
		btnDetalles_1.setBackground(new Color(7, 26, 43));
		btnDetalles_1.setBounds(209, 115, 89, 49);
		panel_3.add(btnDetalles_1);
		
		btnDetalles_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomTypesController rooms = new RoomTypesController();
				frame.dispose();
				rooms.consultRoomType();
			}
			
		});
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBounds(811, 169, 310, 381);
		contentPane.add(panel_1_2);
		panel_1_2.setBorder(BorderFactory.createLineBorder(Color.black));  
		panel_1_2.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_4 = new JPanel();
		Image icon3 = new ImageIcon("src/images/normal.png").getImage().getScaledInstance(500, 200, Image.SCALE_SMOOTH);
		ImageIcon backgroundIcon3 = new ImageIcon(icon3);
		JLabel backgroundLabel3 = new JLabel(backgroundIcon3);
		backgroundLabel3.setBounds(0, 0, 900, 500);
		panel_2.setOpaque(false);
		panel_2.setBounds(0, 0, 900, 500);

		backgroundLabel3.add(panel_4);
		panel_1_2.add(backgroundLabel3);
		
		JPanel panel_7 = new JPanel();
		panel_1_2.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Habitación de lujo");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_2.setBounds(10, 29, 271, 46);
		panel_7.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3_2 = new JLabel("$2.000 MXN por noche");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3_2.setBounds(10, 74, 182, 30);
		panel_7.add(lblNewLabel_3_2);
		
		JButton btnNewButton_1_1 = new JButton("Eliminar");
		btnNewButton_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1.setBackground(new Color(153, 89, 45));
		btnNewButton_1_1.setBounds(10, 114, 89, 49);
		panel_7.add(btnNewButton_1_1);
		
		btnNewButton_1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomTypesController rooms = new RoomTypesController();
				rooms.deleteRoomType();
			}
			
		});
		
		JButton btnEditar_1_1 = new JButton("Editar");
		btnEditar_1_1.setBackground(Color.WHITE);
		btnEditar_1_1.setBounds(109, 114, 89, 49);
		panel_7.add(btnEditar_1_1);
		
		btnEditar_1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomTypesController rooms = new RoomTypesController();
				frame.dispose();
				rooms.editRoomType();
			}
			
		});
		
		JButton btnDetalles_1_1 = new JButton("Detalles");
		btnDetalles_1_1.setForeground(Color.WHITE);
		btnDetalles_1_1.setBackground(new Color(7, 26, 43));
		btnDetalles_1_1.setBounds(209, 114, 89, 49);
		panel_7.add(btnDetalles_1_1);
		
		btnDetalles_1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomTypesController rooms = new RoomTypesController();
				frame.dispose();
				rooms.consultRoomType();
			}
			
		});
		
		JButton btnEditar_2 = new JButton("1");
		btnEditar_2.setBackground(Color.WHITE);
		btnEditar_2.setBounds(514, 561, 44, 44);
		contentPane.add(btnEditar_2);
		
		JButton btnEditar_2_1 = new JButton("2");
		btnEditar_2_1.setBackground(Color.WHITE);
		btnEditar_2_1.setBounds(568, 561, 44, 44);
		contentPane.add(btnEditar_2_1);
		
		JButton btnEditar_2_1_1 = new JButton("3");
		btnEditar_2_1_1.setBackground(Color.WHITE);
		btnEditar_2_1_1.setBounds(622, 561, 44, 44);
		contentPane.add(btnEditar_2_1_1);
		
		JButton btnEditar_2_1_2 = new JButton("...");
		btnEditar_2_1_2.setBackground(Color.WHITE);
		btnEditar_2_1_2.setBounds(676, 561, 44, 44);
		contentPane.add(btnEditar_2_1_2);
		
		JButton btnNuevoTipo = new JButton("Nuevo tipo");
		btnNuevoTipo.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNuevoTipo.setForeground(Color.WHITE);
		btnNuevoTipo.setBackground(new Color(7, 26, 43));
		btnNuevoTipo.setBounds(395, 621, 459, 49);
		contentPane.add(btnNuevoTipo);
		
		btnNuevoTipo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomTypesController rooms = new RoomTypesController();
				frame.dispose();
				rooms.createRoomType();
			}
			
		});
	}
	
	public void createRoomType() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(new Rectangle(0, 0, 720, 1280));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1280, 720);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 252, 247));
		contentPane.setBounds(new Rectangle(0, 0, 720, 1280));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(7, 26, 43));
		panel.setBounds(-26, 0, 1290, 119);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(130, 30, 78, 56);
		Image imgBtnHome = new ImageIcon("src/images/btnHome.png").getImage().getScaledInstance(78, 56, Image.SCALE_SMOOTH);
		btnNewButton.setIcon(new ImageIcon(imgBtnHome));
		btnNewButton.setBackground(null);
		btnNewButton.setBorder(null);
		panel.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomTypesController rooms = new RoomTypesController();
				frame.dispose();
				rooms.roomTypes();
			}
			
		});
		
		JLabel lblNewLabel_4 = new JLabel("Añadir tipo de habitación");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_4.setBounds(238, 21, 550, 66);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(146, 21, 128, 75);
		Image img = new ImageIcon("src/flecha.png").getImage().getScaledInstance(76, 58, Image.SCALE_SMOOTH);
		lblNewLabel_5.setIcon(new ImageIcon(img));
		panel.add(lblNewLabel_5);
		
		JTextField textField = new JTextField();
		textField.setBounds(122, 196, 470, 49);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(646, 196, 470, 49);
		contentPane.add(textField_1);
		
		JTextField textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(122, 327, 994, 49);
		contentPane.add(textField_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(122, 466, 470, 49);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(646, 466, 470, 49);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel = new JLabel("Tipo de habitación");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(122, 158, 309, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblTipoDeTarifa = new JLabel("Tipo de tarifa");
		lblTipoDeTarifa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTipoDeTarifa.setBounds(646, 158, 309, 27);
		contentPane.add(lblTipoDeTarifa);
		
		JLabel lblHabitacionesSeleccionadas = new JLabel("Habitaciones seleccionadas");
		lblHabitacionesSeleccionadas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHabitacionesSeleccionadas.setBounds(122, 289, 309, 27);
		contentPane.add(lblHabitacionesSeleccionadas);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPiso.setBounds(122, 428, 309, 27);
		contentPane.add(lblPiso);
		
		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCapacidad.setBounds(646, 428, 309, 27);
		contentPane.add(lblCapacidad);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(new Color(153, 89, 45));
		btnCancelar.setBounds(808, 571, 140, 72);
		contentPane.add(btnCancelar);
		
		JButton btnNewButton_1 = new JButton("Añadir");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(7, 26, 43));
		btnNewButton_1.setBounds(976, 571, 140, 72);
		contentPane.add(btnNewButton_1);
		/*frame.setTitle("Hotel Ancla de Paz");
		frame.setResizable(false);
		frame.setBounds(0,0,1280,720);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setBackground(Color.decode("#FFFCF7"));//FBF3E6
		panel.setLayout(null);
		
		
		JLabel lblTitle = new JLabel("Añadir tipo de habitación");
		lblTitle.setBounds(100,50,800,70);
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
				RoomTypesController rooms = new RoomTypesController();
				frame.dispose();
				rooms.roomTypes();
			}
			
		});*/
	}
	
	public void editRoomType() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(new Rectangle(0, 0, 720, 1280));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1280, 720);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 252, 247));
		contentPane.setBounds(new Rectangle(0, 0, 720, 1280));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(7, 26, 43));
		panel.setBounds(-26, 0, 1290, 119);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(130, 30, 78, 56);
		Image imgBtnHome = new ImageIcon("src/images/btnHome.png").getImage().getScaledInstance(78, 56, Image.SCALE_SMOOTH);
		btnNewButton.setIcon(new ImageIcon(imgBtnHome));
		btnNewButton.setBackground(null);
		btnNewButton.setBorder(null);
		panel.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomTypesController rooms = new RoomTypesController();
				frame.dispose();
				rooms.roomTypes();
			}
			
		});
		
		JLabel lblNewLabel_4 = new JLabel("Editar tipo de habitación");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_4.setBounds(238, 21, 550, 66);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(146, 21, 128, 75);
		Image img = new ImageIcon("src/flecha.png").getImage().getScaledInstance(76, 58, Image.SCALE_SMOOTH);
		lblNewLabel_5.setIcon(new ImageIcon(img));
		panel.add(lblNewLabel_5);
		
		JTextField textField = new JTextField();
		textField.setBounds(122, 196, 470, 49);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(646, 196, 470, 49);
		contentPane.add(textField_1);
		
		JTextField textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(122, 327, 994, 49);
		contentPane.add(textField_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(122, 466, 470, 49);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(646, 466, 470, 49);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel = new JLabel("Tipo de habitación");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(122, 158, 309, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblTipoDeTarifa = new JLabel("Tipo de tarifa");
		lblTipoDeTarifa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTipoDeTarifa.setBounds(646, 158, 309, 27);
		contentPane.add(lblTipoDeTarifa);
		
		JLabel lblHabitacionesSeleccionadas = new JLabel("Habitaciones seleccionadas");
		lblHabitacionesSeleccionadas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHabitacionesSeleccionadas.setBounds(122, 289, 309, 27);
		contentPane.add(lblHabitacionesSeleccionadas);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPiso.setBounds(122, 428, 309, 27);
		contentPane.add(lblPiso);
		
		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCapacidad.setBounds(646, 428, 309, 27);
		contentPane.add(lblCapacidad);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(new Color(153, 89, 45));
		btnCancelar.setBounds(808, 571, 140, 72);
		contentPane.add(btnCancelar);
		
		JButton btnNewButton_1 = new JButton("Editar");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(7, 26, 43));
		btnNewButton_1.setBounds(976, 571, 140, 72);
		contentPane.add(btnNewButton_1);
	}
	
	public void consultRoomType() {
		/*frame = new JFrame();
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
		panel.add(btnHome);*/
		frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 1280, 720);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 252, 247));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(7, 26, 43));
		panel.setBounds(-26, 0, 1290, 119);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Detalles de habitación");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_4.setBounds(238, 21, 490, 66);
		panel.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(130, 30, 78, 56);
		Image imgBtnHome = new ImageIcon("src/images/btnHome.png").getImage().getScaledInstance(78, 56, Image.SCALE_SMOOTH);
		btnNewButton.setIcon(new ImageIcon(imgBtnHome));
		btnNewButton.setBackground(null);
		btnNewButton.setBorder(null);
		panel.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomTypesController rooms = new RoomTypesController();
				frame.dispose();
				rooms.roomTypes();
			}
			
		});
		
		JLabel lblTipoDeTarifa = new JLabel("Tipo de tarifa");
		lblTipoDeTarifa.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTipoDeTarifa.setBounds(358, 159, 309, 27);
		contentPane.add(lblTipoDeTarifa);
		
		
		JLabel lblNewLabel = new JLabel("Habitaciones amplias con salida y comedor ideales para familiar grandes");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(358, 185, 660, 61);
		contentPane.add(lblNewLabel);
		
	
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(7, 26, 43));
		panel_1.setBounds(126, 289, 990, 272);
		contentPane.add(panel_1);
		String[] columnNames = {
				"#-Habitación",
				"Piso",
				"Capacidad",
				"Tarifa"
		};
		
		Object [][] data = {
				{"110", 2, "5 personas","Variable"},
				{"111", 2, "5 personas","Variable"},
				{"112", 2, "5 personas","Variable"},
				{"113", 2, "5 personas","Variable"},
				{"114", 2, "5 personas","Variable"},
				{"115", 2, "5 personas","Variable"},				
		};
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable roomsTypeDetailsTable = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(roomsTypeDetailsTable);
		scrollPane.setBounds(0, 54, 990, 218);
		panel_1.add(scrollPane);
		roomsTypeDetailsTable.getTableHeader().setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
		roomsTypeDetailsTable.getTableHeader().setBackground(Color.decode("#071A2B"));
		roomsTypeDetailsTable.getTableHeader().setForeground(Color.decode("#FFFFFF"));
		roomsTypeDetailsTable.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 22));
		roomsTypeDetailsTable.setRowHeight(30);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
		lblNewLabel_2.setForeground(Color.decode("#FFFFFF"));
		lblNewLabel_2.setBounds(400, 10, 200, 40);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(130, 158, 180, 90);
		Image img = new ImageIcon("src/images/normal.png").getImage().getScaledInstance(180, 90, Image.SCALE_SMOOTH);
		lblNewLabel_5.setIcon(new ImageIcon(img));
		contentPane.add(lblNewLabel_5);
		roomsTypeDetailsTable.setDefaultEditor(Object.class,null);
		
		
		
		
		JButton btnDownload = new JButton("Descargar .pdf");
		btnDownload.setBounds(125, 580, 990,70);
		btnDownload.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnDownload.setForeground(Color.decode("#FFFFFF"));
		btnDownload.setBackground(Color.decode("#071A2B"));
		contentPane.add(btnDownload);
		
		btnDownload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomTypesController rooms = new RoomTypesController();
				rooms.successDownload();
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
				RoomTypesController rooms = new RoomTypesController();
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
				RoomTypesController rooms = new RoomTypesController();
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
		
		JLabel title = new JLabel("Tipo de habitación eliminado con exito");
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
