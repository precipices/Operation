package temp;

import java.sql.Date;
import java.util.Vector;

import com.operation.common.Operation;
import com.operation.common.Patient;
import com.operation.common.RemoteCall;
import com.operation.common.Worker;
import com.operation.mainframe.MainFrame;
import com.operation.rpc.RPCHelper;

public class TestRPCHelper {
	//��������д���һ��ر�����Ϊ����һ�����򣬶�MainFrame���õ���[this.setDefaultCloseOperation(EXIT_ON_CLOSE)]�������رճ���
	public static void main(String[] args) {
		RPCHelper helper = null;
		Worker worker = null;

		System.out.println("**********************************************Login**********************************************************");
		helper = new RPCHelper();
		//����������͵�½����,�����������жϺ󷵼�һ��int����,���ص�int���������RPCHelper����ľ�̬����
		helper.login("w0001", "123");
		// ����Id��ѯWorker������null���ʾid������
		worker = helper.selectWorkerById("w0001");
		new MainFrame(helper,worker);
		System.out.println("**********************************************Worker**********************************************************");
		// ��ѯĳ���пյĻ�ʿ������null���ʾ����û���пյĻ�ʿ
		helper.selectNursesByDate(Date.valueOf("2018-5-18"));
		// ��ѯĳ���пյ�����ʦ������null���ʾ����û���пյ�����ʦ
		helper.selectAnesthetistsByDate(Date.valueOf("2018-5-18"));
		// ����Ա��,����false��ʾ����ʧ��
		helper.addWorker(new Worker("w1005", "123", "����", "Ů", Date.valueOf("1995-5-5"), "��ʿ", "158-0001-0005","����"));
		// ɾ��Ա��,����false��ʾɾ��ʧ��
		helper.deleteWorker("w1005");
		System.out.println("**********************************************Patient**********************************************************");
		// ��ѯ���в���,����null��ʾû�в���
		helper.selectAllPatients();
		// ����Id��ѯ���ˣ�����null���ʾid������
		helper.selectPatientById("p0001");
		// �������ֲ�ѯ���ˣ�����null���ʾ���ֲ�����
		helper.selectPatientByName("����");
		// ���Ӳ���,����false��ʾ����ʧ��
//		helper.addPatient(new Patient("p0005", "���", "Ů", Date.valueOf("1993-8-8"), "135-0000-0050"));
		System.out.println("**********************************************Operation**********************************************************");
		// ��ѯ��������,����null��ʾû������
		helper.selectAllOperations();
		// ����id�鵥������,����null��ʾid������
		helper.selectOperationById("o0001");
		// ĳ�����������,����null��ʾ����û������
		helper.selectOperationByDate(Date.valueOf("2018-3-3"));
		// ��ѯ��ĳ����֮�������
		helper.selectOperationAfterDate(Date.valueOf("2018-3-3"));
		// ����������֮�������,����null��ʾ��������֮��û������
		helper.selectOperationsBetween(Date.valueOf("2018-1-1"), Date.valueOf("2018-3-3")); 
		// ��ĳԱ�������ȫ������,����null��ʾԱ��û������
		helper.selectWorkerAllOperations("w0001");
		// ��ĳԱ��ĳ���ڲ���ĵ�������,����null��ʾԱ������û������
		helper.selectWorkerOperationsByDate("w0001", Date.valueOf("2018-1-1"));
		// ��ĳԱ��������֮������ȫ������,����null��ʾԱ����������֮��û������
		helper.selectWorkerAllOperationsBetween("w0001", Date.valueOf("2018-1-1"), Date.valueOf("2018-3-3")); 
		// ��ĳ���˲����ȫ������,����null��ʾ����û������
		helper.selectPatientAllOperations("p0001");
		// ��������,����false��ʾ����ʧ��
//		helper.addOperation(new Operation("o0006","����6",Date.valueOf("2018-5-20"),"14","p0001","w0001","w1001","w2001",null,null,null));
		// ���ӻ��޸Ĳ���������ҽ��,����false��ʾ�޸�ʧ��
		helper.updateDoctorToOperation("o0001", "w0001");
		// ���ӻ��޸Ĳ��������Ļ�ʿ,����false��ʾ�޸�ʧ��
		helper.updateNurseToOperation("o0001", "w1001");
		// ���ӻ��޸Ĳ�������������ʦ,����false��ʾ�޸�ʧ��
		helper.updateAnesthetistToOperation("o0001", "w2001"); 
		// ���ӻ��޸�ҽ��������¼,����false��ʾ�޸�ʧ��
		helper.updateDoctorRecordToOperation("o0001", "ҽ����¼1");
		// ���ӻ��޸Ļ�ʿ������¼,����false��ʾ�޸�ʧ��
		helper.updateNurseRecordToOperation("o0001", "��ʿ��¼1");
		// ���ӻ��޸�����ʦ������¼,����false��ʾ�޸�ʧ��
		helper.updateAnesthetistRecordToOperation("o0001", "����ʦ��¼1");
		// ���ӻ��޸�������,����false��ʾ�޸�ʧ��
		helper.updateRoomToOperation("o0001", "10");
		System.out.println("**********************************************Room**********************************************************");
		//��ѯĳ���пյ�������,����null��ʾ����û���������п�
		helper.selectRoomByDate(Date.valueOf("2018-1-1"));
		//��ѯĳ�����ұ�ռ�õ�ʱ��,����null��ʾ��������û���κ�һ����ʹ�û�id������
		helper.selectRoomBusyTimesById("10");
	
//		helper = new RPCHelper();
//		helper.login("w1001", "123");
//		worker = helper.selectWorkerById("w1001");
//		new MainFrame(helper,worker);
//		
//		helper = new RPCHelper();
//		helper.login("w2001", "123");
//		worker = helper.selectWorkerById("w2001");
//		new MainFrame(helper,worker);
		

	}
}