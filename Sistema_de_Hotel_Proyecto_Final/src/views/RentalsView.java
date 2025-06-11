package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import buttonCells.TableActionCellEditor;
import buttonCells.TableActionCellEditor2;
import buttonCells.TableActionCellRender;
import buttonCells.TableActionCellRender2;
import buttonCells.TableActionEvent;
import buttonCells.TableActionEvent2;
import controllers.ClientsController;
import controllers.HomeController;
import controllers.RentalsController;
import controllers.RoomTypesController;
import controllers.RoomsController;
import models.Client;
import models.ClientsModel;
import models.Rental;
import models.RentalsModel;
import models.Room;
import models.RoomImage;
import models.RoomType;
import models.RoomTypesModel;
import models.RoomsModel;
import models.Tariff;
import models.TariffsModel;

public class RentalsView {

	private JFrame frame;
	
	private RentalsModel functions = new RentalsModel();
	RoomTypesModel rtm = new RoomTypesModel();
	RoomsModel rm = new RoomsModel();
	TariffsModel tm = new TariffsModel();
	RoomType rt;
	Room r;
	Client c;
    Tariff t;
    float precio;
    float total;
    String nombre;
    JLabel pricePerNight_label;
    JLabel totalPrecio_label;
    java.sql.Date sqlCheckIn;
    java.sql.Date sqlCheckOut;
    long diffMillis;
    long diffDays;

