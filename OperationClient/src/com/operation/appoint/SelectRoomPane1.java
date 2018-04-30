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
		System.out.println("进入手术室选择界面");
		this.setLayout(new BorderLayout());
		// 上:JLabel
		JLabel jl = new JLabel("符合时间要求的手术室有:");
		this.add(jl, BorderLayout.NORTH);
		// 中:数据表格
		model = new DataTableModel();
		model.setColumnNames(getColumnNames());
		model.setRowData(getRowData());
		table = new JTable(model);
		BackScrollPane center = new BackScrollPane(table);
		this.add(center, BorderLayout.CENTER);

		// 下:提交,返回
		backParent = new BackButton("返回");
		submit = new BackButton("选择");
		BackPane bottom = new BackPane();
		bottom.add(backParent);
		bottom.add(submit);
		this.add(bottom, BorderLayout.SOUTH);
		//选择/返回监听器
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int select = table.getSelectedRow();
				if (select < 0) {
					JOptionPane.showMessageDialog(SelectRoomPane1.this, "未选择手术室!");
					return;
				}
				String id = (String) model.getValueAt(select, 0);
				JOptionPane.showMessageDialog(SelectRoomPane1.this, "选择了手术室" + id + "!");
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
		columnNames.add("手术室编号");
		columnNames.add("状态");
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
				room.add("空闲");
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
			JOptionPane.showMessageDialog(SelectRoomPane1.this, "未连接服务器!");
			return;
		}
		//从服务器获取数据
		Date begin=parentPane.getDate();
		System.out.println("时间:"+begin);
		Vector<String> rooms=InitComponent.helper.selectRoomByDate(begin);
		this.setRowData(rooms);
	}
}