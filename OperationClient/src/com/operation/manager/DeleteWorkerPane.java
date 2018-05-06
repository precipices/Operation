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
	String selectType = "查询全部账号";
	JLabel beginDate;
	JTextField name;
	JTextField id;
	DataTableModel model;
	JTable table;
	Vector<Vector<String>> rowData = new Vector<Vector<String>>();

	public DeleteWorkerPane() {
		System.out.println("进入账号查询界面");
		this.setLayout(new BorderLayout());
		// 上:边界布局[工具条,卡片布局(日期查询,姓名查询,ID查询),开始查询]
		BackPane north = new BackPane();
		north.setLayout(new BorderLayout());
		this.add(north, BorderLayout.NORTH);
		// 1.工具条
		toolBar = new BackToolBar();
		selectDate = new BackButton("查询全部账号");
		selectName = new BackButton("按姓名查询");
		selectId = new BackButton("按ID查询");
		toolBar.add(selectDate);
		toolBar.add(selectName);
		toolBar.add(selectId);
		north.add(toolBar, BorderLayout.NORTH);
		// 2.卡片布局
		card = new CardLayout();
		select = new BackPane();
		select.setLayout(card);
		north.add(select, BorderLayout.CENTER);
		// (1).日期查询
		BackPane selectDatePane = new BackPane();
		JLabel jl1 = new JLabel("查询全部账号:");
		// beginDate = new JLabel("单击选择时间");
		// DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		// dateChooser1.register(beginDate);
		selectDatePane.add(jl1);
		// selectDatePane.add(beginDate);
		select.add(selectDatePane, "查询全部账号");
		// (2).姓名查询
		BackPane selectNamePane = new BackPane();
		JLabel jl3 = new JLabel("姓名:");
		name = new JTextField(10);
		selectNamePane.add(jl3);
		selectNamePane.add(name);
		select.add(selectNamePane, "按姓名查询");
		// (3).ID查询
		BackPane selectIDPane = new BackPane();
		JLabel jl4 = new JLabel("ID:");
		id = new JTextField(10);
		selectIDPane.add(jl4);
		selectIDPane.add(id);
		select.add(selectIDPane, "按ID查询");
		// 3.开始查询
		BackButton beginSelect = new BackButton("开始查询");
		north.add(beginSelect, BorderLayout.SOUTH);

		// 中:数据表格
		model = new DataTableModel();
		model.setColumnNames(getColumnNames());
		model.setRowData(getRowData());
		table = new JTable(model);
		BackScrollPane center = new BackScrollPane(table);
		this.add(center, BorderLayout.CENTER);

		BackButton delete;
		// 下:提交,返回
		delete = new BackButton("删除");
		BackPane bottom = new BackPane();
		bottom.add(delete);
		this.add(bottom, BorderLayout.SOUTH);
		// 3.选择/返回监听器
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int select = table.getSelectedRow();
				if (select < 0) {
					JOptionPane.showMessageDialog(DeleteWorkerPane.this, "未选择账号!");
					return;
				}
				String str = (String) model.getValueAt(select, 0);
				if(str.equals("w0000")) {
					JOptionPane.showMessageDialog(DeleteWorkerPane.this, "管理员账号不可删除!");
					return;
				}
				if(JOptionPane.showConfirmDialog(DeleteWorkerPane.this, "确定删除?","警告",JOptionPane.YES_NO_OPTION)>=1) {
					return;
				}
				InitComponent.helper.deleteWorker(str);
				JOptionPane.showMessageDialog(DeleteWorkerPane.this, "删除成功!");
//				refresh------------------------------
			}
		});
		// 监听器
		// 1.查询方式选择监听器
		selectDate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(select, "查询全部账号");
				selectType = "查询全部账号";
			}
		});
		selectName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(select, "按姓名查询");
				selectType = "按姓名查询";
			}
		});
		selectId.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(select, "按ID查询");
				selectType = "按ID查询";
			}
		});
		// 2.开始查询监听器
		beginSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (selectType.equals("查询全部账号")) {
					System.out.println("查询全部账号");
					if (InitComponent.helper == null) {
						JOptionPane.showMessageDialog(DeleteWorkerPane.this, "未连接服务器!");
					} else {
						Vector<Worker> workers = InitComponent.helper.selectAllWorkers();
						DeleteWorkerPane.this.setRowData(workers);
					}
				} else if (selectType.equals("按姓名查询")) {
					System.out.println("按姓名查询");
					if (InitComponent.helper == null) {
						JOptionPane.showMessageDialog(DeleteWorkerPane.this, "未连接服务器!");
					} else {
						Vector<Worker> workers = InitComponent.helper.selectWorkerByName(name.getText());
						DeleteWorkerPane.this.setRowData(workers);
					}

				} else if (selectType.equals("按ID查询")) {
					System.out.println("按ID查询");
					if (InitComponent.helper == null) {
						JOptionPane.showMessageDialog(DeleteWorkerPane.this, "未连接服务器!");
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