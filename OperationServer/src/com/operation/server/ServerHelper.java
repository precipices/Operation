package com.operation.server;

import java.sql.Date;
import java.util.Vector;

import com.operation.common.Message;
import com.operation.common.Operation;
import com.operation.common.Patient;
import com.operation.common.Worker;
import com.operation.dao.OperationHelper;
import com.operation.dao.OtherHelper;
import com.operation.dao.PatientHelper;
import com.operation.dao.SqlHelper;
import com.operation.dao.WorkerHelper;

/**
 * ������������ͻ��˵�����
 */
public class ServerHelper {
	public static final int OTHER = -2;// δ֪����
	public static final int CONNECT_FAIL = -1;// ����ʧ��
	public static final int NOT_LOGIN = 0;// ��һ�������ǵ�½
	public static final int LOGINED = 1;//��ID�ѵ�½
	public static final int ID_NOT_FOUND = 2;// ID������
	public static final int PASSWORD_NOT_TRUE = 3;// �������
	public static final int DOCTOR = 4;// ��½�ɹ�,��½��Ϊҽ��
	public static final int NURSE = 5;// ��½�ɹ�,��½��Ϊ��ʿ
	public static final int ANESTHETIST = 6;// ��½�ɹ�,��½��Ϊ����ʦ
	public static final int ADMIN=7;// ��½�ɹ�,��½��Ϊ����Ա

	public String id = null;
	public Worker worker = null;
	private boolean logined = false;// �Ƿ��½

	//��½
	public int login(String id, String password) {
		WorkerHelper wh = new WorkerHelper();
		Worker worker = wh.selectWorkerById(id);
		if (worker == null)
			return ServerHelper.ID_NOT_FOUND;
		if (!worker.getPassword().equals(password))
			return ServerHelper.PASSWORD_NOT_TRUE;
		this.id = id;
		this.worker = worker;
		logined = true;
		String position = worker.getPosition();
		if (position.equals("ҽ��"))
			return ServerHelper.DOCTOR;
		if (position.equals("��ʿ"))
			return ServerHelper.NURSE;
		if (position.equals("����ʦ"))
			return ServerHelper.ANESTHETIST;
		if (position.equals("����Ա"))
			return ServerHelper.ADMIN;
		return ServerHelper.OTHER;
	}

	// ����⵽�÷�������ʱ,��������رն�Ӧ�Ŀͻ��˵�����
	public void close() {
		if (!logined)
			return;
		logined = false;
	}

	// ����Id��ѯWorker������null���ʾid������
	public Worker selectWorkerById(String id) {
		if (!logined) {
			return null;
		}
		return new WorkerHelper().selectWorkerById(id);
	}
	// ����������ѯWorker������null���ʾid������
	public Vector<Worker> selectWorkerByName(String name) {
		if (!logined) {
			return null;
		}
		return new WorkerHelper().selectWorkerByName(name);
	}
	// ��ѯ����Worker
	public Vector<Worker> selectAllWorkers() {
		if (!logined) {
			return null;
		}
		return new WorkerHelper().selectAllWorkers();
	}
	// ��ѯĳ���пյ�ҽ��������null���ʾ����û���пյ�ҽ��
	public Vector<Worker> selectDoctorsByDate(Date date) {
		if (!logined) {
			return null;
		}
		return new WorkerHelper().selectDoctorsByDate(date);
	}
	// ��ѯĳ���пյĻ�ʿ������null���ʾ����û���пյĻ�ʿ
	public Vector<Worker> selectNursesByDate(Date date) {
		if (!logined) {
			return null;
		}
		return new WorkerHelper().selectNursesByDate(date);
	}

	// ��ѯĳ���пյ�����ʦ������null���ʾ����û���пյ�����ʦ
	public Vector<Worker> selectAnesthetistsByDate(Date date) {
		if (!logined) {
			return null;
		}
		return new WorkerHelper().selectAnesthetistsByDate(date);
	}

	// ����Ա��,����false��ʾ����ʧ��
	public boolean addWorker(Worker worker) {
		if (!logined) {
			return false;
		}
		return new WorkerHelper().addWorker(worker);
	}

