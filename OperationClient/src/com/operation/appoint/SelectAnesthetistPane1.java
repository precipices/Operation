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

public class SelectAnesthetistPane1 extends BackPane {
	BackButton backParent, submit;
	DataTableModel model;
	JTable table;
	Vector<Vector<String>> rowData = new Vector<Vector<String>>();
	public AppointPane parentPane=null;
	public SelectAnesthetistPane1(final AppointPane parentPane) {
		this.parentPane=parentPane;
		System.out.println("进入麻醉师选择界面");
		this.setLayout(new BorderLayout());
		//上:JLabel
		JLabel jl=new JLabel("符合时间要求的麻醉师有:");
		this.add(jl,BorderLayout.NORTH);

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
		// 3.选择/返回监听器
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int select=table.getSelectedRow();
				if(select<0) {
					JOptionPane.showMessageDialog(SelectAnesthetistPane1.this, "未选择麻醉师!");
					return;
				}
				String str=(String) model.getValueAt(select, 0);
				Worker anesthetist=InitComponent.helper.selectWorkerById(str);
				JOptionPane.showMessageDialog(SelectAnesthetistPane1.this, "选择了"+anesthetist.getName()+"!");
				parentPane.showMainWithAnesthetist(anesthetist);
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
			JOptionPane.showMessageDialog(SelectAnesthetistPane1.this, "未连接服务器!");
			return;
		}
		//从服务器获取数据
		Date begin=parentPane.getDate();
		System.out.println("时间:"+begin);
		Vector<Worker> workers = InitComponent.helper.selectAnesthetistsByDate(begin);
		if(workers!=null&&workers.size()!=0)
		this.setRowData(workers);
	}
	public Vector<String> getColumnNames() {
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("ID");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("出生日期");
		columnNames.add("职位");
		columnNames.add("联系方式");
		columnNames.add("科室");
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
		SelectAnesthetistPane1 p = new SelectAnesthetistPane1(null);
		JFrame f = new BackFrame("test", "./imgs/bg2.jpg");
		f.add(p);
		f.setBounds(200, 100, 800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.repaint();
	}
}