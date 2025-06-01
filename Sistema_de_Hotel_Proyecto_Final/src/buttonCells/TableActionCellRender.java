package buttonCells;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TableActionCellRender implements TableCellRenderer{
	public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean bln1, int row, int column) {
		PanelAction action = new PanelAction();
		return action;
		
	}

}
