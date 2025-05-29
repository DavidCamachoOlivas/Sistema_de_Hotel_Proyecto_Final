package viewsWB;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class RoomsTypeWB extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel mainPanel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	RoomsTypeWB frame = new RoomsTypeWB();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
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
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 252, 247));
        mainPanel.setBounds(new Rectangle(0, 0, 720, 1280));
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
        backArrowLabel.setBounds(157, 21, 128, 75);
        Image backIcon = new ImageIcon("src/flecha.png").getImage().getScaledInstance(76, 58, Image.SCALE_SMOOTH);
        backArrowLabel.setIcon(new ImageIcon(backIcon));
        headerPanel.add(backArrowLabel);

        JPanel standardRoomPanel = new JPanel();
        standardRoomPanel.setBounds(130, 169, 310, 381);
        mainPanel.add(standardRoomPanel);
        standardRoomPanel.setBorder(BorderFactory.createLineBorder(Color.black));  
        standardRoomPanel.setLayout(new GridLayout(2, 0, 0, 0));
        
        JPanel standardImagePanel = new JPanel();
        Image standardIcon = new ImageIcon("src/cama.png").getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        JLabel standardImageLabel = new JLabel(new ImageIcon(standardIcon));
        standardImageLabel.setBounds(0, 0, 900, 500);
        standardImagePanel.setOpaque(false);
        standardImagePanel.setBounds(0, 0, 900, 500);
        standardImageLabel.add(standardImagePanel);
        standardRoomPanel.add(standardImageLabel);
        
        JPanel standardInfoPanel = new JPanel();
        standardRoomPanel.add(standardInfoPanel);
        standardInfoPanel.setLayout(null);
        
        JLabel standardRoomLabel = new JLabel("Habitación estándar");
        standardRoomLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
        standardRoomLabel.setBounds(10, 31, 271, 46);
        standardInfoPanel.add(standardRoomLabel);
        
        JLabel standardPriceLabel = new JLabel("$1,000 MXN por noche");
        standardPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        standardPriceLabel.setBounds(10, 77, 182, 30);
        standardInfoPanel.add(standardPriceLabel);
        
        JButton deleteButtonStandard = new JButton("Eliminar");
        deleteButtonStandard.setBackground(new Color(153, 89, 45));
        deleteButtonStandard.setForeground(Color.WHITE);
        deleteButtonStandard.setBounds(10, 118, 89, 49);
        standardInfoPanel.add(deleteButtonStandard);
        
        JButton editButtonStandard = new JButton("Editar");
        editButtonStandard.setBackground(Color.WHITE);
        editButtonStandard.setBounds(110, 118, 89, 49);
        standardInfoPanel.add(editButtonStandard);
        
        JButton detailsButtonStandard = new JButton("Detalles");
        detailsButtonStandard.setForeground(Color.WHITE);
        detailsButtonStandard.setBackground(new Color(7, 26, 43));
        detailsButtonStandard.setBounds(209, 118, 89, 49);
        standardInfoPanel.add(detailsButtonStandard);

        JPanel deluxeRoomPanel = new JPanel();
        deluxeRoomPanel.setBounds(468, 169, 310, 381);
        mainPanel.add(deluxeRoomPanel);
        deluxeRoomPanel.setBorder(BorderFactory.createLineBorder(Color.black));  
        deluxeRoomPanel.setLayout(new GridLayout(2, 0, 0, 0));
        
        JPanel deluxeImagePanel = new JPanel();
        Image deluxeIcon = new ImageIcon("src/suite.png").getImage().getScaledInstance(500, 200, Image.SCALE_SMOOTH);
        JLabel deluxeImageLabel = new JLabel(new ImageIcon(deluxeIcon));
        deluxeImageLabel.setBounds(0, 0, 900, 500);
        deluxeImagePanel.setOpaque(false);
        deluxeImagePanel.setBounds(0, 0, 900, 500);
        deluxeImageLabel.add(deluxeImagePanel);
        deluxeRoomPanel.add(deluxeImageLabel);
        
        JPanel deluxeInfoPanel = new JPanel();
        deluxeRoomPanel.add(deluxeInfoPanel);
        deluxeInfoPanel.setLayout(null);
        
        JLabel deluxeRoomLabel = new JLabel("Habitación de lujo");
        deluxeRoomLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
        deluxeRoomLabel.setBounds(10, 29, 271, 46);
        deluxeInfoPanel.add(deluxeRoomLabel);
        
        JLabel deluxePriceLabel = new JLabel("$2,000 MXN por noche");
        deluxePriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        deluxePriceLabel.setBounds(10, 74, 182, 30);
        deluxeInfoPanel.add(deluxePriceLabel);
        
        JButton deleteButtonDeluxe = new JButton("Eliminar");
        deleteButtonDeluxe.setForeground(Color.WHITE);        
        deleteButtonDeluxe.setBackground(new Color(153, 89, 45));
        deleteButtonDeluxe.setBounds(10, 115, 89, 49);
        deluxeInfoPanel.add(deleteButtonDeluxe);
        
        JButton editButtonDeluxe = new JButton("Editar");
        editButtonDeluxe.setBackground(Color.WHITE);
        editButtonDeluxe.setBounds(109, 115, 89, 49);
        deluxeInfoPanel.add(editButtonDeluxe);
        
        JButton detailsButtonDeluxe = new JButton("Detalles");
        detailsButtonDeluxe.setForeground(Color.WHITE);
        detailsButtonDeluxe.setBackground(new Color(7, 26, 43));
        detailsButtonDeluxe.setBounds(209, 115, 89, 49);
        deluxeInfoPanel.add(detailsButtonDeluxe);

        JPanel suiteRoomPanel = new JPanel();
        suiteRoomPanel.setBounds(811, 169, 310, 381);
        mainPanel.add(suiteRoomPanel);
        suiteRoomPanel.setBorder(BorderFactory.createLineBorder(Color.black));  
        suiteRoomPanel.setLayout(new GridLayout(2, 0, 0, 0));
        
        JPanel suiteImagePanel = new JPanel();
        Image suiteIcon = new ImageIcon("src/normal.png").getImage().getScaledInstance(500, 200, Image.SCALE_SMOOTH);
        JLabel suiteImageLabel = new JLabel(new ImageIcon(suiteIcon));
        suiteImageLabel.setBounds(0, 0, 900, 500);
        suiteImagePanel.setOpaque(false);
        suiteImagePanel.setBounds(0, 0, 900, 500);
        suiteImageLabel.add(suiteImagePanel);
        suiteRoomPanel.add(suiteImageLabel);
        
        JPanel suiteInfoPanel = new JPanel();
        suiteRoomPanel.add(suiteInfoPanel);
        suiteInfoPanel.setLayout(null);
        
        JLabel suiteRoomLabel = new JLabel("Suite");
        suiteRoomLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
        suiteRoomLabel.setBounds(10, 29, 271, 46);
        suiteInfoPanel.add(suiteRoomLabel);
        
        JLabel suitePriceLabel = new JLabel("$3,000 MXN por noche");
        suitePriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        suitePriceLabel.setBounds(10, 74, 182, 30);
        suiteInfoPanel.add(suitePriceLabel);
        
        JButton deleteButtonSuite = new JButton("Eliminar");
        deleteButtonSuite.setForeground(Color.WHITE);
        deleteButtonSuite.setBackground(new Color(153, 89, 45));
        deleteButtonSuite.setBounds(10, 114, 89, 49);
        suiteInfoPanel.add(deleteButtonSuite);
        
        JButton editButtonSuite = new JButton("Editar");
        editButtonSuite.setBackground(Color.WHITE);
        editButtonSuite.setBounds(109, 114, 89, 49);
        suiteInfoPanel.add(editButtonSuite);
        
        JButton detailsButtonSuite = new JButton("Detalles");
        detailsButtonSuite.setForeground(Color.WHITE);
        detailsButtonSuite.setBackground(new Color(7, 26, 43));
        detailsButtonSuite.setBounds(209, 114, 89, 49);
        suiteInfoPanel.add(detailsButtonSuite);

        JButton pageButton1 = new JButton("1");
        pageButton1.setBackground(Color.WHITE);
        pageButton1.setBounds(514, 561, 44, 44);mainPanel.add(pageButton1);
        
        JButton pageButton2 = new JButton("2");
        pageButton2.setBackground(Color.WHITE);
        pageButton2.setBounds(568, 561, 44, 44);
        mainPanel.add(pageButton2);
        
        JButton pageButton3 = new JButton("3");
        pageButton3.setBackground(Color.WHITE);
        pageButton3.setBounds(622, 561, 44, 44);
        mainPanel.add(pageButton3);
        
        JButton pageButtonMore = new JButton("...");
        pageButtonMore.setBackground(Color.WHITE);
        pageButtonMore.setBounds(676, 561, 44, 44);
        mainPanel.add(pageButtonMore);
        
        JButton newRoomTypeButton = new JButton("Nuevo tipo");
        newRoomTypeButton.setFont(new Font("Tahoma", Font.BOLD, 25));
        newRoomTypeButton.setForeground(Color.WHITE);
        newRoomTypeButton.setBackground(new Color(7, 26, 43));
        newRoomTypeButton.setBounds(395, 621, 459, 49);
        mainPanel.add(newRoomTypeButton);
    }
}
