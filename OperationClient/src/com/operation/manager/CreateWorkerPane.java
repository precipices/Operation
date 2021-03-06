package com.operation.manager;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.operation.appoint.PatientPane;
import com.operation.common.Patient;
import com.operation.common.Worker;
import com.operation.mainframe.InitComponent;
import com.operation.myComponent.BackPane;
import com.operation.myComponent.DateChooser;
import com.operation.rpc.RPCHelper;

public class CreateWorkerPane extends BackPane {
	JTextField id, name, call;
	JPasswordField password;
	JComboBox<String> sex, position, section;
	JLabel birth;
	String[] sectionNames = new String[] { "眼科", "耳鼻喉科", "口腔科", "内科", "外科", "妇产科", "儿科", "皮肤科", "精神科", "传染科" };

	JButton create;

	public CreateWorkerPane() {
		JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8, jl9;
		jl1 = new JLabel("创建账户", JLabel.CENTER);
		jl2 = new JLabel("账户　ID:", JLabel.CENTER);
		jl3 = new JLabel("密　　码:", JLabel.CENTER);
		jl4 = new JLabel("姓　　名:", JLabel.CENTER);
		jl5 = new JLabel("性　　别:", JLabel.CENTER);
		jl6 = new JLabel("出生日期:", JLabel.CENTER);
		jl7 = new JLabel("职　　位:", JLabel.CENTER);
		jl8 = new JLabel("联系方式:", JLabel.CENTER);
		jl9 = new JLabel("科　　室:", JLabel.CENTER);
		id = new JTextField(10);
		password = new JPasswordField(10);
		name = new JTextField(10);
		sex = new JComboBox<String>(new String[] { "男", "女" });
		birth = new JLabel("单击选择日期");
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(birth);
		position = new JComboBox<String>(new String[] { "医生", "护士", "麻醉师" });
		call = new JTextField(10);
		section = new JComboBox<String>(sectionNames);

		BackPane centerPanel = new BackPane();
		centerPanel.setLayout(new GridLayout(8, 2, 10, 10));
		centerPanel.add(jl2);
		centerPanel.add(id);
		centerPanel.add(jl3);
		centerPanel.add(password);
		centerPanel.add(jl4);
		centerPanel.add(name);
		centerPanel.add(jl5);
		centerPanel.add(sex);
		centerPanel.add(jl6);
		centerPanel.add(birth);
		centerPanel.add(jl7);
		centerPanel.add(position);
		centerPanel.add(jl8);
		centerPanel.add(call);
		centerPanel.add(jl9);
		centerPanel.add(section);

		BackPane jp1 = new BackPane();
		jp1.setLayout(new BorderLayout());
		jl1.setFont(new Font("宋体", Font.BOLD, 20));

		jp1.add(jl1, BorderLayout.NORTH);
		jp1.add(centerPanel);

		BackPane jp2 = new BackPane();
		create = new JButton("创建账户");
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (id.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(CreateWorkerPane.this, "ID不能为空!");
					return;
				}
				if (password.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(CreateWorkerPane.this, "密码不能为空!");
					return;
				}
				if (name.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(CreateWorkerPane.this, "姓名不能为空!");
					return;
				}
				Date birthTime = null;
				try {
					birthTime = Date.valueOf(birth.getText());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(CreateWorkerPane.this, "请输入出生日期!");
					return;
				}
				if (call.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(CreateWorkerPane.this, "联系方式不能为空!");
					return;
				}

				Worker worker = new Worker(id.getText().trim(), password.getText().trim(), name.getText().trim(),
						(String) sex.getSelectedItem(), birthTime, (String) position.getSelectedItem(),
						call.getText().trim(), (String) section.getSelectedItem());
				System.out.println(worker);
				if (InitComponent.helper == null) {
					JOptionPane.showMessageDialog(CreateWorkerPane.this, "未连接数据库!");
					return;
				}
				Worker w=InitComponent.helper.selectWorkerById(id.getText().trim());
				if(w!=null) {
					JOptionPane.showMessageDialog(CreateWorkerPane.this, "ID已被占用!");
					return;
				}
				if (InitComponent.helper.addWorker(worker)) {
					JOptionPane.showMessageDialog(CreateWorkerPane.this, "提交成功,账号信息已被录入数据库,现已可以使用!");
					clearInput();
				} else {
					JOptionPane.showMessageDialog(CreateWorkerPane.this, "病人信息添加失败!");
				}
			}
		});
		jp2.add(create);
		jp1.add(jp2, BorderLayout.SOUTH);
		this.add(jp1);
	}

	protected void clearInput() {
		id.setText("");
		password.setText("");
		name.setText("");
		sex.setSelectedIndex(0);
		birth.setText("单击选择时间");
		position.setSelectedIndex(0);
		call.setText("");
		section.setSelectedIndex(0);
	}
}
