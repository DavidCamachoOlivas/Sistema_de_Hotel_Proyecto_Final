package viewsWB;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.ClientsController;
import buttonCells.TableActionCellEditor;
import buttonCells.TableActionCellRender;
import buttonCells.TableActionEvent;
import views.AuthView;

public class ClientsDetailsWB extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientsDetailsWB frame = new ClientsDetailsWB();
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
	public ClientsDetailsWB() {
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
				dispose();
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
		
		
		
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ClientsController download = new ClientsController();
				download.successDownload();
			}
			
		});
				
	}

}
