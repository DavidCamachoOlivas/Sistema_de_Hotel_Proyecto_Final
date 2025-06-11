package buttonCells;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TableActionCellRender2 implements TableCellRenderer{
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean hasFocus, int row, int column) {
		PanelAction2 action = new PanelAction2();
		
		// Establecer el color de fondo según la selección
		if (isSelected) {
			action.setBackground(table.getSelectionBackground());
		} else {
			action.setBackground(table.getBackground());
		}
		
		return action;
	}
}