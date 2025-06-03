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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import buttonCells.TableActionCellEditor;
import buttonCells.TableActionCellRender;
import buttonCells.TableActionEvent;
import controllers.ClientsController;
import controllers.HomeController;
import controllers.RoomTypesController;
import controllers.RoomsController;
import models.ClientsModel;
import models.Room;
import models.RoomType;
import models.RoomTypesModel;
import models.RoomsModel;
import models.Tariff;

public class RoomTypesView {

	private JFrame frame;
	private RoomTypesModel functions;
	private int currentPage = 1;
	private final int itemsPerPage = 3;
	private JPanel contentPane;
	private List<RoomType> roomTypeList; // Asignado desde tu modelo
	
	byte[] imageBytes;
	float precioNoche = 0 ;
	String titulo;
	double precio;
	int x = 120;
	
	
	public RoomTypesView() {
		functions = new RoomTypesModel();
	}
	
	public void roomTypes() throws SQLException {
	    frame = new JFrame();
	    frame.setResizable(false);
	    frame.setBounds(new Rectangle(0, 0, 720, 1280));
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setBounds(0, 0, 1280, 720);
	    frame.setLocationRelativeTo(null);

	    contentPane = new JPanel();
	    contentPane.setBackground(new Color(255, 252, 247));
	    contentPane.setBounds(new Rectangle(0, 0, 720, 1280));
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    contentPane.setLayout(null);
	    frame.setContentPane(contentPane);

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

	    functions = new RoomTypesModel();
	    roomTypeList = functions.getAvailableRoomType(); // esta asignación es crucial
	    loadPage(currentPage);
	    
	    JButton btnNuevoTipo = new JButton("Nuevo tipo");
	    btnNuevoTipo.setFont(new Font("Tahoma", Font.BOLD, 25));
	    btnNuevoTipo.setForeground(Color.WHITE);
	    btnNuevoTipo.setBackground(new Color(7, 26, 43));
	    btnNuevoTipo.setBounds(395, 621, 459, 49);
	    contentPane.add(btnNuevoTipo);

	    btnNuevoTipo.addActionListener(e -> {
	        RoomTypesController rooms = new RoomTypesController();
	        frame.dispose();
	        try {
	            rooms.createRoomType();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    });

	    frame.setVisible(true);
	}

	
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
		
		JLabel previewLabel = new JLabel();
		previewLabel.setBounds(100, 270, 300, 300); 
		contentPane.add(previewLabel);

		JButton btnNewButton1 = new JButton();
		btnNewButton1.setBounds(200, 350, 400, 300);
		btnNewButton1.setBackground(new Color(216, 216, 216));
		btnNewButton1.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120), 2));
		panel.add(btnNewButton1);
		contentPane.add(btnNewButton1);

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
		                rt.setImage(imageBytes);
		              
	                	ImageIcon icon = new ImageIcon(imageBytes);
	                	Image scaled = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
	                	btnNewButton1.setIcon(new ImageIcon(scaled));

		            } catch (IOException e1) {
		                e1.printStackTrace();
		            }
		        }
		    }
		});
		contentPane.add(btnNewButton1);
		
			JTextField tipoHabitacionField = new JTextField();
		    tipoHabitacionField.setBounds(200, 196, 400, 49);
		    tipoHabitacionField.setFont(new Font("Inter_18pt Bold", Font.BOLD, 20));
		    contentPane.add(tipoHabitacionField);
		    
		    JTextField tipoHabitacionIncluidas = new JTextField();
		    tipoHabitacionIncluidas.setBounds(200, 290, 400, 49);
		    tipoHabitacionIncluidas.setFont(new Font("Inter_18pt Bold", Font.BOLD, 20));
		    contentPane.add(tipoHabitacionIncluidas);
		    
		    JTextArea textArea = new JTextArea(5, 30); 
		    textArea.setLineWrap(true); 
		    textArea.setWrapStyleWord(true); 
		    textArea.setText("Descripcion");
		    textArea.setFont(new Font("Inter_18pt Bold", Font.BOLD, 20));
		    textArea.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					if(textArea.getText().equals("Descripcion")) {
						textArea.setText("");
					}
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		    JScrollPane scrollPane = new JScrollPane(textArea);
		    scrollPane.setBounds(696,350,420,200);
		    contentPane.add(scrollPane);
		    
		    Integer[] opcionesPiso = {1, 2, 3, 4, 5, 6};
		    JComboBox<Integer> pisoCombo = new JComboBox<>(opcionesPiso);
		    pisoCombo.setFont(new Font("Inter_18pt Bold", Font.BOLD, 20));
		    pisoCombo.setBounds(696, 290, 150, 49);
		    contentPane.add(pisoCombo);
		    
		    JComboBox<Tariff> tarifaCombo = new JComboBox<>();
		    tarifaCombo.setFont(new Font("Inter_18pt Bold", Font.BOLD, 20));
		    tarifaCombo.setBounds(696, 196, 420, 49);
		    contentPane.add(tarifaCombo);
		    
		    JComboBox<Integer> tariffCapacity = new JComboBox<>();
		    tariffCapacity.setFont(new Font("Inter_18pt Bold", Font.BOLD, 20));
		    tariffCapacity.setBounds(970, 290, 150, 49);
		    contentPane.add(tariffCapacity);
		    
		    try {
		        List<Tariff> tarifas = new RoomTypesModel().getAvailableTariffs();
		        DefaultComboBoxModel<Tariff> model = new DefaultComboBoxModel<>();
		        for (Tariff tarifa : tarifas) {
		            model.addElement(tarifa);
		        }
		        tarifaCombo.setModel(model);
		    } 
		    catch (SQLException e) {
		    }
		    
		    try {
		        List<Tariff> tarifas = new RoomTypesModel().getAvailableTariffs();
		        DefaultComboBoxModel<Integer> tarifas1 = new DefaultComboBoxModel<>();
		        for (Tariff tarifa : tarifas) {
		        	tarifas1.addElement(tarifa.getCapacity());
		        }
		        tariffCapacity.setModel(tarifas1);
		    }
		    catch (SQLException e) {
		    }
		    
		    // Botón Añadir
		    JButton btnAñadir = new JButton("Añadir");
		    btnAñadir.setFont(new Font("Tahoma", Font.BOLD, 20));
		    btnAñadir.setForeground(Color.WHITE);
		    btnAñadir.setBackground(new Color(7, 26, 43));
		    btnAñadir.setBounds(976, 571, 140, 72);

		    btnAñadir.addActionListener(e -> {
		        boolean valido = true;
		        Border bordeRojo = BorderFactory.createLineBorder(Color.RED, 2);
		        Border bordeNormal = BorderFactory.createLineBorder(Color.GRAY);

		        tipoHabitacionField.setBorder(bordeNormal);
		        tipoHabitacionIncluidas.setBorder(bordeNormal);
		        tarifaCombo.setBorder(bordeNormal);
		        textArea.setBorder(bordeNormal);
		        btnNewButton1.setBorder(null);

		        String tipoHabitacion = tipoHabitacionField.getText().trim();
		        String habitacionesTexto = tipoHabitacionIncluidas.getText().trim();
		        Tariff tarifaSeleccionada = (Tariff) tarifaCombo.getSelectedItem();
		        String descripcion = textArea.getText().trim();
		        int piso = (int) pisoCombo.getSelectedItem();

		        if (tipoHabitacion.isEmpty() || !tipoHabitacion.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+")) {
		            tipoHabitacionField.setBorder(bordeRojo);
		            valido = false;
		        }

		        int habitaciones_integradas = -1;
		        try {
		            habitaciones_integradas = Integer.parseInt(habitacionesTexto);
		            if (habitaciones_integradas <= 0) {
		                tipoHabitacionIncluidas.setBorder(bordeRojo);
		                valido = false;
		            }
		        } catch (NumberFormatException ex) {
		            tipoHabitacionIncluidas.setBorder(bordeRojo);
		            valido = false;
		        }

		        if (tarifaSeleccionada == null) {
		            tarifaCombo.setBorder(bordeRojo);
		            valido = false;
		        }

		        if (descripcion.isEmpty() || descripcion.equalsIgnoreCase("Descripcion")) {
		            textArea.setBorder(bordeRojo);
		            valido = false;
		        }

		        ImageIcon predeterminada = new ImageIcon("src/images/subir.png");
		        Image img1 = predeterminada.getImage();
		        Image img2 = btnNewButton1.getIcon() != null ? ((ImageIcon) btnNewButton1.getIcon()).getImage() : null;
		        if (img2 == null || img1.getSource().equals(img2.getSource())) {
		            btnNewButton1.setBorder(bordeRojo);
		            valido = false;
		        }

		        if (!valido) {
		            JOptionPane.showMessageDialog(frame, "Por favor corrige los campos marcados en rojo.", "Error de validación", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        try {
		            RoomType nuevoTipo = new RoomType();
		            nuevoTipo.setRoom_type(tipoHabitacion);
		            nuevoTipo.setRooms_included(habitaciones_integradas);
		            nuevoTipo.setNum_floor(piso);
		            nuevoTipo.setId_tariff(tarifaSeleccionada.getId_tariff());
		            nuevoTipo.setImage(imageBytes); 
		            nuevoTipo.setDescription(descripcion);

		            int idGenerado = new RoomTypesModel().createRoomType(nuevoTipo);

		            frame.dispose();
		            new RoomTypesController().roomTypes();
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(frame, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    });
		    contentPane.add(btnAñadir);

		    
		JLabel lblNewLabel = new JLabel("Tipo de habitación");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(200, 158, 250, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblTipoDeTarifa = new JLabel("Tipo de tarifa");
		lblTipoDeTarifa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTipoDeTarifa.setBounds(696, 158, 309, 27);
		contentPane.add(lblTipoDeTarifa);
		
		JLabel lblHabitacionesSeleccionadas = new JLabel("Habitaciones incluidas");
		lblHabitacionesSeleccionadas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHabitacionesSeleccionadas.setBounds(200, 260, 309, 27);
		contentPane.add(lblHabitacionesSeleccionadas);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPiso.setBounds(696, 260, 309, 27);
		contentPane.add(lblPiso);
		
		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCapacidad.setBounds(970, 260, 309, 27);
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
		
		JLabel previewLabel = new JLabel();
		previewLabel.setBounds(100, 270, 300, 300);
		contentPane.add(previewLabel);

		JButton btnNewButton1 = new JButton();
		btnNewButton1.setBounds(100, 250, 470, 400);
		btnNewButton1.setBackground(new Color(216, 216, 216));
		btnNewButton1.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120), 2));
		panel.add(btnNewButton1);
		contentPane.add(btnNewButton1);
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
		                rt.setImage(imageBytes); 
		                
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
			tipoHabitacionField.setFont(new Font("Inter_18pt Bold", Font.BOLD, 20));
			tipoHabitacionField.setText(rt.getRoom_type());
		    tipoHabitacionField.setBounds(100, 196, 470, 49);
		    contentPane.add(tipoHabitacionField);
		    
		    JTextField tipoHabitacionIncluidas = new JTextField();
		    tipoHabitacionIncluidas.setFont(new Font("Inter_18pt Bold", Font.BOLD, 20));
		    tipoHabitacionIncluidas.setText(""+rt.getRooms_included());
		    tipoHabitacionIncluidas.setBounds(646, 350, 470, 49);
		    contentPane.add(tipoHabitacionIncluidas);

		    Integer[] opcionesPiso = {1, 2, 3, 4, 5, 6};
		    JComboBox<Integer> pisoCombo = new JComboBox<>(opcionesPiso);
		    pisoCombo.setFont(new Font("Inter_18pt Bold", Font.BOLD, 20));
		    pisoCombo.setSelectedItem(rt.getNum_floor());
		    pisoCombo.setBounds(646, 466, 150, 49);
		    contentPane.add(pisoCombo);

		    JComboBox<Tariff> tarifaCombo = new JComboBox<>();
		    tarifaCombo.setFont(new Font("Inter_18pt Bold", Font.BOLD, 20));
		    tarifaCombo.setBounds(646, 196, 470, 49);
		    contentPane.add(tarifaCombo);
		    
		    JComboBox<Integer> tariffCapacity = new JComboBox<>();
		    tariffCapacity.setFont(new Font("Inter_18pt Bold", Font.BOLD, 20));
		    tariffCapacity.setBounds(970, 466, 150, 49);
		    tariffCapacity.setEnabled(false);
		    contentPane.add(tariffCapacity);
		    
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
	
	public void consultRoomType(RoomType rt) {
		
		List<Room> rooms = new ArrayList<>();
		List<RoomType> roomTypes = new ArrayList<>();
		List<Tariff> tariffs = new ArrayList<>();
		
		System.out.println("RT ID: " + rt.getId_room_type());
		for (Room room : rooms) {
		    System.out.println("Room: " + room.getNum_room() + ", ID tipo: " + room.getId_room_type());
		}
		RoomsModel functions = new RoomsModel();
		try {
		    rooms = functions.getAvailableRoom();
		    roomTypes = functions.getAvailableRoomType();
		    tariffs = functions.getAvailableTariffs();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		
		List<Room> filteredRooms = new ArrayList<>();
		List<Tariff> filteredTariffs = new ArrayList<>();

		for (int i = 0; i < rooms.size(); i++) {
		    Room room = rooms.get(i);
		    Tariff tariff = tariffs.get(i);

		    if (room.getId_room_type() == rt.getId_room_type()) {
		        filteredRooms.add(room);
		        filteredTariffs.add(tariff);
		    }
		}
		
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
		
		
		JLabel lblNewLabel_4 = new JLabel("Detalles de tipo de habitación");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_4.setBounds(238, 21, 600, 66);
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
		
		JLabel lblTipoDeTarifa = new JLabel();
		lblTipoDeTarifa.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTipoDeTarifa.setBounds(358, 159, 309, 27);
		contentPane.add(lblTipoDeTarifa);
		
		
		JLabel lblNewLabel = new JLabel(rt.getDescription());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(358, 185, 660, 61);
		contentPane.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(7, 26, 43));
		panel_1.setBounds(126, 289, 990, 272);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("impresion");
		lblNewLabel_2.setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
		lblNewLabel_2.setForeground(Color.decode("#FFFFFF"));
		lblNewLabel_2.setBounds(400, 10, 200, 40);
		panel_1.add(lblNewLabel_2);
		
		String[] columnas = {"Habitacion", "Piso", "Capacidad", "Tarifa"};
		
		Object[][] datos = new Object[filteredRooms.size()][4];
		for (int i = 0; i < filteredRooms.size(); i++) {
		    datos[i][0] = filteredRooms.get(i).getNum_room();
		    datos[i][1] = rt.getNum_floor();
		    datos[i][2] = filteredTariffs.get(i).getCapacity() + " Personas";
		    datos[i][3] = filteredTariffs.get(i).isRefundable() ? "Reembolsable" : "No reembolsable";
		}
		
		DefaultTableModel model = new DefaultTableModel(datos, columnas);
		JTable roomTypesTable = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(roomTypesTable);
		scrollPane.setBounds(0, 40, 1000, 260);
				
				roomTypesTable.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 22));
				roomTypesTable.setRowHeight(30);
				roomTypesTable.getTableHeader().setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
				roomTypesTable.getTableHeader().setBackground(Color.decode("#071A2B"));
				roomTypesTable.getTableHeader().setForeground(Color.decode("#FFFFFF"));
				roomTypesTable.getColumnModel().getColumn(3).setPreferredWidth(150);
				roomTypesTable.setDefaultEditor(Object.class,null);
				
		        panel_1.add(scrollPane);
		
		
		
		JLabel lblNewLabel_5 = new JLabel();
		lblNewLabel_5.setText(""+rt.getId_tariff());
		lblNewLabel_5.setBounds(130, 158, 180, 90);
		Image img = new ImageIcon(rt.getImage()).getImage().getScaledInstance(180, 90, Image.SCALE_SMOOTH);
		lblNewLabel_5.setIcon(new ImageIcon(img));
		contentPane.add(lblNewLabel_5);
		roomTypesTable.setDefaultEditor(Object.class,null);
		
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
					RoomTypesModel rtm = new RoomTypesModel();
					String url;
					JFileChooser fileChooser = new JFileChooser();
			        fileChooser.setDialogTitle("Selecciona una carpeta");
			        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			        int resultado = fileChooser.showOpenDialog(null);
			        if (resultado == JFileChooser.APPROVE_OPTION) {
			            File carpeta = fileChooser.getSelectedFile();
			            url = carpeta.getAbsolutePath() + "/tipoHabitacion.pdf";
			            rtm.exportarTablaPDF(roomTypesTable, url);
			        }
			        else{
			        	System.out.println("ruta no valida");
			        }
					
				}
				
			});
	}
	
	public void deleteConfirm(RoomType rm, JFrame mainFrame) {
	    JFrame confirmFrame = new JFrame(); // ¡No uses el mismo frame principal!
	    confirmFrame.setSize(700, 500);
	    confirmFrame.setLocationRelativeTo(null);
	    confirmFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    JPanel panel = new JPanel();
	    confirmFrame.getContentPane().add(panel, BorderLayout.CENTER);
	    panel.setBackground(Color.decode("#FFFCF7"));
	    panel.setLayout(null);

	    JLabel title = new JLabel("Confirmar eliminación ");
	    title.setBounds(100, 100, 400, 70);
	    title.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
	    panel.add(title);

	    JButton accept = new JButton("Aceptar");
	    accept.setBounds(350, 350, 300, 70);
	    accept.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
	    accept.setForeground(Color.WHITE);
	    accept.setBackground(Color.decode("#071A2B"));
	    panel.add(accept);

	    accept.addActionListener(e -> {
	        try {
	            new RoomTypesModel().deleteRoomType(rm);

	            confirmFrame.dispose(); // Cierra la ventana de confirmación
	            mainFrame.dispose();    // Cierra la ventana principal actual

	            JOptionPane.showMessageDialog(null, "Tipo de habitación eliminado con éxito", "Eliminado", JOptionPane.INFORMATION_MESSAGE);

	            new RoomTypesController().roomTypes(); // Vuelve a cargar la ventana principal

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(confirmFrame, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });

	    confirmFrame.setVisible(true);

		
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
				try {
					rooms.roomTypes();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
	
	private void loadPage(int page) throws SQLException {
	    contentPane.removeAll();

	    int start = (page - 1) * itemsPerPage;
	    int end = Math.min(start + itemsPerPage, roomTypeList.size());
	    int x = 140;

	    JPanel panel = new JPanel();
	    panel.setBackground(new Color(7, 26, 43));
	    panel.setBounds(-26, 0, 1290, 130);
	    contentPane.add(panel);
	    panel.setLayout(null);
	    
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

	    JLabel lblNewLabel_4 = new JLabel("Tipo de habitaciones");
	    lblNewLabel_4.setForeground(Color.WHITE);
	    lblNewLabel_4.setFont(new Font("Inter_18pt Bold", Font.BOLD, 44));
	    lblNewLabel_4.setBounds(200, 42, 500, 82);
	    panel.add(lblNewLabel_4);
	    
	    for (int i = start; i < end; i++) {
	    	
	        RoomType roomType = roomTypeList.get(i);
	        String titulo = roomType.getRoom_type();
	        double precio = roomType.getId_tariff();
	        float precioNoche = 0;

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

	        RoomTypesModel rtm = new RoomTypesModel();
	        List<Tariff> prueba = rtm.getAvailableTariffs();
	        for (Tariff roomType2 : prueba) {
	            if(precio == roomType2.getId_tariff()) {
	                precioNoche = roomType2.getPrice_per_night();
	            }
	        }

	        JLabel lblNewLabel_3 = new JLabel("" + precioNoche);
	        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        lblNewLabel_3.setBounds(10, 77, 182, 30);
	        panel_5.add(lblNewLabel_3);

	        JButton btnNewButton2 = new JButton("Eliminar");
	        btnNewButton2.setBackground(new Color(153, 89, 45));
	        btnNewButton2.setForeground(Color.WHITE);
	        btnNewButton2.setBounds(10, 118, 89, 49);
	        panel_5.add(btnNewButton2);
	        btnNewButton2.addActionListener(e -> {
	            RoomTypesController rooms = new RoomTypesController();
	            rooms.deleteRoomType(roomType, frame);
	            try {
					loadPage(currentPage);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // Refresca la página
	        });

	        JButton btnEditar = new JButton("Editar");
	        btnEditar.setBackground(Color.WHITE);
	        btnEditar.setBounds(110, 118, 89, 49);
	        panel_5.add(btnEditar);
	        btnEditar.addActionListener(e -> {
	            RoomTypesController rooms = new RoomTypesController();
	            frame.dispose();
	            try {
	                rooms.editRoomType(roomType);
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        });

	        JButton btnDetalles = new JButton("Detalles");
	        btnDetalles.setForeground(Color.WHITE);
	        btnDetalles.setBackground(new Color(7, 26, 43));
	        btnDetalles.setBounds(209, 118, 89, 49);
	        panel_5.add(btnDetalles);
	        btnDetalles.addActionListener(e -> {
	            RoomTypesController rooms = new RoomTypesController();
	            frame.dispose();
	            rooms.consultRoomType(roomType);
	        });

	        x += 330;
	    }
	    
	    JButton btnNuevoTipo = new JButton("Nuevo tipo");
	    btnNuevoTipo.setFont(new Font("Tahoma", Font.BOLD, 25));
	    btnNuevoTipo.setForeground(Color.WHITE);
	    btnNuevoTipo.setBackground(new Color(7, 26, 43));
	    btnNuevoTipo.setBounds(395, 621, 459, 49);
	    contentPane.add(btnNuevoTipo);

	    btnNuevoTipo.addActionListener(e -> {
	        RoomTypesController rooms = new RoomTypesController();
	        frame.dispose();
	        try {
	            rooms.createRoomType();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    });

	    createPaginationButtons();
	    contentPane.repaint();
	    contentPane.revalidate();
	}

	
	private void createPaginationButtons() {
	    int totalPages = (int) Math.ceil((double) roomTypeList.size() / itemsPerPage);
	    int startX = 524;

	    for (int i = 1; i <= totalPages; i++) {
	        JButton pageButton = new JButton(String.valueOf(i));
	        pageButton.setBounds(startX + ((i - 1) * 54), 561, 44, 44);
	        contentPane.add(pageButton);

	        int targetPage = i;
	        pageButton.addActionListener(e -> {
	            currentPage = targetPage;
	            try {
					loadPage(currentPage);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        });
	    }
	}
}
