package buttonCells;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TableActionCellRender2 implements TableCellRenderer{
	public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean bln1, int row, int column) {
		PanelAction2 action = new PanelAction2();
		return action;
		
	}

}
