package views;

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
import java.io.Serial;
import java.sql.Date;
import java.sql.SQLException;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

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
import models.RoomType;
import models.RoomTypesModel;
import models.RoomsModel;
import models.Tariff;

public class RoomsView {

	private JFrame frame;
	private RoomsModel functions;
	byte[] imageBytes;
	
	public void rooms() {
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
		
		List<Room> rooms = new ArrayList<>();
		List<RoomType> roomTypes = new ArrayList<>();
		List<Tariff> tariffs = new ArrayList<>();
		
		RoomsModel functions = new RoomsModel();
		try {
			rooms = functions.getAvailableRoom();
			roomTypes = functions.getAvailableRoomType();
			tariffs = functions.getAvailableTariffs();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		Object[][] datos = new Object[rooms.size()][3];

		for (int i = 0; i < rooms.size(); i++) {
		    datos[i][0] = rooms.get(i).getNum_room();
		    datos[i][1] = roomTypes.get(i).getRoom_type();
		    if(tariffs.get(i).isRefundable()) {
		    	datos[i][2] = "Reembolsable";    	
		    }
		    else {
		    	datos[i][2] = "No reembolsable";
		    }
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
		            	RoomsController client = new RoomsController();
						frame.dispose();
						client.editRoom();
		                System.out.println("Edit row : " + row);
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
		
		JLabel lblImg = new JLabel();
		lblImg.setBounds(130,150,400,250);
		lblImg.setText(null);
		lblImg.setBorder(BorderFactory.createLineBorder(Color.black));
		
		ImageIcon imagenPredeterminada = new ImageIcon("src/images/subir.png");
		Image scaledPredeterminada = imagenPredeterminada.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		
		
		lblImg.setHorizontalAlignment(JLabel.CENTER);
		lblImg.setVerticalAlignment(JLabel.CENTER);
		lblImg.setIcon(new ImageIcon(scaledPredeterminada));
		panel.add(lblImg);
		
		JButton btnAddImage = new JButton("Agregar imagen");
		btnAddImage.setBounds(700,300,400,60);
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
			            	Image scaled = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
			            	lblImg.setIcon(new ImageIcon(scaled));
			            	
			        } catch (IOException e1) {
			            e1.printStackTrace();
			        }
			    }
			}			
		});
		
		JComboBox<RoomType> tipoHabitacionCombo = new JComboBox<>();
		tipoHabitacionCombo.setBounds(130, 400, 400, 49);
	    panel.add(tipoHabitacionCombo);
		
		try {
	        List<RoomType> tarifas = new RoomsModel().getAvailableRoomType();
	        DefaultComboBoxModel<RoomType> model = new DefaultComboBoxModel<>();
	        for (RoomType tarifa : tarifas) {
	            model.addElement(tarifa);
	        }
	        tipoHabitacionCombo.setModel(model);
	    } catch (SQLException e) {
	    }

		
		JTextField nombreTextField = new JTextField();
		nombreTextField.setText("Nombre");
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
				if(nombreTextField.getText().equals("Nombre")) {
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
		numeroTextField.setText("Numero de habitacion");
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
				if(numeroTextField.getText().equals("Numero de habitacion")) {
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
		
		JTextField capacidadTextField = new JTextField();
		capacidadTextField.setColumns(10);
		capacidadTextField.setText("Huespedes");
		capacidadTextField.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 24));
		capacidadTextField.setBounds(380, 500, 150, 50);
		panel.add(capacidadTextField);
		
		JTextField bedQtTextField = new JTextField();
		bedQtTextField.setColumns(10);
		bedQtTextField.setText("Camas");
		bedQtTextField.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 24));
		bedQtTextField.setBounds(130, 500, 150, 50);
		panel.add(bedQtTextField);

		TextArea amenitiestextField_3 = new TextArea();
		amenitiestextField_3.setColumns(10);
		amenitiestextField_3.setBounds(700, 390, 470, 160);
		panel.add(amenitiestextField_3);
		
		JButton btnCreate = new JButton("Guardar");
		btnCreate.setBounds(970,600,200,70);
		btnCreate.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnCreate.setForeground(Color.decode("#FFFFFF"));
		btnCreate.setBackground(Color.decode("#071A2B"));
		panel.add(btnCreate);
		
		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
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
	
	public void editRoom() {
		String name,email;
		int phone;// puede ser string: no me acuerdo como estaba en la base de datos
		
		name="david";
		email="david@e.mail";
		phone=1234567890;
		
		
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
				RoomsController home = new RoomsController();
				frame.dispose();
				home.rooms();
			}
			
		});
		
		JLabel lblImg = new JLabel();
		lblImg.setBounds(130,150,400,250);
		lblImg.setText(null);
		lblImg.setBorder(BorderFactory.createLineBorder(Color.black));
		
		ImageIcon lblImgOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/suite.png"));
		Image lblImgScaledImage = lblImgOriginalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon lblImgScaledIcon = new ImageIcon(lblImgScaledImage);//btnConsult
		
		lblImg.setHorizontalAlignment(JLabel.CENTER);
		lblImg.setVerticalAlignment(JLabel.CENTER);
		lblImg.setIcon(lblImgScaledIcon);
		panel.add(lblImg);
		
		JButton btnAddImage = new JButton("Agregar imagen");
		btnAddImage.setBounds(700,300,400,60);
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
			           
			            // Mostrar vista previa
			          
			            	ImageIcon icon = new ImageIcon(imageBytes);
			            	Image scaled = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
			            	btnAddImage.setIcon(new ImageIcon(scaled));
			
			            System.out.println("Imagen cargada y mostrada correctamente");
			        } catch (IOException e1) {
			            e1.printStackTrace();
			        }
			    }
			}			
		});
		
		JComboBox<RoomType> tipoHabitacionCombo = new JComboBox<>();
		tipoHabitacionCombo.setBounds(130, 400, 400, 49);
	    panel.add(tipoHabitacionCombo);
		
		try {
	        List<RoomType> tarifas = new RoomsModel().getAvailableRoomType();
	        DefaultComboBoxModel<RoomType> model = new DefaultComboBoxModel<>();
	        for (RoomType tarifa : tarifas) {
	            model.addElement(tarifa);
	        }
	        tipoHabitacionCombo.setModel(model);
	    } catch (SQLException e) {
	    }

		
		JTextField nombreTextField = new JTextField();
		nombreTextField.setText(name);
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
				if(nombreTextField.getText().equals("Nombre")) {
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
		numeroTextField.setText("Numero de habitacion");
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
				if(numeroTextField.getText().equals("Numero de habitacion")) {
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
		
		JTextField capacidadTextField = new JTextField();
		capacidadTextField.setColumns(10);
		capacidadTextField.setText("6");
		capacidadTextField.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 24));
		capacidadTextField.setBounds(380, 500, 150, 50);
		panel.add(capacidadTextField);
		
		JTextField bedQtTextField = new JTextField();
		bedQtTextField.setColumns(10);
		bedQtTextField.setText("4");
		bedQtTextField.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 24));
		bedQtTextField.setBounds(130, 500, 150, 50);
		panel.add(bedQtTextField);

		TextArea amenitiestextField = new TextArea("Suite amplia y espaciosa con vista al mar ");
		amenitiestextField.setColumns(10);
		amenitiestextField.setBounds(700, 390, 470, 160);
		panel.add(amenitiestextField);
		
		JButton btnCreate = new JButton("Guardar");
		btnCreate.setBounds(970,600,200,70);
		btnCreate.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnCreate.setForeground(Color.decode("#FFFFFF"));
		btnCreate.setBackground(Color.decode("#071A2B"));
		panel.add(btnCreate);
		
		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
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
