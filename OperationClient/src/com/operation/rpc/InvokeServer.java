package com.operation.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.operation.common.RemoteCall;
/**
 * 本类用来与服务器建立连接,发送消息并接收回复
 */
public class InvokeServer {
	private String IPaddress = "localhost";
	private int port = 9999;
	private Socket s = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	/**
	 * 建立socket连接服务器,并得到文件输入输出流
	 * @return
	 */
	public boolean getConnection() {
		System.out.println("正在建立连接");
		try {
			s = new Socket(IPaddress, port);
			out = new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream(s.getInputStream());
			
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("建立连接失败,请检查IP和端口号!");
			return false;
		}
		System.out.println("已连接到服务器");
		return true;
	}
	/**
	 * 关闭连接,关闭文件输入输出流
	 */
	public void close() {
		System.out.println("正在关闭连接");
		try {
			if(in!=null)
				in.close();
			if(out!=null)
				out.close();
			if(s!=null)
				s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("关闭连接成功");
	}
	/**
	 * 向服务器发送一个RemoteCall请求对象关得到回复的RemoteCall对象
	 * @param call
	 * @return
	 */
	public synchronized Object invoke(RemoteCall call){
		System.out.println("向服务器发送请求: "+call);
		Object get = null;
		try {
			//向服务器发出请求并接收回信
			out.writeObject(call);
			get = in.readObject();
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("服务器发回消息: "+get);
		return get;
	}
}
