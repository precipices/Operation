package com.operation.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.operation.common.Message;

/**
 * ����Ϊ�̳߳�,����������е��߳�
 * 
 * @author pre
 *
 */
public class ThreadPool {
	// ��Ŵ������߳�
	private static List<ServerThread> waitThreads = Collections.synchronizedList(new ArrayList<ServerThread>());
	// ��������ӵ��ͻ��˵��߳�,StringΪ�û�id
	private static Map<String, ServerThread> serverThreads = new HashMap<String, ServerThread>();
	//
	// private static Map<String,Message> messages = new HashMap<String, Message>();
	private static List<Message> messages = Collections.synchronizedList(new ArrayList<Message>());

	public static boolean addMessage(Message message) {
		messages.add(message);
		System.out.println("������Ϣ:"+message);
		return true;
	}

	public static Vector<Message> getMessages(String id) {
		Vector<Message> ms = new Vector<Message>();
		Message m=null;
		for(int i=0;i<messages.size();i++) {
			m=messages.get(i);
			if (m.getToId().equals(id)) {
				ms.add(m);
			}
		}
		return ms;
	}

	public static boolean removeMessage(Message message) {
		boolean flag=false;
		Message m=null;
		for(int i=0;i<messages.size();i++) {
			m=messages.get(i);
			if (m.equals(message)) {
				messages.remove(m);
				System.out.println("ɾ����Ϣ:"+m);
				flag=true;
			}
		}
		return flag;
	}

	public static void addWaitThread(ServerThread waitThread) {
		waitThreads.add(waitThread);
	}

	public static void removeWaitThread(ServerThread waitThread) {
		waitThreads.remove(waitThread);
	}

	//�ж��˺��Ƿ��Ѿ���½
	public static boolean isLogin(String id) {
		Set<String> keys=serverThreads.keySet();
		Iterator<String> it=keys.iterator();
		while(it.hasNext()) {
			String s=it.next();
			if(s.equals(id))
				return true;
		}
		return false;
	}
	public static boolean addServerThread(String id, ServerThread serverThread) {
		if (serverThreads.get(id) != null) {
			System.out.println("���û��ѵ�½,�����ظ���½!");
			return false;
		}
		serverThreads.put(id, serverThread);
		return true;
	}

	public static void removeServerThread(String id) {
		serverThreads.remove(id);
	}

	public static int getWaitThreadsSize() {
		return waitThreads.size();
	}

	// ��Ÿ���Message

}