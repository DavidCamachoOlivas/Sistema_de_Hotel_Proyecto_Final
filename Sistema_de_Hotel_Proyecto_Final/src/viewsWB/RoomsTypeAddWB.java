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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RoomsTypeAddWB extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel mainPanel;
    private JTextField roomTypeField;
    private JTextField rateTypeField;
    private JTextField selectedRoomsField;
    private JComboBox<String> floorComboBox;
    private JComboBox<String> capacityComboBox;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                RoomsTypeAddWB frame = new RoomsTypeAddWB();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
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

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 252, 247));
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(7, 26, 43));
        headerPanel.setBounds(0, 0, 1290, 119);
        mainPanel.add(headerPanel);
        headerPanel.setLayout(null);

        JLabel roomTypeTitleLabel = new JLabel("Tipo de habitaciones");
        roomTypeTitleLabel.setForeground(Color.WHITE);
        roomTypeTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
        roomTypeTitleLabel.setBounds(238, 21, 490, 66);
        headerPanel.add(roomTypeTitleLabel);

        JLabel backArrowLabel = new JLabel();
        backArrowLabel.setBounds(146, 21, 128, 75);
        Image backIcon = new ImageIcon("src/flecha.png").getImage().getScaledInstance(76, 58, Image.SCALE_SMOOTH);
        backArrowLabel.setIcon(new ImageIcon(backIcon));
        headerPanel.add(backArrowLabel);

        roomTypeField = new JTextField();
        roomTypeField.setBounds(122, 196, 470, 49);
        roomTypeField.setColumns(10);
        mainPanel.add(roomTypeField);

        rateTypeField = new JTextField();
        rateTypeField.setBounds(646, 196, 470, 49);
        rateTypeField.setColumns(10);
        mainPanel.add(rateTypeField);

        selectedRoomsField = new JTextField();
        selectedRoomsField.setBounds(122, 327, 994, 49);
        selectedRoomsField.setColumns(10);
        mainPanel.add(selectedRoomsField);

        floorComboBox = new JComboBox<>();
        floorComboBox.setBounds(122, 466, 470, 49);
        mainPanel.add(floorComboBox);

        capacityComboBox = new JComboBox<>();
        capacityComboBox.setBounds(646, 466, 470, 49);
        mainPanel.add(capacityComboBox);

        JLabel roomTypeLabel = new JLabel("Tipo de habitación");
        roomTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        roomTypeLabel.setBounds(122, 158, 309, 27);
        mainPanel.add(roomTypeLabel);

        JLabel rateTypeLabel = new JLabel("Tipo de tarifa");
        rateTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        rateTypeLabel.setBounds(646, 158, 309, 27);
        mainPanel.add(rateTypeLabel);

        JLabel selectedRoomsLabel = new JLabel("Habitaciones seleccionadas");
        selectedRoomsLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        selectedRoomsLabel.setBounds(122, 289, 309, 27);
        mainPanel.add(selectedRoomsLabel);

        JLabel floorLabel = new JLabel("Piso");
        floorLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        floorLabel.setBounds(122, 428, 309, 27);
        mainPanel.add(floorLabel);

        JLabel capacityLabel = new JLabel("Capacidad");
        capacityLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        capacityLabel.setBounds(646, 428, 309, 27);
        mainPanel.add(capacityLabel);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBackground(new Color(153, 89, 45));
        cancelButton.setBounds(808, 571, 140, 72);
        mainPanel.add(cancelButton);

        JButton deleteButton = new JButton("Aceptar");
        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(7, 26, 43));
        deleteButton.setBounds(976, 571, 140, 72);
        mainPanel.add(deleteButton);
    }
}
