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

import com.operation.common.Operation;
import com.operation.mainframe.InitComponent;
import com.operation.myComponent.BackButton;
import com.operation.myComponent.BackFrame;
import com.operation.myComponent.BackPane;
import com.operation.myComponent.BackScrollPane;
import com.operation.myComponent.BackToolBar;
import com.operation.myComponent.DateChooser;

public class SelectMyOperationPane extends BackPane {
	BackToolBar toolBar;
	BackButton selectDate, selectName, selectId;
	CardLayout card;
	BackPane select;
	String selectType = "�����ڲ�ѯ";
	JLabel beginDate;
	JLabel endDate;
	JTextField name;
	JTextField id;
	DataTableModel model;
	JTable table;
	Vector<Vector<String>> rowData = new Vector<Vector<String>>();

	public SelectMyOperationPane() {
		this.setLayout(new BorderLayout());
		// ��:�߽粼��[������,��Ƭ����(���ڲ�ѯ,��������ѯ,ID��ѯ),��ʼ��ѯ]
		BackPane north = new BackPane();
		north.setLayout(new BorderLayout());
		this.add(north, BorderLayout.NORTH);
		// 1.������
		toolBar = new BackToolBar();
		selectDate = new BackButton("�����ڲ�ѯ");
		selectName = new BackButton("����������ѯ");
		selectId = new BackButton("��ID��ѯ");
		toolBar.add(selectDate);
		toolBar.add(selectName);
		toolBar.add(selectId);
		north.add(toolBar, BorderLayout.NORTH);
		// 2.��Ƭ����
		card = new CardLayout();
		select = new BackPane();
		select.setLayout(card);
		north.add(select, BorderLayout.CENTER);
		// (1).���ڲ�ѯ
		BackPane selectDatePane = new BackPane();
		JLabel jl1 = new JLabel("��������: ��:");
		beginDate = new JLabel("����ѡ��ʱ��");
		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser1.register(beginDate);
		JLabel jl2 = new JLabel("��:");
		endDate = new JLabel("����ѡ��ʱ��");
		DateChooser dateChooser2 = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser2.register(endDate);
		selectDatePane.add(jl1);
		selectDatePane.add(beginDate);
		selectDatePane.add(jl2);
		selectDatePane.add(endDate);
		select.add(selectDatePane, "�����ڲ�ѯ");
		// (2).��������ѯ
		BackPane selectNamePane = new BackPane();
		JLabel jl3 = new JLabel("������:");
		name = new JTextField(10);
		selectNamePane.add(jl3);
		selectNamePane.add(name);
		select.add(selectNamePane, "����������ѯ");
		// (3).ID��ѯ
		BackPane selectIDPane = new BackPane();
		JLabel jl4 = new JLabel("ID:");
		id = new JTextField(10);
		selectIDPane.add(jl4);
		selectIDPane.add(id);
		select.add(selectIDPane, "��ID��ѯ");
		// 3.��ʼ��ѯ
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
		JLabel jl = new JLabel("������ص�����");
		this.add(jl, BorderLayout.SOUTH);
		// ������
		// 1.��ѯ��ʽѡ�������
		selectDate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(select, "�����ڲ�ѯ");
				selectType = "�����ڲ�ѯ";
			}
		});
		selectName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(select, "����������ѯ");
				selectType = "����������ѯ";
			}
		});
		selectId.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(select, "��ID��ѯ");
				selectType = "��ID��ѯ";
			}
		});
		// 2.��ʼ��ѯ������
		beginSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (selectType.equals("�����ڲ�ѯ")) {
					System.out.println("�����ڲ�ѯ");
					if (InitComponent.helper == null) {
						JOptionPane.showMessageDialog(SelectMyOperationPane.this, "δ���ӷ�����!");
					} else {
						Date begin = null;
						Date end=null;
						try {
							begin = Date.valueOf(beginDate.getText());
							end=Date.valueOf(endDate.getText());
						} catch (Exception e) {
							JOptionPane.showMessageDialog(SelectMyOperationPane.this, "��ѡ������!");
							return;
						}
						if (begin.getTime() > new java.util.Date().getTime() - 24 * 3600 * 1000) {
							System.out.println("begin.getTime()>now.getTime()-24*3600*1000");
						} else {
							JOptionPane.showMessageDialog(SelectMyOperationPane.this, "���ɲ�ѯ��ȥ���Ű�!");
							return;
						}
						if(begin.getTime()>end.getTime()) {
							JOptionPane.showMessageDialog(SelectMyOperationPane.this, "��ʼʱ�� ���ɳ�������ʱ��!");
							return;
						}else {
							Vector<Operation> operations=InitComponent.helper.selectWorkerAllOperationsBetween(InitComponent.worker.getId(),begin, end);
							SelectMyOperationPane.this.setRowData(operations);
						}
					}
				} else if (selectType.equals("����������ѯ")) {
					System.out.println("����������ѯ");
					if (InitComponent.helper == null) {
						JOptionPane.showMessageDialog(SelectMyOperationPane.this, "δ���ӷ�����!");
					} else {
						Vector<Operation> operations=InitComponent.helper.selectOperationByName(name.getText());
						SelectMyOperationPane.this.setRowData(operations);
					}

				} else if (selectType.equals("��ID��ѯ")) {
					System.out.println("��ID��ѯ");
					if (InitComponent.helper == null) {
						JOptionPane.showMessageDialog(SelectMyOperationPane.this, "δ���ӷ�����!");
					} else {
						Operation operation=InitComponent.helper.selectOperationById(id.getText());
						SelectMyOperationPane.this.setRowData(operation);
					}

				}
			}
		});

	}
	public Vector<String> getColumnNames() {
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("ID");
		columnNames.add("������");
		columnNames.add("��ʼʱ��");
		columnNames.add("������");
		columnNames.add("�� ��");
		columnNames.add("ҽ��");
		columnNames.add("��ʿ");
		columnNames.add("����ʦ");
		return columnNames;
	}

	public Vector<Vector<String>> getRowData() {
		return rowData;
	}

	public void setRowData(Vector<Operation> operations) {
		Vector<Vector<String>> datas = new Vector<Vector<String>>();
		if (operations != null && operations.size() != 0)
			for (int i = 0; i < operations.size(); i++) {
				datas.add(operations.get(i).toVector());
			}
		this.rowData = datas;
		model.setRowData(rowData);
		model.fireTableDataChanged();
	}

	public void setRowData(Operation operation) {
		Vector<Vector<String>> datas = new Vector<Vector<String>>();
		if (operation != null)
			datas.add(operation.toVector());
		this.rowData = datas;
		model.setRowData(rowData);
		model.fireTableDataChanged();
	}

	public static void main(String[] args) {
		SelectMyOperationPane p = new SelectMyOperationPane();
		JFrame f = new BackFrame("test", "./imgs/bg2.jpg");
		f.add(p);
		f.setBounds(200, 100, 800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.repaint();
	}
}