	// ɾ��Ա��,����false��ʾɾ��ʧ��
	public boolean deleteWorker(String id) {
		if (!logined) {
			return false;
		}
		return new WorkerHelper().deleteWorker(id);
	}

	// ��ѯ���в���,����null��ʾû�в���
	public Vector<Patient> selectAllPatients() {
		if (!logined) {
			return null;
		}
		return new PatientHelper().selectAllPatients();
	}

	// ����Id��ѯ���ˣ�����null���ʾid������
	public Patient selectPatientById(String id) {
		if (!logined) {
			return null;
		}
		return new PatientHelper().selectPatientById(id);
	}

	// �������ֲ�ѯ���ˣ�����null���ʾ���ֲ�����
	public Vector<Patient> selectPatientByName(String name) {
		if (!logined) {
			return null;
		}
		return new PatientHelper().selectPatientByName(name);
	}
	// ��ѯĳ���пյĲ��ˣ�����null���ʾ����û���пյĲ���
	public Vector<Patient> selectPatientsByDate(Date date) {
		if (!logined) {
			return null;
		}
		return new PatientHelper().selectPatientsByDate(date);
	}
	// ���Ӳ���,����false��ʾ����ʧ��
	public boolean addPatient(Patient patient) {
		if (!logined) {
			return false;
		}
		return new PatientHelper().addPatient(patient);
	}

	// ��ѯ��������,����null��ʾû������
	public Vector<Operation> selectAllOperations() {
		if (!logined) {
			return null;
		}
		return new OperationHelper().selectAllOperations();
	}

	// ����id�鵥������,����null��ʾid������
	public Operation selectOperationById(String id) {
		if (!logined) {
			return null;
		}
		return new OperationHelper().selectOperationById(id);
	}
	// ����name������,����null��ʾname������
	public Vector<Operation> selectOperationByName(String name) {
		if (!logined) {
			return null;
		}
		return new OperationHelper().selectOperationByName(name);
	}
	// ĳ�����������,����null��ʾ����û������
	public Vector<Operation> selectOperationByDate(Date date) {
		if (!logined) {
			return null;
		}
		return new OperationHelper().selectOperationByDate(date);
	}

	// ��ѯ��ĳ����֮�������
	public Vector<Operation> selectOperationAfterDate(Date date) {
		if (!logined) {
			return null;
		}
		return new OperationHelper().selectOperationAfterDate(date);
	}

	// ����������֮�������,����null��ʾ��������֮��û������
	public Vector<Operation> selectOperationsBetween(Date begin, Date end) {
		if (!logined) {
			return null;
		}
		return new OperationHelper().selectOperationsBetween(begin, end);
	}

	// ��ĳԱ�������ȫ������,����null��ʾԱ��û������
	public Vector<Operation> selectWorkerAllOperations(String workerId) {
		if (!logined) {
			return null;
		}
		return new OperationHelper().selectWorkerAllOperations(workerId) ;
	}

	// ��ĳԱ��ĳ���ڲ���ĵ�������,����null��ʾԱ������û������
	public Operation selectWorkerOperationsByDate(String workerId, Date date) {
		if (!logined) {
			return null;
		}
		return new OperationHelper().selectWorkerOperationsByDate(workerId, date);
	}

	// ��ĳԱ��������֮������ȫ������,����null��ʾԱ����������֮��û������
	public Vector<Operation> selectWorkerAllOperationsBetween(String workerId, Date begin, Date end) {
		if (!logined) {
			return null;
		}
		return new OperationHelper().selectWorkerAllOperationsBetween(workerId, begin, end);
	}

	// ��ĳ���˲����ȫ������,����null��ʾ����û������
	public Vector<Operation> selectPatientAllOperations(String patientId) {
		if (!logined) {
			return null;
		}
		return new OperationHelper().selectPatientAllOperations(patientId);
	}

	// ��������,����false��ʾ����ʧ��
	public boolean addOperation(Operation operation) {
		if (!logined) {
			return false;
		}
		return new OperationHelper().addOperation(operation);
	}

	// ��ӻ��޸Ĳ���������ҽ��,����false��ʾ�޸�ʧ��
	public boolean updateDoctorToOperation(String id, String doctorId) {
		if (!logined) {
			return false;
		}
		return new OperationHelper().updateDoctorToOperation(id, doctorId);
	}

