package com.operation.myComponent;

import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class BackButton extends JButton {
	private void init() {
		this.setContentAreaFilled(false);
	}
	public BackButton(String name) {
		super(name);
		init();
	}

	public BackButton() {
		super();
		init();
	}

	public BackButton(Action a) {
		super(a);
		init();
	}

	public BackButton(Icon icon) {
		super(icon);
		init();
		this.setPreferredSize(new Dimension(icon.getIconWidth(),icon.getIconHeight()));
	}

	public BackButton(String text, Icon icon) {
		super(text, icon);
		init();
	}
	
}
