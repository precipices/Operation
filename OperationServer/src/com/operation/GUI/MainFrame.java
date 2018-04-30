package com.operation.GUI;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import com.operation.server.ServerThread;
import com.operation.server.ThreadPool;

/**
 * ����Ϊ����������,���и�������������
 */
public class MainFrame extends JFrame {
	private static final int THREAD_NUM = 3;// ������ͬʱ�����ĵȴ����ӵ��߳���(�����ӵ��ͻ��˵��̲߳�������)
	public static boolean opened = false;// �ж��Ƿ��ѿ���������

	private JToolBar jtb;
	private JButton jb_open, jb_close;
	private MyListener myListener;
	private JPanel jp;
	private static ServerTableModel model = new ServerTableModel();
	private static JTable threadTable = new JTable(model);
	private static JScrollPane jsp = null;

	private JToolBar createToolBar() {
		myListener = new MyListener();// ���ü�����
		JToolBar jtb = new JToolBar();// �½�������
		// �½���Ť
		jb_open = new JButton("����������");
		jb_open.addActionListener(myListener);
		jb_close = new JButton("�رշ�����");
		jb_close.addActionListener(myListener);
		// jb3 = new JButton("�������ӵ��߳�");
		// jb3.addActionListener(myListener);
		// ���Ӱ�Ť
		jtb.add(jb_open);
		jtb.add(jb_close);
		// jtb.add(jb3);
		return jtb;
	}

	public MainFrame() {
		super("������");
		jtb = createToolBar();
		this.add(jtb, BorderLayout.NORTH);
		jp = new JPanel();
		this.add(jp, BorderLayout.CENTER);
		this.setBounds(200, 100, 800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	class MyListener implements ActionListener {// ��������
		@Override
		public void actionPerformed(ActionEvent a) {
			if (a.getSource() == jb_open) {
				if (opened) {
					JOptionPane.showMessageDialog(MainFrame.this, "�����������ظ�����!");
					return;
				}
				System.out.println("����˿���������");
				open();
				remove(jp);
				jsp = new JScrollPane(threadTable);
				add(jsp);
				validate();
				repaint();
			} else if (a.getSource() == jb_close) {
				System.out.println("����˹رշ�����");
				System.exit(0);
			}
		}
	}

	private void open() {
		if (opened) {
			JOptionPane.showMessageDialog(this, "�����������ظ�����!");
			return;
		}
		opened = true;
		System.out.println("��������������...");
		// ����һ���������ӿͻ��˵��̵߳��߳�
		Thread t = new Thread() {
			public void run() {
				ServerSocket ss = null;
				try {
					ss = new ServerSocket(9999);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (ThreadPool.getWaitThreadsSize() < MainFrame.THREAD_NUM) {
						// Server server = new Server(ss);
						new Thread(new ServerThread(ss)).start();
					}
				}
			}
		};
		t.start();
	}

	private void close() {
		System.exit(0);
	}

	public synchronized static void refreshTable() {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				model.fireTableDataChanged();
			}
		});
	}

	public static void main(String[] args) {
		// ע����񣺰����ȴ�����RemoteServceImpl ������뵽�������Ļ�����
		// Register.register(ServerHelper.class.getName(), new ServerHelper());
		new MainFrame();
	}

//	public void draw(Graphics g) {
//		Graphics2D g2d = (Graphics2D) g;
//		// ����͸����
//		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f); // ���һ������������͸���� �� �� ���һ������Ϊ 0.1f
//																						// ʱ ���ʵ�͸���Ⱦ�Ϊ90%
//		g2d.setComposite(ac);
//
//	}
//
//	@Override
//	public void paint(Graphics arg0) {
//		draw(arg0);
//		super.paint(arg0);
//	}

}