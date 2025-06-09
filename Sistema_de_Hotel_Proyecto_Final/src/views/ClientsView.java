package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import models.Client;
import models.ClientsModel;
import models.Room;
import models.RoomType;

public class ClientsView {

	private JFrame frame;
	private ClientsModel functions;
	byte[] imageBytes;
	
	public ClientsView() {
		functions = new ClientsModel();
	}
	
	public void clients() throws SQLException {
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

		ClientsModel cm = new ClientsModel();
		List<Client> clients = new ArrayList<>();
		
		if(cm.getAvailableRoomType() != null) {
			clients = cm.getAvailableRoomType();			
		}
		else {
			System.out.println("No hay clientes");
		}
		
		String[] columnNames = {"ID", "Cliente", "Email", "Telefono", "Acciones"};
		Object[][] data = new Object[clients.size()][5];

		for (int i = 0; i < clients.size(); i++) {
		    Client   c = clients.get(i);

		    data[i][0] = c.getId_client();

		    data[i][1] = c.getClient_name();

		    data[i][2] = c.getEmail();

		    data[i][3] = c.getPhone_number();
		   
		    data[i][4] = "Acciones";
		}
		
		
		JButton btnCreate = new JButton("Crear");
		btnCreate.setBounds(831,603,300,70);
		btnCreate.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnCreate.setForeground(Color.decode("#FFFFFF"));
		btnCreate.setBackground(Color.decode("#071A2B"));
		panel.add(btnCreate);
		
		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ClientsController client = new ClientsController();
				frame.dispose();
				client.createClient();
			}
		});
		
		
		JPanel header = new JPanel();
		header.setBounds(0, 0, 1266, 130);
		header.setBackground(Color.decode("#071A2B"));
		panel.add(header);
		header.setLayout(null);
		
		
		JLabel lblTitle = new JLabel("Clientes");
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
		
		JPanel clientsTablePanel = new JPanel();
		clientsTablePanel.setBounds(130, 260, 1000, 310);
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
				clientsTable.getColumnModel().getColumn(4).setPreferredWidth(150);
				clientsTable.setDefaultEditor(Object.class,null);
				TableActionEvent event = new TableActionEvent() {
		            @Override
		            public void onEdit(int row) {
		            	ClientsController client = new ClientsController();
						frame.dispose();
						client.editClient();
		                System.out.println("Edit row : " + row);
		            }

		            @Override
		            public void onDelete(int row) {
		            	ClientsController client = new ClientsController();
						client.deleteClient();
						//lo de abajo se implementará al dar click en el boton "aceptar"
		                /*if (clientsTable.isEditing()) {
		                	clientsTable.getCellEditor().stopCellEditing();
		                }
		                model.removeRow(row);*/
		            }

		            @Override
		            public void onView(int row) {
		            	ClientsController client = new ClientsController();
						frame.dispose();
						client.consultClient();
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
	
	public void createClient() {
		Client c = new Client();
		
		frame = new JFrame();
		frame.setTitle("Hotel Ancla de Paz");
		frame.setResizable(false);
		frame.setBounds(0, 0, 1280, 720);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setBackground(Color.decode("#FFFCF7"));
		mainPanel.setLayout(null);

		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(0, 0, 1266, 130);
		headerPanel.setBackground(Color.decode("#071A2B"));
		mainPanel.add(headerPanel);
		headerPanel.setLayout(null);

		JLabel titleLabel = new JLabel("Añadir cliente");
		titleLabel.setBounds(200, 42, 350, 82);
		headerPanel.add(titleLabel);
		titleLabel.setFont(new Font("Inter_18pt Bold", Font.BOLD, 44));
		titleLabel.setForeground(Color.decode("#FFFFFF"));
		titleLabel.setOpaque(true);
		titleLabel.setBackground(null);

		JButton homeButton = new JButton("");
		homeButton.setBounds(130, 60, 56, 56);
		headerPanel.add(homeButton);
		homeButton.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		homeButton.setForeground(Color.decode("#FFFFFF"));
		homeButton.setBorderPainted(false);
		homeButton.setBackground(null);
		ImageIcon homeButtonOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/btnHome.png"));
		Image homeButtonScaledImage = homeButtonOriginalIcon.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH);
		ImageIcon homeButtonScaledIcon = new ImageIcon(homeButtonScaledImage);
		homeButton.setIcon(homeButtonScaledIcon);

		homeButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        ClientsController home = new ClientsController();
		        frame.dispose();
		        home.clients();
		    }
		});

		JLabel userImageLabel = new JLabel();
		userImageLabel.setBounds(130, 150, 400, 300);
		userImageLabel.setText(null);
		userImageLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		ImageIcon userImageOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/userImg.png"));
		Image userImageScaled = userImageOriginalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon userImageScaledIcon = new ImageIcon(userImageScaled);
		userImageLabel.setHorizontalAlignment(JLabel.CENTER);
		userImageLabel.setVerticalAlignment(JLabel.CENTER);
		userImageLabel.setIcon(userImageScaledIcon);

		JButton addImageButton = new JButton("Agregar imagen");
		addImageButton.setBounds(130, 450, 400, 70);
		addImageButton.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		addImageButton.setForeground(Color.decode("#FFFFFF"));
		addImageButton.setBackground(Color.decode("#071A2B"));
		mainPanel.add(addImageButton);

		addImageButton.addActionListener(new ActionListener() {
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
		            } catch (IOException e1) {
		                e1.printStackTrace();
		            }
		        }
		    }
		});
		mainPanel.add(addImageButton);

		JLabel nameLabel = new JLabel("Nombre");
		nameLabel.setBounds(700, 160, 100, 15);
		nameLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		mainPanel.add(nameLabel);

		JTextField nameTextField = new JTextField();
		nameTextField.setBounds(700, 180, 460, 50);
		nameTextField.setColumns(10);
		mainPanel.add(nameTextField);

		JLabel phoneLabel = new JLabel("Telefono");
		phoneLabel.setBounds(700, 240, 100, 15);
		phoneLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		mainPanel.add(phoneLabel);

		JTextField phoneTextField = new JTextField();
		phoneTextField.setBounds(700, 260, 460, 50);
		phoneTextField.setColumns(10);
		mainPanel.add(phoneTextField);

		JLabel emailLabel = new JLabel("Email");
		emailLabel.setBounds(700, 320, 100, 15);
		emailLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		mainPanel.add(emailLabel);

		JTextField emailTextField = new JTextField();
		emailTextField.setBounds(700, 340, 460, 50);
		emailTextField.setColumns(10);
		mainPanel.add(emailTextField);

		JLabel birthDateLabel = new JLabel("Fecha de nacimiento");
		birthDateLabel.setBounds(700, 400, 200, 15);
		birthDateLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		mainPanel.add(birthDateLabel);

		JTextField birthDateTextField = new JTextField();
		birthDateTextField.setBounds(700, 420, 249, 50);
		birthDateTextField.setColumns(10);
		mainPanel.add(birthDateTextField);

		JButton saveButton = new JButton("Guardar");
		saveButton.setBounds(950, 540, 220, 70);
		saveButton.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		saveButton.setForeground(Color.decode("#FFFFFF"));
		saveButton.setBackground(Color.decode("#071A2B"));
		mainPanel.add(saveButton);

		saveButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        ClientsController home = new ClientsController();
		        frame.dispose();
		        home.clients();
		    }
		});

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setBounds(700, 540, 220, 70);
		cancelButton.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		cancelButton.setForeground(Color.decode("#FFFFFF"));
		cancelButton.setBackground(new Color(153, 89, 45));
		mainPanel.add(cancelButton);

		cancelButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        ClientsController home = new ClientsController();
		        frame.dispose();
		        home.clients();
		    }
		});

	}
	
	public void editClient() {
		String name = "david";
		String email = "david@e.mail";
		Date birthdate = Date.valueOf("2025-01-01");
		int phone = 1234567890;

		frame = new JFrame();
		frame.setTitle("Hotel Ancla de Paz");
		frame.setResizable(false);
		frame.setBounds(0, 0, 1280, 720);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setBackground(Color.decode("#FFFCF7"));
		mainPanel.setLayout(null);

		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(0, 0, 1266, 130);
		headerPanel.setBackground(Color.decode("#071A2B"));
		mainPanel.add(headerPanel);
		headerPanel.setLayout(null);

		JLabel titleLabel = new JLabel("Editar cliente");
		titleLabel.setBounds(200, 42, 350, 82);
		titleLabel.setFont(new Font("Inter_18pt Bold", Font.BOLD, 44));
		titleLabel.setForeground(Color.decode("#FFFFFF"));
		titleLabel.setOpaque(false);
		headerPanel.add(titleLabel);

		JButton btnHome = new JButton("");
		btnHome.setBounds(130, 60, 56, 56);
		headerPanel.add(btnHome);
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
				ClientsController home = new ClientsController();
				frame.dispose();
				home.clients();
			}
					
		});

		JLabel userImageLabel = new JLabel();
		userImageLabel.setBounds(130, 150, 400, 300);
		userImageLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		userImageLabel.setHorizontalAlignment(JLabel.CENTER);
		userImageLabel.setVerticalAlignment(JLabel.CENTER);
		ImageIcon userIcon = new ImageIcon(AuthView.class.getResource("/images/userImg.png"));
		Image scaledUserIcon = userIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		userImageLabel.setIcon(new ImageIcon(scaledUserIcon));
		mainPanel.add(userImageLabel);

		JButton btnAddImage = new JButton("Cambiar imagen");
		btnAddImage.setBounds(130,450,400,70);
		btnAddImage.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnAddImage.setForeground(Color.decode("#FFFFFF"));
		btnAddImage.setBackground(Color.decode("#071A2B"));
		mainPanel.add(btnAddImage);
				
		btnAddImage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
						
			}
					
		});

		JLabel nameLabel = new JLabel("Nombre");
		nameLabel.setBounds(700, 160, 100, 15);
		nameLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		mainPanel.add(nameLabel);

		JTextField nameTextField = new JTextField(name);
		nameTextField.setBounds(700, 180, 460, 50);
		nameTextField.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		mainPanel.add(nameTextField);

		JLabel phoneLabel = new JLabel("Teléfono");
		phoneLabel.setBounds(700, 240, 100, 15);
		phoneLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		mainPanel.add(phoneLabel);

		JTextField phoneTextField = new JTextField(String.valueOf(phone));
		phoneTextField.setBounds(700, 260, 460, 50);
		phoneTextField.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		mainPanel.add(phoneTextField);

		JLabel emailLabel = new JLabel("Email");
		emailLabel.setBounds(700, 320, 100, 15);
		emailLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		mainPanel.add(emailLabel);

		JTextField emailTextField = new JTextField(email);
		emailTextField.setBounds(700, 340, 460, 50);
		emailTextField.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		mainPanel.add(emailTextField);

		JLabel birthdateLabel = new JLabel("Fecha de nacimiento");
		birthdateLabel.setBounds(700, 400, 200, 15);
		birthdateLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		mainPanel.add(birthdateLabel);

		JTextField birthdateTextField = new JTextField(birthdate.toString());
		birthdateTextField.setBounds(700, 420, 249, 50);
		birthdateTextField.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		mainPanel.add(birthdateTextField);

		JButton btnCreate = new JButton("Guardar");
		btnCreate.setBounds(950,540,220,70);
		btnCreate.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnCreate.setForeground(Color.decode("#FFFFFF"));
		btnCreate.setBackground(Color.decode("#071A2B"));
		mainPanel.add(btnCreate);
				
		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ClientsController home = new ClientsController();
				frame.dispose();
				home.clients();
			}
					
		});
				
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(700,540,220,70);
		btnCancel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnCancel.setForeground(Color.decode("#FFFFFF"));
		btnCancel.setBackground(new Color(153, 89, 45));
		mainPanel.add(btnCancel);
				
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ClientsController home = new ClientsController();
				frame.dispose();
				home.clients();
			}
					
		});

	}
	
	public void consultClient() {
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
				ClientsController home = new ClientsController();
				frame.dispose();
				home.clients();
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
		
		JLabel lblID = new JLabel("ID:");
		lblID.setBounds(400, 214, 62, 27);
		lblID.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 26));
		panel.add(lblID);
		
		JLabel lblID2 = new JLabel(id+"");
		lblID2.setBounds(472, 214, 62, 27);
		lblID2.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 26));
		panel.add(lblID2);
		
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(400, 251, 150, 27);
		lblEmail.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 26));
		panel.add(lblEmail);
		
		JLabel lblEmail2 = new JLabel(email);
		lblEmail2.setBounds(500, 251, 450, 27);
		lblEmail2.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 26));
		panel.add(lblEmail2);
		
		JLabel lblPhoneNumber = new JLabel("Numero de telefono:");
		lblPhoneNumber.setBounds(400, 288, 400, 27);
		lblPhoneNumber.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 26));
		panel.add(lblPhoneNumber);
		
		JLabel lblPhoneNumber2 = new JLabel(phoneNumber+"");
		lblPhoneNumber2.setBounds(670, 288, 400, 27);
		lblPhoneNumber2.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 26));
		panel.add(lblPhoneNumber2);
		
		
		
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ClientsController download = new ClientsController();
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
				ClientsController client = new ClientsController();
				frame.dispose();
				client.successDelete();
			}
			
		});
		
		JButton deny = new JButton("Cancelar");
		deny.setBounds(50,350,300,70);
		deny.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		deny.setForeground(Color.decode("#FFFFFF"));
		deny.setBackground(Color.decode("#99592D"));
		panel.add(deny);
		
		deny.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ClientsController client = new ClientsController();
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
				frame.dispose();
			}
			
		});
		
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
				frame.dispose();
			}
			
		});
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
				frame.dispose();
			}
			
		});
	}
}