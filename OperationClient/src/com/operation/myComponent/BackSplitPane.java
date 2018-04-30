package com.operation.myComponent;

import java.awt.Component;

import javax.swing.JSplitPane;

public class BackSplitPane extends JSplitPane{

	private void init() {
		this.setOpaque(false);
		this.setBackground(null);
	}
	public BackSplitPane() {
		super();
		init();
	}

	public BackSplitPane(int newOrientation, boolean newContinuousLayout, Component newLeftComponent,
			Component newRightComponent) {
		super(newOrientation, newContinuousLayout, newLeftComponent, newRightComponent);
		init();
	}

	public BackSplitPane(int newOrientation, boolean newContinuousLayout) {
		super(newOrientation, newContinuousLayout);
		init();
	}

	public BackSplitPane(int newOrientation, Component newLeftComponent, Component newRightComponent) {
		super(newOrientation, newLeftComponent, newRightComponent);
		init();
	}

	public BackSplitPane(int newOrientation) {
		super(newOrientation);
		init();
	}

}
