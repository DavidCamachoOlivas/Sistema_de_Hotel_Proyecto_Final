package buttonCells;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JCheckBox;

public class TableActionCellEditor extends DefaultCellEditor {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3635471702436514097L;
	private TableActionEvent event;
	private TableActionEvent2 event2;

    public TableActionCellEditor(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

	@Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        PanelAction action = new PanelAction();
        action.initEvent(event, row);
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}