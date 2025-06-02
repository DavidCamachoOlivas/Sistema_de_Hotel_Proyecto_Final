package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import models.RoomType;
import models.RoomTypesModel;
import models.Tariff;

public class RoomTypesView {

	private JFrame frame;
	private RoomTypesModel functions;
	
	byte[] imageBytes;
	float precioNoche = 0 ;
	String titulo;
	double precio;
	int x = 120;
	
	
	public RoomTypesView() {
		functions = new RoomTypesModel();
	}
	
	public void roomTypes() throws SQLException {
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
		
		
		JLabel lblTitle = new JLabel("Tipos de habitaciones");
		lblTitle.setBounds(100,50,750,70);
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
				RoomTypesController rooms = new RoomTypesController();
				frame.dispose();
				rooms.createRoomType();
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
				RoomTypesController rooms = new RoomTypesController();
				frame.dispose();
				rooms.editRoomType();
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
				RoomTypesController rooms = new RoomTypesController();
				frame.dispose();
				rooms.consultRoomType();
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
				RoomTypesController rooms = new RoomTypesController();
				rooms.deleteRoomType();
			}
			
		});*/
		
		//Aqui se hace la vista predeterminada, no cambie nada del Frame principal
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
		
		//Aqui declare unos valores que ocupe para hacer los paneles y las busquedas
		
		List<RoomType> roomTypeList = new ArrayList<>();
		roomTypeList = functions.getAvailableRoomType();
		
		int vuelta = 0;
		float precio ;
		Tariff t = null;
		String titulo ;
		
		//Igual aqui no cambie nada, son las mismas segun yo lo del titulo de tipo de habitaciones y la flecha
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
		
		//Aqui ya hice cambios, lo mayor fue esto, poder imprimir los tipos de habitaciones ya desde la base de datos
		for (RoomType roomType : roomTypeList) {

			if(vuelta!=3) {
				titulo = roomType.getRoom_type();
				
				JPanel panel_1 = new JPanel();
				panel_1.setBounds(x, 169, 310, 381);
				contentPane.add(panel_1);
				panel_1.setBorder(BorderFactory.createLineBorder(Color.black));  
				panel_1.setLayout(new GridLayout(2, 0, 0, 0));
				
				JPanel panel_2 = new JPanel();
				panel_2.setOpaque(false);
				panel_2.setBounds(0, 0, 900, 500);
				
				if(roomType.getImage() != null) {
					Image icon = new ImageIcon(roomType.getImage()).getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
					ImageIcon backgroundIcon = new ImageIcon(icon);
					
					JLabel backgroundLabel = new JLabel(backgroundIcon);
					backgroundLabel.setBounds(0, 0, 900, 500);
					panel_2.setOpaque(false);
					panel_2.setBounds(0, 0, 900, 500);
					backgroundLabel.add(panel_2);
					panel_1.add(backgroundLabel);					
				}

				JPanel panel_5 = new JPanel();
				panel_1.add(panel_5);
				panel_5.setLayout(null);
				

				JLabel lblNewLabel = new JLabel(titulo);
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
				lblNewLabel.setBounds(10, 31, 271, 46);
				panel_5.add(lblNewLabel);
				
				precio = roomType.getId_tariff();
				RoomTypesModel rtm = new RoomTypesModel();
				List<Tariff> prueba = new ArrayList<>(rtm.getAvailableTariffs());
				for (Tariff roomType2 : prueba) {
					if(precio == roomType2.getId_tariff()) {
						precioNoche = roomType2.getPrice_per_night();
					}
				}
				JLabel lblNewLabel_3 = new JLabel();
				lblNewLabel_3.setText(""+precioNoche);
				lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblNewLabel_3.setBounds(10, 77, 182, 30);
				panel_5.add(lblNewLabel_3);
				
				panel_1.add(panel_5);
				
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
						rooms.deleteRoomType(roomType);
						frame.repaint();
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
						try {
							rooms.editRoomType(roomType);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
				x+= 320;
				vuelta++;
			}
		 }
		
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
		
		
		//Aqui tambien hice cambios, es para la creacion de un nuevo tipo de habitacion
		JButton btnNuevoTipo = new JButton("Nuevo tipo");
		btnNuevoTipo.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNuevoTipo.setForeground(Color.WHITE);
		btnNuevoTipo.setBackground(new Color(7, 26, 43));
		btnNuevoTipo.setBounds(395, 621, 459, 49);
		contentPane.add(btnNuevoTipo);
		
		btnNuevoTipo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomTypesController rooms = new RoomTypesController();
				frame.dispose();
				try {
					rooms.createRoomType();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.repaint();
		contentPane.revalidate();
	}
	
	//Lo mas recomendable es que copies y pegues todo el Metodo roomTypes()
	
	
	RoomType rt = new RoomType();
	
	public void createRoomType() throws SQLException {
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
				try {
					rooms.roomTypes();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		
		//Aqui hice cambios donde se ingresan los datos y cambie el JTextField de tipo de tarifa por un JComboBox
		
		JLabel previewLabel = new JLabel();
		previewLabel.setBounds(100, 270, 300, 300); // ajustado para no chocar con el botón
		contentPane.add(previewLabel);

		
		JButton btnNewButton1 = new JButton();
		btnNewButton1.setBounds(100, 250, 470, 400);
		btnNewButton1.setBackground(new Color(216, 216, 216));
		btnNewButton1.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120), 2));
		panel.add(btnNewButton1); // Este botón va en el panel
		contentPane.add(btnNewButton1); // Este botón también está en el contentPane si deseas que se muestre completo

		ImageIcon imagenPredeterminada = new ImageIcon("src/images/subir.png");
		Image scaledPredeterminada = imagenPredeterminada.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		btnNewButton1.setIcon(new ImageIcon(scaledPredeterminada));
		
		btnNewButton1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setDialogTitle("Selecciona una imagen");
		        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		        int resultado = fileChooser.showOpenDialog(null);
		        if (resultado == JFileChooser.APPROVE_OPTION) {
		            File imagenSeleccionada = fileChooser.getSelectedFile();

		            try (FileInputStream fis = new FileInputStream(imagenSeleccionada)) {
		            	imageBytes = fis.readAllBytes();
		                rt.setImage(imageBytes); // Asignas al modelo

		                // Mostrar vista previa
		              
		                	ImageIcon icon = new ImageIcon(imageBytes);
		                	Image scaled = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		                	btnNewButton1.setIcon(new ImageIcon(scaled));

		                System.out.println("Imagen cargada y mostrada correctamente");
		            } catch (IOException e1) {
		                e1.printStackTrace();
		            }
		        }
		    }
		});
		contentPane.add(btnNewButton1);
		
			JTextField tipoHabitacionField = new JTextField();
		    tipoHabitacionField.setBounds(100, 196, 470, 49);
		    contentPane.add(tipoHabitacionField);
		    
		    JTextField tipoHabitacionIncluidas = new JTextField();
		    tipoHabitacionIncluidas.setBounds(646, 350, 470, 49);
		    contentPane.add(tipoHabitacionIncluidas);
		    
		    // Combo para piso
		    Integer[] opcionesPiso = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		    JComboBox<Integer> pisoCombo = new JComboBox<>(opcionesPiso);
		    pisoCombo.setBounds(646, 466, 150, 49);
		    contentPane.add(pisoCombo);
		    
		    // Combo para tarifas
		    JComboBox<Tariff> tarifaCombo = new JComboBox<>();
		    tarifaCombo.setBounds(646, 196, 470, 49);
		    contentPane.add(tarifaCombo);
		    
		    JComboBox<Integer> tariffCapacity = new JComboBox<>();
		    tariffCapacity.setBounds(970, 466, 150, 49);
		    contentPane.add(tariffCapacity);
		    
		    // Cargar tarifas disponibles
		    try {
		        List<Tariff> tarifas = new RoomTypesModel().getAvailableTariffs();
		        DefaultComboBoxModel<Tariff> model = new DefaultComboBoxModel<>();
		        for (Tariff tarifa : tarifas) {
		            model.addElement(tarifa);
		        }
		        tarifaCombo.setModel(model);
		    } catch (SQLException e) {
		    }
		    
		    // Combo para cargar la capacidad de cada tarifa
		    try {
		        List<Tariff> tarifas = new RoomTypesModel().getAvailableTariffs();
		        DefaultComboBoxModel<Integer> tarifas1 = new DefaultComboBoxModel<>();
		        for (Tariff tarifa : tarifas) {
		        	tarifas1.addElement(tarifa.getCapacity());
		        }
		        tariffCapacity.setModel(tarifas1);
		    } catch (SQLException e) {
		    }
		    
		    // Botón Añadir
		    JButton btnAñadir = new JButton("Añadir");
		    btnAñadir.setFont(new Font("Tahoma", Font.BOLD, 20));
		    btnAñadir.setForeground(Color.WHITE);
		    btnAñadir.setBackground(new Color(7, 26, 43));
		    btnAñadir.setBounds(976, 571, 140, 72);
		    btnAñadir.addActionListener(e -> {
	        try {
	            
	            // Obtener datos
	            String tipoHabitacion = tipoHabitacionField.getText();
	            int piso = (int) pisoCombo.getSelectedItem();
	            int habitaciones_integradas = Integer.parseInt(tipoHabitacionIncluidas.getText());
	            Tariff tarifaSeleccionada = (Tariff) tarifaCombo.getSelectedItem();
	            byte[] imageBytesSel = imageBytes;
	            
	            // Validar tarifa seleccionada
	            if (tarifaSeleccionada == null) {
	                JOptionPane.showMessageDialog(frame, "Debe seleccionar una tarifa","Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            
	            // Crear y guardar el tipo de habitación
	            RoomType nuevoTipo = new RoomType();
	            nuevoTipo.setRoom_type(tipoHabitacion);
	            nuevoTipo.setRooms_included(habitaciones_integradas);
	            nuevoTipo.setNum_floor(piso);
	            nuevoTipo.setId_tariff(tarifaSeleccionada.getId_tariff());
	            nuevoTipo.setImage(imageBytes);
	            
	            int idGenerado = new RoomTypesModel().createRoomType(nuevoTipo);
	            
	            // Cerrar ventana y volver al listado
	            frame.dispose();
	            new RoomTypesController().roomTypes();
	            
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(frame, "Error al guardar: " + ex.getMessage(),  "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });
	    contentPane.add(btnAñadir);
		    
		JLabel lblNewLabel = new JLabel("Tipo de habitación");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(100, 158, 309, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblTipoDeTarifa = new JLabel("Tipo de tarifa");
		lblTipoDeTarifa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTipoDeTarifa.setBounds(646, 158, 309, 27);
		contentPane.add(lblTipoDeTarifa);
		
		JLabel lblHabitacionesSeleccionadas = new JLabel("Habitaciones incluidas");
		lblHabitacionesSeleccionadas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHabitacionesSeleccionadas.setBounds(646, 289, 309, 27);
		contentPane.add(lblHabitacionesSeleccionadas);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPiso.setBounds(646, 428, 309, 27);
		contentPane.add(lblPiso);
		
		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCapacidad.setBounds(970, 428, 309, 27);
		contentPane.add(lblCapacidad);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(new Color(153, 89, 45));
		btnCancelar.setBounds(808, 571, 140, 72);
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomTypesController rtc = new RoomTypesController();
				frame.dispose();
				try {
					rtc.roomTypes();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnCancelar);
		
		
		
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
	
	public void editRoomType(RoomType rt) throws SQLException {
		if(rt.getImage()!=null) {
			imageBytes = rt.getImage();			
		}
		
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
				try {
					rooms.roomTypes();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		
		//Aqui hice cambios donde se ingresan los datos y cambie el JTextField de tipo de tarifa por un JComboBox
		
		JLabel previewLabel = new JLabel();
		previewLabel.setBounds(100, 270, 300, 300); // ajustado para no chocar con el botón
		contentPane.add(previewLabel);

		JButton btnNewButton1 = new JButton();
		btnNewButton1.setBounds(100, 250, 470, 400);
		btnNewButton1.setBackground(new Color(216, 216, 216));
		btnNewButton1.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120), 2));
		panel.add(btnNewButton1); // Este botón va en el panel
		contentPane.add(btnNewButton1); // Este botón también está en el contentPane si deseas que se muestre completo
		if(rt.getImage() != null) {
			ImageIcon imagenPredeterminada = new ImageIcon(rt.getImage());
			Image scaledPredeterminada = imagenPredeterminada.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
			btnNewButton1.setIcon(new ImageIcon(scaledPredeterminada));			
		}
		
		btnNewButton1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setDialogTitle("Selecciona una imagen");
		        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		        int resultado = fileChooser.showOpenDialog(null);
		        if (resultado == JFileChooser.APPROVE_OPTION) {
		            File imagenSeleccionada = fileChooser.getSelectedFile();

		            try (FileInputStream fis = new FileInputStream(imagenSeleccionada)) {
		            	imageBytes = fis.readAllBytes();
		                rt.setImage(imageBytes); // Asignas al modelo

		                // Mostrar vista previa
		              
		                	ImageIcon icon = new ImageIcon(imageBytes);
		                	Image scaled = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		                	btnNewButton1.setIcon(new ImageIcon(scaled));

		                System.out.println("Imagen cargada y mostrada correctamente");
		            } catch (IOException e1) {
		                e1.printStackTrace();
		            }
		        }
		    }
		});
		contentPane.add(btnNewButton1);
		
			JTextField tipoHabitacionField = new JTextField();
			tipoHabitacionField.setText(rt.getRoom_type());
		    tipoHabitacionField.setBounds(100, 196, 470, 49);
		    contentPane.add(tipoHabitacionField);
		    
		    JTextField tipoHabitacionIncluidas = new JTextField();
		    tipoHabitacionIncluidas.setText(""+rt.getRooms_included());
		    tipoHabitacionIncluidas.setBounds(646, 350, 470, 49);
		    contentPane.add(tipoHabitacionIncluidas);
		    
		    // Combo para piso
		    Integer[] opcionesPiso = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		    JComboBox<Integer> pisoCombo = new JComboBox<>(opcionesPiso);
		    pisoCombo.setSelectedItem(rt.getNum_floor());
		    pisoCombo.setBounds(646, 466, 150, 49);
		    contentPane.add(pisoCombo);
		    
		    // Combo para tarifas
		    JComboBox<Tariff> tarifaCombo = new JComboBox<>();
		    tarifaCombo.setBounds(646, 196, 470, 49);
		    contentPane.add(tarifaCombo);
		    
		    JComboBox<Integer> tariffCapacity = new JComboBox<>();
		    tariffCapacity.setBounds(970, 466, 150, 49);
		    tariffCapacity.setEnabled(false);
		    contentPane.add(tariffCapacity);
		    
		    // Cargar tarifas disponibles
		    try {
		        List<Tariff> tarifas = new RoomTypesModel().getAvailableTariffs();
		        DefaultComboBoxModel<Tariff> modelTarifas = new DefaultComboBoxModel<>();

		        Tariff tarifaPreseleccionada = null;

		        for (Tariff tarifa : tarifas) {
		            modelTarifas.addElement(tarifa);
		            if (tarifa.getId_tariff() == rt.getId_tariff()) {
		                tarifaPreseleccionada = tarifa;
		            }
		        }

		        tarifaCombo.setModel(modelTarifas);

		        if (tarifaPreseleccionada != null) {
		            tarifaCombo.setSelectedItem(tarifaPreseleccionada);
		            tariffCapacity.removeAllItems();
		            tariffCapacity.addItem(tarifaPreseleccionada.getCapacity());
		        }

		        tarifaCombo.addActionListener(evt -> {
		            Tariff seleccionada = (Tariff) tarifaCombo.getSelectedItem();
		            if (seleccionada != null) {
		                tariffCapacity.removeAllItems();
		                tariffCapacity.addItem(seleccionada.getCapacity());
		            }
		        });
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(frame, "Error al cargar tarifas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    }
		    
		    // Botón Guardar
		    JButton btnGuardar = new JButton("Guardar");
		    btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 20));
		    btnGuardar.setForeground(Color.WHITE);
		    btnGuardar.setBackground(new Color(7, 26, 43));
		    btnGuardar.setBounds(976, 571, 140, 72);
		    btnGuardar.addActionListener(e -> {
		        try {
		            
		        	String tipoHabitacion = tipoHabitacionField.getText();
		            int piso = (int) pisoCombo.getSelectedItem();
		            int habitaciones_integradas = Integer.parseInt(tipoHabitacionIncluidas.getText());
		            Tariff tarifaSeleccionada = (Tariff) tarifaCombo.getSelectedItem();
		            byte[] imagen = imageBytes;
		            
		            // Actualiza y guardar el tipo de habitación
		            RoomTypesModel rtm = new RoomTypesModel();
		            RoomType ActualizarTipo = new RoomType();
		           
		            ActualizarTipo.setId_room_type(rt.getId_room_type());
		            ActualizarTipo.setRoom_type(tipoHabitacion);
		            ActualizarTipo.setRooms_included(habitaciones_integradas);
		            ActualizarTipo.setNum_floor(piso);
		            ActualizarTipo.setId_tariff(tarifaSeleccionada.getId_tariff());
		            ActualizarTipo.setImage(imagen);
		            
		            rtm.updateRoomType(ActualizarTipo);
		           
		            // Cerrar ventana y volver al listado
		            frame.dispose();
		            new RoomTypesController().roomTypes();
		            
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(frame, "Error al guardar: " + ex.getMessage(),  "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    });
		    contentPane.add(btnGuardar);
		    
		JLabel lblNewLabel = new JLabel("Tipo de habitación");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(100, 158, 309, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblTipoDeTarifa = new JLabel("Tipo de tarifa");
		lblTipoDeTarifa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTipoDeTarifa.setBounds(646, 158, 309, 27);
		contentPane.add(lblTipoDeTarifa);
		
		JLabel lblHabitacionesSeleccionadas = new JLabel("Habitaciones incluidas");
		lblHabitacionesSeleccionadas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHabitacionesSeleccionadas.setBounds(646, 289, 309, 27);
		contentPane.add(lblHabitacionesSeleccionadas);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPiso.setBounds(646, 428, 309, 27);
		contentPane.add(lblPiso);
		
		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCapacidad.setBounds(970, 428, 309, 27);
		contentPane.add(lblCapacidad);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(new Color(153, 89, 45));
		btnCancelar.setBounds(808, 571, 140, 72);
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomTypesController rtc = new RoomTypesController();
				frame.dispose();
				try {
					rtc.roomTypes();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnCancelar);

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
				try {
					rooms.roomTypes();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
	
	public void deleteConfirm(RoomType rm) {
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
				RoomTypesModel rtm = new RoomTypesModel();
				try {
					rtm.deleteRoomType(rm);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.getMessage();
				}
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
	
	public void vistaPrevia() {
		
	}
}
