package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PopUpsView {

	JFrame frame = new JFrame();
	JFrame loadingFrame;
	private JDialog loadingDialog;
	private JDialog signInDialog;
	public void successDownload() {
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
		title.setBounds(50,30,600,70);
		title.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		title.setVisible(true);
		panel.add(title);
		
		JLabel img = new JLabel();
        img.setBounds(225,80,250,250);
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

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
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
		
		JLabel title = new JLabel("Eliminado con exito");
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
	
	public void incorrectSignIn() {
		if (loadingDialog == null) {
	    	loadingDialog = new JDialog();
	    	loadingDialog.setTitle("Cargando");
	    	loadingDialog.setModal(false); // No bloquear otras ventanas
	    	loadingDialog.setAlwaysOnTop(true);
	        loadingDialog.setSize(700, 500);
	        loadingDialog.setLocationRelativeTo(null);
	        loadingDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        loadingDialog.setAlwaysOnTop(true); // 🔥 Mantener por encima de otras ventanas

	        JPanel panel = new JPanel();
	        panel.setBackground(Color.decode("#FFFCF7"));
	        panel.setLayout(null);

	        JLabel title = new JLabel("Datos ingresados incorrectos");
	        title.setBounds(50, 20, 600, 70);
	        title.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
	        panel.add(title);
	        
	        JLabel img = new JLabel();
	        img.setBounds(225,110,250,250);
	        ImageIcon lblImgOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/error.png"));
			Image lblImgScaledImage = lblImgOriginalIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
			ImageIcon lblImgScaledIcon = new ImageIcon(lblImgScaledImage);//btnConsult
			img.setIcon(lblImgScaledIcon);
			panel.add(img);

			JButton accept = new JButton("Aceptar");
			accept.setBounds(350,370,300,70);
			accept.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
			accept.setForeground(Color.decode("#FFFFFF"));
			accept.setBackground(Color.decode("#071A2B"));
			panel.add(accept);
			
			accept.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					loadingDialog.dispose();
				}
				
			});
			
	        loadingDialog.getContentPane().add(panel, BorderLayout.CENTER);
	    }

	    loadingDialog.setVisible(true); // Mostrar el dialog
		
	}
	

	public void loading() {
	    if (loadingDialog == null) {
	    	loadingDialog = new JDialog();
	    	loadingDialog.setTitle("Cargando");
	    	loadingDialog.setModal(false); // No bloquear otras ventanas
	    	loadingDialog.setAlwaysOnTop(true);
	        loadingDialog.setSize(700, 500);
	        loadingDialog.setLocationRelativeTo(null);
	        loadingDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        loadingDialog.setAlwaysOnTop(true); // 🔥 Mantener por encima de otras ventanas

	        JPanel panel = new JPanel();
	        panel.setBackground(Color.decode("#FFFCF7"));
	        panel.setLayout(null);

	        JLabel title = new JLabel("Espere un momento...");
	        title.setBounds(50, 60, 600, 70);
	        title.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
	        panel.add(title);
	        
	        JLabel img = new JLabel();
	        img.setBounds(225,140,250,250);
	        ImageIcon lblImgOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/loading.png"));
			Image lblImgScaledImage = lblImgOriginalIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
			ImageIcon lblImgScaledIcon = new ImageIcon(lblImgScaledImage);//btnConsult
			img.setIcon(lblImgScaledIcon);
			panel.add(img);

	        loadingDialog.getContentPane().add(panel, BorderLayout.CENTER);
	    }

	    loadingDialog.setVisible(true); // Mostrar el dialog
	}
	
	public void closeLoading() {
	    if (loadingDialog != null) {
	        loadingDialog.dispose();
	        loadingDialog = null;
	    }
	}
}
