package com.operation.manager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
		createButton=new JButton("创建账户");
		deleteButton=new JButton("删除账户");
		createButton.addActionListener(listener);
		deleteButton.addActionListener(listener);
		toolBar.add(createButton, 0);
		toolBar.add(deleteButton, 1);
		return toolBar;
	}
	public ManagerMainFrame(RPCHelper helper) {
		super("账户管理","./imgs/bg2.jpg");
		
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
		
		this.addWindowListener(new MyWindowListener());
		
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
	class MyWindowListener extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {
			super.windowClosing(e);
			try {
				helper.close();
				System.out.println("关闭客户端");

			} catch (Exception e2) {
				System.out.println(e2 + "服务器已关闭！");
				System.exit(0);
			}
		}

	}
}