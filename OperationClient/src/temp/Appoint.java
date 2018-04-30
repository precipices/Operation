package temp;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.operation.myComponent.BackFrame;
import com.operation.myComponent.BackPane;
import com.operation.select.SelectAnesthetistPane;
import com.operation.select.SelectNursePane;
import com.operation.select.SelectPatientPane;

//û��fieldȡֵ
public class Appoint extends BackPane{
	
	private JTextField textField_patient;
	private JTextField textField_nurse;
	private JTextField textField_anesthetist;
	private AppointListener listener;
	private JButton button_select_patient;
	private JButton button_select_nurse;
	private JButton button_select_anesthetist;
	private JButton button_save;
	private JComboBox cb_select_name;private String select_name;
	private JComboBox cb_select_year;private String select_year;
	private JComboBox cb_select_month;private String select_month;
	private JComboBox cb_select_day;private String select_day;
	private JComboBox cb_select_stime;private String select_stime;
	private JComboBox cb_select_room;private String select_room;
		
	public BackPane panel_acenter;
	public BackPane j;
	
	
	public Appoint() {
		listener = new AppointListener();
		initialize();
	}
	
	class AppointListener implements ActionListener {//�����������
		@Override
		public void actionPerformed(ActionEvent a) {
			if(a.getActionCommand().equals("button_select_patient")) {
				System.out.println("����˲���ѡ��Ť");
				j = panel_acenter;
				remove(panel_acenter);
				panel_acenter = new SelectPatientPane();
				panel_acenter.setBounds(0, 0, 580, 500);
				add(panel_acenter);
				validate();//���²���
				repaint();//�ػ滭��
				//���ܲ���ʾ�ش���Ϣ�󣬼���ִ�д���
				/*JOptionPane.showMessageDialog(null, "�ָ�");
				
				remove(panel_center);
				panel_center = j;
				panel_center.setBounds(0, 0, 580, 500);
				add(panel_center);
				validate();//���²���
				repaint();//�ػ滭��*/
				
			}else if(a.getActionCommand().equals("button_select_nurse")) {
				System.out.println("����˻�ʿѡ��Ť");
				j = panel_acenter;
				remove(panel_acenter);
				panel_acenter = new SelectNursePane();
				panel_acenter.setBounds(0, 0, 580, 500);
				add(panel_acenter);
				validate();//���²���
				repaint();//�ػ滭��
				
				/*JOptionPane.showMessageDialog(null, "�ָ�");
				
				remove(panel_center);
				panel_center = j;
				panel_center.setBounds(0, 0, 580, 500);
				add(panel_center);
				validate();//���²���
				repaint();//�ػ滭��*/
				
			}else if(a.getActionCommand().equals("button_select_anesthetist")) {
				System.out.println("���������ʦѡ��Ť");
				j = panel_acenter;
				remove(panel_acenter);
				panel_acenter = new SelectAnesthetistPane();
				panel_acenter.setBounds(0, 0, 580, 500);
				add(panel_acenter);
				validate();//���²���
				repaint();//�ػ滭��
				
				/*JOptionPane.showMessageDialog(null, "�ָ�");
				
				remove(panel_center);
				panel_center = j;
				panel_center.setBounds(0, 0, 580, 500);
				add(panel_center);
				validate();
				repaint();*/
			}else if(a.getActionCommand().equals("button_save")) {
				System.out.println("����˱��水Ť");
				//¼�벢������Ϣ
				JOptionPane.showMessageDialog(null, "����ɹ�");
				
				select_name = (String)cb_select_name.getSelectedItem();
				System.out.println("��������ѡ����"+select_name);
				select_year = (String)cb_select_year.getSelectedItem();
				System.out.println("�꣺"+select_year);
				select_month = (String)cb_select_month.getSelectedItem();
				System.out.println("�£�"+select_month);	
				select_day = (String)cb_select_day.getSelectedItem();
				System.out.println("�գ�"+select_day);
				select_stime = (String)cb_select_stime.getSelectedItem();
				System.out.println("����ʱ��ѡ���ˣ�"+select_stime);
				select_room = (String)cb_select_room.getSelectedItem();
				System.out.println("������ѡ���ˣ�"+select_room);
				
				
//				new ExistOperation();//���޸ģ���������������ʾ�½�ԤԼ��������Ϣ���½��󱣴棬����NewOperation���棬�´β鿴ʱ��ʾ
								
//				new MainOperationPane();//���޸ģ���remove�ص���������
			}
		}
	}
	
	
	private void initialize() {
		this.setLayout(null);	
		
		panel_acenter = new BackPane();
		panel_acenter.setBounds(10, 10, 580, 500);
		this.add(panel_acenter);
		panel_acenter.setLayout(null);
		panel_acenter.setVisible(true);
		
		JLabel label_title = new JLabel("����ԤԼ");
		label_title.setFont(new Font("����", Font.PLAIN, 16));
		label_title.setBounds(100, 4, 109, 15);
		panel_acenter.add(label_title);
		
		
		JLabel label_patient = new JLabel("���ˣ�");
		label_patient.setFont(new Font("����", Font.PLAIN, 15));
		label_patient.setBounds(45, 29, 87, 18);
		panel_acenter.add(label_patient);
		
		button_select_patient = new JButton();
		button_select_patient.setText("����ѡ��");
		button_select_patient.setBounds(142, 27, 110, 23);
		panel_acenter.add(button_select_patient);
		button_select_patient.setActionCommand("button_select_patient");
		button_select_patient.addActionListener(listener);

		textField_patient = new JTextField();
		textField_patient.setColumns(10);
		textField_patient.setBounds(142, 60, 235, 21);
		panel_acenter.add(textField_patient);
		

		JLabel label_name = new JLabel("�������֣�");
		label_name.setFont(new Font("����", Font.PLAIN, 15));
		label_name.setBounds(45, 92, 87, 18);
		panel_acenter.add(label_name);
		
		String[] name = {"----��ѡ����������----","��һ��","�ڶ���","������","������"};
		cb_select_name = new JComboBox(name);
		cb_select_name.setBounds(142, 91, 235, 21);
		panel_acenter.add(cb_select_name);
		
		
		
		JLabel label_date = new JLabel("�������ڣ�");
		label_date.setFont(new Font("����", Font.PLAIN, 15));
		label_date.setBounds(45, 120, 87, 18);
		panel_acenter.add(label_date);
		
		JLabel label_year = new JLabel("�꣺");
		label_year.setBounds(142, 124, 29, 15);
		panel_acenter.add(label_year);

		String[] year = {"2018","2019","2020","2021","2022"};
		cb_select_year = new JComboBox(year);
		cb_select_year.setBounds(167, 119, 56, 21);
		panel_acenter.add(cb_select_year);
		
				
		JLabel label_month = new JLabel("�£�");
		label_month.setBounds(233, 123, 29, 15);
		panel_acenter.add(label_month);
		
		String[] month = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		cb_select_month = new JComboBox(month);
		cb_select_month.setBounds(256, 119, 56, 21);
		panel_acenter.add(cb_select_month);
		
		
		JLabel label_day = new JLabel("�գ�");
		label_day.setBounds(325, 123, 29, 15);
		panel_acenter.add(label_day);
		
		String[] day = {"1","2","3","4","5","6","7","8","9","10",
				"11","12","13","14","15","16","17","18","19","20",
				"21","22","23","24","25","26","27","28","29","30","31"};
		cb_select_day = new JComboBox(day);
		cb_select_day.setBounds(347, 119, 56, 21);
		panel_acenter.add(cb_select_day);
		
		
		
		
		JLabel label_time = new JLabel("����ʱ�䣺");
		label_time.setFont(new Font("����", Font.PLAIN, 15));
		label_time.setBounds(45, 148, 87, 18);
		panel_acenter.add(label_time);
		
		String[] time = {"00:00","01:00","02:00","03:00","04:00","05:00",
				"06:00","07:00","08:00","09:00","10:00","11:00",
				"12:00","13:00","14:00","15:00","16:00","17:00",
				"18:00","19:00","20:00","21:00","22:00","23:00"};
		
		cb_select_stime = new JComboBox(time);
		cb_select_stime.setBounds(142, 147, 113, 21);
		panel_acenter.add(cb_select_stime);
		
		
		
		JLabel label_room = new JLabel("�����ң�");
		label_room.setFont(new Font("����", Font.PLAIN, 15));
		label_room.setBounds(45, 178, 87, 18);
		panel_acenter.add(label_room);
		
		String[] room = {"1��","2��","3��","4��","5��","6��","7��","8��","9��","10��",
				         "11��","12��","13��","14��","15��"};
		
		cb_select_room = new JComboBox(room);
		cb_select_room.setBounds(142, 177, 113, 21);
		panel_acenter.add(cb_select_room);
		
		
		JLabel label_nurse = new JLabel("��ʿ��");
		label_nurse.setFont(new Font("����", Font.PLAIN, 15));
		label_nurse.setBounds(45, 206, 87, 18);
		panel_acenter.add(label_nurse);
		
		button_select_nurse = new JButton("��ʿѡ��");
		button_select_nurse.setBounds(142, 204, 110, 23);
		panel_acenter.add(button_select_nurse);
		//���������˼�����,�Լ�ָ����,�������Ҫ�������������Ϊ��Ա����,�����᷽�����������ҵ�������,���Message.java******************
		button_select_nurse.setActionCommand("button_select_nurse");
		button_select_nurse.addActionListener(listener);
		
		textField_nurse = new JTextField();
		textField_nurse.setBounds(142, 229, 235, 21);
		panel_acenter.add(textField_nurse);
		textField_nurse.setColumns(10);
		
		
		JLabel label_anesthetist = new JLabel("����ʦ��");
		label_anesthetist.setFont(new Font("����", Font.PLAIN, 15));
		label_anesthetist.setBounds(45, 262, 87, 18);
		panel_acenter.add(label_anesthetist);
		
		button_select_anesthetist = new JButton("����ʦѡ��");
		button_select_anesthetist.setBounds(142, 260, 110, 23);
		panel_acenter.add(button_select_anesthetist);
		button_select_anesthetist.setActionCommand("button_select_anesthetist");
		button_select_anesthetist.addActionListener(listener);
		
		textField_anesthetist = new JTextField();
		textField_anesthetist.setColumns(10);
		textField_anesthetist.setBounds(142, 287, 235, 21);
		panel_acenter.add(textField_anesthetist);
		
		
		button_save = new JButton("����");
		button_save.setBounds(143, 352, 93, 23);
		panel_acenter.add(button_save);
		button_save.setActionCommand("button_save");
		button_save.addActionListener(listener);
		
	}
	public static void main(String[] args) {
		JFrame f =new BackFrame("test","./imgs/bg2.jpg");
//		f.setLayout(new FlowLayout());
		f.add(new Appoint());
		// ���ô�С����ʾ����
		f.setBounds(200, 100, 800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}