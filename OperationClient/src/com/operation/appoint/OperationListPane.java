package com.operation.appoint;

import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import com.operation.common.Operation;
import com.operation.mainframe.InitComponent;
import com.operation.myComponent.BackFrame;
import com.operation.myComponent.BackPane;
import com.operation.myComponent.BackScrollPane;

public class OperationListPane extends BackPane{
	//Operation opTest=new Operation("id", "name", Date.valueOf("1980-1-1"), "roomId", "patientId", "doctorId", "nurseId", "anesthetistId", "doctorRecord", "nurseRecord", "anesthetistRecord");
	Vector<ExistOperation> ops=null;
	MainOperationPane parentPane=null;
	GridLayout grid=new GridLayout(10,1);
	public OperationListPane( MainOperationPane parentPane) {
		this.parentPane=parentPane;
		this.setLayout(new GridLayout(10,1));
		
	}
	//刷新手术列表
	public void updateOperations() {
		if (InitComponent.helper == null) {
			JOptionPane.showMessageDialog(this, "未连接服务器!");
			return;
		}
		this.removeAll();
		Vector<Operation> operations=InitComponent.helper.selectWorkerAllOperations(InitComponent.worker.getId());
		if(operations.size()>10)
			grid.setRows(operations.size());
		for(int i=0;i<operations.size();i++) {
			ExistOperation p=new ExistOperation(operations.get(i),parentPane);
			p.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			this.add(p);
		}
	}
	public static void main(String[] args) {
		JFrame f =new BackFrame("test","./imgs/bg2.jpg");
		JScrollPane jsp = new BackScrollPane(new OperationListPane(null));
		jsp.getVerticalScrollBar().setUnitIncrement(20);
		jsp.setWheelScrollingEnabled(true);
		f.add(jsp);
		// 设置大小和显示类型
		f.setBounds(200, 100, 800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
