package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.regex.Pattern;
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
import javax.swing.table.DefaultTableModel;

import buttonCells.TableActionCellEditor2;
import buttonCells.TableActionCellRender2;
import buttonCells.TableActionEvent2;
import controllers.ClientsController;
import controllers.HomeController;
import controllers.RentalsController;
import controllers.RoomsController;
import controllers.TariffsController;
import models.ClientsModel;
import models.Tariff;
import models.TariffsModel;

public class TariffsView {

	private JFrame frame;
	private TariffsModel functions;
	public TariffsView() {
		functions = new TariffsModel();
	}
	
	public void tariff() {
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
		
		JLabel lblTitle = new JLabel("Tarifas");
		lblTitle.setBounds(200, 42, 250, 82);
		header.add(lblTitle);
		lblTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 44));
		lblTitle.setForeground(Color.decode("#FFFFFF"));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(null);
		
		JTextField textField = new JTextField();
		textField.setBounds(130, 208, 777, 58);
		panel.add(textField);
		textField.setColumns(10);
		
		TariffsModel tm = new TariffsModel();
		List<Tariff> tariffs = new ArrayList<>();
		List<Tariff> allTariffs = new ArrayList<>();
		
		try {
		    tariffs = tm.getAvailableTariffs();
		    allTariffs.addAll(tariffs);
		} catch (SQLException e1) {
		    e1.printStackTrace();
		}
		try {
			tariffs = tm.getAvailableTariffs();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Object[][] datos = new Object[tariffs.size()][5];

		for (int i = 0; i < tariffs.size(); i++) {
		    datos[i][0] = tariffs.get(i).getTariff_type();
		    datos[i][1] = tariffs.get(i).getPrice_per_night();
		    datos[i][2] = tariffs.get(i).getCapacity();
		    if(tariffs.get(i).isRefundable()) {
		    	datos[i][3] = "Reembolsable";    	
		    }
		    else {
		    	datos[i][3] = "No reembolsable";
		    }
		    datos[i][4] = tariffs.get(i).getDescription();
		}
		
		
		
		
		String[] columnNames = {
				"Tarifas",
				"Precios",
				"Capacidad",
				"Descripción",
				"Acciones"
		};
		
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(130, 286, 1000, 300);
		panel.add(panel_1);
			
		DefaultTableModel model = new DefaultTableModel(datos, columnNames);
			JTable clientsTable = new JTable(model);
			clientsTable.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 22));
			clientsTable.setRowHeight(30);
			clientsTable.getTableHeader().setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
			clientsTable.getTableHeader().setBackground(Color.decode("#071A2B"));
			clientsTable.getTableHeader().setForeground(Color.decode("#FFFFFF"));
			clientsTable.setDefaultEditor(Object.class,null);
			TableActionEvent2 event = new TableActionEvent2() {
	            @Override
	            public void onEdit(int row) {
	            	TariffsController rte = new TariffsController();
	            	frame.dispose();
	            	rte.editTariff();
	                System.out.println("Edit row : " + row);
	            }
	            @Override
				public void onDelete(int row) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onDelete(int row, Tariff t) {
					// TODO Auto-generated method stub
					
				}
	            @Override
	            public void onDelete(int row, Tariff t, DefaultTableModel model) {
	                if (clientsTable.isEditing()) {
	                	clientsTable.getCellEditor().stopCellEditing();
	                }
	                
	                deleteConfirm(row,t,model);
	            }

				
	
	        };
	        clientsTable.getColumn("Acciones").setCellRenderer(new TableActionCellRender2());
	        clientsTable.getColumn("Acciones").setCellEditor(new TableActionCellEditor2(event, tariffs, model));
			JScrollPane scrollPane = new JScrollPane(clientsTable);
			panel_1.setLayout(null);
			scrollPane.setBounds(0, 0, 1000, 300);
			panel_1.add(scrollPane);
	
			
			JButton btnNewButton = new JButton("Ver");
			btnNewButton.setBounds(917, 207, 213, 58);
			btnNewButton.setFont(new Font("Inter_18pt Bold", Font.BOLD, 22));
			btnNewButton.setForeground(Color.decode("#FFFFFF"));
			btnNewButton.setBackground(new Color(7, 26, 43));
			panel.add(btnNewButton);
			
			btnNewButton.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        String keyword = textField.getText().trim();
			        if (keyword.isEmpty()) {
			            model.setRowCount(0);
			            for (Object[] row : toData(allTariffs)) {
			                model.addRow(row);
			            }
			            return;
			        }
			        
			        List<TariffMatch> matches = new ArrayList<>();
			        String keywordLower = keyword.toLowerCase();
			        
			        for (Tariff t : allTariffs) {
			            int score = calculateMatchScore(t, keywordLower);
			            if (score > 0) {
			                matches.add(new TariffMatch(t, score));
			            }
			        }
			        matches.sort((a, b) -> Integer.compare(b.score, a.score));

			        List<Tariff> filtered = matches.stream().map(match -> match.tariff).collect(Collectors.toList());	        
			        model.setRowCount(0);
			        for (Object[] row : toData(filtered)) {
			            model.addRow(row);
			        }
			    }
			    
			    private int calculateMatchScore(Tariff tariff, String keyword) {
			        int score = 0;
			        String tariffType = tariff.getTariff_type().toLowerCase();

			        if (tariffType.equals(keyword)) {
			            score += 100;
			        }

			        else if (tariffType.startsWith(keyword)) {
			            score += 80;
			        }

			        else if (containsWholeWord(tariffType, keyword)) {
			            score += 60;
			        }

			        else if (tariffType.contains(keyword)) {
			            score += 30;
			        }

			        if (Math.abs(tariffType.length() - keyword.length()) <= 2) {
			            score += 10;
			        }
			        
			        return score;
			    }
			    
			    private boolean containsWholeWord(String text, String word) {
			        return text.matches(".\\b" + Pattern.quote(word) + "\\b.");
			    }

			    class TariffMatch {
			        final Tariff tariff;
			        final int score;
			        
			        TariffMatch(Tariff tariff, int score) {
			            this.tariff = tariff;
			            this.score = score;
			        }
			    }
			});
			
		JButton btnNewButton1 = new JButton("Añadir");
		btnNewButton1.setBounds(917, 600, 213, 58);
		btnNewButton1.setFont(new Font("Inter_18pt Bold", Font.BOLD, 32));
		btnNewButton1.setForeground(Color.decode("#FFFFFF"));
		btnNewButton1.setBackground(new Color(7, 26, 43));
		panel.add(btnNewButton1);
		btnNewButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController rte = new TariffsController();
				frame.dispose();
				rte.createTariff();
			}
			
		});
		
		

		frame.revalidate();
		frame.repaint();
	}
	private Object[][] toData(List<Tariff> list) {
		Object[][] data = new Object[list.size()][5];
		for (int i = 0; i < list.size(); i++) {
			Tariff t = list.get(i);
	        data[i][0] = t.getTariff_type();
	        data[i][1] = t.getPrice_per_night();
	        data[i][2] = t.getCapacity();
	        data[i][3] = t.isRefundable() ? "Reembolsable" : "No reembolsable";
	        data[i][4] = t.getDescription();
		}
		return data;
	}
	public void createTariff() {
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
				TariffsController tariffs = new TariffsController();
				frame.dispose();
				tariffs.tariffs();
			}
			
		});
		
		JLabel lblTitle = new JLabel("Añadir tarifas");
		lblTitle.setBounds(200, 42, 550, 82);
		header.add(lblTitle);
		lblTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 44));
		lblTitle.setForeground(Color.decode("#FFFFFF"));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(null);
		
		JLabel tariffType_lblNewLabel = new JLabel("Tipo de tarifa:");
		tariffType_lblNewLabel.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		tariffType_lblNewLabel.setBounds(130, 160, 202, 42);
		panel.add(tariffType_lblNewLabel);
		
		String[] type_tariff = {"Flexible", "No reembolsable", "Reembolsable"};
		JComboBox tariffType_comboBox = new JComboBox(type_tariff);
		tariffType_comboBox.setBounds(130, 210, 400, 56);
		panel.add(tariffType_comboBox);
		
		JLabel priceNight_lblNewLabel = new JLabel("Precio por noche:");
		priceNight_lblNewLabel.setBounds(730, 160, 250, 42);
		priceNight_lblNewLabel.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		panel.add(priceNight_lblNewLabel);
		
		JTextField priceNight_textField = new JTextField();
		priceNight_textField.setColumns(10);
		priceNight_textField.setBounds(730, 210, 400, 56);
		panel.add(priceNight_textField);
		
		JLabel capacity_lblNewLabel = new JLabel("Capacidad:");
		capacity_lblNewLabel.setBounds(130, 270, 400, 42);
		capacity_lblNewLabel.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		panel.add(capacity_lblNewLabel);
		
		Integer[] num_guest = {1, 2, 3, 4, 5, 6};
		JComboBox<Integer> capacity_comboBox = new JComboBox(num_guest);
		capacity_comboBox.setBounds(130, 320, 400, 56);
		panel.add(capacity_comboBox);
		
		JLabel isRefundable_lblNewLabel = new JLabel("¿Es reembolsable?");
		isRefundable_lblNewLabel.setBounds(730, 270, 250, 42);
		isRefundable_lblNewLabel.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		panel.add(isRefundable_lblNewLabel);
		
		String[] opciones = {"Si", "No"};
		JComboBox<String> comboBox = new JComboBox<>(opciones);
		comboBox.setBounds(730, 320, 400, 56);
		panel.add(comboBox);
		
		//Cambion de texto a resultado booleano para "¿es reembolsable?"
		String selected = (String) comboBox.getSelectedItem();
		boolean isRefundable = selected.equals("Sí");
		
		JLabel description_lblNewLabel = new JLabel("Descripción:");
		description_lblNewLabel.setBounds(130, 380, 250, 42);
		description_lblNewLabel.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		panel.add(description_lblNewLabel);
		
		TextArea description_textArea = new TextArea();
		description_textArea.setBounds(130, 430, 1000, 130);
		description_textArea.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		panel.add(description_textArea);

		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(947, 587, 183, 56);
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("Inter_18pt Bold", Font.BOLD, 22));
		btnNewButton.setForeground(Color.decode("#FFFFFF"));
		btnNewButton.setBackground(new Color(7, 26, 43));
		btnNewButton.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {

				//Obtener datos
				float price_night = Float.parseFloat(priceNight_textField.getText());
				int capacity = (int) capacity_comboBox.getSelectedItem();
				String tariffType = (String) tariffType_comboBox.getSelectedItem();
				boolean refundable = isRefundable;
				String description = description_textArea.getText();
				Tariff t = new Tariff(0, 0, price_night, capacity, tariffType, refundable, description);
				
				// TODO Auto-generated method stub
				TariffsModel tm = new TariffsModel();
				try {
					System.out.println("Entró");
					tm.createTariff(t);
					frame.dispose();
					TariffsView tv = new TariffsView();
					tv.tariff();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(732, 587, 183, 56);
		panel.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Inter_18pt Bold", Font.BOLD, 22));
		btnNewButton_1.setForeground(Color.decode("#FFFFFF"));
		btnNewButton_1.setBackground(Color.decode("#99592D"));
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController home = new TariffsController();
				frame.dispose();
				home.tariffs();
			}
			
		});

		frame.revalidate();
		frame.repaint();
	}
	
	public void editTariff() {
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
				TariffsController tariffs = new TariffsController();
				frame.dispose();
				tariffs.tariffs();
			}
			
		});
		
		JLabel lblTitle = new JLabel("Editar tarifa");
		lblTitle.setBounds(200, 42, 550, 82);
		header.add(lblTitle);
		lblTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 44));
		lblTitle.setForeground(Color.decode("#FFFFFF"));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(null);
		
		JLabel tariffType_lblNewLabel = new JLabel("Tipo de tarifa:");
		tariffType_lblNewLabel.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		tariffType_lblNewLabel.setBounds(130, 160, 202, 42);
		panel.add(tariffType_lblNewLabel);
		
		String[] type_tariff = {"Flexible", "No reembolsable", "Reembolsable"};
		JComboBox tariffType_comboBox = new JComboBox(type_tariff);
		tariffType_comboBox.setBounds(130, 210, 400, 56);
		panel.add(tariffType_comboBox);
		
		JLabel priceNight_lblNewLabel = new JLabel("Precio por noche:");
		priceNight_lblNewLabel.setBounds(730, 160, 250, 42);
		priceNight_lblNewLabel.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		panel.add(priceNight_lblNewLabel);
		
		JTextField priceNight_textField = new JTextField();
		priceNight_textField.setColumns(10);
		priceNight_textField.setBounds(730, 210, 400, 56);
		panel.add(priceNight_textField);
		
		JLabel capacity_lblNewLabel = new JLabel("Capacidad:");
		capacity_lblNewLabel.setBounds(130, 270, 400, 42);
		capacity_lblNewLabel.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		panel.add(capacity_lblNewLabel);
		
		Integer[] num_guest = {1, 2, 3, 4, 5, 6};
		JComboBox<Integer> capacity_comboBox = new JComboBox(num_guest);
		capacity_comboBox.setBounds(130, 320, 400, 56);
		panel.add(capacity_comboBox);
		
		JLabel isRefundable_lblNewLabel = new JLabel("¿Es reembolsable?");
		isRefundable_lblNewLabel.setBounds(730, 270, 250, 42);
		isRefundable_lblNewLabel.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		panel.add(isRefundable_lblNewLabel);
		
		String[] opciones = {"Si", "No"};
		JComboBox<String> comboBox = new JComboBox<>(opciones);
		comboBox.setBounds(730, 320, 400, 56);
		panel.add(comboBox);
		
		//Cambion de texto a resultado booleano para "¿es reembolsable?"
		String selected = (String) comboBox.getSelectedItem();
		boolean isRefundable = selected.equals("Sí");
		
		JLabel description_lblNewLabel = new JLabel("Descripción:");
		description_lblNewLabel.setBounds(130, 380, 250, 42);
		description_lblNewLabel.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		panel.add(description_lblNewLabel);
		
		TextArea description_textArea = new TextArea();
		description_textArea.setBounds(130, 430, 1000, 130);
		description_textArea.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		panel.add(description_textArea);

		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(947, 587, 183, 56);
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("Inter_18pt Bold", Font.BOLD, 22));
		btnNewButton.setForeground(Color.decode("#FFFFFF"));
		btnNewButton.setBackground(new Color(7, 26, 43));
		btnNewButton.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {

				//Obtener datos
				float price_night = Float.parseFloat(priceNight_textField.getText());
				int capacity = (int) capacity_comboBox.getSelectedItem();
				String tariffType = (String) tariffType_comboBox.getSelectedItem();
				boolean refundable = isRefundable;
				String description = description_textArea.getText();
				Tariff t = new Tariff(0, 0, price_night, capacity, tariffType, refundable, description);
				
				// TODO Auto-generated method stub
				TariffsModel tm = new TariffsModel();
				try {
					System.out.println("Entró");
					tm.createTariff(t);
					frame.dispose();
					TariffsView tv = new TariffsView();
					tv.tariff();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(732, 587, 183, 56);
		panel.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Inter_18pt Bold", Font.BOLD, 22));
		btnNewButton_1.setForeground(Color.decode("#FFFFFF"));
		btnNewButton_1.setBackground(Color.decode("#99592D"));
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController home = new TariffsController();
				frame.dispose();
				home.tariffs();
			}
			
		});

		frame.revalidate();
		frame.repaint();
	}
	
	public void consultTariff() {
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
		panel.add(btnHome);
		
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController tariff = new TariffsController();
				frame.dispose();
				tariff.tariffs();
			}
			
		});
		
		JButton lblDownload = new JButton("Descargar .pdf");
		lblDownload.setBounds(900,500,300,70);
		lblDownload.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		lblDownload.setForeground(Color.decode("#FFFFFF"));
		lblDownload.setBackground(Color.decode("#071A2B"));
		panel.add(lblDownload);
		
		lblDownload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController tariff = new TariffsController();
				frame.dispose();
				tariff.successDownload();
			}
			
		});

		frame.revalidate();
		frame.repaint();
	}
	
	public void deleteConfirm(int row, Tariff t, DefaultTableModel model) {
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
		    public void actionPerformed(ActionEvent e) {
		        try {
		            TariffsModel tm = new TariffsModel();

		            if (tm.isTariffInUse(t)) {
		                JOptionPane.showMessageDialog(null, "No puedes eliminar esta tarifa porque está en uso por algún tipo de habitación.");
		                return; // Salimos sin hacer nada
		            }

		            boolean deleted = tm.deleteRoomType(t);
		            if (deleted) {
		                model.removeRow(row);
		                confirmFrame.dispose();
		                TariffsController success = new TariffsController();
		                success.successDelete();
		                //JOptionPane.showMessageDialog(null, "Tarifa eliminada con éxito.");
		            } else {
		                JOptionPane.showMessageDialog(null, "No se pudo eliminar la tarifa.");
		            }
		        } catch (SQLException e1) {
		            JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage());
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
		
		JLabel title = new JLabel("Tarifa eliminado con exito");
		title.setBounds(50,40,600,70);
		title.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		title.setVisible(true);
		panel.add(title);
		
		JLabel img = new JLabel();
		img.setBounds(225,90,250,250);
		ImageIcon lblImgOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/success.png"));
		Image lblImgScaledImage = lblImgOriginalIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		ImageIcon lblImgScaledIcon = new ImageIcon(lblImgScaledImage);//btnConsult
		img.setIcon(lblImgScaledIcon);
		panel.add(img);
		
		JButton accept = new JButton("Aceptar");
		accept.setBounds(350,350,300,70);
		accept.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		accept.setForeground(Color.decode("#FFFFFF"));
		accept.setBackground(Color.decode("#071A2B"));
		panel.add(accept);
		
		accept.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
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
	}
	
}
