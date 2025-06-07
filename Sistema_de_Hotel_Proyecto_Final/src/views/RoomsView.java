package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serial;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLEditorKit.Parser;

import buttonCells.TableActionCellEditor;
import buttonCells.TableActionCellRender;
import buttonCells.TableActionEvent;
import controllers.AuthController;
import controllers.ClientsController;
import controllers.HomeController;
import controllers.RoomTypesController;
import controllers.RoomsController;
import models.ClientsModel;
import models.Room;
import models.RoomImage;
import models.RoomImageModel;
import models.RoomType;
import models.RoomTypesModel;
import models.RoomsModel;
import models.Tariff;
import models.TariffsModel;

public class RoomsView {
	JLabel lblImg;
	private JFrame frame;
	private RoomsModel f_rm = new RoomsModel();
	private RoomTypesModel f_rtm = new RoomTypesModel();
	private TariffsModel f_tm = new TariffsModel();
	byte[] imageBytes;
	
	public void rooms() throws SQLException {
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
		
		RoomsModel f_rm = new RoomsModel();
		List<Room> rooms = f_rm.getAvailableRoom(); 
		
		List<RoomType> allTypes = new RoomTypesModel().getAvailableRoomType();
		Map<Integer, RoomType> typeById = new HashMap<>();
		for (RoomType rt : allTypes) {
		    typeById.put(rt.getId_room_type(), rt);
		}

		
		JButton btnCreate = new JButton("Añadir");
		btnCreate.setBounds(830,580,300,70);
		btnCreate.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnCreate.setForeground(Color.decode("#FFFFFF"));
		btnCreate.setBackground(Color.decode("#071A2B"));
		panel.add(btnCreate);
		
		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomsController client = new RoomsController();
				frame.dispose();
				client.createRoom();
			}
			
		});
		
		
		JPanel header = new JPanel();
		header.setBounds(0, 0, 1266, 130);
		header.setBackground(Color.decode("#071A2B"));
		panel.add(header);
		header.setLayout(null);
		
		
		JLabel lblTitle = new JLabel("Habitaciones");
		lblTitle.setBounds(200, 42, 300, 82);
		header.add(lblTitle);
		lblTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 44));
		lblTitle.setForeground(Color.decode("#FFFFFF"));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(null);
		
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
		
		String[] columnas = {"Num_room", "Tipo", "Estado", "Acciones"};
		Object[][] datos = new Object[rooms.size()][4];

		for (int i = 0; i < rooms.size(); i++) {
		    Room   r = rooms.get(i);

		    datos[i][0] = r.getNum_room();

		    // Busca el RoomType correspondiente
		    RoomType rt = typeById.get(r.getId_room_type());
		    datos[i][1] = rt != null ? rt.getRoom_type() : "—";

		    datos[i][2] = r.isStatus() ? "No disponible" : "Disponible";

		    // Deja columna Acciones vacía (será renderizada por el TableActionCellRender)
		    datos[i][3] = "Acciones";
		}

		
		JPanel RoomTablePanel = new JPanel();
		RoomTablePanel.setBounds(130, 240, 1000, 310);
		RoomTablePanel.setBackground(Color.decode("#071A2B"));
		panel.add(RoomTablePanel);
		RoomTablePanel.setLayout(null);
				DefaultTableModel model = new DefaultTableModel(datos, columnas);
				JTable RoomTable = new JTable(model);
				RoomTable.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 22));
				RoomTable.setRowHeight(30);
				RoomTable.getTableHeader().setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
				RoomTable.getTableHeader().setBackground(Color.decode("#071A2B"));
				RoomTable.getTableHeader().setForeground(Color.decode("#FFFFFF"));
				RoomTable.getColumnModel().getColumn(3).setPreferredWidth(150);
				RoomTable.setDefaultEditor(Object.class,null);
				TableActionEvent event = new TableActionEvent() {
					@Override
					public void onEdit(int row) {
					    try {
					        int idRoom = Integer.parseInt(RoomTable.getValueAt(row, 0).toString());
					        System.out.println("Editando habitación con ID: " + idRoom);
					        RoomsController rc = new RoomsController();
					        RoomsModel rm = new RoomsModel();
					        
					        frame.dispose();
					        rc.editRoom(rm.getRoomById(idRoom));
					    } catch (Exception ex) {
					        ex.printStackTrace();
					    }
					}




		            @Override
		            public void onDelete(int row) {
		            	RoomsController rc = new RoomsController();
						rc.deleteRoom();
						//lo de abajo se implementará al dar click en el boton "aceptar"
		                /*if (clientsTable.isEditing()) {
		                	clientsTable.getCellEditor().stopCellEditing();
		                }
		                model.removeRow(row);*/
		            }

		            @Override
		            public void onView(int row) {
		            	RoomsController rc = new RoomsController();
						frame.dispose();
						rc.consultRoom();
		                System.out.println("View row : " + row);
		            }
		        };
		        RoomTable.getColumn("Acciones").setCellRenderer(new TableActionCellRender());
		        RoomTable.getColumn("Acciones").setCellEditor(new TableActionCellEditor(event));
				
		        JScrollPane scrollPane = new JScrollPane(RoomTable);
				scrollPane.setBounds(0, 50, 1000, 260);
				RoomTablePanel.add(scrollPane);
				
				JLabel lblTableTitle = new JLabel("Habitaciones");
				lblTableTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
				lblTableTitle.setForeground(Color.decode("#FFFFFF"));
				lblTableTitle.setBounds(400, 5, 200, 40);
				RoomTablePanel.add(lblTableTitle);
				
				JTextField search = new JTextField();
				search.setBounds(130, 180, 800, 50);
				search.setText("🔍 Buscar");
				search.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 22));
				search.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						if(search.getText().equals("🔍 Buscar")) {
							search.setText("");							
						}
						else {
							return;
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
				panel.add(search);
				search.setColumns(10);
				
				JButton btnSearch = new JButton("Ver");
				btnSearch.setFont(new Font("Inter_18pt Bold", Font.BOLD, 22));
				btnSearch.setBackground(Color.decode("#071A2B"));
				btnSearch.setForeground(Color.decode("#FFFFFF"));
				btnSearch.setBounds(935, 180, 195, 50);
				panel.add(btnSearch);
				
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HomeController home = new HomeController();
				frame.dispose();
				home.home();
			}
			
		});
	}
	Room r = new Room();
	
	public void createRoom() {
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
		
		
		JLabel lblTitle = new JLabel("Añadir Habitacion");
		lblTitle.setBounds(200, 42, 550, 82);
		header.add(lblTitle);
		lblTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 44));
		lblTitle.setForeground(Color.decode("#FFFFFF"));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(null);
		
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
				RoomsController home = new RoomsController();
				frame.dispose();
				home.rooms();
			}
			
		});
		
		// JLabel para mostrar la imagen seleccionada
		lblImg = new JLabel();
		lblImg.setBounds(130, 150, 510, 200);
		lblImg.setText(null);
		lblImg.setBorder(BorderFactory.createLineBorder(Color.black));
		lblImg.setHorizontalAlignment(JLabel.CENTER);
		lblImg.setVerticalAlignment(JLabel.CENTER);

		// Imagen por defecto
		ImageIcon imagenPredeterminada = new ImageIcon("src/images/subir.png");
		Image scaledPredeterminada = imagenPredeterminada.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		lblImg.setIcon(new ImageIcon(scaledPredeterminada));
		panel.add(lblImg);

		// JComboBox para seleccionar una imagen
		JComboBox<RoomImage> roomImage_Combo = new JComboBox<>();
		roomImage_Combo.setBounds(700, 300, 460, 60);
		panel.add(roomImage_Combo);

		try {
		    List<RoomImage> room_images = new RoomImageModel().getAvailableRoomImage();
		    DefaultComboBoxModel<RoomImage> model = new DefaultComboBoxModel<>();
		    model.addElement(null); // Primer elemento vacío

		    for (RoomImage roomImage : room_images) {
		        model.addElement(roomImage);
		    }
		    roomImage_Combo.setModel(model);
		} catch (SQLException e1) {
		    e1.printStackTrace();
		}

		// Listener para mostrar la imagen seleccionada en el JLabel
		roomImage_Combo.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            RoomImage selected = (RoomImage) roomImage_Combo.getSelectedItem();
		            if (selected != null && selected.getRoom_Image() != null) {
		                ImageIcon icon = new ImageIcon(selected.getRoom_Image());
		                Image scaled = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		                lblImg.setIcon(new ImageIcon(scaled));
		            } else {
		                // Imagen por defecto si se deselecciona o no hay imagen
		                ImageIcon defaultIcon = new ImageIcon("src/images/subir.png");
		                Image scaledDefault = defaultIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		                lblImg.setIcon(new ImageIcon(scaledDefault));
		            }
		        }
		    }
		});

		JComboBox<RoomType> roomType_Combo = new JComboBox<>();
		roomType_Combo.setBounds(130, 300, 460, 60);
		panel.add(roomType_Combo);
		


		try {
		    List<RoomType> room_type = new RoomTypesModel().getAvailableRoomType();
		    DefaultComboBoxModel<RoomType> model = new DefaultComboBoxModel<>();
		    model.addElement(null);

		    for (RoomType roomType : room_type) {
		        model.addElement(roomType);
		    }
		    roomType_Combo.setModel(model);
		} catch (SQLException e1) {
		    e1.printStackTrace();
		}

		JComboBox<Tariff> tariff_Combo = new JComboBox<>();
		tariff_Combo.setBounds(130, 300, 460, 60);
		panel.add(tariff_Combo);
		
		try {
		    List<Tariff> tariffs = new TariffsModel().getAvailableTariffs();
		    DefaultComboBoxModel<Tariff> model = new DefaultComboBoxModel<>();
		    model.addElement(null);

		    for (Tariff tariff : tariffs) {
		        model.addElement(tariff);
		    }
		    tariff_Combo.setModel(model);
		} catch (SQLException e1) {
		    e1.printStackTrace();
		}
		
		JTextField nombreTextField = new JTextField();
		nombreTextField.setText("Nombre de la habitación");
		nombreTextField.setBounds(700, 150, 460, 50);
		nombreTextField.setForeground(Color.gray);
		nombreTextField.setColumns(10);
		nombreTextField.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(nombreTextField.getText().equals("Nombre de la habitación")) {
					nombreTextField.setText("");
				}
				else {
					return;
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
			}
				
		});
		panel.add(nombreTextField);

		JTextField numeroTextField = new JTextField();
		numeroTextField.setText("Número de habitación");
		numeroTextField.setBounds(700, 230, 460, 50);
		numeroTextField.setColumns(10);
		numeroTextField.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(numeroTextField.getText().equals("Número de habitación")) {
					numeroTextField.setText("");
				}
				else {
					return;
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
			}
				
		});
		panel.add(numeroTextField);
		
		JLabel guestLabel = new JLabel("Num. Huespedes:");
		guestLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 24));
		guestLabel.setBounds(410, 450, 250, 50);
		guestLabel.setBackground(null);
		guestLabel.setBorder(null);
		panel.add(guestLabel);
		
		JLabel bedQtLabel = new JLabel("Cant. Camas:");
		bedQtLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 24));
		bedQtLabel.setBounds(130, 450, 250, 50);
		bedQtLabel.setBackground(null);
		bedQtLabel.setBorder(null);
		panel.add(bedQtLabel);
		
		Integer[] guests = {1,2,3,4,5,6} ;
		JComboBox Guests_comboBox = new JComboBox(guests);
		Guests_comboBox.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 24));
		Guests_comboBox.setBounds(410, 500, 230, 50);
		panel.add(Guests_comboBox);
		
		Integer[] bedQt = {1,2,3,4,5,6} ;
		JComboBox bedQtTextField = new JComboBox(bedQt);
		bedQtTextField.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 24));
		bedQtTextField.setBounds(130, 500, 230, 50);
		panel.add(bedQtTextField);

		TextArea amenities_textField = new TextArea();
		amenities_textField.setColumns(10);
		amenities_textField.setBounds(700, 390, 460, 160);
		panel.add(amenities_textField);
		
		JButton btnSave = new JButton("Guardar");
		btnSave.setBounds(970,600,200,70);
		btnSave.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnSave.setForeground(Color.decode("#FFFFFF"));
		btnSave.setBackground(Color.decode("#071A2B"));
		panel.add(btnSave);
		
		btnSave.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        RoomImage selectedImage = (RoomImage) roomImage_Combo.getSelectedItem();
		        RoomType selectedType  = (RoomType) roomType_Combo.getSelectedItem();
		        Tariff   selectedTariff= (Tariff) tariff_Combo.getSelectedItem(); // ← CORRECTO

		        Integer guests  = (Integer) Guests_comboBox.getSelectedItem();
		        Integer bedQt   = (Integer) bedQtTextField.getSelectedItem();
		        String  nombre  = nombreTextField.getText().trim();
		        String  numero  = numeroTextField.getText().trim();
		        String  amenities = amenities_textField.getText().trim();

		        try {
		            // 1) Crear y guardar la habitación
		            Room nuevaHabitacion = new Room();
		            nuevaHabitacion.setRoom_name(nombre);
		            nuevaHabitacion.setNum_room(Integer.parseInt(numero));
		            nuevaHabitacion.setId_room_type(selectedType.getId_room_type());
		            nuevaHabitacion.setId_room_image(
		                selectedImage != null 
		                    ? selectedImage.getid_Room_image() 
		                    : null
		            );
		            nuevaHabitacion.setMax_guest_qty(guests);
		            nuevaHabitacion.setBeds_qty(bedQt);
		            nuevaHabitacion.setAmenities(amenities);
		            nuevaHabitacion.setStatus(false);

		            // 2) Crear la tarifa y asociar más adelante el id_room
		            Tariff editTariff = new Tariff();
		            editTariff.setId_room(nuevaHabitacion.getId_room());

		            // 3) Llamar al controlador que inserta room y tarifa en secuencia
		            RoomsController rc = new RoomsController();
		            rc.createRoomWithTariff(nuevaHabitacion, editTariff);

		            JOptionPane.showMessageDialog(frame, "Habitación guardada exitosamente.");
		            frame.dispose();
		            new RoomsController().rooms();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(
		              frame,
		              "Error al guardar: " + ex.getMessage(),
		              "Error",
		              JOptionPane.ERROR_MESSAGE
		            );
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
	
	public void editRoom(Room r) {
		
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
		
		
		JLabel lblTitle = new JLabel("Añadir Habitacion");
		lblTitle.setBounds(200, 42, 550, 82);
		header.add(lblTitle);
		lblTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 44));
		lblTitle.setForeground(Color.decode("#FFFFFF"));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(null);
		
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
				RoomsController home = new RoomsController();
				frame.dispose();
				home.rooms();
			}
			
		});
		
		lblImg = new JLabel();
		lblImg.setBounds(130, 150, 510, 200);
		lblImg.setBorder(BorderFactory.createLineBorder(Color.black));
		lblImg.setHorizontalAlignment(JLabel.CENTER);
		lblImg.setVerticalAlignment(JLabel.CENTER);

		JComboBox<RoomImage> roomImage_Combo = new JComboBox<>();
		roomImage_Combo.setBounds(700, 300, 460, 60);
		panel.add(roomImage_Combo);
		panel.add(lblImg);

		try {
		    List<RoomImage> room_images = new RoomImageModel().getAvailableRoomImage();
		    DefaultComboBoxModel<RoomImage> model = new DefaultComboBoxModel<>();
		    for (RoomImage roomImage : room_images) {
		        model.addElement(roomImage);
		    }
		    roomImage_Combo.setModel(model);
		    for (int i = 0; i < roomImage_Combo.getItemCount(); i++) {
		        RoomImage ri = roomImage_Combo.getItemAt(i);
		        if (ri != null && ri.getid_Room_image() == r.getId_room_image()) {
		            roomImage_Combo.setSelectedIndex(i);
		            break;
		        }
		    }


		
		} catch (SQLException e1) {
		    e1.printStackTrace();
		}
		RoomImage seleccionada = (RoomImage) roomImage_Combo.getSelectedItem();

		if (seleccionada != null && seleccionada.getRoom_Image() != null) {
			System.out.println("1- Imagen cargada y mostrada");
		    ImageIcon icon = new ImageIcon(seleccionada.getRoom_Image());
		    Image scaled = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		    lblImg.setIcon(new ImageIcon(scaled));
		}

		roomImage_Combo.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		        	System.out.println("2- Imagen cargada y mostrada");

		            RoomImage selected = (RoomImage) roomImage_Combo.getSelectedItem();
		            if (selected != null && selected.getRoom_Image() != null) {
		                ImageIcon icon = new ImageIcon(selected.getRoom_Image());
		                Image scaled = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		                lblImg.setIcon(new ImageIcon(scaled));
		            } 
		            else {
		                ImageIcon defaultIcon = new ImageIcon("src/images/subir.png");
		                Image scaledDefault = defaultIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		                lblImg.setIcon(new ImageIcon(scaledDefault));
		            }
		        }
		    }
		});
		
		JComboBox<RoomType> roomType_Combo = new JComboBox<>();
		roomType_Combo.setBounds(130, 350, 510, 60);
		panel.add(roomType_Combo);

		try {
		    List<RoomType> room_type = new RoomTypesModel().getAvailableRoomType();
		    DefaultComboBoxModel<RoomType> model = new DefaultComboBoxModel<>();
		    model.addElement(null);

		    for (RoomType roomType : room_type) {
		        model.addElement(roomType);
		    }
		    roomType_Combo.setModel(model);
		    for (int i = 0; i < roomType_Combo.getItemCount(); i++) {
		        RoomType rt = roomType_Combo.getItemAt(i);
		        if (rt != null && rt.getId_room_type() == r.getId_room_type()) {
		            roomType_Combo.setSelectedIndex(i);
		            break;
		        }
		    }


		    
		} catch (SQLException e1) {
		    e1.printStackTrace();
		}

		
		JTextField nombreTextField = new JTextField();
		nombreTextField.setText(r.getRoom_name());
		nombreTextField.setBounds(700, 150, 460, 50);
		nombreTextField.setForeground(Color.gray);
		nombreTextField.setColumns(10);
		panel.add(nombreTextField);

		JTextField numeroTextField = new JTextField();
		numeroTextField.setText(""+r.getNum_room());
		numeroTextField.setBounds(700, 230, 460, 50);
		numeroTextField.setColumns(10);
		panel.add(numeroTextField);
		
		JLabel guestLabel = new JLabel("Num. Huespedes:");
		guestLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 24));
		guestLabel.setBounds(410, 450, 250, 50);
		guestLabel.setBackground(null);
		guestLabel.setBorder(null);
		panel.add(guestLabel);
		
		JLabel bedQtLabel = new JLabel("Cant. Camas:");
		bedQtLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 24));
		bedQtLabel.setBounds(130, 450, 250, 50);
		bedQtLabel.setBackground(null);
		bedQtLabel.setBorder(null);
		panel.add(bedQtLabel);
		
		Integer[] guests = {1,2,3,4,5,6} ;
		JComboBox Guests_comboBox = new JComboBox(guests);
		Guests_comboBox.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 24));
		Guests_comboBox.setSelectedItem(r.getMax_guest_qty());
		Guests_comboBox.setBounds(410, 500, 230, 50);
		panel.add(Guests_comboBox);
		
		Integer[] bedQt = {1,2,3,4,5,6} ;
		JComboBox bedQt_comboBox = new JComboBox(bedQt);
		bedQt_comboBox.setSelectedItem(r.getBeds_qty());
		bedQt_comboBox.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 24));
		bedQt_comboBox.setBounds(130, 500, 230, 50);
		panel.add(bedQt_comboBox);

		TextArea amenities_textField = new TextArea();
		amenities_textField.setText(r.getAmenities());
		amenities_textField.setColumns(10);
		amenities_textField.setBounds(700, 390, 460, 160);
		panel.add(amenities_textField);
		
		JButton btnCreate = new JButton("Guardar");
		btnCreate.setBounds(970,600,200,70);
		btnCreate.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnCreate.setForeground(Color.decode("#FFFFFF"));
		btnCreate.setBackground(Color.decode("#071A2B"));
		panel.add(btnCreate);
		
		System.out.println("ROOM A EDITAR:");
    	System.out.println("Nombre: " + r.getRoom_name());
    	System.out.println("Número: " + r.getNum_room());
    	System.out.println("ID tipo: " + r.getId_room_type());
    	System.out.println("ID imagen: " + r.getId_room_image());
    	System.out.println("Camas: " + r.getBeds_qty());
    	System.out.println("Huespedes: " + r.getMax_guest_qty());
    	System.out.println("Amenities: " + r.getAmenities());

		btnCreate.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		        RoomImage selectedImage = (RoomImage) roomImage_Combo.getSelectedItem();
		        RoomType selectedType = (RoomType) roomType_Combo.getSelectedItem();
		        Integer guests = (Integer) Guests_comboBox.getSelectedItem();
		        Integer bedQt = (Integer) bedQt_comboBox.getSelectedItem();
		        String nombre = nombreTextField.getText();
		        String numero = numeroTextField.getText();
		        String amenities = amenities_textField.getText().trim();

		        int idImagenAnterior = r.getId_room_image();

		        try {
		        	
		            r.setRoom_name(nombre);
		            r.setNum_room(Integer.parseInt(numero));
		            r.setId_room_type(selectedType.getId_room_type());
		            r.setId_room_image(selectedImage != null ? selectedImage.getid_Room_image() : null);
		            r.setMax_guest_qty(guests);
		            r.setBeds_qty(bedQt);
		            r.setAmenities(amenities);

		            boolean actualizado = new RoomsModel().updateRoomType(r);

		            if (actualizado) {
		                int idImagenNueva = selectedImage != null ? selectedImage.getid_Room_image() : -1;

		                if (idImagenAnterior != idImagenNueva) {
		                    new RoomImageModel().updateRoomImageReference(idImagenAnterior, idImagenNueva, r.getId_room());
		                }

		                JOptionPane.showMessageDialog(frame, "Habitación actualizada exitosamente.");
		                frame.dispose();
		                new RoomsController().rooms();
		            } else {
		                JOptionPane.showMessageDialog(frame, "Error al actualizar la habitación.");
		            }

		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(frame, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(frame, "Número inválido en el campo 'número de habitación'.", "Error", JOptionPane.ERROR_MESSAGE);
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
		frame.setVisible(true);
	}
	
	public void consultRoom() {
		frame = new JFrame();
	    frame.setTitle("Hotel Ancla de Paz");
	    frame.setResizable(false);
	    frame.setBounds(0,0,1280,720);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    
	    JPanel panel = new JPanel();
	    frame.getContentPane().add(panel, BorderLayout.CENTER);
	    panel.setBackground(Color.decode("#FFFCF7"));
	    panel.setLayout(null);

	    JPanel header = new JPanel();
	    header.setBounds(0, 0, 1266, 130);
	    header.setBackground(Color.decode("#071A2B"));
	    panel.add(header);
	    header.setLayout(null);
	    
	    JLabel lblTitle = new JLabel("Detalles");
	    lblTitle.setBounds(200, 42, 250, 82);
	    header.add(lblTitle);
	    lblTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 44));
	    lblTitle.setForeground(Color.decode("#FFFFFF"));
	    lblTitle.setOpaque(true);
	    lblTitle.setBackground(null);
	    
	    JButton btnBack = new JButton("");
	    btnBack.setBounds(130, 60, 56, 56);
	    header.add(btnBack);
	    btnBack.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
	    btnBack.setForeground(Color.decode("#FFFFFF"));
	    btnBack.setBorderPainted(false);
	    btnBack.setBackground(null);

	    ImageIcon btnBackOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/btnHome.png"));
	    Image btnBackScaledImage = btnBackOriginalIcon.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH);
	    ImageIcon btnBackScaledIcon = new ImageIcon(btnBackScaledImage);
	    btnBack.setIcon(btnBackScaledIcon);

	    JPanel roomInfoPanel = new JPanel();
	    roomInfoPanel.setBounds(130, 160, 400, 400);
	    roomInfoPanel.setBackground(Color.decode("#FFFFFF"));
	    roomInfoPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#E0E0E0"), 1));
	    panel.add(roomInfoPanel);
	    roomInfoPanel.setLayout(null);

	    JPanel roomImagePanel = new JPanel();
	    roomImagePanel.setBounds(20, 20, 360, 200);
	    roomImagePanel.setBackground(Color.decode("#F0F0F0"));
	    roomImagePanel.setBorder(BorderFactory.createLineBorder(Color.decode("#E0E0E0"), 1));
	    roomInfoPanel.add(roomImagePanel);
	    
	    JLabel roomImageLabel = new JLabel("Imagen de habitación", SwingConstants.CENTER);
	    roomImageLabel.setBounds(0, 0, 360, 200);
	    roomImageLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 14));
	    roomImageLabel.setForeground(Color.decode("#888888"));
	    roomImagePanel.add(roomImageLabel);
	    
	    JLabel lblRoomTitle = new JLabel("Habitación de lujo");
	    lblRoomTitle.setBounds(20, 240, 300, 30);
	    lblRoomTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
	    lblRoomTitle.setForeground(Color.decode("#071A2B"));
	    roomInfoPanel.add(lblRoomTitle);

	    JLabel lblPrice = new JLabel("$2,000.00 MXN/noche");
	    lblPrice.setBounds(20, 275, 200, 25);
	    lblPrice.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
	    lblPrice.setForeground(Color.decode("#071A2B"));
	    roomInfoPanel.add(lblPrice);

	    JLabel lblTariff = new JLabel("Tarifa variable");
	    lblTariff.setBounds(20, 300, 150, 20);
	    lblTariff.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 14));
	    lblTariff.setForeground(Color.decode("#666666"));
	    roomInfoPanel.add(lblTariff);

	    JLabel lblStars = new JLabel("5 estrellas");
	    lblStars.setBounds(20, 320, 100, 20);
	    lblStars.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 14));
	    lblStars.setForeground(Color.decode("#666666"));
	    roomInfoPanel.add(lblStars);

	    JLabel lblAmenidadesTitle = new JLabel("Amenidades");
	    lblAmenidadesTitle.setBounds(20, 345, 120, 25);
	    lblAmenidadesTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 18));
	    lblAmenidadesTitle.setForeground(Color.decode("#071A2B"));
	    roomInfoPanel.add(lblAmenidadesTitle);
	    
	    JLabel lblWifi = new JLabel("Wifi");
	    lblWifi.setBounds(20, 370, 60, 20);
	    lblWifi.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 14));
	    lblWifi.setForeground(Color.decode("#666666"));
	    roomInfoPanel.add(lblWifi);
	    
	    JLabel lblHotWater = new JLabel("Agua caliente");
	    lblHotWater.setBounds(90, 370, 100, 20);
	    lblHotWater.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 14));
	    lblHotWater.setForeground(Color.decode("#666666"));
	    roomInfoPanel.add(lblHotWater);
	    
	    JLabel lblJacuzzi = new JLabel("Jacuzzi");
	    lblJacuzzi.setBounds(200, 370, 60, 20);
	    lblJacuzzi.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 14));
	    lblJacuzzi.setForeground(Color.decode("#666666"));
	    roomInfoPanel.add(lblJacuzzi);
	    
	    String[] columnNames = {
	        "Cliente",
	        "Check in",
	        "Check out"
	    };
	    
	    Object[][] data = {
	        {"1", "12/02/25", "15/02/25"},
	        {"1", "12/02/25", "15/02/25"},
	        {"1", "12/02/25", "15/02/25"},
	        {"1", "12/02/25", "15/02/25"},
	        {"1", "12/02/25", "15/02/25"},
	        {"1", "12/02/25", "15/02/25"}
	    };
	    
	    JPanel historialTablePanel = new JPanel();
	    historialTablePanel.setBounds(560, 160, 570, 400);
	    historialTablePanel.setBackground(Color.decode("#071A2B"));
	    panel.add(historialTablePanel);
	    historialTablePanel.setLayout(null);
	    
	    JLabel lblTableTitle = new JLabel("Historial de renta");
	    lblTableTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
	    lblTableTitle.setForeground(Color.decode("#FFFFFF"));
	    lblTableTitle.setBounds(200, 10, 250, 40);
	    historialTablePanel.add(lblTableTitle);
	    
	    DefaultTableModel model = new DefaultTableModel(data, columnNames);
	    JTable historialTable = new JTable(model);
	    historialTable.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
	    historialTable.setRowHeight(35);
	    historialTable.getTableHeader().setFont(new Font("Inter_18pt Bold", Font.BOLD, 20));
	    historialTable.getTableHeader().setBackground(Color.decode("#071A2B"));
	    historialTable.getTableHeader().setForeground(Color.decode("#FFFFFF"));
	    historialTable.setDefaultEditor(Object.class, null);
	    
	    JScrollPane scrollPane = new JScrollPane(historialTable);
	    scrollPane.setBounds(20, 60, 530, 320);
	    historialTablePanel.add(scrollPane);
	    
	    JButton btnDownloadPDF = new JButton("Descargar detalles (PDF)");
	    btnDownloadPDF.setBounds(560, 580, 350, 50);
	    btnDownloadPDF.setFont(new Font("Inter_18pt Bold", Font.BOLD, 18));
	    btnDownloadPDF.setForeground(Color.decode("#FFFFFF"));
	    btnDownloadPDF.setBackground(Color.decode("#28A745")); 
	    panel.add(btnDownloadPDF);

	    btnBack.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	RoomsController home = new RoomsController();
				frame.dispose();
				home.rooms();
	        }
	    });
	    
	    btnDownloadPDF.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.out.println("Descargando PDF...");
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
				RoomsController client = new RoomsController();
				frame.dispose();
				client.successDelete();
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
				RoomsController client = new RoomsController();
				frame.dispose();
				client.errorDelete();
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
		
		JLabel title = new JLabel("Cliente eliminado con exito");
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
