package com.operation.select;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.operation.mainframe.InitComponent;
import com.operation.myComponent.BackFrame;
import com.operation.myComponent.BackScrollPane;
import com.operation.myComponent.BackSplitPane;

public class MainSelectPane extends BackSplitPane{
	JScrollPane leftPanel = null;
	RightPane rightPanel = null;

	public MainSelectPane() {
		rightPanel = new RightPane();
		leftPanel = new BackScrollPane(new LeftPane(rightPanel));
		this.setLeftComponent(leftPanel);
		this.setRightComponent(rightPanel);
		this.setDividerLocation(200);
		this.setDividerSize(1);
	}


	public static void main(String[] args) {
		InitComponent.initClient();
		MainSelectPane p = new MainSelectPane();
		JFrame f = new BackFrame("test","./imgs/bg2.jpg");
		f.add(p);
		f.setBounds(200, 100, 800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.repaint();
	}
}
