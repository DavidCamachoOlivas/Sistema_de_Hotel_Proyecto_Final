package buttonCells;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import models.Tariff;
import views.AuthView;

public class PanelAction2 extends JPanel {

	private static final long serialVersionUID = 1L;

	public ActionButton btnDelete;
	public ActionButton btnEdit;
	/**
	 * Create the panel.
	 */
	public PanelAction2() {

		setLayout(new GridLayout(1,2));
		
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
		
		
	}
	
	public void initEvent(TableActionEvent2 event, int row) {
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
		
    }
	public void initEvent(TableActionEvent2 event, int row, Tariff t, DefaultTableModel model) {
	    btnDelete.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent ae) {
	            event.onDelete(row, t, model);
	        }
	    });

	    btnEdit.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent ae) {
	            event.onEdit(row);
	        }
	    });
	}
	
}
