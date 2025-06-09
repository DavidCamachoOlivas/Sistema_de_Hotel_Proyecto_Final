package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controllers.AuthController;
import controllers.ClientsController;
import controllers.HomeController;
import controllers.PopUpsController;
import controllers.RentalsController;
import controllers.RoomTypesController;
import controllers.RoomsController;
import controllers.TariffsController;
import models.AuthModel;
import models.HomeModel;

public class HomeView extends JFrame{

	private JFrame frame;
	private HomeModel functions;
	public HomeView() {
		functions = new HomeModel();
	}
	
	public void home() {
		frame = new JFrame("Ventana Principal");
		PopUpsController pop = new PopUpsController();
		setTitle("Hotel Ancla de Paz");
		setResizable(false);
		setBounds(0,0,1280,720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setBackground(Color.decode("#FFFCF7"));//FBF3E6
		panel.setLayout(null);
		
		JPanel header = new JPanel();
		header.setBounds(0, 0, 1280, 130);
		header.setBackground(Color.decode("#071A2B"));
		panel.add(header);
		header.setLayout(null);
		
		JLabel lblTitle = new JLabel("Inicio");
		lblTitle.setBounds(100, 42, 250, 82);
		header.add(lblTitle);
		lblTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 44));
		lblTitle.setForeground(Color.decode("#FFFFFF"));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(null);
		
