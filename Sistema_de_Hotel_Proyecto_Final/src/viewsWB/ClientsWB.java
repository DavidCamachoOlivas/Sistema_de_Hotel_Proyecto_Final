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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.ClientsController;
import controllers.HomeController;
import buttonCells.TableActionCellEditor;
import buttonCells.TableActionCellRender;
import buttonCells.TableActionEvent;
import views.AuthView;

import javax.swing.JTable;
import javax.swing.JTextField;

public class ClientsWB extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame frame;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientsWB frame = new ClientsWB();
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
	public ClientsWB() {
		/*setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);*/
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
		
		String[] columnNames = {
				"ID",
				"Cliente",
				"Email",
				"Telefono",
				"Acciones"
		};
		
		Object [][] data = {
				{"1", "Axdiael","Ax@gmail.com","6121234567",""},
				{"1", "Axdiael","Ax@gmail.com","6121234567",""},
				{"1", "Axdiael","Ax@gmail.com","6121234567",""},
				{"1", "Axdiael","Ax@gmail.com","6121234567",""},
				{"1", "Axdiael","Ax@gmail.com","6121234567",""},
				{"1", "Axdiael","Ax@gmail.com","6121234567",""},
				
		};
		
		
		
		JButton btnCreate = new JButton("Crear");
		btnCreate.setBounds(868,603,300,70);
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
		
		JButton btnEdit = new JButton("");
		btnEdit.setBounds(1094,334,74,54);
		btnEdit.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnEdit.setForeground(Color.decode("#FFFFFF"));
		btnEdit.setBackground(null);
		btnEdit.setBorderPainted(false);
		ImageIcon btnEditOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/btnEdit.png"));
		Image btnEditScaledImage = btnEditOriginalIcon.getImage().getScaledInstance(74, 54, Image.SCALE_SMOOTH);
		ImageIcon btnEditScaledIcon = new ImageIcon(btnEditScaledImage);//btnConsult
		btnEdit.setIcon(btnEditScaledIcon);
		panel.add(btnEdit);
		
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ClientsController client = new ClientsController();
				frame.dispose();
				client.editClient();
			}
			
		});
		
		JButton btnConsult = new JButton("");
		btnConsult.setBounds(1132,426,74,54);
		btnConsult.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 32));
		btnConsult.setBackground(null);
		btnConsult.setBorderPainted(false);
		ImageIcon btnConsultOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/btnConsult.png"));
		Image btnConsultScaledImage = btnConsultOriginalIcon.getImage().getScaledInstance(74, 54, Image.SCALE_SMOOTH);
		ImageIcon btnConsultScaledIcon = new ImageIcon(btnConsultScaledImage);//btnConsult
		btnConsult.setIcon(btnConsultScaledIcon);
		panel.add(btnConsult);
		
		btnConsult.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ClientsController client = new ClientsController();
				frame.dispose();
				client.consultClient();
			}
			
		});
		
		JButton btnDelete = new JButton("");
		btnDelete.setBounds(1120,217,76,76);
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(null);
		ImageIcon btnDeleteOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/btnDelete.png"));
		Image btnDeleteScaledImage = btnDeleteOriginalIcon.getImage().getScaledInstance(76, 76, Image.SCALE_SMOOTH);
		ImageIcon btnDeleteScaledIcon = new ImageIcon(btnDeleteScaledImage);
		btnDelete.setIcon(btnDeleteScaledIcon);
		panel.add(btnDelete);
		
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
		clientsTablePanel.setBounds(200, 260, 860, 310);
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
		                System.out.println("Edit row : " + row);
		            }

		            @Override
		            public void onDelete(int row) {
		                if (clientsTable.isEditing()) {
		                	clientsTable.getCellEditor().stopCellEditing();
		                }
		                model.removeRow(row);
		            }

		            @Override
		            public void onView(int row) {
		                System.out.println("View row : " + row);
		            }
		        };
		        clientsTable.getColumn("Acciones").setCellRenderer(new TableActionCellRender());
		        clientsTable.getColumn("Acciones").setCellEditor(new TableActionCellEditor(event));
				JScrollPane scrollPane = new JScrollPane(clientsTable);
				scrollPane.setBounds(0, 50, 860, 260);
				clientsTablePanel.add(scrollPane);
				
				JLabel lblTableTitle = new JLabel("Clientes");
				lblTableTitle.setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
				lblTableTitle.setForeground(Color.decode("#FFFFFF"));
				lblTableTitle.setBounds(359, 10, 200, 40);
				clientsTablePanel.add(lblTableTitle);
				
				JTextField search = new JTextField();
				search.setBounds(200, 200, 680, 50);
				search.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 22));
				panel.add(search);
				search.setColumns(10);
				
				JButton btnSearch = new JButton("Ver");
				btnSearch.setFont(new Font("Inter_18pt Bold", Font.BOLD, 22));
				btnSearch.setBackground(Color.decode("#071A2B"));
				btnSearch.setForeground(Color.decode("#FFFFFF"));
				btnSearch.setBounds(885, 199, 175, 50);
				panel.add(btnSearch);
		
		
		
		
		
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HomeController home = new HomeController();
				frame.dispose();
				home.home();
			}
			
		});
		
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ClientsController client = new ClientsController();
				client.deleteClient();
			}
			
		});
	}
}
