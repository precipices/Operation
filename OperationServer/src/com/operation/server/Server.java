package com.operation.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

import com.operation.common.RemoteCall;

public class Server {
	// 连接客户端需要的对象
	private ServerSocket ss = null;
	private Socket s = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private ServerHelper serverHelper = null;
	private String id=null;

	public Server(ServerSocket ss, ServerHelper serverHelper) {
		this.ss = ss;
		this.serverHelper = serverHelper;
	}

	private Server() {}// 不允许建立无参的对象

	/**
	 * 与客户端建立连接
	 */
	public void waitConnection() {
		System.out.println("服务器线程[" + Thread.currentThread().getName() + "]正在等待连接");
		try {
			s = ss.accept();// 建立连接
			out = new ObjectOutputStream(s.getOutputStream());// 得到socket的输出流并建立对象输出流
			in = new ObjectInputStream(s.getInputStream());// 得到socket的输入流并建立对象输入流
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("服务器线程[" + Thread.currentThread().getName() + "]已连接到客户端");
	}

	/**
	 * 关闭连接
	 */
	public void close() {
		try {
			in.close();
			out.close();
			s.close();
			System.out.println("服务器线程[" + Thread.currentThread().getName() + "]断开了与客户端的连接]");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 处理客户端的每一次请求--登陆请求,如果不是登陆请求,返回ServerHelper.NOT_LOGIN
	 * 
	 * @return
	 */
	public RemoteCall login() {
		RemoteCall receive = null;
		Object result = null;
		try {
			receive = (RemoteCall) in.readObject();// 接收客户发送的Call对象
			System.out.println(Thread.currentThread().getName() + "收到客户发来的请求：" + receive);
			// 如果请求不是登陆,不处理请求并直接返回ServerHelper.NOT_LOGIN
			if (!("login".equals(receive.getMethodName()))) {
				result = ServerHelper.NOT_LOGIN;
				receive.setResult(result);
				out.writeObject(result);
				return receive;
			}
			//判断是否已经登陆
			id = (String) receive.getParams()[0];
			if(ThreadPool.isLogin(id)) {
				result = ServerHelper.LOGINED;
				receive.setResult(result);
				out.writeObject(result);
				return receive;
			}
			// 如果请求是登陆,处理请求
			result = invoke(receive);// 调用invoke方法处理请求
			receive.setResult(result);
			out.writeObject(result);// 向客户发送执行结果
			System.out.println(Thread.currentThread().getName() + "向客户端发回了消息：" + result);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Thread.currentThread().getName() + "处理客户请求时发生了未知错误");
		}
		return receive;
	}

	/**
	 * 处理客户的一般请求,将处理结果result(Object)发回给客户,
	 * 并将result放入到receive中作为方法返回参数
	 * @return
	 * @throws Exception 
	 */
	public RemoteCall service() throws Exception {
		RemoteCall receive = null;
		Object result = null;
		try {
			receive = (RemoteCall) in.readObject();// 接收客户发送的Call对象
			System.out.println(Thread.currentThread().getName() + "收到客户发来的请求：" + receive);

			result = invoke(receive);// 调用invoke方法处理请求
			receive.setResult(result);
			out.writeObject(result);// 向客户发送执行结果
			System.out.println(Thread.currentThread().getName() + "向客户端发回了消息：" + result);
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(Thread.currentThread().getName() + "处理客户请求时发生了未知错误,可能是客户端被强制关闭");
			throw e;
		}
		return receive;
	}

	/**
	 * 通过反射处理一次请求,将处理结果放到result(Object)中
	 * @param call
	 * @return
	 */
	private Object invoke(RemoteCall call) {
		Object result = null;
		try {
			String className = call.getClassName();
			String methodName = call.getMethodName();
			Class[] paramTypes = call.getParamTypes();
			Object[] params = call.getParams();
			Class classType = Class.forName(className);
			Method method = classType.getMethod(methodName, paramTypes);
			if(serverHelper == null) {
				throw new Exception(className + " 的远程对象不存在");
			}else {
				result = method.invoke(serverHelper, params);
			}
			// 从缓存中取出相关的远程对象Object
//			Object remoteObject = Register.remoteObjects.get(className);
//			if (remoteObject == null) {
//				throw new Exception(className + " 的远程对象不存在");
//			} else {
//				result = method.invoke(remoteObject, params);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			result = e;
		}
		return result;
	}
}
