package com.operation.select;

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

public class SelectRoomPane extends BackPane {
	BackToolBar toolBar;
	CardLayout card;
	JLabel beginDate;
	JTextField name;
	JTextField id;
	DataTableModel model;
	JTable table;
	Vector<Vector<String>> rowData = new Vector<Vector<String>>();

	public SelectRoomPane() {
		System.out.println("���������Ҳ�ѯ����");
		this.setLayout(new BorderLayout());
		// ��:���ڲ�ѯ,��ʼ��ѯ
		BackPane north = new BackPane();
		north.setLayout(new BorderLayout());
		this.add(north, BorderLayout.NORTH);
		// 1.���ڲ�ѯ
		BackPane selectDatePane = new BackPane();
		JLabel jl1 = new JLabel("��������:");
		beginDate = new JLabel("����ѡ��ʱ��");
		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser1.register(beginDate);
		selectDatePane.add(jl1);
		selectDatePane.add(beginDate);
		north.add(selectDatePane, BorderLayout.CENTER);
		// 2.��ʼ��ѯ
		BackButton beginSelect = new BackButton("��ʼ��ѯ");
		north.add(beginSelect, BorderLayout.SOUTH);

		// ��:���ݱ���
		model = new DataTableModel();
		model.setColumnNames(getColumnNames());
		model.setRowData(getRowData());
		table = new JTable(model);
		BackScrollPane center = new BackScrollPane(table);
		this.add(center, BorderLayout.CENTER);

		// ��:��ʾ��ѯ����
		JLabel jl = new JLabel("��ѯ������");
		this.add(jl, BorderLayout.SOUTH);
		// ������
		// 1.��ʼ��ѯ������
		beginSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (InitComponent.helper == null) {
					JOptionPane.showMessageDialog(SelectRoomPane.this, "δ���ӷ�����!");
				} else {
					Date begin = null;
					try {
						begin = Date.valueOf(beginDate.getText());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(SelectRoomPane.this, "��ѡ������!");
						return;
					}
					if (begin.getTime() > new java.util.Date().getTime() - 24 * 3600 * 1000) {
						System.out.println("begin.getTime()>now.getTime()-24*3600*1000");
						Vector<String> rooms=InitComponent.helper.selectRoomByDate(begin);
						SelectRoomPane.this.setRowData(rooms);
					} else {
						JOptionPane.showMessageDialog(SelectRoomPane.this, "���ɲ�ѯ��ȥ���Ű�!");
					}
				}

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
			Vector<String> room=null;
			for (int i = 0; i < rooms.size(); i++) {
				room=new Vector<String>();
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
		SelectRoomPane p = new SelectRoomPane();
		JFrame f = new BackFrame("test", "./imgs/bg2.jpg");
		f.add(p);
		f.setBounds(200, 100, 800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.repaint();
	}
}