		JButton btnHome = new JButton();
		btnHome.setBounds(970, 60, 250, 40);
		btnHome.setText("Cerrar sesión");
		btnHome.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnHome.setForeground(Color.decode("#FFFFFF"));
		btnHome.setBorderPainted(false);
		btnHome.setBackground(null);
		btnHome.setOpaque(false);
		btnHome.setFocusPainted(false);
		header.add(btnHome);
		
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AuthController auth = new AuthController();
				dispose();
				auth.login();
			}
			
		});
		
		JPanel panelRoomTypes = new JPanel();
		panelRoomTypes.setBounds(92, 219, 243, 425);
		panel.add(panelRoomTypes);
		panelRoomTypes.setLayout(null);
		
		JLabel lblRoomTypes = new JLabel();
		lblRoomTypes.setBounds(0, 0, 243, 329);
		ImageIcon lblImgOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/btnHomeToR.png"));
		Image lblImgScaledImage = lblImgOriginalIcon.getImage().getScaledInstance(243, 329, Image.SCALE_SMOOTH);
		ImageIcon lblImgScaledIcon = new ImageIcon(lblImgScaledImage);//btnConsult
		lblRoomTypes.setIcon(lblImgScaledIcon);
		panelRoomTypes.add(lblRoomTypes);
		lblRoomTypes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRoomTypes.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	javax.swing.SwingUtilities.invokeLater(() -> {
		            pop.loading(); 
		        });

		        new Thread(() -> {
		        	dispose();
		            RoomTypesController rooms = new RoomTypesController();
		            try {
		                rooms.roomTypes(); 
		            } catch (SQLException e1) {
		                e1.printStackTrace();
		            }
		            javax.swing.SwingUtilities.invokeLater(() -> {
		                pop.closeLoading();
		            });
		        }).start();
		    	
		    	/*pop.loading();
		    	dispose();
		    	RoomTypesController rooms = new RoomTypesController();
		    	try {
					rooms.roomTypes();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	pop.closeLoading();*/
		    }
		});
		
		JButton btnRoomTypes = new JButton("Tipos Hab.");
		btnRoomTypes.setBounds(0, 330, 243, 95);
		btnRoomTypes.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		btnRoomTypes.setBackground(Color.decode("#FBF3E6"));
		btnRoomTypes.setOpaque(false);
		btnRoomTypes.setFocusPainted(false);
		panelRoomTypes.add(btnRoomTypes);
		
		btnRoomTypes.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        javax.swing.SwingUtilities.invokeLater(() -> {
		            pop.loading(); 
		        });

		        new Thread(() -> {
		        	dispose();
		            RoomTypesController rooms = new RoomTypesController();
		            try {
		                rooms.roomTypes(); 
		            } catch (SQLException e1) {
		                e1.printStackTrace();
		            }
		            javax.swing.SwingUtilities.invokeLater(() -> {
		                pop.closeLoading();
		            });
		        }).start();
		    }
		});
		
		JPanel panelClients = new JPanel();
		panelClients.setBounds(952, 219, 243, 198);
		panel.add(panelClients);
		panelClients.setLayout(null);
		
		JButton btnClients = new JButton("Clientes");
		btnClients.setBounds(0, 0, 243, 198);
		btnClients.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		btnClients.setBackground(Color.decode("#FBF3E6"));
		btnClients.setOpaque(false);
		btnClients.setFocusPainted(false);
		ImageIcon lblImgOriginalIcon11 = new ImageIcon(AuthView.class.getResource("/images/userImg.png"));
		Image lblImgScaledImage11 = lblImgOriginalIcon11.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
		ImageIcon lblImgScaledIcon11 = new ImageIcon(lblImgScaledImage11);//btnConsult
		btnClients.setHorizontalAlignment(SwingConstants.CENTER);
		btnClients.setVerticalAlignment(SwingConstants.TOP);
		btnClients.setHorizontalTextPosition(SwingConstants.CENTER);
		btnClients.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnClients.setIcon(lblImgScaledIcon11);
		panelClients.add(btnClients);
		
		btnClients.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				javax.swing.SwingUtilities.invokeLater(() -> {
		            pop.loading();
		        });

		        new Thread(() -> {
		        	dispose();
		        	ClientsController client = new ClientsController();
		            client.clients();
		            javax.swing.SwingUtilities.invokeLater(() -> {
		                pop.closeLoading();
		                frame.dispose();
		            });
		        }).start();
			}
			
		});
		
		JPanel panelRates = new JPanel();
		panelRates.setBounds(952, 446, 243, 198);
		panel.add(panelRates);
		panelRates.setLayout(null);
		
		JButton btnRates = new JButton("Tarifas");
		btnRates.setBounds(0, 0, 243, 198);
		btnRates.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		btnRates.setBackground(Color.decode("#FBF3E6"));
		btnRates.setOpaque(false);
		btnRates.setFocusPainted(false);
		ImageIcon lblImgOriginalIcon111 = new ImageIcon(AuthView.class.getResource("/images/btnHomeRates.png"));
		Image lblImgScaledImage111 = lblImgOriginalIcon111.getImage().getScaledInstance(160, 130, Image.SCALE_SMOOTH);
		ImageIcon lblImgScaledIcon111 = new ImageIcon(lblImgScaledImage111);//btnConsult
		btnRates.setHorizontalAlignment(SwingConstants.CENTER);
		btnRates.setVerticalAlignment(SwingConstants.TOP);
		btnRates.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRates.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRates.setIcon(lblImgScaledIcon111);
		panelRates.add(btnRates);
		
		btnRates.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				javax.swing.SwingUtilities.invokeLater(() -> {
		            pop.loading();
		        });

		        new Thread(() -> {
		        	dispose();
		        	TariffsController tariffs = new TariffsController();
		        	tariffs.tariffs();
		            javax.swing.SwingUtilities.invokeLater(() -> {
		                pop.closeLoading();
		            });
		        }).start();
				
			}
			
		});
		
		JPanel panelRooms = new JPanel();
		panelRooms.setLayout(null);
		panelRooms.setBounds(378, 219, 243, 425);
		panel.add(panelRooms);
		
		JLabel lblRooms = new JLabel();
		lblRooms.setBounds(0, 0, 243, 329);
		ImageIcon lblImgOriginalIcon1 = new ImageIcon(AuthView.class.getResource("/images/btnHomeR.png"));
		Image lblImgScaledImage1 = lblImgOriginalIcon1.getImage().getScaledInstance(243, 329, Image.SCALE_SMOOTH);
		ImageIcon lblImgScaledIcon1 = new ImageIcon(lblImgScaledImage1);
		lblRooms.setIcon(lblImgScaledIcon1);
		panelRooms.add(lblRooms);
		lblRooms.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRooms.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	pop.loading(); 

		        new Thread(() -> {

			        dispose();
		            try {
		                Thread.sleep(100); 
		            } catch (InterruptedException ex) {
		                ex.printStackTrace();
		            }

		            javax.swing.SwingUtilities.invokeLater(() -> {
		                RoomsController rooms = new RoomsController();
		                pop.closeLoading();
		                rooms.rooms(); 
		                frame.dispose();
		            });
		        }).start();
		    }
		});
		
		JButton btnRooms = new JButton("Habitaciones");
		btnRooms.setBounds(0, 330, 243, 95);
		btnRooms.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		btnRooms.setBackground(Color.decode("#FBF3E6"));
		btnRooms.setOpaque(false);
		btnRooms.setFocusPainted(false);
		panelRooms.add(btnRooms);
		
		btnRooms.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        pop.loading();  // ahora es un JDialog encima de todo

		        new Thread(() -> {

			        dispose();
		            try {
		                Thread.sleep(100); // pequeño retraso opcional para que loading se muestre bien
		            } catch (InterruptedException ex) {
		                ex.printStackTrace();
		            }

		            javax.swing.SwingUtilities.invokeLater(() -> {
		                RoomsController rooms = new RoomsController();
		                pop.closeLoading();
		                rooms.rooms(); // mostrar nueva UI
		                frame.dispose();
		            });
		        }).start();
		    }
		});

		
		JPanel paneRentals = new JPanel();
		paneRentals.setLayout(null);
		paneRentals.setBounds(662, 219, 243, 425);
		panel.add(paneRentals);
		
		JLabel lblRentals = new JLabel();
		lblRentals.setBounds(0, 0, 243, 329);
		ImageIcon lblImgOriginalIcon2 = new ImageIcon(AuthView.class.getResource("/images/btnHomeRentals.png"));
		Image lblImgScaledImage2 = lblImgOriginalIcon2.getImage().getScaledInstance(243, 329, Image.SCALE_SMOOTH);
		ImageIcon lblImgScaledIcon2 = new ImageIcon(lblImgScaledImage2);//btnConsult
		lblRentals.setIcon(lblImgScaledIcon2);
		paneRentals.add(lblRentals);
		lblRentals.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRentals.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	// TODO Auto-generated method stub
				javax.swing.SwingUtilities.invokeLater(() -> {
		            pop.loading();  
		            dispose();      
		        });

		        new Thread(() -> {
		        	RentalsController rental = new RentalsController();
		        	rental.rentals();
		            javax.swing.SwingUtilities.invokeLater(() -> {
		                pop.closeLoading();
		                frame.dispose();
		            });
		        }).start();
		    }
		});
		
		JButton btnRentals = new JButton("Rentas");
		btnRentals.setBounds(0, 330, 243, 95);
		btnRentals.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		btnRentals.setBackground(Color.decode("#FBF3E6"));
		btnRentals.setOpaque(false);
		btnRentals.setFocusPainted(false);
		paneRentals.add(btnRentals);
		
		btnRentals.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				javax.swing.SwingUtilities.invokeLater(() -> {
		            pop.loading();  
		            dispose();      
		        });

		        new Thread(() -> {
		        	RentalsController rental = new RentalsController();
		        	rental.rentals();
		            javax.swing.SwingUtilities.invokeLater(() -> {
		                pop.closeLoading();
		                frame.dispose();
		            });
		        }).start();
				
			}
			
		});
	}
}
