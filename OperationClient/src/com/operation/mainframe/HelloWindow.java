package com.operation.mainframe;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

public class HelloWindow extends JWindow {
	Image img = null;
	MainFrame m=null;
	public HelloWindow(MainFrame m) {
		super();
		this.m=m;
		img = new ImageIcon("imgs/hellow.png").getImage();
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		this.setAlwaysOnTop(true);
		this.setLocation(200, 100);
		this.setSize(800, 600);
		this.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		// super.paint(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		paintRect(g, 10, this.getHeight()-30, this.getWidth()-20);

	}

	private void paintRect(Graphics g, int x, int y, int width) {
		// ���������ɫ��
		g.setColor(new Color(102, 102, 150));
		g.drawString("���ڳ�ʼ��ϵͳ......", x, y);
		// ���þ��ο�ı���ɫ�ʡ�
		g.setColor(new Color(255, 255, 255));
		// ���ƾ��ο�
		g.fillRect(x, y + 5, width, 7);
		// �������ý�Ҫ��Ϳ�ھ��ο��е���ɫ
		g.setColor(new Color(102, 102, 150));
		for (int n = 0; n <= width; n += 20) {
			try {
				// �߳�����50����
				Thread.sleep(50L);
				// ��Ϳ���ο�
				g.fillRect(x + 1, y + 6, n, 5);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.dispose();
		m.setVisible(true);
//		new HelloWindow();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new HelloWindow(null);
			}
		});
	}

}