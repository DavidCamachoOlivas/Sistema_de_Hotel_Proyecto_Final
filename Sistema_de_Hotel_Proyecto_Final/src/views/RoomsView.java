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
		
		JButton btnCreate = new JButton("Crear");
		btnCreate.setBounds(868,603,300,70);
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
		lblTitle.setBounds(200, 42, 250, 82);
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
		
		JPanel clientsTablePanel = new JPanel();
		clientsTablePanel.setBounds(130, 260, 1000, 310);
		clientsTablePanel.setBackground(Color.decode("#071A2B"));
		panel.add(clientsTablePanel);
		clientsTablePanel.setLayout(null);
				DefaultTableModel model = new DefaultTableModel(datos, columnas);
				JTable clientsTable = new JTable(model);
				clientsTable.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 22));
				clientsTable.setRowHeight(30);
				clientsTable.getTableHeader().setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
				clientsTable.getTableHeader().setBackground(Color.decode("#071A2B"));
				clientsTable.getTableHeader().setForeground(Color.decode("#FFFFFF"));
				clientsTable.getColumnModel().getColumn(3).setPreferredWidth(150);
				clientsTable.setDefaultEditor(Object.class,null);
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
		            	RoomsController client = new RoomsController();
						client.deleteRoom();
						//lo de abajo se implementará al dar click en el boton "aceptar"
		                /*if (clientsTable.isEditing()) {
		                	clientsTable.getCellEditor().stopCellEditing();
		                }
		                model.removeRow(row);*/
		            }

		            @Override
		            public void onView(int row) {
		            	RoomsController client = new RoomsController();
						frame.dispose();
						client.consultRoom();
		                System.out.println("View row : " + row);
		            }
		        };
		        clientsTable.getColumn("Acciones").setCellRenderer(new TableActionCellRender());
		        clientsTable.getColumn("Acciones").setCellEditor(new TableActionCellEditor(event));
				JScrollPane scrollPane = new JScrollPane(clientsTable);
				scrollPane.setBounds(0, 50, 1000, 260);
				clientsTablePanel.add(scrollPane);
				
				JLabel lblTableTitle = new JLabel("Clientes");
				lblTableTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
				lblTableTitle.setForeground(Color.decode("#FFFFFF"));
				lblTableTitle.setBounds(450, 10, 200, 40);
				clientsTablePanel.add(lblTableTitle);
				
				JTextField search = new JTextField();
				search.setBounds(130, 200, 800, 50);
				search.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 22));
				panel.add(search);
				search.setColumns(10);
				
				JButton btnSearch = new JButton("Ver");
				btnSearch.setFont(new Font("Inter_18pt Bold", Font.BOLD, 22));
				btnSearch.setBackground(Color.decode("#071A2B"));
				btnSearch.setForeground(Color.decode("#FFFFFF"));
				btnSearch.setBounds(935, 200, 195, 50);
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
		
		
		JLabel lblTitle = new JLabel("Añadir cliente");
		lblTitle.setBounds(200, 42, 350, 82);
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
		
		ImageIcon lblImgOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/userImg.png"));
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
		Date birthdate;
		int phone;// puede ser string: no me acuerdo como estaba en la base de datos
		
		name="david";
		email="david@e.mail";
		phone=1234567890;
		birthdate=Date.valueOf("2025-01-01");
		
		
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
		
		
		JLabel lblTitle = new JLabel("Editar cliente");
		lblTitle.setBounds(200, 42, 350, 82);
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
		lblImg.setBounds(130,150,400,300);
		lblImg.setText(null);
		lblImg.setBorder(BorderFactory.createLineBorder(Color.black));
		ImageIcon lblImgOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/userImg.png"));
		Image lblImgScaledImage = lblImgOriginalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon lblImgScaledIcon = new ImageIcon(lblImgScaledImage);//btnConsult
		lblImg.setHorizontalAlignment(JLabel.CENTER);
		lblImg.setVerticalAlignment(JLabel.CENTER);
		lblImg.setIcon(lblImgScaledIcon);
		panel.add(lblImg);
		
		JButton btnAddImage = new JButton("Cambiar imagen");
		btnAddImage.setBounds(130,450,400,70);
		btnAddImage.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnAddImage.setForeground(Color.decode("#FFFFFF"));
		btnAddImage.setBackground(Color.decode("#071A2B"));
		panel.add(btnAddImage);
		
		btnAddImage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(700, 160, 100, 15);
		lblNewLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel);
		
		JTextField textField = new JTextField(name);
		textField.setColumns(10);
		textField.setBounds(700, 180, 460, 50);
		textField.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(textField);
		
		
		JLabel lblNewLabel_1 = new JLabel("Telefono");
		lblNewLabel_1.setBounds(700, 240, 100, 15);
		lblNewLabel_1.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel_1);
		
		JTextField textField_1 = new JTextField(phone+"");
		textField_1.setColumns(10);
		textField_1.setBounds(700, 260, 460, 50);
		textField_1.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(textField_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(700, 320, 100, 15);
		lblNewLabel_2.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel_2);
		
		JTextField textField_2 = new JTextField(email);
		textField_2.setColumns(10);
		textField_2.setBounds(700, 340, 460, 50);
		textField_2.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(textField_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha de nacimiento");
		lblNewLabel_3.setBounds(700, 400, 200, 15);
		lblNewLabel_3.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel_3);
		
		JTextField textField_3 = new JTextField(birthdate.toString());
		textField_3.setColumns(10);
		textField_3.setBounds(700, 420, 249, 50);
		textField_3.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(textField_3);
		
		JButton btnCreate = new JButton("Guardar");
		btnCreate.setBounds(870,600,300,70);
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
		btnCancel.setBounds(560,600,300,70);
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
		JFrame frame = new JFrame();
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
		
		
		JLabel lblTitle = new JLabel("Detalles del cliente");
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
		lblImg.setBounds(130, 140, 200, 200);
		ImageIcon lblImgOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/userImg.png"));
		Image lblImgScaledImage = lblImgOriginalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon lblImgScaledIcon = new ImageIcon(lblImgScaledImage);//btnConsult
		lblImg.setHorizontalAlignment(JLabel.CENTER);
		lblImg.setVerticalAlignment(JLabel.CENTER);
		lblImg.setIcon(lblImgScaledIcon);
		panel.add(lblImg);
		
		String[] columnNames = {
				"Check-In",
				"Check-Out",
				"Habitación",
				"Tipo"
		};
		
		Object [][] data = {
				{"2025-01-01", "2025-01-02","101","Estandar"},
				{"2025-01-01", "2025-01-02","101","Estandar"},
				{"2025-01-01", "2025-01-02","101","Estandar"},
				{"2025-01-01", "2025-01-02","101","Estandar"},
				
		};
		
		JPanel clientsTablePanel = new JPanel();
		clientsTablePanel.setBounds(130, 350, 1000, 230);
		clientsTablePanel.setBackground(Color.decode("#071A2B"));
		panel.add(clientsTablePanel);
		clientsTablePanel.setLayout(null);
				DefaultTableModel model = new DefaultTableModel(data, columnNames);
				JTable clientsTable = new JTable(model);
				clientsTable.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 22));
				clientsTable.setRowHeight(30);
				clientsTable.getTableHeader().setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
				clientsTable.getTableHeader().setBackground(Color.decode("#071A2B"));
				clientsTable.getTableHeader().setForeground(Color.decode("#FFFFFF"));
				clientsTable.setDefaultEditor(Object.class,null);
				
		        
				JScrollPane scrollPane = new JScrollPane(clientsTable);
				scrollPane.setBounds(0, 50, 1000, 230);
				clientsTablePanel.add(scrollPane);
				
				JLabel lblTableTitle = new JLabel("Historial de rentas");
				lblTableTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
				lblTableTitle.setForeground(Color.decode("#FFFFFF"));
				lblTableTitle.setBounds(430, 10, 400, 40);
				clientsTablePanel.add(lblTableTitle);
		
		JButton btnCancel = new JButton("Descargar .pdf");
		btnCancel.setBounds(130,600,1000,70);
		btnCancel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnCancel.setForeground(Color.decode("#FFFFFF"));
		btnCancel.setBackground(Color.decode("#0E651B"));
		panel.add(btnCancel);
		
		String name,email,phoneNumber;
		int id;
		name="Axdiael Trinidad Cardenas";
		email="ax@diael.tc";
		id=01;
		phoneNumber = "6131234567";
		JLabel lblNewLabel = new JLabel(name);
		lblNewLabel.setBounds(400, 160, 606, 44);
		lblNewLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setBounds(400, 214, 62, 27);
		lblNewLabel_1.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 26));
		panel.add(lblNewLabel_1);
		JLabel lblNewLabel_1_3 = new JLabel(id+"");
		lblNewLabel_1_3.setBounds(472, 214, 62, 27);
		lblNewLabel_1_3.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 26));
		panel.add(lblNewLabel_1_3);
		
		
		JLabel lblNewLabel_1_1 = new JLabel("Email:");
		lblNewLabel_1_1.setBounds(400, 251, 150, 27);
		lblNewLabel_1_1.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 26));
		panel.add(lblNewLabel_1_1);
		JLabel lblNewLabel_1_4 = new JLabel(email);
		lblNewLabel_1_4.setBounds(500, 251, 450, 27);
		lblNewLabel_1_4.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 26));
		panel.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_2 = new JLabel("Numero de telefono:");
		lblNewLabel_1_2.setBounds(400, 288, 400, 27);
		lblNewLabel_1_2.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 26));
		panel.add(lblNewLabel_1_2);
		JLabel lblNewLabel_1_5 = new JLabel(phoneNumber+"");
		lblNewLabel_1_5.setBounds(670, 288, 400, 27);
		lblNewLabel_1_5.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 26));
		panel.add(lblNewLabel_1_5);
		
		
		
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomsController download = new RoomsController();
				download.successDownload();
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
