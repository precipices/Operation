package com.operation.appoint;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.operation.common.Worker;
import com.operation.mainframe.InitComponent;
import com.operation.myComponent.BackButton;
import com.operation.myComponent.BackFrame;
import com.operation.myComponent.BackPane;
import com.operation.myComponent.BackScrollPane;
import com.operation.myComponent.BackToolBar;
import com.operation.myComponent.DateChooser;
import com.operation.select.DataTableModel;
import com.operation.select.SelectRoomPane;

public class SelectRoomPane1 extends BackPane {
	BackButton backParent, submit;
	BackToolBar toolBar;
	CardLayout card;
	JLabel beginDate;
	JTextField name;
	JTextField id;
	DataTableModel model;
	JTable table;
	Vector<Vector<String>> rowData = new Vector<Vector<String>>();
	AppointPane parentPane = null;

	public SelectRoomPane1(final AppointPane parentPane) {
		this.parentPane = parentPane;
		System.out.println("����������ѡ�����");
		this.setLayout(new BorderLayout());
		// ��:JLabel
		JLabel jl = new JLabel("����ʱ��Ҫ�����������:");
		this.add(jl, BorderLayout.NORTH);
		// ��:���ݱ��
		model = new DataTableModel();
		model.setColumnNames(getColumnNames());
		model.setRowData(getRowData());
		table = new JTable(model);
		BackScrollPane center = new BackScrollPane(table);
		this.add(center, BorderLayout.CENTER);

		// ��:�ύ,����
		backParent = new BackButton("����");
		submit = new BackButton("ѡ��");
		BackPane bottom = new BackPane();
		bottom.add(backParent);
		bottom.add(submit);
		this.add(bottom, BorderLayout.SOUTH);
		//ѡ��/���ؼ�����
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int select = table.getSelectedRow();
				if (select < 0) {
					JOptionPane.showMessageDialog(SelectRoomPane1.this, "δѡ��������!");
					return;
				}
				String id = (String) model.getValueAt(select, 0);
				JOptionPane.showMessageDialog(SelectRoomPane1.this, "ѡ����������" + id + "!");
				parentPane.showMainWithRoom(id);
			}
		});
		backParent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parentPane.showMain();
			}
		});
	}

	public Vector<String> getColumnNames() {
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("�����ұ��");
		columnNames.add("״̬");
		return columnNames;
	}

	public Vector<Vector<String>> getRowData() {
		return rowData;
	}

	public void setRowData(Vector<String> rooms) {
		Vector<Vector<String>> datas = new Vector<Vector<String>>();
		if (rooms != null && rooms.size() != 0) {
			Vector<String> room = null;
			for (int i = 0; i < rooms.size(); i++) {
				room = new Vector<String>();
				room.add(rooms.get(i));
				room.add("����");
				datas.add(room);
			}
		}
		this.rowData = datas;
		model.setRowData(rowData);
		model.fireTableDataChanged();
	}

	public static void main(String[] args) {
		SelectRoomPane1 p = new SelectRoomPane1(null);
		JFrame f = new BackFrame("test", "./imgs/bg2.jpg");
		f.add(p);
		f.setBounds(200, 100, 800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.repaint();
	}

	public void getData() {
		if (InitComponent.helper == null) {
			JOptionPane.showMessageDialog(SelectRoomPane1.this, "δ���ӷ�����!");
			return;
		}
		//�ӷ�������ȡ����
		Date begin=parentPane.getDate();
		System.out.println("ʱ��:"+begin);
		Vector<String> rooms=InitComponent.helper.selectRoomByDate(begin);
		this.setRowData(rooms);
	}
}