	// ��ӻ��޸Ĳ��������Ļ�ʿ,����false��ʾ�޸�ʧ��
	public boolean updateNurseToOperation(String id, String nurseId) {
		if (!logined) {
			return false;
		}
		return new OperationHelper().updateNurseToOperation(id, nurseId);
	}

	// ��ӻ��޸Ĳ�������������ʦ,����false��ʾ�޸�ʧ��
	public boolean updateAnesthetistToOperation(String id, String anesthetistId) {
		if (!logined) {
			return false;
		}
		return new OperationHelper().updateAnesthetistToOperation(id, anesthetistId);
	}

	// ��ӻ��޸�ҽ��������¼,����false��ʾ�޸�ʧ��
	public boolean updateDoctorRecordToOperation(String id, String doctorRecord) {
		if (!logined) {
			return false;
		}
		return new OperationHelper().updateDoctorRecordToOperation(id, doctorRecord);
	}

	// ��ӻ��޸Ļ�ʿ������¼,����false��ʾ�޸�ʧ��
	public boolean updateNurseRecordToOperation(String id, String nurseRecord) {
		if (!logined) {
			return false;
		}
		return new OperationHelper().updateNurseRecordToOperation(id, nurseRecord);
	}

	// ��ӻ��޸�����ʦ������¼,����false��ʾ�޸�ʧ��
	public boolean updateAnesthetistRecordToOperation(String id, String anesthetistRecord) {
		if (!logined) {
			return false;
		}
		return new OperationHelper().updateAnesthetistRecordToOperation(id, anesthetistRecord);
	}

	// ��ӻ��޸�������,����false��ʾ�޸�ʧ��
	public boolean updateRoomToOperation(String id, String roomId) {
		if (!logined) {
			return false;
		}
		return new OperationHelper().updateRoomToOperation(id, roomId);
	}
	// ɾ������,����false��ʾ�޸�ʧ��
	public boolean deleteOperation(String id) {
		if (!logined) {
			return false;
		}
		return new OperationHelper().deleteOperation(id);
	}
	//��ѯĳ���пյ�������,����null��ʾ����û���������п�
	public Vector<String> selectRoomByDate(Date date){
		if (!logined) {
			return null;
		}
		return new OtherHelper().selectRoomByDate(date);
	}
	//��ѯĳ�����ұ�ռ�õ�ʱ��,����null��ʾ��������û���κ�һ����ʹ�û�id������
	public Vector<Date> selectRoomBusyTimesById(String id){
		if (!logined) {
			return null;
		}
		return new OtherHelper().selectRoomBusyTimesById(id);
	}
	//�����������һ��Message
	public boolean sendMessage(String toWorkerId,String operationId,int messageType) {
		if (!logined) {
			return false;
		}
		if(this.id.equals(toWorkerId)) {
			return false;
		}
		Message m = new Message(this.id,toWorkerId,operationId,messageType);
		return ThreadPool.addMessage(m);
	}
	//���������ѯ�����Լ���Message
	public Vector<Message> getMessages() {
		if (!logined)
			return null;
		return ThreadPool.getMessages(this.id);
	}
	//�ӷ�����ɾ��һ��Message
	public boolean removeMessage(Message message) {
		if (!logined) {
			return false;
		}
		return ThreadPool.removeMessage(message);
	}
	// ��ѯ��refuseId��date+-3���������������ٵĻ�ʿ,����null��ʾ����û���пյĻ�ʿ,���ػ�ʿrefuseId��ʾ���˵����idû���пյĻ�ʿ
	public Worker autoSelectNurse(Date date, String refuseId) {
		if (!logined) {
			return null;
		}
		return new WorkerHelper().autoSelectNurse(date, refuseId);
	}

	// ��ѯ��refuseId��date+-3���������������ٵ�����ʦ,����null��ʾ����û���пյ�����ʦ,��������ʦrefuseId��ʾ���˵����idû���пյ�����ʦ
	public Worker autoSelectAnesthetist(Date date, String refuseId) {
		if (!logined) {
			return null;
		}
		return new WorkerHelper().autoSelectAnesthetist(date, refuseId);
	}
}
