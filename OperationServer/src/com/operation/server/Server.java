package com.operation.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

import com.operation.common.RemoteCall;

public class Server {
	// ���ӿͻ�����Ҫ�Ķ���
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

	private Server() {}// �����������޲εĶ���

	/**
	 * ��ͻ��˽�������
	 */
	public void waitConnection() {
		System.out.println("�������߳�[" + Thread.currentThread().getName() + "]���ڵȴ�����");
		try {
			s = ss.accept();// ��������
			out = new ObjectOutputStream(s.getOutputStream());// �õ�socket����������������������
			in = new ObjectInputStream(s.getInputStream());// �õ�socket������������������������
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("�������߳�[" + Thread.currentThread().getName() + "]�����ӵ��ͻ���");
	}

	/**
	 * �ر�����
	 */
	public void close() {
		try {
			in.close();
			out.close();
			s.close();
			System.out.println("�������߳�[" + Thread.currentThread().getName() + "]�Ͽ�����ͻ��˵�����]");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �����ͻ��˵�ÿһ������--��½����,������ǵ�½����,����ServerHelper.NOT_LOGIN
	 * 
	 * @return
	 */
	public RemoteCall login() {
		RemoteCall receive = null;
		Object result = null;
		try {
			receive = (RemoteCall) in.readObject();// ���տͻ����͵�Call����
			System.out.println(Thread.currentThread().getName() + "�յ��ͻ�����������" + receive);
			// ��������ǵ�½,����������ֱ�ӷ���ServerHelper.NOT_LOGIN
			if (!("login".equals(receive.getMethodName()))) {
				result = ServerHelper.NOT_LOGIN;
				receive.setResult(result);
				out.writeObject(result);
				return receive;
			}
			//�ж��Ƿ��Ѿ���½
			id = (String) receive.getParams()[0];
			if(ThreadPool.isLogin(id)) {
				result = ServerHelper.LOGINED;
				receive.setResult(result);
				out.writeObject(result);
				return receive;
			}
			// ��������ǵ�½,��������
			result = invoke(receive);// ����invoke������������
			receive.setResult(result);
			out.writeObject(result);// ��ͻ�����ִ�н��
			System.out.println(Thread.currentThread().getName() + "��ͻ��˷�������Ϣ��" + result);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Thread.currentThread().getName() + "�����ͻ�����ʱ������δ֪����");
		}
		return receive;
	}

	/**
	 * �����ͻ���һ������,���������result(Object)���ظ��ͻ�,
	 * ����result���뵽receive����Ϊ�������ز���
	 * @return
	 * @throws Exception 
	 */
	public RemoteCall service() throws Exception {
		RemoteCall receive = null;
		Object result = null;
		try {
			receive = (RemoteCall) in.readObject();// ���տͻ����͵�Call����
			System.out.println(Thread.currentThread().getName() + "�յ��ͻ�����������" + receive);

			result = invoke(receive);// ����invoke������������
			receive.setResult(result);
			out.writeObject(result);// ��ͻ�����ִ�н��
			System.out.println(Thread.currentThread().getName() + "��ͻ��˷�������Ϣ��" + result);
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(Thread.currentThread().getName() + "�����ͻ�����ʱ������δ֪����,�����ǿͻ��˱�ǿ�ƹر�");
			throw e;
		}
		return receive;
	}

	/**
	 * ͨ�����䴦��һ������,����������ŵ�result(Object)��
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
				throw new Exception(className + " ��Զ�̶��󲻴���");
			}else {
				result = method.invoke(serverHelper, params);
			}
			// �ӻ�����ȡ����ص�Զ�̶���Object
//			Object remoteObject = Register.remoteObjects.get(className);
//			if (remoteObject == null) {
//				throw new Exception(className + " ��Զ�̶��󲻴���");
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