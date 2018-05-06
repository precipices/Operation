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
 * 本类用来处理客户端的请求
 */
public class ServerHelper {
	public static final int OTHER = -2;// 未知错误
	public static final int CONNECT_FAIL = -1;// 连接失败
	public static final int NOT_LOGIN = 0;// 第一个请求不是登陆
	public static final int LOGINED = 1;//该ID已登陆
	public static final int ID_NOT_FOUND = 2;// ID不存在
	public static final int PASSWORD_NOT_TRUE = 3;// 密码错误
	public static final int DOCTOR = 4;// 登陆成功,登陆者为医生
	public static final int NURSE = 5;// 登陆成功,登陆者为护士
	public static final int ANESTHETIST = 6;// 登陆成功,登陆者为麻醉师
	public static final int ADMIN=7;// 登陆成功,登陆者为管理员

	public String id = null;
	public Worker worker = null;
	private boolean logined = false;// 是否登陆

	//登陆
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
		if (position.equals("医生"))
			return ServerHelper.DOCTOR;
		if (position.equals("护士"))
			return ServerHelper.NURSE;
		if (position.equals("麻醉师"))
			return ServerHelper.ANESTHETIST;
		if (position.equals("管理员"))
			return ServerHelper.ADMIN;
		return ServerHelper.OTHER;
	}

	// 当检测到该方法调用时,服务器会关闭对应的客户端的连接
	public void close() {
		if (!logined)
			return;
		logined = false;
	}

	// 根据Id查询Worker，返回null则表示id不存在
	public Worker selectWorkerById(String id) {
		if (!logined) {
			return null;
		}
		return new WorkerHelper().selectWorkerById(id);
	}
	// 根据姓名查询Worker，返回null则表示id不存在
	public Vector<Worker> selectWorkerByName(String name) {
		if (!logined) {
			return null;
		}
		return new WorkerHelper().selectWorkerByName(name);
	}
	// 查询所有Worker
	public Vector<Worker> selectAllWorkers() {
		if (!logined) {
			return null;
		}
		return new WorkerHelper().selectAllWorkers();
	}
	// 查询某天有空的医生，返回null则表示该天没有有空的医生
	public Vector<Worker> selectDoctorsByDate(Date date) {
		if (!logined) {
			return null;
		}
		return new WorkerHelper().selectDoctorsByDate(date);
	}
	// 查询某天有空的护士，返回null则表示该天没有有空的护士
	public Vector<Worker> selectNursesByDate(Date date) {
		if (!logined) {
			return null;
		}
		return new WorkerHelper().selectNursesByDate(date);
	}

	// 查询某天有空的麻醉师，返回null则表示该天没有有空的麻醉师
	public Vector<Worker> selectAnesthetistsByDate(Date date) {
		if (!logined) {
			return null;
		}
		return new WorkerHelper().selectAnesthetistsByDate(date);
	}

	// 增加员工,返回false表示增加失败
	public boolean addWorker(Worker worker) {
		if (!logined) {
			return false;
		}
		return new WorkerHelper().addWorker(worker);
	}

	// 删除员工,返回false表示删除失败
	public boolean deleteWorker(String id) {
		if (!logined) {
			return false;
		}
		return new WorkerHelper().deleteWorker(id);
	}

	// 查询所有病人,返回null表示没有病人
	public Vector<Patient> selectAllPatients() {
		if (!logined) {
			return null;
		}
		return new PatientHelper().selectAllPatients();
	}

	// 根据Id查询病人，返回null则表示id不存在
	public Patient selectPatientById(String id) {
		if (!logined) {
			return null;
		}
		return new PatientHelper().selectPatientById(id);
	}

	// 根据名字查询病人，返回null则表示名字不存在
	public Vector<Patient> selectPatientByName(String name) {
		if (!logined) {
			return null;
		}
		return new PatientHelper().selectPatientByName(name);
	}
	// 查询某天有空的病人，返回null则表示该天没有有空的病人
	public Vector<Patient> selectPatientsByDate(Date date) {
		if (!logined) {
			return null;
		}
		return new PatientHelper().selectPatientsByDate(date);
	}
	// 增加病人,返回false表示增加失败
	public boolean addPatient(Patient patient) {
		if (!logined) {
			return false;
		}
		return new PatientHelper().addPatient(patient);
	}

	// 查询所有手术,返回null表示没有手术
	public Vector<Operation> selectAllOperations() {
		if (!logined) {
			return null;
		}
		return new OperationHelper().selectAllOperations();
	}

	// 根据id查单个手术,返回null表示id不存在
	public Operation selectOperationById(String id) {
		if (!logined) {
			return null;
		}
		return new OperationHelper().selectOperationById(id);
	}
	// 根据name查手术,返回null表示name不存在
	public Vector<Operation> selectOperationByName(String name) {
		if (!logined) {
			return null;
		}
		return new OperationHelper().selectOperationByName(name);
	}
	// 某天的所有手术,返回null表示当天没有手术
	public Vector<Operation> selectOperationByDate(Date date) {
		if (!logined) {
			return null;
		}
		return new OperationHelper().selectOperationByDate(date);
	}

	// 查询自某日期之后的手术
	public Vector<Operation> selectOperationAfterDate(Date date) {
		if (!logined) {
			return null;
		}
		return new OperationHelper().selectOperationAfterDate(date);
	}

	// 查两个日期之间的手术,返回null表示两个日期之间没有手术
	public Vector<Operation> selectOperationsBetween(Date begin, Date end) {
		if (!logined) {
			return null;
		}
		return new OperationHelper().selectOperationsBetween(begin, end);
	}

	// 查某员工参与的全部手术,返回null表示员工没有手术
	public Vector<Operation> selectWorkerAllOperations(String workerId) {
		if (!logined) {
			return null;
		}
		return new OperationHelper().selectWorkerAllOperations(workerId) ;
	}

	// 查某员工某日期参与的单个手术,返回null表示员工当天没有手术
	public Operation selectWorkerOperationsByDate(String workerId, Date date) {
		if (!logined) {
			return null;
		}
		return new OperationHelper().selectWorkerOperationsByDate(workerId, date);
	}

	// 查某员工两日期之间参与的全部手术,返回null表示员工两个日期之间没有手术
	public Vector<Operation> selectWorkerAllOperationsBetween(String workerId, Date begin, Date end) {
		if (!logined) {
			return null;
		}
		return new OperationHelper().selectWorkerAllOperationsBetween(workerId, begin, end);
	}

	// 查某病人参与的全部手术,返回null表示病人没有手术
	public Vector<Operation> selectPatientAllOperations(String patientId) {
		if (!logined) {
			return null;
		}
		return new OperationHelper().selectPatientAllOperations(patientId);
	}

	// 增加手术,返回false表示增加失败
	public boolean addOperation(Operation operation) {
		if (!logined) {
			return false;
		}
		return new OperationHelper().addOperation(operation);
	}

	// 添加或修改参与手术的医生,返回false表示修改失败
	public boolean updateDoctorToOperation(String id, String doctorId) {
		if (!logined) {
			return false;
		}
		return new OperationHelper().updateDoctorToOperation(id, doctorId);
	}

	// 添加或修改参与手术的护士,返回false表示修改失败
	public boolean updateNurseToOperation(String id, String nurseId) {
		if (!logined) {
			return false;
		}
		return new OperationHelper().updateNurseToOperation(id, nurseId);
	}

	// 添加或修改参与手术的麻醉师,返回false表示修改失败
	public boolean updateAnesthetistToOperation(String id, String anesthetistId) {
		if (!logined) {
			return false;
		}
		return new OperationHelper().updateAnesthetistToOperation(id, anesthetistId);
	}

	// 添加或修改医生手术记录,返回false表示修改失败
	public boolean updateDoctorRecordToOperation(String id, String doctorRecord) {
		if (!logined) {
			return false;
		}
		return new OperationHelper().updateDoctorRecordToOperation(id, doctorRecord);
	}

	// 添加或修改护士手术记录,返回false表示修改失败
	public boolean updateNurseRecordToOperation(String id, String nurseRecord) {
		if (!logined) {
			return false;
		}
		return new OperationHelper().updateNurseRecordToOperation(id, nurseRecord);
	}

	// 添加或修改麻醉师手术记录,返回false表示修改失败
	public boolean updateAnesthetistRecordToOperation(String id, String anesthetistRecord) {
		if (!logined) {
			return false;
		}
		return new OperationHelper().updateAnesthetistRecordToOperation(id, anesthetistRecord);
	}

	// 添加或修改手术室,返回false表示修改失败
	public boolean updateRoomToOperation(String id, String roomId) {
		if (!logined) {
			return false;
		}
		return new OperationHelper().updateRoomToOperation(id, roomId);
	}
	// 删除手术,返回false表示修改失败
	public boolean deleteOperation(String id) {
		if (!logined) {
			return false;
		}
		return new OperationHelper().deleteOperation(id);
	}
	//查询某天有空的手术室,返回null表示当天没有手术室有空
	public Vector<String> selectRoomByDate(Date date){
		if (!logined) {
			return null;
		}
		return new OtherHelper().selectRoomByDate(date);
	}
	//查询某手术室被占用的时间,返回null表示该手术室没有任何一天在使用或id不存在
	public Vector<Date> selectRoomBusyTimesById(String id){
		if (!logined) {
			return null;
		}
		return new OtherHelper().selectRoomBusyTimesById(id);
	}
	//向服务器发送一条Message
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
	//向服务器查询发给自己的Message
	public Vector<Message> getMessages() {
		if (!logined)
			return null;
		return ThreadPool.getMessages(this.id);
	}
	//从服务器删除一条Message
	public boolean removeMessage(Message message) {
		if (!logined) {
			return false;
		}
		return ThreadPool.removeMessage(message);
	}
	// 查询除refuseId外date+-3天内手术数量最少的护士,返回null表示当天没有有空的护士,返回护士refuseId表示除了当天该id没有有空的护士
	public Worker autoSelectNurse(Date date, String refuseId) {
		if (!logined) {
			return null;
		}
		return new WorkerHelper().autoSelectNurse(date, refuseId);
	}

	// 查询除refuseId外date+-3天内手术数量最少的麻醉师,返回null表示当天没有有空的麻醉师,返回麻醉师refuseId表示除了当天该id没有有空的麻醉师
	public Worker autoSelectAnesthetist(Date date, String refuseId) {
		if (!logined) {
			return null;
		}
		return new WorkerHelper().autoSelectAnesthetist(date, refuseId);
	}
}
