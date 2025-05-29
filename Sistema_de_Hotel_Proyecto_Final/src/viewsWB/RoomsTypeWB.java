package viewsWB;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class RoomsTypeWB extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomsTypeWB frame = new RoomsTypeWB();
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
	public RoomsTypeWB() {
		
		setResizable(false);
		setBounds(new Rectangle(0, 0, 720, 1280));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 252, 247));
		contentPane.setBounds(new Rectangle(0, 0, 720, 1280));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(7, 26, 43));
		panel.setBounds(-26, 0, 1290, 130);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Tipo de habitaciones");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_4.setBounds(238, 40, 490, 66);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(157, 21, 128, 75);
		Image img = new ImageIcon("src/flecha.png").getImage().getScaledInstance(76, 58, Image.SCALE_SMOOTH);
		lblNewLabel_5.setIcon(new ImageIcon(img));
		panel.add(lblNewLabel_5);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(130, 169, 310, 381);
		contentPane.add(panel_1);
		panel_1.setBorder(BorderFactory.createLineBorder(Color.black));  
		panel_1.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		Image icon = new ImageIcon("src/cama.png").getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
		ImageIcon backgroundIcon = new ImageIcon(icon);
		JLabel backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setBounds(0, 0, 900, 500);
		panel_2.setOpaque(false);
		panel_2.setBounds(0, 0, 900, 500);

		backgroundLabel.add(panel_2);
		panel_1.add(backgroundLabel);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Habitación de lujo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(10, 31, 271, 46);
		panel_5.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("$2.000 MXN por noche");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(10, 77, 182, 30);
		panel_5.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.setBackground(new Color(153, 89, 45));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(10, 118, 89, 49);
		panel_5.add(btnNewButton);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setBounds(110, 118, 89, 49);
		panel_5.add(btnEditar);
		
		JButton btnDetalles = new JButton("Detalles");
		btnDetalles.setForeground(Color.WHITE);
		btnDetalles.setBackground(new Color(7, 26, 43));
		btnDetalles.setBounds(209, 118, 89, 49);
		panel_5.add(btnDetalles);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(468, 169, 310, 381);
		contentPane.add(panel_1_1);
		panel_1_1.setBorder(BorderFactory.createLineBorder(Color.black));  
		panel_1_1.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_6 = new JPanel();
		Image icon2 = new ImageIcon("src/suite.png").getImage().getScaledInstance(500, 200, Image.SCALE_SMOOTH);
		ImageIcon backgroundIcon2 = new ImageIcon(icon2);
		JLabel backgroundLabel2 = new JLabel(backgroundIcon2);
		backgroundLabel2.setBounds(0, 0, 900, 500);
		panel_6.setOpaque(false);
		panel_6.setBounds(0, 0, 900, 500);
		
		backgroundLabel2.add(panel_6);	
		panel_1_1.add(backgroundLabel2);
		
		JPanel panel_3 = new JPanel();
		panel_1_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Habitación de lujo");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_1.setBounds(10, 29, 271, 46);
		panel_3.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("$2.000 MXN por noche");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3_1.setBounds(10, 74, 182, 30);
		panel_3.add(lblNewLabel_3_1);
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(153, 89, 45));
		btnNewButton_1.setBounds(10, 115, 89, 49);
		panel_3.add(btnNewButton_1);
		
		JButton btnEditar_1 = new JButton("Editar");
		btnEditar_1.setBackground(Color.WHITE);
		btnEditar_1.setBounds(109, 115, 89, 49);
		panel_3.add(btnEditar_1);
		
		JButton btnDetalles_1 = new JButton("Detalles");
		btnDetalles_1.setForeground(Color.WHITE);
		btnDetalles_1.setBackground(new Color(7, 26, 43));
		btnDetalles_1.setBounds(209, 115, 89, 49);
		panel_3.add(btnDetalles_1);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBounds(811, 169, 310, 381);
		contentPane.add(panel_1_2);
		panel_1_2.setBorder(BorderFactory.createLineBorder(Color.black));  
		panel_1_2.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_4 = new JPanel();
		Image icon3 = new ImageIcon("src/normal.png").getImage().getScaledInstance(500, 200, Image.SCALE_SMOOTH);
		ImageIcon backgroundIcon3 = new ImageIcon(icon3);
		JLabel backgroundLabel3 = new JLabel(backgroundIcon3);
		backgroundLabel3.setBounds(0, 0, 900, 500);
		panel_2.setOpaque(false);
		panel_2.setBounds(0, 0, 900, 500);

		backgroundLabel3.add(panel_4);
		panel_1_2.add(backgroundLabel3);
		
		JPanel panel_7 = new JPanel();
		panel_1_2.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Habitación de lujo");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_2.setBounds(10, 29, 271, 46);
		panel_7.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3_2 = new JLabel("$2.000 MXN por noche");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3_2.setBounds(10, 74, 182, 30);
		panel_7.add(lblNewLabel_3_2);
		
		JButton btnNewButton_1_1 = new JButton("Eliminar");
		btnNewButton_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1.setBackground(new Color(153, 89, 45));
		btnNewButton_1_1.setBounds(10, 114, 89, 49);
		panel_7.add(btnNewButton_1_1);
		
		JButton btnEditar_1_1 = new JButton("Editar");
		btnEditar_1_1.setBackground(Color.WHITE);
		btnEditar_1_1.setBounds(109, 114, 89, 49);
		panel_7.add(btnEditar_1_1);
		
		JButton btnDetalles_1_1 = new JButton("Detalles");
		btnDetalles_1_1.setForeground(Color.WHITE);
		btnDetalles_1_1.setBackground(new Color(7, 26, 43));
		btnDetalles_1_1.setBounds(209, 114, 89, 49);
		panel_7.add(btnDetalles_1_1);
		
		JButton btnEditar_2 = new JButton("1");
		btnEditar_2.setBackground(Color.WHITE);
		btnEditar_2.setBounds(514, 561, 44, 44);
		contentPane.add(btnEditar_2);
		
		JButton btnEditar_2_1 = new JButton("2");
		btnEditar_2_1.setBackground(Color.WHITE);
		btnEditar_2_1.setBounds(568, 561, 44, 44);
		contentPane.add(btnEditar_2_1);
		
		JButton btnEditar_2_1_1 = new JButton("3");
		btnEditar_2_1_1.setBackground(Color.WHITE);
		btnEditar_2_1_1.setBounds(622, 561, 44, 44);
		contentPane.add(btnEditar_2_1_1);
		
		JButton btnEditar_2_1_2 = new JButton("...");
		btnEditar_2_1_2.setBackground(Color.WHITE);
		btnEditar_2_1_2.setBounds(676, 561, 44, 44);
		contentPane.add(btnEditar_2_1_2);
		
		JButton btnNuevoTipo = new JButton("Nuevo tipo");
		btnNuevoTipo.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNuevoTipo.setForeground(Color.WHITE);
		btnNuevoTipo.setBackground(new Color(7, 26, 43));
		btnNuevoTipo.setBounds(395, 621, 459, 49);
		contentPane.add(btnNuevoTipo);
	}

}