	public void rentals() throws SQLException {
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
		
		JLabel lblNewLabel = new JLabel("Filtros de búsqueda");
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
		
		
		List<Rental> rentals = functions.getAvailableRental();
		
		String[] columnNames = {
				"ID renta",
				"ID cliente",
				"ID room",
				"Check-in",
				"Check-out",
				"Total",
				"Acciones"
		};
		Object[][] data = new Object[rentals.size()][7];

		for (int i = 0; i < rentals.size(); i++) {
		   
			if(rentals.get(i) != null) {
				Rental   r = rentals.get(i);

			    data[i][0] = r.getId_rental();
			    
			    data[i][1] = r.getId_client();
			    
			    data[i][2] = r.getId_room();
			    		    
			    data[i][3] = r.getCheck_in();
			    
			    data[i][4] = r.getCheck_out();
			    
			    data[i][5] = r.getDias_totales();
			    
			    data[i][6] = "Acciones";
			}
		}
		// Reemplaza esta sección en tu método rentals() donde creas la tabla:
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(100, 403, 1100, 270);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable RentalTable = new JTable(model);
		RentalTable.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 22));
		RentalTable.setRowHeight(30);
		RentalTable.getTableHeader().setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
		RentalTable.getTableHeader().setBackground(Color.decode("#071A2B"));
		RentalTable.getTableHeader().setForeground(Color.decode("#FFFFFF"));
		RentalTable.getColumnModel().getColumn(6).setPreferredWidth(150);
		RentalTable.setDefaultEditor(Object.class,null);

		// AQUÍ ESTÁ LA CORRECCIÓN:
		TableActionEvent2 event = new TableActionEvent2() {
		    @Override
		    public void onEdit(int row) {
		        try {
		            int idRental = Integer.parseInt(RentalTable.getValueAt(row, 0).toString());
		            System.out.println("Editando renta con ID: " + idRental);
		            RentalsController rc = new RentalsController();
		            RentalsModel rm = new RentalsModel();
		            
		            frame.dispose();
		            rc.editRental(rm.getRentalById(idRental));
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(frame, "Error al editar la renta: " + ex.getMessage());
		        }
		    }

		    @Override
		    public void onDelete(int row) {
		        try {
		            int idRental = Integer.parseInt(RentalTable.getValueAt(row, 0).toString());
		            int confirm = JOptionPane.showConfirmDialog(
		                frame, 
		                "¿Está seguro de que desea eliminar esta renta?", 
		                "Confirmar eliminación", 
		                JOptionPane.YES_NO_OPTION
		            );
		            
		            if (confirm == JOptionPane.YES_OPTION) {
		                RentalsModel rm = new RentalsModel();
		                Rental rental = rm.getRentalById(idRental);
		                
		                rm.deleteRental(rental);
		                
		                // Remover la fila de la tabla
		                model.removeRow(row);
		                JOptionPane.showMessageDialog(frame, "Renta eliminada correctamente");
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(frame, "Error al eliminar la renta: " + ex.getMessage());
		        }
		    }

		    @Override
		    public void onDelete(int row, Tariff t) {
		        // Este método no se usa en este contexto
		        onDelete(row);
		    }

		    @Override
		    public void onDelete(int row, Tariff t, DefaultTableModel model) {
		        // Este método no se usa en este contexto
		        onDelete(row);
		    }
		};
		RentalTable.getColumn("Acciones").setCellRenderer(new TableActionCellRender2());
		RentalTable.getColumn("Acciones").setCellEditor(new TableActionCellEditor2(event, new ArrayList<>(), model));
		btnNewButton_3_1_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String cliente = textField_1.getText().trim().toLowerCase();
		        String habitacion = textField_2.getText().trim().toLowerCase();
		        String fechaInicio = textField_3.getText().trim();
		        String fechaFin = textField_4.getText().trim();

		        TableRowSorter<TableModel> sorter = new TableRowSorter<>(RentalTable.getModel());
		        RentalTable.setRowSorter(sorter);

		        List<RowFilter<Object, Object>> filters = new ArrayList<>();

		        if (!cliente.isEmpty()) {
		            filters.add(RowFilter.regexFilter("(?i)" + cliente, 1)); // Columna cliente
		        }

		        if (!habitacion.isEmpty()) {
		            filters.add(RowFilter.regexFilter("(?i)" + habitacion, 2)); // Columna habitación
		        }

		        if (!fechaInicio.isEmpty()) {
		            filters.add(RowFilter.regexFilter(".*" + fechaInicio + ".*", 3)); // Check-in
		        }

		        if (!fechaFin.isEmpty()) {
		            filters.add(RowFilter.regexFilter(".*" + fechaFin + ".*", 4)); // Check-out
		        }

		        RowFilter<Object, Object> compoundRowFilter = RowFilter.andFilter(filters);
		        sorter.setRowFilter(compoundRowFilter);
		    }
		});
		btnNewButton_3_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        textField_1.setText("");
		        textField_2.setText("");
		        textField_3.setText("");
		        textField_4.setText("");

		        TableRowSorter<?> sorter = (TableRowSorter<?>) RentalTable.getRowSorter();
		        if (sorter != null) {
		            sorter.setRowFilter(null);
		        }
		    }
		});
		JScrollPane scrollPane = new JScrollPane(RentalTable);
		scrollPane.setBounds(0, 0, 1100, 270);
		panel_2.add(scrollPane);
		frame.revalidate();
		frame.repaint();
	}
	
	public void createRental() throws SQLException {
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
		lblNewLabel.setBounds(130, 175, 178, 34);
		lblNewLabel.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Nuevo cliente");
		btnNewButton.setBounds(481, 219, 146, 50);
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
				client.createClient2();
			}
			
		});

		JComboBox<Client> client_Combo = new JComboBox<>();
		client_Combo.setBounds(130, 219, 344, 50);
		panel.add(client_Combo);

		List<Client> clients = new ClientsModel().getAvailableClient();
		DefaultComboBoxModel<Client> model_client = new DefaultComboBoxModel<>();
		model_client.addElement(null); // optional null for no selection

		for (Client client : clients) {
			model_client.addElement(client);
		}
		client_Combo.setModel(model_client);
		
		client_Combo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			}
				// TODO Auto-generated method stub
				
		});
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de habitación");
		lblNewLabel_1.setBounds(130, 270, 178, 34);
		lblNewLabel_1.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Check in");
		lblNewLabel_2.setBounds(691, 175, 178, 34);
		lblNewLabel_2.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel_2);
		
		JDateChooser dateCheckIn = new JDateChooser();
		dateCheckIn.setBounds(691, 219, 424, 44);
		panel.add(dateCheckIn);
		
		JLabel lblNewLabel_2_1 = new JLabel("Check out");
		lblNewLabel_2_1.setBounds(691, 270, 178, 34);
		lblNewLabel_2_1.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel_2_1);
		
		JDateChooser dateCheckOut = new JDateChooser();
		dateCheckOut.setBounds(691, 314, 424, 44);
		panel.add(dateCheckOut);
		
		JComboBox<Room> room_Combo = new JComboBox<>();
		room_Combo.setBounds(130, 300, 500, 50);//117, 219, 344, 44
		panel.add(room_Combo);

		List<Room> rooms = new RoomsModel().getAvailableRoom();
		DefaultComboBoxModel<Room> model = new DefaultComboBoxModel<>();
		model.addElement(null); // optional null for no selection

		for (Room room : rooms) {
		    model.addElement(room);
		}

		room_Combo.setModel(model);

		room_Combo.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        r = (Room) room_Combo.getSelectedItem();
		        try {
					rt = rtm.getRoomTypeById(r.getId_room_type());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        if (rt != null) {

		            try {
		                
		            	t = tm.getTariffById(rt.getId_tariff());
		                System.out.println(t.getPrice_per_night());
		                precio = t.getPrice_per_night();
		                pricePerNight_label.setText("" + precio);
		                
		                if (dateCheckIn.getDate() != null && dateCheckOut.getDate() != null) {
					        java.util.Date checkInDateUtil = dateCheckIn.getDate();
					        java.util.Date checkOutDateUtil = dateCheckOut.getDate();

					        sqlCheckIn = new java.sql.Date(checkInDateUtil.getTime());
					        sqlCheckOut = new java.sql.Date(checkOutDateUtil.getTime());
					        
					        diffMillis = checkOutDateUtil.getTime() - checkInDateUtil.getTime();
					        diffDays = diffMillis / (1000 * 60 * 60 * 24);

					        if (diffDays < 0) {
					            JOptionPane.showMessageDialog(null, "La fecha de check-out debe ser posterior al check-in.");
					            return;
					        }

					        total = precio * diffDays;
					        totalPrecio_label.setText("$" + total);
					 }
		                frame.repaint();
		            } catch (SQLException e1) {
		                e1.printStackTrace();
		            }
		        } else {
		            System.out.println("NO HICE NADA");
		        }
		    }
		});
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Precio por noche");
		lblNewLabel_2_1_1.setBounds(691, 368, 178, 34);
		lblNewLabel_2_1_1.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel_2_1_1);
		
		pricePerNight_label = new JLabel();
		pricePerNight_label.setBackground(Color.gray);
		pricePerNight_label.setBorder(BorderFactory.createLineBorder(Color.black));
		pricePerNight_label.setBounds(691, 412, 424, 44);
		panel.add(pricePerNight_label);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Total");
		lblNewLabel_2_1_1_1.setBounds(691, 466, 178, 34);
		lblNewLabel_2_1_1_1.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel_2_1_1_1);
		
		totalPrecio_label = new JLabel();
		totalPrecio_label.setBackground(Color.gray);
		totalPrecio_label.setBorder(BorderFactory.createLineBorder(Color.black));
		totalPrecio_label.setBounds(691, 510, 424, 44);
		panel.add(totalPrecio_label);
		
		JButton cancel_Button = new JButton("Cancelar");
		cancel_Button.setBounds(691, 601, 188, 51);
		cancel_Button.setBackground(new Color(153, 89, 45));
		cancel_Button.setForeground(Color.WHITE);
		cancel_Button.setFont(new Font("Inter_18pt Bold", Font.BOLD, 22));
		cancel_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RentalsController rent = new RentalsController();
				frame.dispose();
				rent.rentals();
				
			}
		});
		panel.add(cancel_Button);
		
		JButton accept_Button = new JButton("Aceptar");
		accept_Button.setBounds(927, 601, 188, 51);
		accept_Button.setForeground(Color.WHITE);
		accept_Button.setBackground(new Color(7, 26, 43));
		accept_Button.setFont(new Font("Inter_18pt Bold", Font.BOLD, 22));
		accept_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			    Rental newRental = new Rental();
			    RentalsModel rm = new RentalsModel();
			    ClientsModel cm = new ClientsModel();
			    Room selRoom;
			    selRoom = (Room) room_Combo.getSelectedItem();
			    Client selClient;
			    selClient = (Client) client_Combo.getSelectedItem();

			        newRental = new Rental();
			        newRental.setId_room(selRoom.getId_room());
			        newRental.setId_client(selClient.getId_client());
			        newRental.setCheck_in(sqlCheckIn);
			        newRental.setCheck_out(sqlCheckOut);
			        newRental.setDias_totales((long) total);
			        newRental.setStatus(false);
			        
			        try {
			        	RentalsController rc = new RentalsController();
						rm.createRental(newRental);
						frame.dispose();
						rc.rentals();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    }
			
		});

		panel.add(accept_Button);
		
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
	
	public void editRental(Rental rental) throws SQLException {
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

		JComboBox<Client> client_Combo = new JComboBox<>();
		client_Combo.setBounds(117, 219, 344, 44);
		panel.add(client_Combo);

		List<Client> clients = new ClientsModel().getAvailableClient();
		DefaultComboBoxModel<Client> model_client = new DefaultComboBoxModel<>();
		model_client.addElement(null); // optional null for no selection

		for (Client client : clients) {
			model_client.addElement(client);
		}
		client_Combo.setModel(model_client);
		for (int i = 0; i < client_Combo.getItemCount(); i++) {
	        Client c = client_Combo.getItemAt(i);
	        if (c != null && c.getId_client() == rental.getId_client()) {
	        	client_Combo.setSelectedIndex(i);
	            break;
	        }
	    }
		
		Room RentalRoom = rm.getRoomById(rental.getId_room());
		RoomType RentalRoomType = rtm.getRoomTypeById(RentalRoom.getId_room_type());
		Tariff RentalTariff = tm.getTariffById(RentalRoomType.getId_tariff()); 
		precio = RentalTariff.getPrice_per_night();
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de habitación");
		lblNewLabel_1.setBounds(117, 270, 178, 34);
		lblNewLabel_1.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Check in");
		lblNewLabel_2.setBounds(691, 175, 178, 34);
		lblNewLabel_2.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel_2);
		
		JDateChooser dateCheckIn = new JDateChooser();
		dateCheckIn.setDate(rental.getCheck_in());
		dateCheckIn.setBounds(691, 219, 424, 44);
		panel.add(dateCheckIn);
		
		JLabel lblNewLabel_2_1 = new JLabel("Check out");
		lblNewLabel_2_1.setBounds(691, 270, 178, 34);
		lblNewLabel_2_1.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel_2_1);
		
		JDateChooser dateCheckOut = new JDateChooser();
		dateCheckOut.setDate(rental.getCheck_out());
		dateCheckOut.setBounds(691, 314, 424, 44);
		panel.add(dateCheckOut);
		
		JComboBox<Room> room_Combo = new JComboBox<>();
		room_Combo.setBounds(130, 300, 460, 60);
		panel.add(room_Combo);

		List<Room> rooms = new RoomsModel().getAvailableRoom();
		DefaultComboBoxModel<Room> model = new DefaultComboBoxModel<>();
		model.addElement(null);

		for (Room room : rooms) {
		    model.addElement(room);
		}
		room_Combo.setModel(model);
		for (int i = 0; i < room_Combo.getItemCount(); i++) {
	        Room r = room_Combo.getItemAt(i);
	        if (r != null && r.getId_room() == rental.getId_room()) {
	        	room_Combo.setSelectedIndex(i);
	            break;
	        }
	    }
		
	
		
		room_Combo.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        r = (Room) room_Combo.getSelectedItem();
		        RoomTypesModel rtm = new RoomTypesModel();
		        t = new Tariff();
		        tm = new TariffsModel();

		        try {
					rt = rtm.getRoomTypeById(r.getId_room_type());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        if (rt != null) {

		            try {
		                
		            	t = tm.getTariffById(rt.getId_tariff());
		                precio = t.getPrice_per_night();
		                
		                
		                if (dateCheckIn.getDate() != null && dateCheckOut.getDate() != null) {
					        java.util.Date checkInDateUtil = dateCheckIn.getDate();
					        java.util.Date checkOutDateUtil = dateCheckOut.getDate();

					        sqlCheckIn = new java.sql.Date(checkInDateUtil.getTime());
					        sqlCheckOut = new java.sql.Date(checkOutDateUtil.getTime());
					        
					        diffMillis = checkOutDateUtil.getTime() - checkInDateUtil.getTime();
					        diffDays = diffMillis / (1000 * 60 * 60 * 24);

					        if (diffDays < 0) {
					            JOptionPane.showMessageDialog(null, "La fecha de check-out debe ser posterior al check-in.");
					            return;
					        }

					        total = precio * diffDays;
					        totalPrecio_label.setText("$" + total);
					 }
		                frame.repaint();
		            } catch (SQLException e1) {
		                e1.printStackTrace();
		            }
		        } else {
		            System.out.println("NO HICE NADA");
		        }
		    }
		});
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Precio por noche");
		lblNewLabel_2_1_1.setBounds(691, 368, 178, 34);
		lblNewLabel_2_1_1.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel_2_1_1);
		
		pricePerNight_label = new JLabel();
		pricePerNight_label.setText("" + precio);
		pricePerNight_label.setBackground(Color.gray);
		pricePerNight_label.setBorder(BorderFactory.createLineBorder(Color.black));
		pricePerNight_label.setBounds(691, 412, 424, 44);
		panel.add(pricePerNight_label);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Total");
		lblNewLabel_2_1_1_1.setBounds(691, 466, 178, 34);
		lblNewLabel_2_1_1_1.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 18));
		panel.add(lblNewLabel_2_1_1_1);
		
		totalPrecio_label = new JLabel();
		totalPrecio_label.setText("" + rental.getDias_totales());
		totalPrecio_label.setBackground(Color.gray);
		totalPrecio_label.setBorder(BorderFactory.createLineBorder(Color.black));
		totalPrecio_label.setBounds(691, 510, 424, 44);
		panel.add(totalPrecio_label);
		
		JButton cancel_Button = new JButton("Cancelar");
		cancel_Button.setBounds(691, 601, 188, 51);
		cancel_Button.setBackground(new Color(153, 89, 45));
		cancel_Button.setForeground(Color.WHITE);
		cancel_Button.setFont(new Font("Inter_18pt Bold", Font.BOLD, 22));
		cancel_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RentalsController rent = new RentalsController();
				frame.dispose();
				rent.rentals();
				
			}
		});
		panel.add(cancel_Button);
		
		JButton accept_Button = new JButton("Aceptar");
		accept_Button.setBounds(927, 601, 188, 51);
		accept_Button.setForeground(Color.WHITE);
		accept_Button.setBackground(new Color(7, 26, 43));
		accept_Button.setFont(new Font("Inter_18pt Bold", Font.BOLD, 22));
		accept_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			    Rental editRental = new Rental();
			    RentalsModel rm = new RentalsModel();
			    ClientsModel cm = new ClientsModel();
			    Room selRoom;
			    selRoom = (Room) room_Combo.getSelectedItem();
			    
			    Client selClient;
			    selClient = (Client) client_Combo.getSelectedItem();
	
			    editRental = new Rental();
			    editRental.setId_room(selRoom.getId_room());
			    editRental.setId_client(selClient.getId_client());
			    editRental.setCheck_in(sqlCheckIn);
			    editRental.setCheck_out(sqlCheckOut);
			    editRental.setDias_totales((long) total);
			    editRental.setStatus(false);
		        
		        try {
		        	RentalsController rc = new RentalsController();
					rm.updateRental(editRental);
					frame.dispose();
					rc.rentals();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
			
		});

		panel.add(accept_Button);
		
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
		
	public void deleteConfirm(Rental r) {
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
