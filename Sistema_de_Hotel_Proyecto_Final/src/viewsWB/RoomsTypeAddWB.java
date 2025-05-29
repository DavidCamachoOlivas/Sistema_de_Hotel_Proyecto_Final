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
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;

public class RoomsTypeAddWB extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomsTypeAddWB frame = new RoomsTypeAddWB();
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
	public RoomsTypeAddWB() {
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
		panel.setBounds(-26, 0, 1290, 119);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Tipo de habitaciones");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_4.setBounds(238, 21, 490, 66);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(146, 21, 128, 75);
		Image img = new ImageIcon("src/flecha.png").getImage().getScaledInstance(76, 58, Image.SCALE_SMOOTH);
		lblNewLabel_5.setIcon(new ImageIcon(img));
		panel.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(122, 196, 470, 49);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(646, 196, 470, 49);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(122, 327, 994, 49);
		contentPane.add(textField_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(122, 466, 470, 49);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(646, 466, 470, 49);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel = new JLabel("Tipo de habitación");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(122, 158, 309, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblTipoDeTarifa = new JLabel("Tipo de tarifa");
		lblTipoDeTarifa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTipoDeTarifa.setBounds(646, 158, 309, 27);
		contentPane.add(lblTipoDeTarifa);
		
		JLabel lblHabitacionesSeleccionadas = new JLabel("Habitaciones seleccionadas");
		lblHabitacionesSeleccionadas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHabitacionesSeleccionadas.setBounds(122, 289, 309, 27);
		contentPane.add(lblHabitacionesSeleccionadas);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPiso.setBounds(122, 428, 309, 27);
		contentPane.add(lblPiso);
		
		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCapacidad.setBounds(646, 428, 309, 27);
		contentPane.add(lblCapacidad);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(new Color(153, 89, 45));
		btnCancelar.setBounds(808, 571, 140, 72);
		contentPane.add(btnCancelar);
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(7, 26, 43));
		btnNewButton_1.setBounds(976, 571, 140, 72);
		contentPane.add(btnNewButton_1);
		
	}
}
