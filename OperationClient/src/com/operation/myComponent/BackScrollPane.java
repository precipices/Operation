package com.operation.myComponent;

import java.awt.Component;

import javax.swing.JScrollPane;

public class BackScrollPane extends JScrollPane {

	public BackScrollPane() {
		super();
		this.setOpaque(false);
		this.getViewport().setOpaque(false);
	}

	public BackScrollPane(Component arg0, int arg1, int arg2) {
		super(arg0, arg1, arg2);
		this.setOpaque(false);
		this.getViewport().setOpaque(false);	}

	public BackScrollPane(Component arg0) {
		super(arg0);
		this.setOpaque(false);
		this.getViewport().setOpaque(false);
	}

	public BackScrollPane(int arg0, int arg1) {
		super(arg0, arg1);
		this.setOpaque(false);
		this.getViewport().setOpaque(false);
	}

}
