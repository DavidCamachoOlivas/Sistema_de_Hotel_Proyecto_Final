package buttonCells;

import javax.swing.table.DefaultTableModel;

import models.Tariff;

public interface TableActionEvent2 {

    public void onEdit(int row);

    public void onDelete(int row);

	void onDelete(int row, Tariff t);

	void onDelete(int row, Tariff t, DefaultTableModel model);

}
