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
	String[] sectionNames = new String[] { "�ۿ�", "���Ǻ���", "��ǻ��", "�ڿ�", "���", "������", "����", "Ƥ����", "�����", "��Ⱦ��" };

	JButton create;

	public CreateWorkerPane() {
		JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8, jl9;
		jl1 = new JLabel("�����˻�", JLabel.CENTER);
		jl2 = new JLabel("�˻���ID:", JLabel.CENTER);
		jl3 = new JLabel("�ܡ�����:", JLabel.CENTER);
		jl4 = new JLabel("�ա�����:", JLabel.CENTER);
		jl5 = new JLabel("�ԡ�����:", JLabel.CENTER);
		jl6 = new JLabel("��������:", JLabel.CENTER);
		jl7 = new JLabel("ְ����λ:", JLabel.CENTER);
		jl8 = new JLabel("��ϵ��ʽ:", JLabel.CENTER);
		jl9 = new JLabel("�ơ�����:", JLabel.CENTER);
		id = new JTextField(10);
		password = new JPasswordField(10);
		name = new JTextField(10);
		sex = new JComboBox<String>(new String[] { "��", "Ů" });
		birth = new JLabel("����ѡ������");
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(birth);
		position = new JComboBox<String>(new String[] { "ҽ��", "��ʿ", "����ʦ" });
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
		jl1.setFont(new Font("����", Font.BOLD, 20));

		jp1.add(jl1, BorderLayout.NORTH);
		jp1.add(centerPanel);

		BackPane jp2 = new BackPane();
		create = new JButton("�����˻�");
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (id.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(CreateWorkerPane.this, "ID����Ϊ��!");
					return;
				}
				if (password.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(CreateWorkerPane.this, "���벻��Ϊ��!");
					return;
				}
				if (name.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(CreateWorkerPane.this, "��������Ϊ��!");
					return;
				}
				Date birthTime = null;
				try {
					birthTime = Date.valueOf(birth.getText());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(CreateWorkerPane.this, "�������������!");
					return;
				}
				if (call.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(CreateWorkerPane.this, "��ϵ��ʽ����Ϊ��!");
					return;
				}

				Worker worker = new Worker(id.getText().trim(), password.getText().trim(), name.getText().trim(),
						(String) sex.getSelectedItem(), birthTime, (String) position.getSelectedItem(),
						call.getText().trim(), (String) section.getSelectedItem());
				System.out.println(worker);
				if (InitComponent.helper == null) {
					JOptionPane.showMessageDialog(CreateWorkerPane.this, "δ�������ݿ�!");
					return;
				}
				Worker w=InitComponent.helper.selectWorkerById(id.getText().trim());
				if(w!=null) {
					JOptionPane.showMessageDialog(CreateWorkerPane.this, "ID�ѱ�ռ��!");
					return;
				}
				if (InitComponent.helper.addWorker(worker)) {
					JOptionPane.showMessageDialog(CreateWorkerPane.this, "�ύ�ɹ�,�˺���Ϣ�ѱ�¼�����ݿ�,���ѿ���ʹ��!");
					clearInput();
				} else {
					JOptionPane.showMessageDialog(CreateWorkerPane.this, "������Ϣ����ʧ��!");
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
		birth.setText("");
		position.setSelectedIndex(0);
		call.setText("");
		section.setSelectedIndex(0);
	}
}