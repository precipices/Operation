package com.operation.appoint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.operation.common.Message;
import com.operation.common.Operation;
import com.operation.mainframe.InitComponent;
import com.operation.manager.DeleteWorkerPane;
import com.operation.myComponent.BackButton;
import com.operation.myComponent.BackFrame;
import com.operation.myComponent.BackPane;

//点击按钮后的页面跳转未写
public class ExistOperation extends BackPane{
	private ExistOperationListener listener;
	
	private JTextField patientName;
	private JTextField operationName;
	private JTextField beginDate;
	private JTextField operationId;
	MainOperationPane parentPane=null;
	Operation operation=null;
	public ExistOperation(Operation operation,MainOperationPane parentPane) {
		this.parentPane=parentPane;
		this.operation=operation;
		listener = new ExistOperationListener();

		this.setLayout(null);
		this.setPreferredSize(new Dimension(550, 70));
		this.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		BackButton editButton = new BackButton("编辑");
		editButton.setBounds(437, 10, 78, 23);
		editButton.setActionCommand("editButton");
		editButton.addActionListener(listener);

		BackButton deleteButton = new BackButton("删除");
		deleteButton.setBounds(437, 37, 78, 23);
		deleteButton.setActionCommand("deleteButton");
		deleteButton.addActionListener(listener);
		

		JLabel label = new JLabel("手术ID：");
//		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(10, 10, 57, 23);
		
		operationId = new JTextField(8);//放手术编号用以标识
		operationId.setBounds(54, 11, 78, 21);
		operationId.setEnabled(false);
		operationId.setText(operation.getId());

		
		JLabel label_type = new JLabel("手术名称：");
		label_type.setBounds(148, 15, 78, 15);
		
		operationName = new JTextField(15);
		operationName.setBounds(211, 12, 193, 21);
		operationName.setEnabled(false);
		operationName.setText(operation.getName());
		
		JLabel label_pname = new JLabel("病人ID：");
		label_pname.setBounds(10, 41, 54, 15);
		
		patientName = new JTextField(8);
		patientName.setBounds(54, 38, 78, 21);
		patientName.setEnabled(false);
		patientName.setText(operation.getPatientId());
		
		JLabel label_date = new JLabel("手术日期：");
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
		this.addMouseListener(new MouseHoverListener(this,operation));
	}
	
	class ExistOperationListener implements ActionListener {//定义监听器类
		@Override
		public void actionPerformed(ActionEvent a) {
			if(a.getActionCommand().equals("editButton")) {
				System.out.println("点击了编辑按扭");
				parentPane.showInfoPane(operation);
			}else if(a.getActionCommand().equals("deleteButton")) {
				System.out.println("点击了删除按扭");
				System.out.println(InitComponent.worker.getPosition());
				if(InitComponent.worker.getPosition().equals("医生")) {
					if(JOptionPane.showConfirmDialog(ExistOperation.this, "确定删除?","警告",JOptionPane.YES_NO_OPTION)>=1) {
						return;
					}
					if(InitComponent.helper.deleteOperation(operation.getId())) {
						JOptionPane.showMessageDialog(ExistOperation.this, "删除成功!");
						InitComponent.helper.sendMessage(operation.getNurseId(), operation.getId(), Message.NOTCHOOSE);
						InitComponent.helper.sendMessage(operation.getAnesthetistId(), operation.getId(), Message.NOTCHOOSE);
						JOptionPane.showMessageDialog(ExistOperation.this, "系统向"+operation.getNurseId()+"和"+operation.getAnesthetistId()+"发送了确认消息!");
						parentPane.updateListPane();
					}
					else {
						JOptionPane.showMessageDialog(ExistOperation.this, "删除失败!");
					}
				}else {
					JOptionPane.showMessageDialog(ExistOperation.this, "权限不足!");
				}
			}
		}
	}
	private class MouseHoverListener extends MouseAdapter{
		private BackPane c=null;
		private Operation operation=null;
		public MouseHoverListener(BackPane c,Operation operation) {
			this.c=c;
			this.operation=operation;
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			c.setBackground(null);
			c.setForeground(Color.BLACK);
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
			c.setBackground(Color.red);
			c.setForeground(Color.YELLOW);
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
//			fatherCard.show(parentPane, "手术编辑");
			parentPane.showInfoPane(operation);
		}
	}
	public static void main(String[] args) {
		JFrame f =new BackFrame("test","./imgs/bg2.jpg");
		f.setLayout(new FlowLayout());
		f.add(new ExistOperation(new Operation("id", "name", Date.valueOf("1980-1-1"), "roomId", "patientId", "doctorId", "nurseId", "anesthetistId", "doctorRecord", "nurseRecord", "anesthetistRecord"),null));
		// 设置大小和显示类型
		f.setBounds(200, 100, 800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
