package buttonCells;

import java.awt.Component;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

import models.Rental;
import models.RentalsModel;
import models.Tariff;

public class TableActionCellEditor2 extends DefaultCellEditor {

    private static final long serialVersionUID = -3635471702436514097L;
    private TableActionEvent2 event;
    private DefaultTableModel model;
    private List<Tariff> tarifas;

    public TableActionCellEditor2(TableActionEvent2 event, List<Tariff> tarifas, DefaultTableModel model) {
        super(new JCheckBox());
        this.event = event;
        this.model = model;
        this.tarifas = tarifas;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        PanelAction2 action = new PanelAction2();
        // Convierte el índice de vista a índice de modelo si usas sorter:
        int modelRow = table.convertRowIndexToModel(row);
        // Supongamos que tu Rental está en la columna 0 ó lo recuperas por ID:
        Object id = model.getValueAt(modelRow, 0);
        RentalsModel rm = new RentalsModel();
        Rental r = new Rental();
		try {
			r = rm.getRentalById(Integer.parseInt(id.toString()));
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        action.initEvent(event, modelRow, r, model);
        return action;
    }

    
}
