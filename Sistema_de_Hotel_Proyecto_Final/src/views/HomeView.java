package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.AuthController;
import controllers.HomeController;
import models.AuthModel;
import models.HomeModel;

public class HomeView extends JFrame{

	private JFrame frame;
	private HomeModel functions;
	public HomeView() {
		functions = new HomeModel();
	}
	
	public void home() {
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
		
		JLabel lblTitle = new JLabel("Home");
		lblTitle.setBounds(300,200,450,70);
		lblTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 64));
		lblTitle.setForeground(Color.decode("#071A2B"));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(Color.green);
		panel.add(lblTitle);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(70,470,850,70);
		btnLogin.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnLogin.setForeground(Color.decode("#FFFFFF"));
		btnLogin.setBackground(Color.decode("#071A2B"));
		panel.add(btnLogin);
		
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AuthController auth = new AuthController();
				frame.dispose();
				auth.login();
			}
			
		});
	}
}
