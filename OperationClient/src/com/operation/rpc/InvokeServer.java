package com.operation.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.operation.common.RemoteCall;
/**
 * �����������������������,������Ϣ�����ջظ�
 */
public class InvokeServer {
	private String IPaddress = "localhost";
	private int port = 9999;
	private Socket s = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	/**
	 * ����socket���ӷ�����,���õ��ļ����������
	 * @return
	 */
	public boolean getConnection() {
		System.out.println("���ڽ�������");
		try {
			s = new Socket(IPaddress, port);
			out = new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream(s.getInputStream());
			
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("��������ʧ��,����IP�Ͷ˿ں�!");
			return false;
		}
		System.out.println("�����ӵ�������");
		return true;
	}
	/**
	 * �ر�����,�ر��ļ����������
	 */
	public void close() {
		System.out.println("���ڹر�����");
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
		System.out.println("�ر����ӳɹ�");
	}
	/**
	 * �����������һ��RemoteCall�������صõ��ظ���RemoteCall����
	 * @param call
	 * @return
	 */
	public synchronized Object invoke(RemoteCall call){
		System.out.println("���������������: "+call);
		Object get = null;
		try {
			//��������������󲢽��ջ���
			out.writeObject(call);
			get = in.readObject();
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("������������Ϣ: "+get);
		return get;
	}
}
