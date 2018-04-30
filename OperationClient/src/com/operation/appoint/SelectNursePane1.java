package com.operation.appoint;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.operation.common.Worker;
import com.operation.mainframe.InitComponent;
import com.operation.myComponent.BackButton;
import com.operation.myComponent.BackFrame;
import com.operation.myComponent.BackPane;
import com.operation.myComponent.BackScrollPane;
import com.operation.select.DataTableModel;

public class SelectNursePane1 extends BackPane {
	BackButton backParent, submit;
	DataTableModel model;
	JTable table;
	Vector<Vector<String>> rowData = new Vector<Vector<String>>();
	AppointPane parentPane=null;
	public SelectNursePane1(final AppointPane parentPane) {
		this.parentPane=parentPane;
		System.out.println("���뻤ʿѡ�����");
		this.setLayout(new BorderLayout());
		//��:JLabel
		JLabel jl=new JLabel("����ʱ��Ҫ��Ļ�ʿ��:");
		this.add(jl,BorderLayout.NORTH);

		// ��:���ݱ���
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
		// 3.ѡ��/���ؼ�����
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int select=table.getSelectedRow();
				if(select<0) {
					JOptionPane.showMessageDialog(SelectNursePane1.this, "δѡ��ʿ!");
					return;
				}
				String str=(String) model.getValueAt(select, 0);
				Worker nurse=InitComponent.helper.selectWorkerById(str);
				JOptionPane.showMessageDialog(SelectNursePane1.this, "ѡ����"+nurse.getName()+"!");
				parentPane.showMainWithNurse(nurse);
			}
		});
		backParent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parentPane.showMain();
			}
		});
	}

	public void getData() {
		if (InitComponent.helper == null) {
			JOptionPane.showMessageDialog(SelectNursePane1.this, "δ���ӷ�����!");
			return;
		}
		//�ӷ�������ȡ����
		Date begin=parentPane.getDate();
		System.out.println("ʱ��:"+begin);
		Vector<Worker> workers = InitComponent.helper.selectNursesByDate(begin);
		this.setRowData(workers);
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
		SelectNursePane1 p = new SelectNursePane1(null);
		JFrame f = new BackFrame("test", "./imgs/bg2.jpg");
		f.add(p);
		f.setBounds(200, 100, 800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.repaint();
	}
}