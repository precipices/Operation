package com.operation.server;

import java.net.ServerSocket;
import java.util.Vector;

import com.operation.GUI.MainFrame;
import com.operation.GUI.ServerTableModel;
import com.operation.common.RemoteCall;

public class ServerThread implements Runnable{

	//连接客户端需要的对象
	private ServerSocket ss = null;
	private Server server = null;

	//客户端使用的数据
	private String id = null;
	private ServerHelper serverHelper = null;//每个客户端有一个ServerHelper对象
	
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
		System.out.println("开启线程:"+Thread.currentThread().getName());

		serverHelper = new ServerHelper();//为该客户端新建ServerHelper对象
		server = new Server(ss,serverHelper);//为该客户端新建Server对象
		
		ThreadPool.addWaitThread(this);//将线程加入未连接线程池(ArrayList)
		server.waitConnection();//准备连接
		ThreadPool.removeWaitThread(this);//将线程移出未连接线程池
		receive = server.login();//处理第一次请求,第一次服务必须为登陆
		result = receive.getResult();
		int r = (Integer) result;
		if(r < ServerHelper.DOCTOR) {//如果登陆失败,关闭连接,结束线程
			server.close();//关闭连接
			return;
		}
		//登陆成功,将线程加入已连接线程池(HashMap)
		id = (String) receive.getParams()[0];
		ThreadPool.addServerThread(id, this);
		//将一行线程数据加入表格
		Vector row = new Vector();
		row.add(id);
		row.add(Thread.currentThread().getName());
		row.add("login");
		ServerTableModel.addRow(row);
		MainFrame.refreshTable();//更新MainFrame里的表格jt
		//循环处理请求
		try {
			while(true) {
				receive = server.service();
				row.set(2, receive.getMethodName());//修改本行数据
				MainFrame.refreshTable();//更新MainFrame里的表格jt
				if(receive.getMethodName().equals("close"))
					break;
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e);
		}finally {
			server.close();//如对方断开连接,将连接关闭
			ThreadPool.removeServerThread(id);//将线程移出已连接线程池
			ServerTableModel.removeRow(row);//将线程数据移出表格
			MainFrame.refreshTable();//更新MainFrame里的表格jt
			System.out.println("关闭线程:"+Thread.currentThread().getName());
		}
	}

}
