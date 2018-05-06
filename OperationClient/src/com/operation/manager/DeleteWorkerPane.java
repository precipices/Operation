package com.operation.manager;

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

import com.operation.appoint.SelectNursePane1;
import com.operation.common.Worker;
import com.operation.mainframe.InitComponent;
import com.operation.myComponent.BackButton;
import com.operation.myComponent.BackFrame;
import com.operation.myComponent.BackPane;
import com.operation.myComponent.BackScrollPane;
import com.operation.myComponent.BackToolBar;
import com.operation.myComponent.DateChooser;
import com.operation.select.DataTableModel;

public class DeleteWorkerPane extends BackPane {
	BackToolBar toolBar;
	BackButton selectDate, selectName, selectId;
	CardLayout card;
	BackPane select;
	String selectType = "��ѯȫ���˺�";
	JLabel beginDate;
	JTextField name;
	JTextField id;
	DataTableModel model;
	JTable table;
	Vector<Vector<String>> rowData = new Vector<Vector<String>>();

	public DeleteWorkerPane() {
		System.out.println("�����˺Ų�ѯ����");
		this.setLayout(new BorderLayout());
		// ��:�߽粼��[������,��Ƭ����(���ڲ�ѯ,������ѯ,ID��ѯ),��ʼ��ѯ]
		BackPane north = new BackPane();
		north.setLayout(new BorderLayout());
		this.add(north, BorderLayout.NORTH);
		// 1.������
		toolBar = new BackToolBar();
		selectDate = new BackButton("��ѯȫ���˺�");
		selectName = new BackButton("��������ѯ");
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
		JLabel jl1 = new JLabel("��ѯȫ���˺�:");
		// beginDate = new JLabel("����ѡ��ʱ��");
		// DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		// dateChooser1.register(beginDate);
		selectDatePane.add(jl1);
		// selectDatePane.add(beginDate);
		select.add(selectDatePane, "��ѯȫ���˺�");
		// (2).������ѯ
		BackPane selectNamePane = new BackPane();
		JLabel jl3 = new JLabel("����:");
		name = new JTextField(10);
		selectNamePane.add(jl3);
		selectNamePane.add(name);
		select.add(selectNamePane, "��������ѯ");
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

		// ��:���ݱ��
		model = new DataTableModel();
		model.setColumnNames(getColumnNames());
		model.setRowData(getRowData());
		table = new JTable(model);
		BackScrollPane center = new BackScrollPane(table);
		this.add(center, BorderLayout.CENTER);

		BackButton delete;
		// ��:�ύ,����
		delete = new BackButton("ɾ��");
		BackPane bottom = new BackPane();
		bottom.add(delete);
		this.add(bottom, BorderLayout.SOUTH);
		// 3.ѡ��/���ؼ�����
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int select = table.getSelectedRow();
				if (select < 0) {
					JOptionPane.showMessageDialog(DeleteWorkerPane.this, "δѡ���˺�!");
					return;
				}
				String str = (String) model.getValueAt(select, 0);
				if(str.equals("w0000")) {
					JOptionPane.showMessageDialog(DeleteWorkerPane.this, "����Ա�˺Ų���ɾ��!");
					return;
				}
				if(JOptionPane.showConfirmDialog(DeleteWorkerPane.this, "ȷ��ɾ��?","����",JOptionPane.YES_NO_OPTION)>=1) {
					return;
				}
				InitComponent.helper.deleteWorker(str);
				JOptionPane.showMessageDialog(DeleteWorkerPane.this, "ɾ���ɹ�!");
//				refresh------------------------------
			}
		});
		// ������
		// 1.��ѯ��ʽѡ�������
		selectDate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(select, "��ѯȫ���˺�");
				selectType = "��ѯȫ���˺�";
			}
		});
		selectName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(select, "��������ѯ");
				selectType = "��������ѯ";
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
				if (selectType.equals("��ѯȫ���˺�")) {
					System.out.println("��ѯȫ���˺�");
					if (InitComponent.helper == null) {
						JOptionPane.showMessageDialog(DeleteWorkerPane.this, "δ���ӷ�����!");
					} else {
						Vector<Worker> workers = InitComponent.helper.selectAllWorkers();
						DeleteWorkerPane.this.setRowData(workers);
					}
				} else if (selectType.equals("��������ѯ")) {
					System.out.println("��������ѯ");
					if (InitComponent.helper == null) {
						JOptionPane.showMessageDialog(DeleteWorkerPane.this, "δ���ӷ�����!");
					} else {
						Vector<Worker> workers = InitComponent.helper.selectWorkerByName(name.getText());
						DeleteWorkerPane.this.setRowData(workers);
					}

				} else if (selectType.equals("��ID��ѯ")) {
					System.out.println("��ID��ѯ");
					if (InitComponent.helper == null) {
						JOptionPane.showMessageDialog(DeleteWorkerPane.this, "δ���ӷ�����!");
					} else {
						Worker worker = InitComponent.helper.selectWorkerById(id.getText());
						DeleteWorkerPane.this.setRowData(worker);
					}

				}
			}
		});

	}

	public Vector<String> getColumnNames() {
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("ID");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("��������");
		columnNames.add("ְλ");
		columnNames.add("��ϵ��ʽ");
		columnNames.add("����");
		return columnNames;
	}

	public Vector<Vector<String>> getRowData() {
		return rowData;
	}

	public void setRowData(Vector<Worker> workers) {
		Vector<Vector<String>> datas = new Vector<Vector<String>>();
		if (workers != null && workers.size() != 0)
			for (int i = 0; i < workers.size(); i++) {
				datas.add(workers.get(i).toVector());
			}
		this.rowData = datas;
		model.setRowData(rowData);
		model.fireTableDataChanged();
	}

	public void setRowData(Worker worker) {
		Vector<Vector<String>> datas = new Vector<Vector<String>>();
		if (worker != null)
			datas.add(worker.toVector());
		this.rowData = datas;
		model.setRowData(rowData);
		model.fireTableDataChanged();
	}

	public static void main(String[] args) {
		DeleteWorkerPane p = new DeleteWorkerPane();
		JFrame f = new BackFrame("test", "./imgs/bg2.jpg");
		f.add(p);
		f.setBounds(200, 100, 800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.repaint();
	}
}