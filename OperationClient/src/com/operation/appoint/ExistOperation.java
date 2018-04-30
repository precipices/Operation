package com.operation.appoint;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.operation.common.Operation;
import com.operation.myComponent.BackButton;
import com.operation.myComponent.BackFrame;
import com.operation.myComponent.BackPane;

//�����ť���ҳ����תδд
public class ExistOperation extends BackPane{
	private ExistOperationListener listener;
	
	private JTextField patientName;
	private JTextField operationName;
	private JTextField beginDate;
	private JTextField operationId;
	CardLayout fatherCard=null;
	BackPane fatherPane=null;
	Operation operation=null;
	public ExistOperation(Operation operation, CardLayout fatherCard, BackPane fatherPane) {
		this.fatherCard=fatherCard;
		this.fatherPane=fatherPane;
		this.operation=operation;
		listener = new ExistOperationListener();

		this.setLayout(null);
		this.setPreferredSize(new Dimension(550, 70));
		this.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		BackButton editButton = new BackButton("�༭");
		editButton.setBounds(437, 10, 78, 23);
		editButton.setActionCommand("editButton");
		editButton.addActionListener(listener);

		BackButton deleteButton = new BackButton("ɾ��");
		deleteButton.setBounds(437, 37, 78, 23);
		deleteButton.setActionCommand("deleteButton");
		deleteButton.addActionListener(listener);
		

		JLabel label = new JLabel("������");
//		label.setFont(new Font("����", Font.PLAIN, 14));
		label.setBounds(10, 10, 57, 23);
		
		operationId = new JTextField(8);//������������Ա�ʶ
		operationId.setBounds(48, 11, 78, 21);
		operationId.setEnabled(false);
		operationId.setText(operation.getId());

		
		JLabel label_type = new JLabel("�������ƣ�");
		label_type.setBounds(148, 15, 78, 15);
		
		operationName = new JTextField(15);
		operationName.setBounds(211, 12, 193, 21);
		operationName.setEnabled(false);
		operationName.setText(operation.getName());
		
		JLabel label_pname = new JLabel("���ˣ�");
		label_pname.setBounds(10, 41, 54, 15);
		
		patientName = new JTextField(8);
		patientName.setBounds(48, 38, 78, 21);
		patientName.setEnabled(false);
		patientName.setText(operation.getPatientId()+"(����)");
		
		JLabel label_date = new JLabel("�������ڣ�");
		label_date.setBounds(148, 42, 66, 15);
		
		beginDate = new JTextField(15);
		beginDate.setBounds(211, 38, 193, 21);
		beginDate.setEnabled(false);
		beginDate.setText(operation.getBeginTime()+"");

		
		this.add(label);
		this.add(operationId);
		this.add(label_type);
		this.add(operationName);
		this.add(editButton);
		this.add(label_pname);
		this.add(patientName);
		this.add(label_date);
		this.add(beginDate);
		this.add(deleteButton);
//		FlowLayout f=new FlowLayout();
//		f.setHgap(20);
//		this.setLayout(f);
	}
	
	class ExistOperationListener implements ActionListener {//�����������
		@Override
		public void actionPerformed(ActionEvent a) {
			if(a.getActionCommand().equals("editButton")) {
				System.out.println("����˱༭��Ť");
				fatherCard.show(fatherPane, "�����༭");
			}else if(a.getActionCommand().equals("deleteButton")) {
				System.out.println("�����ɾ����Ť");
				
			}
		}
	}
	
	public static void main(String[] args) {
		JFrame f =new BackFrame("test","./imgs/bg2.jpg");
		f.setLayout(new FlowLayout());
		f.add(new ExistOperation(new Operation("id", "name", Date.valueOf("1980-1-1"), "roomId", "patientId", "doctorId", "nurseId", "anesthetistId", "doctorRecord", "nurseRecord", "anesthetistRecord"),null,null));
		// ���ô�С����ʾ����
		f.setBounds(200, 100, 800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}