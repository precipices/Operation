package com.operation.myComponent;

import java.awt.Component;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class BackTable extends JTable {

	private void init() {
		this.setOpaque(false);
	}

	public BackTable() {
		super();
		init();
	}

	public BackTable(int arg0, int arg1) {
		super(arg0, arg1);
		init();
	}

	public BackTable(Object[][] arg0, Object[] arg1) {
		super(arg0, arg1);
		init();
	}

	public BackTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
		super(dm, cm, sm);
		init();
	}

	public BackTable(TableModel dm, TableColumnModel cm) {
		super(dm, cm);
		init();
	}

	public BackTable(TableModel dm) {
		super(dm);
		init();
	}

	public BackTable(Vector rowData, Vector columnNames) {
		super(rowData, columnNames);
		init();
	}
//	判断对象属于哪种类型的
//	if(obj instanceof class)
//	{
//	}
//	其返回true情况如下
//	1.obj是class类的对象
//	2.obj是class类子类的对象
	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);
		if (c instanceof JComponent) {
			((JComponent) c).setOpaque(false);
		}
		return c;
	}
}
