package buttonCells;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import views.AuthView;

public class PanelAction extends JPanel {

	private static final long serialVersionUID = 1L;

	public ActionButton btnDelete;
	public ActionButton btnEdit;
	public ActionButton btnConsult;
	/**
	 * Create the panel.
	 */
	public PanelAction() {

		setLayout(new GridLayout(1,3));
		
		btnDelete = new ActionButton();
		btnDelete.setSize(45, 30);
		btnDelete.setVisible(true);
		ImageIcon btnDeleteOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/btnDelete.png"));
		Image btnDeleteScaledImage = btnDeleteOriginalIcon.getImage().getScaledInstance(45, 30, Image.SCALE_SMOOTH);
		ImageIcon btnDeleteScaledIcon = new ImageIcon(btnDeleteScaledImage);
		btnDelete.setIcon(btnDeleteScaledIcon);
		add(btnDelete);
		
		btnEdit = new ActionButton();
		btnEdit.setSize(45, 30);
		btnEdit.setVisible(true);
		ImageIcon btnEditOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/btnEdit.png"));
		Image btnEditScaledImage = btnEditOriginalIcon.getImage().getScaledInstance(45, 30, Image.SCALE_SMOOTH);
		ImageIcon btnEditScaledIcon = new ImageIcon(btnEditScaledImage);
		btnEdit.setIcon(btnEditScaledIcon);
		add(btnEdit);
		
		btnConsult = new ActionButton();
		btnConsult.setSize(45, 30);
		btnConsult.setVisible(true);
		ImageIcon btnConsultOriginalIcon = new ImageIcon(AuthView.class.getResource("/images/btnConsult.png"));
		Image btnConsultScaledImage = btnConsultOriginalIcon.getImage().getScaledInstance(45, 30, Image.SCALE_SMOOTH);
		ImageIcon btnConsultScaledIcon = new ImageIcon(btnConsultScaledImage);
		btnConsult.setIcon(btnConsultScaledIcon);
		add(btnConsult);
	}
	
	public void initEvent(TableActionEvent event, int row) {
		btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.onDelete(row);
            }
        });
		
		btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.onEdit(row);
            }
        });
		
		btnConsult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.onView(row);
            }
        });
    }
}
