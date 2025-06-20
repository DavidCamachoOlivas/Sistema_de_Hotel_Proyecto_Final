package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import buttonCells.TableActionCellEditor;
import buttonCells.TableActionCellRender;
import buttonCells.TableActionEvent;
import controllers.AuthController;
import controllers.ClientsController;
import controllers.HomeController;
import controllers.PopUpsController;
import controllers.RentalsController;
import controllers.RoomTypesController;
import controllers.RoomsController;
import models.Client;
import models.ClientsModel;
import models.Rental;
import models.RentalsModel;
import models.Room;
import models.RoomType;
import models.RoomTypesModel;
import models.RoomsModel;

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
		
		if(cm.getAvailableClient() != null) {
			clients = cm.getAvailableClient();			
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
			            	
						 	int idClient = Integer.parseInt(clientsTable.getValueAt(row, 0).toString());
							System.out.println("Editando cliente con ID: " + idClient);
							ClientsController rc = new ClientsController();
							ClientsModel cm = new ClientsModel();
							
							frame.dispose();
							try {
								rc.editClient(cm.getClientById(idClient));
							}
							catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			            }


					  @Override
			            public void onDelete(int row) {
			            	 int idClient = Integer.parseInt(clientsTable.getValueAt(row, 0).toString());
						        System.out.println("Eliminando cliente con ID: " + idClient);
						        ClientsController rc = new ClientsController();
						        ClientsModel cm = new ClientsModel();
						        
						        try {
									rc.deleteClient(cm.getClientById(idClient),frame);
								}
						        catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			            }
		            
		            @Override
		            public void onView(int row) {
		            	int idClient = Integer.parseInt(clientsTable.getValueAt(row, 0).toString());
				        System.out.println("Consultando cliente con ID: " + idClient);
				        ClientsController rc = new ClientsController();
				        ClientsModel cm = new ClientsModel();
				        
				        frame.dispose();
				        try {
							rc.consultClient(cm.getClientById(idClient));
						}
				        catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
		List<Client> allClients = new ArrayList<>(clients);
    	clients = cm.getAvailableClient();
		JButton btnSearch = new JButton("Ver");
		btnSearch.setFont(new Font("Inter_18pt Bold", Font.BOLD, 22));
		btnSearch.setBackground(Color.decode("#071A2B"));
		btnSearch.setForeground(Color.decode("#FFFFFF"));
		btnSearch.setBounds(935, 200, 195, 50);
		panel.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		        String keyword = search.getText().trim().toLowerCase();

		        if (keyword.isEmpty()) {
		            model.setRowCount(0);
		            for (Object[] row : toData(allClients)) {
		                model.addRow(row);
		            }
		            return;
		        }
		        
		        List<ClientMatch> matches = new ArrayList<>();

		        for (Client c : allClients) {
		            int score = calculateMatchScore(c, keyword);
		            if (score > 0) {
		                matches.add(new ClientMatch(c, score));
		            }
		        }

		        matches.sort((a, b) -> Integer.compare(b.score, a.score));
		        List<Client> filtered = matches.stream().map(m -> m.client).collect(Collectors.toList());

		        model.setRowCount(0);
		        for (Object[] row : toData(filtered)) {
		            model.addRow(row);
		        }
		    }

		    private int calculateMatchScore(Client c, String keyword) {
		        int score = 0;
		        String name = c.getClient_name().toLowerCase();
		        String email = c.getEmail().toLowerCase();
		        String phone = c.getPhone_number().toLowerCase();

		        if (name.equals(keyword)) score += 100;
		        else if (name.startsWith(keyword)) score += 80;
		        else if (name.contains(keyword)) score += 60;

		        if (email.contains(keyword)) score += 40;
		        if (phone.contains(keyword)) score += 40;

		        return score;
		    }

		    private Object[][] toData(List<Client> list) {
		        Object[][] data = new Object[list.size()][5];
		        for (int i = 0; i < list.size(); i++) {
		            Client c = list.get(i);
		            data[i][0] = c.getId_client();
		            data[i][1] = c.getClient_name();
		            data[i][2] = c.getEmail();
		            data[i][3] = c.getPhone_number();
		            data[i][4] = "Acciones";
		        }
		        return data;
		    }

		    class ClientMatch {
		        final Client client;
		        final int score;

		        ClientMatch(Client client, int score) {
		            this.client = client;
		            this.score = score;
		        }
		    }
		});
		
		
		
		
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
	    mainPanel.add(userImageLabel);
	    userImageLabel.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	JFileChooser fileChooser = new JFileChooser();
	    		fileChooser.setDialogTitle("Selecciona una imagen");
	    		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    		
	    		int resultado = fileChooser.showOpenDialog(null);
	    		if (resultado == JFileChooser.APPROVE_OPTION) {
	    			File imagenSeleccionada = fileChooser.getSelectedFile();
	    			
	    			try (FileInputStream fis = new FileInputStream(imagenSeleccionada)) {
	    				imageBytes = fis.readAllBytes();
	    				System.out.println("Se leyo la imagen");
	    				ImageIcon userImageSeleccionarIcon = new ImageIcon(imageBytes);
	    				Image userImageScaled = userImageSeleccionarIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	    				ImageIcon userImageScaledIcon = new ImageIcon(userImageScaled);
	    				userImageLabel.setIcon(userImageScaledIcon);
	    				
	    			} catch (IOException e1) {
	    				e1.printStackTrace();
	    			}
	    		}
		    }
		});
	    
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
	    				System.out.println("Se leyo la imagen");
	    				ImageIcon userImageSeleccionarIcon = new ImageIcon(imageBytes);
	    				Image userImageScaled = userImageSeleccionarIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	    				ImageIcon userImageScaledIcon = new ImageIcon(userImageScaled);
	    				userImageLabel.setIcon(userImageScaledIcon);
	    				
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
	    nameTextField.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            char c = e.getKeyChar();
	            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
	                e.consume(); 
	            }
	        }
	    });
	    
	    JLabel phoneLabel = new JLabel("Telefono");
	    phoneLabel.setBounds(700, 240, 100, 15);
	    phoneLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
	    mainPanel.add(phoneLabel);
	    
	    JTextField phoneTextField = new JTextField();
	    phoneTextField.setBounds(700, 260, 460, 50);
	    phoneTextField.setColumns(10);
	    mainPanel.add(phoneTextField);
	    phoneTextField.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            char c = e.getKeyChar();
	            if (!Character.isDigit(c)) {
	                e.consume(); 
	            }
	        }
	    });
	    
	    JLabel emailLabel = new JLabel("Email");
	    emailLabel.setBounds(700, 320, 100, 15);
	    emailLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
	    mainPanel.add(emailLabel);
	    
	    JTextField emailTextField = new JTextField();
	    emailTextField.setBounds(700, 340, 460, 50);
	    emailTextField.setColumns(10);
	    mainPanel.add(emailTextField);
	    emailTextField.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            char c = e.getKeyChar();
	            String allowed = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@._";
	            if (allowed.indexOf(c) == -1) {
	                e.consume(); 
	            }
	        }
	    });
	    
	    JLabel birthDateLabel = new JLabel("Fecha de nacimiento");
	    birthDateLabel.setBounds(700, 400, 200, 15);
	    birthDateLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
	    mainPanel.add(birthDateLabel);
	    
	    
	    JDateChooser birthDateCalendar = new JDateChooser();
	    birthDateCalendar.setBounds(700, 420, 460, 50);
	    mainPanel.add(birthDateCalendar);
	   
	    JButton saveButton = new JButton("Guardar");
	    saveButton.setBounds(950, 540, 220, 70);
	    saveButton.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
	    saveButton.setForeground(Color.decode("#FFFFFF"));
	    saveButton.setBackground(Color.decode("#071A2B"));
	    mainPanel.add(saveButton);
	    
	    saveButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            Border defaultBorder = nameTextField.getBorder();
	            Border redBorder = BorderFactory.createLineBorder(Color.RED);

	            boolean valid = true;

	            if (nameTextField.getText().trim().isEmpty()) {
	                nameTextField.setBorder(redBorder);
	                valid = false;
	            } else {
	                nameTextField.setBorder(defaultBorder);
	            }

	            if (phoneTextField.getText().trim().isEmpty()) {
	                phoneTextField.setBorder(redBorder);
	                valid = false;
	            } else {
	                phoneTextField.setBorder(defaultBorder);
	            }

	            if (emailTextField.getText().trim().isEmpty()) {
	                emailTextField.setBorder(redBorder);
	                valid = false;
	            } else {
	                emailTextField.setBorder(defaultBorder);
	            }

	            java.util.Date fechaUtil = birthDateCalendar.getDate();
	            java.sql.Date fechaNacimiento = null;
	            if (fechaUtil == null) {
	                birthDateCalendar.setBorder(redBorder);
	                valid = false;
	            } else {
	                birthDateCalendar.setBorder(defaultBorder);
	                fechaNacimiento = new java.sql.Date(fechaUtil.getTime());
	            }

	            if (!valid) {
	                JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos obligatorios.", "Campos inválidos", JOptionPane.WARNING_MESSAGE);
	                return;
	            }

	            Client nuevaCliente = new Client();
	            nuevaCliente.setClient_name(nameTextField.getText().trim());
	            nuevaCliente.setPhone_number(phoneTextField.getText().trim());
	            nuevaCliente.setEmail(emailTextField.getText().trim());
	            nuevaCliente.setBirth_date(fechaNacimiento);
	            nuevaCliente.setProfile_picture(imageBytes);

	            ClientsModel cm = new ClientsModel();
	            ClientsController home = new ClientsController();

	            try {
	                cm.createClient(nuevaCliente);
	                frame.dispose();
	                home.clients();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(frame, "Error al guardar cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	            }
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
	frame.revalidate();
	frame.repaint();
}
	public void createClient2() {
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
		    	RentalsController rental = new RentalsController();
				frame.dispose();
				rental.createRental();
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
	    mainPanel.add(userImageLabel);
	    
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
	    				System.out.println("Se leyo la imagen");
	    				ImageIcon userImageSeleccionarIcon = new ImageIcon(imageBytes);
	    				Image userImageScaled = userImageSeleccionarIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	    				ImageIcon userImageScaledIcon = new ImageIcon(userImageScaled);
	    				userImageLabel.setIcon(userImageScaledIcon);
	    				
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
	    
	    
	    JDateChooser birthDateCalendar = new JDateChooser();
	    birthDateCalendar.setBounds(700, 400, 460, 50);
	    mainPanel.add(birthDateCalendar);
	   
	    JButton saveButton = new JButton("Guardar");
	    saveButton.setBounds(950, 540, 220, 70);
	    saveButton.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
	    saveButton.setForeground(Color.decode("#FFFFFF"));
	    saveButton.setBackground(Color.decode("#071A2B"));
	    mainPanel.add(saveButton);
	    
	    saveButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	ClientsController home = new ClientsController();
	    	ClientsModel cm = new ClientsModel();
	    	Client nuevaCliente = new Client();
	    	java.util.Date fechaUtil = birthDateCalendar.getDate();
	        java.sql.Date fechaNacimiento = null;
	        
	        if (fechaUtil != null) {
	             fechaNacimiento = new java.sql.Date(fechaUtil.getTime());
	        }
	        
	    	nuevaCliente.setClient_name(nameTextField.getText());
	    	nuevaCliente.setPhone_number(phoneTextField.getText());
	    	nuevaCliente.setEmail(emailTextField.getText());
	    	nuevaCliente.setBirth_date(fechaNacimiento);
	    	nuevaCliente.setProfile_picture(imageBytes);
	    	
	    	try {
				cm.createClient(nuevaCliente);
				frame.dispose();
		        home.clients();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	
	        
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
	    	RentalsController rental = new RentalsController();
			frame.dispose();
			rental.createRental();
	    }
	});
	frame.revalidate();
	frame.repaint();
}
	
	public void editClient(Client c) {
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
		    imageBytes = c.getProfile_picture();
		    if (imageBytes != null && imageBytes.length > 0) {
		        ImageIcon clientIcon = new ImageIcon(c.getProfile_picture());
		        Image scaledImage = clientIcon.getImage().getScaledInstance(
		            400, 300, Image.SCALE_SMOOTH);
		        userImageLabel.setIcon(new ImageIcon(scaledImage));
		    } else {
		        System.out.println("No hay imagen");
		    }
		    mainPanel.add(userImageLabel);

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
		            	System.out.println("Se leyo la imagen");
		            	ImageIcon userImageSeleccionarIcon = new ImageIcon(imageBytes);
		            	Image userImageScaled = userImageSeleccionarIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		            	ImageIcon userImageScaledIcon = new ImageIcon(userImageScaled);
		            	userImageLabel.setIcon(userImageScaledIcon);
		            	
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
		nameTextField.setText(c.getClient_name());
		nameTextField.setColumns(10);
		mainPanel.add(nameTextField);

		JLabel phoneLabel = new JLabel("Telefono");
		phoneLabel.setBounds(700, 240, 100, 15);
		phoneLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		mainPanel.add(phoneLabel);

		JTextField phoneTextField = new JTextField();
		phoneTextField.setBounds(700, 260, 460, 50);
		phoneTextField.setText(c.getPhone_number());
		phoneTextField.setColumns(10);
		mainPanel.add(phoneTextField);

		JLabel emailLabel = new JLabel("Email");
		emailLabel.setBounds(700, 320, 100, 15);
		emailLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		mainPanel.add(emailLabel);

		JTextField emailTextField = new JTextField();
		emailTextField.setBounds(700, 340, 460, 50);
		emailTextField.setText(c.getEmail());
		emailTextField.setColumns(10);
		mainPanel.add(emailTextField);

		JLabel birthDateLabel = new JLabel("Fecha de nacimiento");
		birthDateLabel.setBounds(700, 350, 200, 15);
		birthDateLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		mainPanel.add(birthDateLabel);

		JDateChooser birthDateCalendar = new JDateChooser();
		birthDateCalendar.setDate(c.getBirth_date());
		birthDateCalendar.setBounds(700, 400, 460, 50);
		mainPanel.add(birthDateCalendar);

		JButton saveButton = new JButton("Guardar");
		saveButton.setBounds(950, 540, 220, 70);
		saveButton.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		saveButton.setForeground(Color.decode("#FFFFFF"));
		saveButton.setBackground(Color.decode("#071A2B"));
		mainPanel.add(saveButton);

		saveButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            Border defaultBorder = nameTextField.getBorder();
	            Border redBorder = BorderFactory.createLineBorder(Color.RED);

	            boolean valid = true;

	            if (nameTextField.getText().trim().isEmpty()) {
	                nameTextField.setBorder(redBorder);
	                valid = false;
	            } else {
	                nameTextField.setBorder(defaultBorder);
	            }

	            if (phoneTextField.getText().trim().isEmpty()) {
	                phoneTextField.setBorder(redBorder);
	                valid = false;
	            } else {
	                phoneTextField.setBorder(defaultBorder);
	            }

	            if (emailTextField.getText().trim().isEmpty()) {
	                emailTextField.setBorder(redBorder);
	                valid = false;
	            } else {
	                emailTextField.setBorder(defaultBorder);
	            }

	            java.util.Date fechaUtil = birthDateCalendar.getDate();
	            java.sql.Date fechaNacimiento = null;
	            if (fechaUtil == null) {
	                birthDateCalendar.setBorder(redBorder);
	                valid = false;
	            } else {
	                birthDateCalendar.setBorder(defaultBorder);
	                fechaNacimiento = new java.sql.Date(fechaUtil.getTime());
	            }

	            if (!valid) {
	                JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos obligatorios.", "Campos inválidos", JOptionPane.WARNING_MESSAGE);
	                return;
	            }

	            Client nuevaCliente = new Client();
	            nuevaCliente.setClient_name(nameTextField.getText().trim());
	            nuevaCliente.setPhone_number(phoneTextField.getText().trim());
	            nuevaCliente.setEmail(emailTextField.getText().trim());
	            nuevaCliente.setBirth_date(fechaNacimiento);
	            nuevaCliente.setProfile_picture(imageBytes);

	            ClientsModel cm = new ClientsModel();
	            ClientsController home = new ClientsController();

	            try {
	                cm.createClient(nuevaCliente);
	                frame.dispose();
	                home.clients();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(frame, "Error al guardar cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	            }
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
		frame.revalidate();
		frame.repaint();
	}
	
	public void consultClient(Client c) throws SQLException {
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
		header.setBounds(0, 0, 1280, 130);
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
		ImageIcon lblImgOriginalIcon = new ImageIcon(c.getProfile_picture());
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
		
		List<Rental> rentals = new ArrayList<>();
	    RentalsModel rm = new RentalsModel();
	    RoomsModel roomModel = new RoomsModel();
	    RoomTypesModel roomTypeModel = new RoomTypesModel();
	    
	    try {
			rentals = rm.getAvailableRental();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		    
	    Rental  rental = new Rental();

	    Object[][] data = new Object[rentals.size()][4];

		for (int i = 0; i < rentals.size(); i++) {
			rental = rentals.get(i);
			
			data[i][0] = rental.getCheck_in();

		    data[i][1] = rental.getCheck_out();

		    data[i][2] = roomModel.getRoomById(rental.getId_room()).getNum_room();
		    
		    data[i][3] = roomTypeModel.getRoomTypeById(roomModel.getRoomById(rental.getId_room()).getId_room_type()).getRoom_type();
		}
		
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

		 JButton btnPDF = new JButton("Descargar .pdf");
		 btnPDF.setBounds(130, 580, 1000, 70);
		 btnPDF.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		 btnPDF.setForeground(Color.decode("#FFFFFF"));
		 btnPDF.setBackground(Color.decode("#071A2B"));
		 panel.add(btnPDF);
		
		 btnPDF.addActionListener(new ActionListener() {

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
		            rtm.exportarTablaPDF(clientsTable, url);
		        }
		        else{
		        	System.out.println("ruta no valida");
		        }
				
			}
			
		});
		panel.add(btnPDF);
		
		String name,email,phoneNumber;
		int id;
		
		name = c.getClient_name();
		email = c.getEmail();
		id = c.getId_client();
		phoneNumber = c.getPhone_number();
		
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
		
		frame.revalidate();
		frame.repaint();
	}
	
	public void deleteConfirm(Client c,JFrame mainFrame) {
		JFrame confirmFrame = new JFrame();

		confirmFrame.setSize(700, 500);
		confirmFrame.setLocationRelativeTo(null);
		confirmFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		confirmFrame.setVisible(true);
		
		JPanel panel = new JPanel();
		confirmFrame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setBackground(Color.decode("#FFFCF7"));//FBF3E6
		panel.setLayout(null);
		
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
		
		JButton accept = new JButton("Aceptar");
		accept.setBounds(380,350,270,70);
		accept.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		accept.setForeground(Color.decode("#FFFFFF"));
		accept.setBackground(Color.decode("#071A2B"));
		panel.add(accept);
		
		accept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					ClientsController client = new ClientsController();
					ClientsModel cm = new ClientsModel();
					
					cm.deleteClient(c);
					if(cm.getClientById(c.getId_client()) == null) {
						confirmFrame.dispose();
						
						new PopUpsController().successDelete();
						mainFrame.dispose();
						new ClientsController().clients();
					}
					else {
						client.errorDelete();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		JButton deny = new JButton("Cancelar");
		deny.setBounds(50,350,270,70);
		deny.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		deny.setForeground(Color.decode("#FFFFFF"));
		deny.setBackground(new Color(153, 89, 45));
		panel.add(deny);
		
		deny.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				confirmFrame.dispose();
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