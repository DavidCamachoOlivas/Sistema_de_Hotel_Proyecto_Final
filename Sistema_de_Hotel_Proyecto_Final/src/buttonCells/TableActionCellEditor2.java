package buttonCells;

import java.awt.Component;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

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

    public TableActionCellEditor2(TableActionEvent2 event) {
        super(new JCheckBox());
        this.event = event;
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        PanelAction2 action = new PanelAction2();
        Tariff t = tarifas.get(row);
        action.initEvent(event, row, t, model);
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}
