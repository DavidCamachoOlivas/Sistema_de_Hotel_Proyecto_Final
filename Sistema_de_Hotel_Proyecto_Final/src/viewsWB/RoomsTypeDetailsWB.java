package viewsWB;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class RoomsTypeDetailsWB extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomsTypeDetailsWB frame = new RoomsTypeDetailsWB();
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
	public RoomsTypeDetailsWB() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 252, 247));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(7, 26, 43));
		panel.setBounds(-26, 0, 1290, 119);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Detalles de habitación");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_4.setBounds(238, 21, 490, 66);
		panel.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(130, 30, 78, 56);
		Image imgBtnHome = new ImageIcon("src/images/btnHome.png").getImage().getScaledInstance(78, 56, Image.SCALE_SMOOTH);
		btnNewButton.setIcon(new ImageIcon(imgBtnHome));
		btnNewButton.setBackground(null);
		btnNewButton.setBorder(null);
		panel.add(btnNewButton);
		
		
		JLabel lblTipoDeTarifa = new JLabel("Tipo de tarifa");
		lblTipoDeTarifa.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTipoDeTarifa.setBounds(358, 159, 309, 27);
		contentPane.add(lblTipoDeTarifa);
		
		
		JLabel lblNewLabel = new JLabel("Habitaciones amplias con salida y comedor ideales para familiar grandes");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(358, 185, 660, 61);
		contentPane.add(lblNewLabel);
		
	
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(7, 26, 43));
		panel_1.setBounds(126, 289, 990, 272);
		contentPane.add(panel_1);
		String[] columnNames = {
				"#-Habitación",
				"Piso",
				"Capacidad",
				"Tarifa"
		};
		
		Object [][] data = {
				{"110", 2, "5 personas","Variable"},
				{"111", 2, "5 personas","Variable"},
				{"112", 2, "5 personas","Variable"},
				{"113", 2, "5 personas","Variable"},
				{"114", 2, "5 personas","Variable"},
				{"115", 2, "5 personas","Variable"},				
		};
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable roomsTypeDetailsTable = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(roomsTypeDetailsTable);
		scrollPane.setBounds(0, 54, 990, 218);
		panel_1.add(scrollPane);
		roomsTypeDetailsTable.getTableHeader().setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
		roomsTypeDetailsTable.getTableHeader().setBackground(Color.decode("#071A2B"));
		roomsTypeDetailsTable.getTableHeader().setForeground(Color.decode("#FFFFFF"));
		roomsTypeDetailsTable.setFont(new Font("Inter_18pt Bold", Font.PLAIN, 22));
		roomsTypeDetailsTable.setRowHeight(30);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setFont(new Font("Inter_18pt Bold", Font.BOLD, 24));
		lblNewLabel_2.setForeground(Color.decode("#FFFFFF"));
		lblNewLabel_2.setBounds(400, 10, 200, 40);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(130, 158, 180, 90);
		Image img = new ImageIcon("src/images/normal.png").getImage().getScaledInstance(180, 90, Image.SCALE_SMOOTH);
		lblNewLabel_5.setIcon(new ImageIcon(img));
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(1015, 618, 85, 21);
		contentPane.add(btnNewButton_1);
		roomsTypeDetailsTable.setDefaultEditor(Object.class,null);
	}
}
