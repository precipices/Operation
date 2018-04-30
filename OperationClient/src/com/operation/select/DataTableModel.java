package com.operation.select;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class DataTableModel extends AbstractTableModel {
	Vector<String> columnNames=new Vector<String>();
	Vector<Vector<String>> rowData=new Vector<Vector<String>>();

	public void setColumnNames(Vector<String> columnNames) {
		this.columnNames=columnNames;
	}
	public void setRowData(Vector<Vector<String>> rowData) {
		this.rowData=rowData;
	}
	@Override
	public String getColumnName(int col) {
		// TODO Auto-generated method stub
		return columnNames.get(col);
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.size();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rowData.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		// TODO Auto-generated method stub
		return rowData.get(rowIndex).get(colIndex);
	}

}
