package com.operation.manager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.operation.common.Worker;
import com.operation.mainframe.InitComponent;
import com.operation.myComponent.BackFrame;
import com.operation.myComponent.BackPane;
import com.operation.myComponent.BackToolBar;
import com.operation.rpc.RPCHelper;
public class ManagerMainFrame extends BackFrame{
	RPCHelper helper=null;
	
	JButton createButton=null;	
	JButton deleteButton=null;	
	BackToolBar toolBar=null;
	BackPane centerPanel=null;
	CardLayout card=null;
	ManagerToolListener listener=null;
	private BackToolBar createToolBar() {
		toolBar = new BackToolBar();
		listener = new ManagerToolListener();
		createButton=new JButton("�����˻�");
		deleteButton=new JButton("ɾ���˻�");
		createButton.addActionListener(listener);
		deleteButton.addActionListener(listener);
		toolBar.add(createButton, 0);
		toolBar.add(deleteButton, 1);
		return toolBar;
	}
	public ManagerMainFrame(RPCHelper helper) {
		super("�˻�����","./imgs/bg2.jpg");
		
		this.helper=helper;
		InitComponent.helper=helper;
		toolBar=createToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		
		centerPanel=new BackPane();
		card=new CardLayout();
		centerPanel.setLayout(card);
		centerPanel.add(new CreateWorkerPane(),"1");
		centerPanel.add(new DeleteWorkerPane(),"2");
		
		this.add(centerPanel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(200, 100, 800, 600);
		this.setVisible(true);
	}
	class ManagerToolListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==createButton) {
				card.show(centerPanel, "1");
			}else if(e.getSource()==deleteButton) {
				card.show(centerPanel, "2");
			}
		}
		
	}
	public static void main(String[] args){
		RPCHelper helper = null;
		Worker worker = null;
		helper = new RPCHelper();
		helper.login("w0000", "123");
//		worker = helper.selectWorkerById("w0000");
//		InitComponent.initClient();
		new ManagerMainFrame(helper);
	}
}