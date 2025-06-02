package viewsWB;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controllers.AuthController;
import controllers.ClientsController;
import controllers.RentalsController;
import controllers.RoomTypesController;
import controllers.RoomsController;
import controllers.TariffsController;
import views.AuthView;

public class HomeWB extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeWB frame = new HomeWB();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomeWB() {
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
		btnHome.setBounds(900, 60, 250, 40);
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(92, 219, 243, 425);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(0, 0, 243, 329);
		ImageIcon lblImgOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/btnHomeToR.png"));
		Image lblImgScaledImage = lblImgOriginalIcon.getImage().getScaledInstance(243, 329, Image.SCALE_SMOOTH);
		ImageIcon lblImgScaledIcon = new ImageIcon(lblImgScaledImage);//btnConsult
		lblNewLabel.setIcon(lblImgScaledIcon);
		panel_1.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Tipos Hab.");
		btnNewButton.setBounds(0, 330, 243, 95);
		btnNewButton.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		btnNewButton.setBackground(Color.decode("#FBF3E6"));
		btnNewButton.setOpaque(false);
		btnNewButton.setFocusPainted(false);
		panel_1.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomTypesController rooms = new RoomTypesController();
				dispose();
				try {
					rooms.roomTypes();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		JPanel panel_1_3 = new JPanel();
		panel_1_3.setBounds(952, 219, 243, 198);
		panel.add(panel_1_3);
		panel_1_3.setLayout(null);
		
		JButton btnNewButton_3 = new JButton("Clientes");
		btnNewButton_3.setBounds(0, 0, 243, 198);
		btnNewButton_3.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		btnNewButton_3.setBackground(Color.decode("#FBF3E6"));
		btnNewButton_3.setOpaque(false);
		btnNewButton_3.setFocusPainted(false);
		ImageIcon lblImgOriginalIcon11 = new ImageIcon(AuthView.class.getResource("/images/userImg.png"));
		Image lblImgScaledImage11 = lblImgOriginalIcon11.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
		ImageIcon lblImgScaledIcon11 = new ImageIcon(lblImgScaledImage11);//btnConsult
		btnNewButton_3.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton_3.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton_3.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton_3.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton_3.setIcon(lblImgScaledIcon11);
		panel_1_3.add(btnNewButton_3);
		
		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ClientsController client = new ClientsController();
				dispose();
				client.clients();
			}
			
		});
		
		JPanel panel_1_3_1 = new JPanel();
		panel_1_3_1.setBounds(952, 446, 243, 198);
		panel.add(panel_1_3_1);
		panel_1_3_1.setLayout(null);
		
		JButton btnNewButton_3_1 = new JButton("Tarifas");
		btnNewButton_3_1.setBounds(0, 0, 243, 198);
		btnNewButton_3_1.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		btnNewButton_3_1.setBackground(Color.decode("#FBF3E6"));
		btnNewButton_3_1.setOpaque(false);
		btnNewButton_3_1.setFocusPainted(false);
		ImageIcon lblImgOriginalIcon111 = new ImageIcon(AuthView.class.getResource("/images/btnHomeRates.png"));
		Image lblImgScaledImage111 = lblImgOriginalIcon111.getImage().getScaledInstance(160, 130, Image.SCALE_SMOOTH);
		ImageIcon lblImgScaledIcon111 = new ImageIcon(lblImgScaledImage111);//btnConsult
		btnNewButton_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton_3_1.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton_3_1.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton_3_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton_3_1.setIcon(lblImgScaledIcon111);
		panel_1_3_1.add(btnNewButton_3_1);
		
		btnNewButton_3_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TariffsController tariffs = new TariffsController();
				dispose();
				tariffs.tariffs();
			}
			
		});
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(378, 219, 243, 425);
		panel.add(panel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(0, 0, 243, 329);
		ImageIcon lblImgOriginalIcon1 = new ImageIcon(AuthView.class.getResource("/images/btnHomeR.png"));
		Image lblImgScaledImage1 = lblImgOriginalIcon1.getImage().getScaledInstance(243, 329, Image.SCALE_SMOOTH);
		ImageIcon lblImgScaledIcon1 = new ImageIcon(lblImgScaledImage1);//btnConsult
		lblNewLabel_1.setIcon(lblImgScaledIcon1);
		panel_1_1.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Habitaciones");
		btnNewButton_1.setBounds(0, 330, 243, 95);
		btnNewButton_1.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		btnNewButton_1.setBackground(Color.decode("#FBF3E6"));
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setFocusPainted(false);
		panel_1_1.add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomsController rooms = new RoomsController();
				dispose();
				rooms.rooms();
			}
			
		});
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBounds(662, 219, 243, 425);
		panel.add(panel_1_2);
		
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setBounds(0, 0, 243, 329);
		ImageIcon lblImgOriginalIcon2 = new ImageIcon(AuthView.class.getResource("/images/btnHomeRentals.png"));
		Image lblImgScaledImage2 = lblImgOriginalIcon2.getImage().getScaledInstance(243, 329, Image.SCALE_SMOOTH);
		ImageIcon lblImgScaledIcon2 = new ImageIcon(lblImgScaledImage2);//btnConsult
		lblNewLabel_2.setIcon(lblImgScaledIcon2);
		panel_1_2.add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("Rentas");
		btnNewButton_2.setBounds(0, 330, 243, 95);
		btnNewButton_2.setFont(new Font("Inter_18pt Bold", Font.BOLD, 26));
		btnNewButton_2.setBackground(Color.decode("#FBF3E6"));
		btnNewButton_2.setOpaque(false);
		btnNewButton_2.setFocusPainted(false);
		panel_1_2.add(btnNewButton_2);
		
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RentalsController rental = new RentalsController();
				dispose();
				rental.rentals();
			}
			
		});
	}

}
