package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import models.AuthModel;

public class AuthView extends JFrame{
	private JFrame frame;
	private AuthModel functions;
	public AuthView() {
		functions = new AuthModel();
	
	}
	
	
	public void login() {
		
		
		frame = new JFrame();
		frame.setTitle("Inicio de sesión");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);
		frame.setBounds(0,0,1400,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setBackground(Color.decode("#071A2B"));//FBF3E6
		panel.setLayout(null);
		
		JLabel logo = new JLabel();
		ImageIcon originalIcon = new ImageIcon(AuthView.class.getResource("/images/logo_AnclaDePaz_.jpg"));
		Image scaledImage = originalIcon.getImage().getScaledInstance(149, 149, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		logo.setIcon(scaledIcon);
		logo.setBounds(380, 30, 149, 149);
		panel.add(logo);
		
		JLabel tituloNombre = new JLabel("Hotel Ancla de Paz");
		tituloNombre.setBounds(550,70,585,77);
		tituloNombre.setFont(new Font("Inter_18pt Bold", Font.BOLD, 64));
		tituloNombre.setForeground(Color.decode("#FBF3E6"));
		tituloNombre.setBackground(null);
		panel.add(tituloNombre);
		
		JPanel login = new JPanel();
		login.setBounds(270,200,1000,600);
		login.setBackground(Color.decode("#FBF3E6"));
		login.setLayout(null);
		panel.add(login);
		
		JLabel tituloLogin = new JLabel("Iniciar sesión");
		tituloLogin.setBounds(300,20,450,70);
		tituloLogin.setFont(new Font("Inter_18pt Bold", Font.BOLD, 64));
		tituloLogin.setForeground(Color.decode("#071A2B"));
		//tituloLogin.setOpaque(true);
		//tituloLogin.setBackground(Color.green);
		login.add(tituloLogin);
		
		JLabel lblEmail = new JLabel("Dirección de email");
		lblEmail.setBounds(70,120,300,50);
		lblEmail.setFont(new Font("Inter_18pt Bold", Font.BOLD, 32));
		lblEmail.setForeground(Color.decode("#000910"));
		//lblEmail.setOpaque(true);
		//lblEmail.setBackground(Color.green);
		login.add(lblEmail);
		
		JTextField tfEmail = new JTextField();
		tfEmail.setBounds(70,170,850,70);
		tfEmail.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		tfEmail.setForeground(Color.decode("#000910"));
		login.add(tfEmail);
		
		JLabel lblPassW = new JLabel("Contraseña");
		lblPassW.setBounds(70,300,300,50);
		lblPassW.setFont(new Font("Inter_18pt Bold", Font.BOLD, 32));
		lblPassW.setForeground(Color.decode("#000910"));
		//lblPassW.setOpaque(true);
		//lblPassW.setBackground(Color.green);
		login.add(lblPassW);
		
		JPasswordField tfPassw = new JPasswordField();
		tfPassw.setBounds(70,350,850,70);
		tfPassw.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		tfPassw.setForeground(Color.decode("#000910"));
		login.add(tfPassw);
		
		//.setBackground(color.green);
		
		
		JButton btnAccess = new JButton("Ingresar");
		btnAccess.setBounds(70,470,850,70);
		btnAccess.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnAccess.setForeground(Color.decode("#FFFFFF"));
		btnAccess.setBackground(Color.decode("#071A2B"));
		login.add(btnAccess);
		
		frame.repaint();
		frame.revalidate();
		frame.setVisible(true);
		
		System.out.println(getClass().getResource("/fonts/Inter_18pt-Bold.ttf"));
		final GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
		final List<String> AVAILABLE_FONT_FAMILY_NAMES = Arrays.asList(GE.getAvailableFontFamilyNames());
		try {
		    final List<File> LIST = Arrays.asList(
		        new File("/fonts/Inter_18pt-Bold.ttf")
		     );
		     for (File LIST_ITEM : LIST) {
		         if (LIST_ITEM.exists()) {
		             Font FONT = Font.createFont(Font.TRUETYPE_FONT, LIST_ITEM);
		             if (!AVAILABLE_FONT_FAMILY_NAMES.contains(FONT.getFontName())){ 
		                 GE.registerFont(FONT);
		             }
		         }
		     }
		} catch (FontFormatException | IOException exception) {
		    JOptionPane.showMessageDialog(null, exception.getMessage());
		}
	}
}

