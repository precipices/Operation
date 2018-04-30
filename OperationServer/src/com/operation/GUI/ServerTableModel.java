package com.operation.GUI;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class ServerTableModel extends AbstractTableModel {

	// private final static int colCount = 3;
	// private String[] colNames = { "�����ͻ�id", "�߳���", "��һ��ִ�е�����" };
	private Vector<String> colNames = null;
	private static Vector<Vector> rowData = new Vector<Vector>();
	// private static boolean isUsing = false;

	public ServerTableModel() {
		// rowData = ThreadPool.getServersData();
		colNames = new Vector<String>();
		colNames.add("�����ͻ�id");
		colNames.add("�߳���");
		colNames.add("��һ��ִ�е�����");
	}

	public static void addRow(Vector row) {
		// synchronized (ServerTableModel.class) {
		// isUsing = true;
		rowData.add(row);
		// isUsing = false;
		// }
	}

	public static void removeRow(Vector row) {
		// synchronized (ServerTableModel.class) {
		// isUsing = true;
		rowData.remove(row);
		// isUsing = false;
		// }
	}

	@Override
	public String getColumnName(int column) {
		return colNames.get(column);
	}

	@Override
	public int getColumnCount() {
		return colNames.size();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rowData.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// synchronized (ServerTableModel.class) {
		return rowData.get(rowIndex).get(columnIndex);
		// }
	}
//
//	@Override
//	public void fireTableDataChanged() {
//		synchronized(ServerTableModel.class) {
//			super.fireTableDataChanged();	
//		}
//	}
	// public boolean isUsing() {
	// return isUsing;
	// }

}