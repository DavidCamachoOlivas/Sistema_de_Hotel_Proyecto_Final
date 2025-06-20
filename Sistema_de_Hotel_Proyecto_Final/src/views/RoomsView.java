package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
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
import controllers.PopUpsController;
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
	PopUpsController pop = new PopUpsController();
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
					        rc.editRoom(rm.getRoomByNum(idRoom));
					    } catch (Exception ex) {
					        ex.printStackTrace();
					    }
					}
				
					@Override
					public void onDelete(int row) {
					    try {
					        int idRoom = Integer.parseInt(RoomTable.getValueAt(row, 0).toString());
					        System.out.println("Solicitando eliminación de habitación con ID: " + idRoom);
					        deleteConfirm(idRoom);
					    } catch (Exception ex) {
					        ex.printStackTrace();
					        // Mostrar mensaje de error al usuario
					        JOptionPane.showMessageDialog(null, "Error al obtener ID de habitación", "Error", JOptionPane.ERROR_MESSAGE);
					    }
					}
					
		            @Override
		            public void onView(int row) {
		            	try {
					        int idRoom = Integer.parseInt(RoomTable.getValueAt(row, 0).toString());
					        System.out.println("Editando habitación con ID: " + idRoom);
					        RoomsController rc = new RoomsController();
					        RoomsModel rm = new RoomsModel();
					        
					        frame.dispose();
					        rc.consultRoom(rm.getRoomByNum(idRoom));
					    } catch (Exception ex) {
					        ex.printStackTrace();
					    }
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
		frame.revalidate();
		frame.repaint();
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
		
		
		JLabel lblTitle = new JLabel("Añadir habitación");
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
				javax.swing.SwingUtilities.invokeLater(() -> {
		            pop.loading();
		        });

		        new Thread(() -> {
		        	frame.dispose();
		        	RoomsController rooms = new RoomsController();
		        	rooms.rooms();
		            javax.swing.SwingUtilities.invokeLater(() -> {
		                pop.closeLoading();
		                frame.dispose();
		            });
		        }).start();
			}
			
		});
		
		// JLabel para mostrar la imagen seleccionada
		lblImg = new JLabel();
		lblImg.setBounds(130, 150, 510, 250);
		lblImg.setText(null);
		lblImg.setBorder(BorderFactory.createLineBorder(Color.black));
		lblImg.setHorizontalAlignment(JLabel.CENTER);
		lblImg.setVerticalAlignment(JLabel.CENTER);

		// Imagen por defecto
		ImageIcon imagenPredeterminada = new ImageIcon("src/images/subir.png");
		Image scaledPredeterminada = imagenPredeterminada.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		lblImg.setIcon(new ImageIcon(scaledPredeterminada));
		panel.add(lblImg);

		JLabel roomImage_Combolbl = new JLabel("Seleccione imagen:");
		roomImage_Combolbl.setBounds(700, 300, 460, 40);
		roomImage_Combolbl.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 20));
		panel.add(roomImage_Combolbl);
		// JComboBox para seleccionar una imagen
		JComboBox<RoomImage> roomImage_Combo = new JComboBox<>();
		roomImage_Combo.setBounds(700, 340, 450, 50);
		panel.add(roomImage_Combo);

		try {
		    List<RoomImage> room_images = new RoomImageModel().getAvailableRoomImage();
		    DefaultComboBoxModel<RoomImage> model = new DefaultComboBoxModel<>();
		    model.addElement(null);

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
		            if (selected != null && selected.getRoom_image() != null) {
		                ImageIcon icon = new ImageIcon(selected.getRoom_image());
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

		JLabel roomType_Combo_lbl = new JLabel("Tipo de habitación:");
		roomType_Combo_lbl.setBounds(130, 410, 230, 40);
		roomType_Combo_lbl.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 20));
		panel.add(roomType_Combo_lbl);
		JComboBox<RoomType> roomType_Combo = new JComboBox<>();
		roomType_Combo.setBounds(130, 450, 230, 50);
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

		JLabel tariff_Combo_lbl = new JLabel("Tarifa:");
		tariff_Combo_lbl.setBounds(410, 410, 230, 40);
		tariff_Combo_lbl.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 20));
		panel.add(tariff_Combo_lbl);
		JComboBox<Tariff> tariff_Combo = new JComboBox<>();
		tariff_Combo.setBounds(410, 450, 230, 50);
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
		nombreTextField.setBounds(700, 150, 450, 50);
		nombreTextField.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 20));
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
		nombreTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && !Character.isWhitespace(c) &&
                    "áéíóúÁÉÍÓÚñÑ".indexOf(c) == -1) {
                    e.consume();
                }
            }
        });
		panel.add(nombreTextField);

		JTextField numeroTextField = new JTextField();
		numeroTextField.setText("Número de habitación");
		numeroTextField.setBounds(700, 230, 450, 50);
		numeroTextField.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 20));
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
		numeroTextField.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	            char c = e.getKeyChar();
	            if (!Character.isDigit(c)) {
	                e.consume();
	            }
	        }
	    });
		panel.add(numeroTextField);
		
		JLabel guestLabel = new JLabel("Num. Huespedes:");
		guestLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 20));
		guestLabel.setBounds(410, 500, 250, 50);
		guestLabel.setBackground(null);
		guestLabel.setBorder(null);
		panel.add(guestLabel);
		
		JLabel bedQtLabel = new JLabel("Cant. Camas:");
		bedQtLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 20));
		bedQtLabel.setBounds(130, 500, 250, 50);
		bedQtLabel.setBackground(null);
		bedQtLabel.setBorder(null);
		panel.add(bedQtLabel);
		
		Integer[] guests = {1,2,3,4,5,6} ;
		JComboBox Guests_comboBox = new JComboBox(guests);
		Guests_comboBox.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 24));
		Guests_comboBox.setBounds(410, 550, 230, 50);
		panel.add(Guests_comboBox);
		
		Integer[] bedQt = {1,2,3,4,5,6} ;
		JComboBox bedQtTextField = new JComboBox(bedQt);
		bedQtTextField.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 24));
		bedQtTextField.setBounds(130, 550, 230, 50);
		panel.add(bedQtTextField);

		JLabel amenitiesLabel = new JLabel("Amenidades:");
		amenitiesLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 20));
		amenitiesLabel.setBounds(700, 410, 250, 40);
		amenitiesLabel.setBackground(null);
		amenitiesLabel.setBorder(null);
		panel.add(amenitiesLabel);
		
		JTextArea amenities_textField = new JTextArea();
		amenities_textField.setColumns(10);
		amenities_textField.setBounds(700, 450, 450, 150);
		panel.add(amenities_textField);				
		
		JButton btnSave = new JButton("Guardar");
		btnSave.setBounds(960,605,200,70);
		btnSave.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnSave.setForeground(Color.decode("#FFFFFF"));
		btnSave.setBackground(Color.decode("#071A2B"));
		panel.add(btnSave);
		
		btnSave.addActionListener(e -> {
		    try {
		        // Recuperamos el borde por defecto para restaurar en los campos válidos
		        Border defaultBorder = nombreTextField.getBorder();
		        Border redBorder = BorderFactory.createLineBorder(Color.RED);

		        // Asumimos que todos son válidos al inicio
		        boolean valid = true;

		        // Validar nombre
		        if (nombreTextField.getText().trim().isEmpty() ||
		            nombreTextField.getText().trim().equals("Nombre de la habitación")) {
		            nombreTextField.setBorder(redBorder);
		            valid = false;
		        } else {
		            nombreTextField.setBorder(defaultBorder);
		        }

		        // Validar número de habitación
		        int numeroHabitacion = -1;
		        if (numeroTextField.getText().trim().isEmpty() ||
		            numeroTextField.getText().trim().equals("Número de habitación")) {
		            numeroTextField.setBorder(redBorder);
		            valid = false;
		        } else {
		            try {
		                numeroHabitacion = Integer.parseInt(numeroTextField.getText().trim());
		                numeroTextField.setBorder(defaultBorder);
		            } catch (NumberFormatException ex) {
		                numeroTextField.setBorder(redBorder);
		                valid = false;
		            }
		        }

		        // Validar RoomType
		        if (roomType_Combo.getSelectedItem() == null) {
		            roomType_Combo.setBorder(redBorder);
		            valid = false;
		        } else {
		            roomType_Combo.setBorder(defaultBorder);
		        }

		        // Validar Tariff
		        if (tariff_Combo.getSelectedItem() == null) {
		            tariff_Combo.setBorder(redBorder);
		            valid = false;
		        } else {
		            tariff_Combo.setBorder(defaultBorder);
		        }

		        // Validar cantidad de huéspedes
		        if (Guests_comboBox.getSelectedItem() == null) {
		            Guests_comboBox.setBorder(redBorder);
		            valid = false;
		        } else {
		            Guests_comboBox.setBorder(defaultBorder);
		        }

		        // Validar cantidad de camas
		        if (bedQtTextField.getSelectedItem() == null) {
		            bedQtTextField.setBorder(redBorder);
		            valid = false;
		        } else {
		            bedQtTextField.setBorder(defaultBorder);
		        }

		        // Validar amenidades (si deseas permitirlo vacío, comenta esto)
		        if (amenities_textField.getText().trim().isEmpty()) {
		            amenities_textField.setBorder(redBorder);
		            valid = false;
		        } else {
		            amenities_textField.setBorder(defaultBorder);
		        }

		        if (!valid) {
		            JOptionPane.showMessageDialog(frame, "Por favor complete todos los campos obligatorios correctamente.", "Campos inválidos", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        // Crear la habitación si todo es válido
		        RoomImage selectedImage = (RoomImage) roomImage_Combo.getSelectedItem();
		        RoomType selectedType = (RoomType) roomType_Combo.getSelectedItem();
		        Tariff selectedTariff = (Tariff) tariff_Combo.getSelectedItem();

		        Room nuevaHabitacion = new Room();
		        nuevaHabitacion.setRoom_name(nombreTextField.getText().trim());
		        nuevaHabitacion.setNum_room(numeroHabitacion);
		        nuevaHabitacion.setId_room_type(selectedType.getId_room_type());
		        nuevaHabitacion.setId_room_image(selectedImage != null ? selectedImage.getId_room_image() : null);
		        nuevaHabitacion.setMax_guest_qty((Integer) Guests_comboBox.getSelectedItem());
		        nuevaHabitacion.setBeds_qty((Integer) bedQtTextField.getSelectedItem());
		        nuevaHabitacion.setAmenities(amenities_textField.getText().trim());
		        nuevaHabitacion.setStatus(false);

		        RoomsModel roomsModel = new RoomsModel();
		        int newRoomId = roomsModel.createRoom(nuevaHabitacion);

		        TariffsModel tm = new TariffsModel();
		        RoomTypesModel rtm = new RoomTypesModel();
		        RoomType rt = rtm.getRoomTypeById(nuevaHabitacion.getId_room_type());
		        Tariff originalTariff = tm.getTariffById(rt.getId_tariff());

		        Tariff editTariff = new Tariff();
		        editTariff.setCapacity(originalTariff.getCapacity());
		        editTariff.setPrice_per_night(originalTariff.getPrice_per_night());
		        editTariff.setTariff_type(originalTariff.getTariff_type());
		        editTariff.setRefundable(originalTariff.isRefundable());

		        tm.createTariff(editTariff);

		        JOptionPane.showMessageDialog(frame, "Habitación guardada exitosamente.");
		        frame.dispose();
		        new RoomsController().rooms();

		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(frame, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    }
		});






		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(750,605,200,70);
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
		frame.revalidate();
		frame.repaint();
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
		
		
		JLabel lblTitle = new JLabel("Editar habitación");
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
				javax.swing.SwingUtilities.invokeLater(() -> {
		            pop.loading();
		        });

		        new Thread(() -> {
		        	frame.dispose();
		        	RoomsController rooms = new RoomsController();
		        	rooms.rooms();
		            javax.swing.SwingUtilities.invokeLater(() -> {
		                pop.closeLoading();
		                frame.dispose();
		            });
		        }).start();
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
		        if (ri != null && ri.getId_room_image() == r.getId_room_image()) {
		            roomImage_Combo.setSelectedIndex(i);
		            break;
		        }
		    }


		
		} catch (SQLException e1) {
		    e1.printStackTrace();
		}
		RoomImage seleccionada = (RoomImage) roomImage_Combo.getSelectedItem();

		if (seleccionada != null && seleccionada.getRoom_image() != null) {
			System.out.println("1- Imagen cargada y mostrada");
		    ImageIcon icon = new ImageIcon(seleccionada.getRoom_image());
		    Image scaled = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		    lblImg.setIcon(new ImageIcon(scaled));
		}

		roomImage_Combo.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		        	System.out.println("2- Imagen cargada y mostrada");

		            RoomImage selected = (RoomImage) roomImage_Combo.getSelectedItem();
		            if (selected != null && selected.getRoom_image() != null) {
		                ImageIcon icon = new ImageIcon(selected.getRoom_image());
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
		            r.setId_room_image(selectedImage != null ? selectedImage.getId_room_image() : null);
		            r.setMax_guest_qty(guests);
		            r.setBeds_qty(bedQt);
		            r.setAmenities(amenities);

		            boolean actualizado = new RoomsModel().updateRoomType(r);

		            if (actualizado) {
		                int idImagenNueva = selectedImage != null ? selectedImage.getId_room_image() : -1;

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
		frame.revalidate();
		frame.repaint();
	}
	
	public void consultRoom(Room r) throws SQLException {
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
	    header.setBounds(0, 0, 1280, 130);
	    header.setBackground(Color.decode("#071A2B"));
	    panel.add(header);
	    header.setLayout(null);
	    
	    JLabel lblTitle = new JLabel("Detalles de la habitación");
	    lblTitle.setBounds(200, 42, 550, 82);
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
	    
	    btnBack.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	javax.swing.SwingUtilities.invokeLater(() -> {
		            pop.loading();
		        });

		        new Thread(() -> {
		        	frame.dispose();
		        	RoomsController rooms = new RoomsController();
		        	rooms.rooms();
		            javax.swing.SwingUtilities.invokeLater(() -> {
		                pop.closeLoading();
		                frame.dispose();
		            });
		        }).start();
	        }
	    });

	    JPanel roomInfoPanel = new JPanel();
	    roomInfoPanel.setBounds(130, 160, 400, 500);
	    roomInfoPanel.setBackground(Color.decode("#FFFFFF"));
	    roomInfoPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#E0E0E0"), 1));
	    panel.add(roomInfoPanel);
	    roomInfoPanel.setLayout(null);

	    JPanel roomImagePanel = new JPanel();
	    roomImagePanel.setBounds(20, 20, 360, 200);
	    roomImagePanel.setBackground(Color.decode("#F0F0F0"));
	    roomImagePanel.setBorder(BorderFactory.createLineBorder(Color.decode("#E0E0E0"), 1));
	    roomInfoPanel.add(roomImagePanel);
	    
	    RoomImageModel rim = new RoomImageModel();
	    RoomImage ri = new RoomImage();
	    ri = rim.getRoomImageById(r.getId_room_image());
	    
	    Image icon = new ImageIcon(ri.getRoom_image()).getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
	    ImageIcon backgroundIcon = new ImageIcon(icon);
	    
	    JLabel roomImageLabel = new JLabel(backgroundIcon, SwingConstants.CENTER);
	    roomImageLabel.setBounds(0, 0, 360, 200);
	    roomImageLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 14));
	    roomImageLabel.setForeground(Color.decode("#888888"));
	    roomImagePanel.add(roomImageLabel);
	    
	    JLabel lblRoomTitle = new JLabel(r.getRoom_name());
	    lblRoomTitle.setBounds(20, 240, 300, 30);
	    lblRoomTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
	    lblRoomTitle.setForeground(Color.decode("#071A2B"));
	    roomInfoPanel.add(lblRoomTitle);
	    
	    RoomTypesModel rtm = new RoomTypesModel();
	    RoomType rt = new RoomType();
	    rt = rtm.getRoomTypeById(r.getId_room_type());
	    
	    TariffsModel tariff = new TariffsModel();
	    Tariff t = new Tariff();
	    t = tariff.getTariffById(rt.getId_tariff());

	    JLabel lblPrice = new JLabel("$"+t.getPrice_per_night()+"/noche");
	    lblPrice.setBounds(20, 275, 200, 25);
	    lblPrice.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
	    lblPrice.setForeground(Color.decode("#071A2B"));
	    roomInfoPanel.add(lblPrice);

	    JLabel lblTariff = new JLabel(t.getTariff_type());
	    lblTariff.setBounds(20, 300, 150, 20);
	    lblTariff.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 14));
	    lblTariff.setForeground(Color.decode("#666666"));
	    roomInfoPanel.add(lblTariff);

	    JLabel lblAmenitiesTitle = new JLabel("Amenidades: ");
	    lblAmenitiesTitle.setBounds(20, 345, 200, 25);
	    lblAmenitiesTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 18));
	    lblAmenitiesTitle.setForeground(Color.decode("#071A2B"));
	    roomInfoPanel.add(lblAmenitiesTitle);
	    
	    JLabel lblAmenities = new JLabel(r.getAmenities());
	    lblAmenities.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 14));
	    lblAmenities.setForeground(Color.decode("#666666"));
	    lblAmenities.setBackground(new Color(255,255,255));
	    
	    ScrollPane sp = new ScrollPane();
	    sp.setBounds(20, 370, 200, 50);
	    sp.add(lblAmenities);
	    roomInfoPanel.add(sp);
	    
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
	    btnDownloadPDF.setBounds(560, 600, 570, 50);
	    btnDownloadPDF.setFont(new Font("Inter_18pt Bold", Font.BOLD, 18));
	    btnDownloadPDF.setForeground(Color.decode("#FFFFFF"));
	    btnDownloadPDF.setBackground(Color.decode("#0E651B")); 
	    panel.add(btnDownloadPDF);

	    btnDownloadPDF.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.out.println("Descargando PDF...");
	        }
	    });
	    frame.revalidate();
	    frame.repaint();
	}
	
	public void deleteConfirm(int roomId) {
	    JDialog confirmDialog = new JDialog();
	    confirmDialog.setTitle("Confirmar eliminación");
	    confirmDialog.setSize(700, 500);
	    confirmDialog.setLocationRelativeTo(null);
	    confirmDialog.setModal(true);
	    confirmDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    
	    JPanel panel = new JPanel();
	    panel.setBackground(Color.decode("#FFFCF7"));
	    panel.setLayout(null);
	    confirmDialog.add(panel);
	    
	    JLabel title = new JLabel("¿Seguro que desea eliminar?");
		title.setBounds(50,20,550,70);
		title.setFont(new Font("Inter_18pt Bold", Font.BOLD, 32));
		title.setVisible(true);
		panel.add(title);	    
		
		JLabel img = new JLabel();
		img.setBounds(225,90,250,250);
		ImageIcon lblImgOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/btnDelete.png"));
		Image lblImgScaledImage = lblImgOriginalIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		ImageIcon lblImgScaledIcon = new ImageIcon(lblImgScaledImage);//btnConsult
		img.setIcon(lblImgScaledIcon);
		panel.add(img);
	    /*JLabel info = new JLabel("<html><div style='text-align: center; width: 600px;'>"
	            + "Las imágenes asociadas permanecerán en el sistema, pero dejarán de estar vinculadas a esta habitación"
	            + "</div></html>");
	    info.setBounds(50, 180, 600, 100);
	    info.setFont(new Font("Inter", Font.PLAIN, 18));
	    panel.add(info);*/
	    
		JButton accept = new JButton("Aceptar");
		accept.setBounds(380,350,270,70);
		accept.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		accept.setForeground(Color.decode("#FFFFFF"));
		accept.setBackground(Color.decode("#071A2B"));
		panel.add(accept);
	    
		JButton deny = new JButton("Cancelar");
		deny.setBounds(50,350,270,70);
		deny.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		deny.setForeground(Color.decode("#FFFFFF"));
		deny.setBackground(new Color(153, 89, 45));
		panel.add(deny);
	    
	    accept.addActionListener(e -> {
	        try {
	            RoomsModel rm = new RoomsModel();
	            Room roomToDelete = rm.getRoomById(roomId);
	            
	            if (roomToDelete != null) {
	                rm.deleteRoom(roomToDelete);
	                new RoomsController().successDelete();
	            } else {
	                new RoomsController().errorDelete();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            new RoomsController().errorDelete();
	        } finally {
	            confirmDialog.dispose();
	        }
	    });
	    
	    deny.addActionListener(e -> confirmDialog.dispose());
	    
	    confirmDialog.setVisible(true);

	
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
		
		JLabel title = new JLabel("Habitación eliminado con exito");
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
	
	private void styleButton(JButton button) {
	    button.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
	    button.setForeground(Color.WHITE);
	    button.setBackground(Color.decode("#071A2B"));
	    button.setFocusPainted(false);
	}
		
}
