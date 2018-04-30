package com.operation.server;

import java.net.ServerSocket;
import java.util.Vector;

import com.operation.GUI.MainFrame;
import com.operation.GUI.ServerTableModel;
import com.operation.common.RemoteCall;

public class ServerThread implements Runnable{

	//���ӿͻ�����Ҫ�Ķ���
	private ServerSocket ss = null;
	private Server server = null;

	//�ͻ���ʹ�õ�����
	private String id = null;
	private ServerHelper serverHelper = null;//ÿ���ͻ�����һ��ServerHelper����
	
	public ServerThread(ServerSocket ss){
		this.ss=ss;
	}

	private String threadName = null;
	
	public String getThreadName() {
		return threadName;
	}

	@Override
	public void run() {
		RemoteCall receive = null;
		Object result = null;
		
		this.threadName=Thread.currentThread().getName();
		System.out.println("�����߳�:"+Thread.currentThread().getName());

		serverHelper = new ServerHelper();//Ϊ�ÿͻ����½�ServerHelper����
		server = new Server(ss,serverHelper);//Ϊ�ÿͻ����½�Server����
		
		ThreadPool.addWaitThread(this);//���̼߳���δ�����̳߳�(ArrayList)
		server.waitConnection();//׼������
		ThreadPool.removeWaitThread(this);//���߳��Ƴ�δ�����̳߳�
		receive = server.login();//�����һ������,��һ�η������Ϊ��½
		result = receive.getResult();
		int r = (Integer) result;
		if(r < ServerHelper.DOCTOR) {//�����½ʧ��,�ر�����,�����߳�
			server.close();//�ر�����
			return;
		}
		//��½�ɹ�,���̼߳����������̳߳�(HashMap)
		id = (String) receive.getParams()[0];
		ThreadPool.addServerThread(id, this);
		//��һ���߳����ݼ�����
		Vector row = new Vector();
		row.add(id);
		row.add(Thread.currentThread().getName());
		row.add("login");
		ServerTableModel.addRow(row);
		MainFrame.refreshTable();//����MainFrame��ı��jt
		//ѭ����������
		try {
			while(true) {
				receive = server.service();
				row.set(2, receive.getMethodName());//�޸ı�������
				MainFrame.refreshTable();//����MainFrame��ı��jt
				if(receive.getMethodName().equals("close"))
					break;
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e);
		}finally {
			server.close();//��Է��Ͽ�����,�����ӹر�
			ThreadPool.removeServerThread(id);//���߳��Ƴ��������̳߳�
			ServerTableModel.removeRow(row);//���߳������Ƴ����
			MainFrame.refreshTable();//����MainFrame��ı��jt
			System.out.println("�ر��߳�:"+Thread.currentThread().getName());
		}
	}

}
