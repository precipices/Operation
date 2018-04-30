package com.operation.mainframe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.operation.common.Worker;
import com.operation.manager.ManagerMainFrame;
import com.operation.rpc.RPCHelper;

public class Login extends JFrame implements ActionListener {
	JTextField id;
	JTextField password;
	JButton loginButton;

	public Login() {
		super("����ԤԼ�Ű�ϵͳ��½");
		// ���
		JLabel jl1 = new JLabel("�˺ţ�");
		JLabel jl2 = new JLabel("���룺");
		id = new JTextField(10);
		password = new JPasswordField(10);
		loginButton = new JButton("��    ¼");

		// ��Ӽ�����
		loginButton.addActionListener(this);

		// ����
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		jp1.add(jl1);
		jp1.add(id);
		jp2.add(jl2);
		jp2.add(password);
		jp3.add(loginButton);
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		jp.add(jp1, BorderLayout.NORTH);
		jp.add(jp2, BorderLayout.CENTER);
		jp.add(jp3, BorderLayout.SOUTH);
		this.setLayout(new FlowLayout());
		this.add(jp);

		this.setBounds(200, 100, 300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Login();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			// ���ı����еõ�����
			String id_Str = id.getText();
			String password_Str = password.getText();
			// �½�RPCHelper
			RPCHelper helper = new RPCHelper();
			int r = helper.login(id_Str, password_Str);
			String message = "test";
			switch (r) {
			case RPCHelper.CONNECT_FAIL:
				message = "����������ʧ��!\nIP�Ͷ˿ںŴ���������δ����!";
				break;
			case RPCHelper.LOGINED:
				message = "ID[" + id_Str + "]�ѵ�½!";
				break;
			case RPCHelper.ID_NOT_FOUND:
				message = "ID[" + id_Str + "]������!";
				break;
			case RPCHelper.PASSWORD_NOT_TRUE:
				message = "�������!";
				break;
			case RPCHelper.DOCTOR:
				message = "��½�ɹ�,��½����ҽ��";
				break;
			case RPCHelper.ANESTHETIST:
				message = "��½�ɹ�,��½��������ʦ";
				break;
			case RPCHelper.NURSE:
				message = "��½�ɹ�,��½���ǻ�ʿ";
				break;
			case RPCHelper.ADMIN:
				message = "��ӭ��½,�𾴵Ĺ���Ա����";
				break;
			default:
				message = "δ֪����!";
			}
			JOptionPane.showMessageDialog(this, message);
			if (r >= RPCHelper.DOCTOR && r<= RPCHelper.ANESTHETIST) {// ����ɹ���½,��رյ�½����,����������
				this.dispose();
				Worker worker = helper.selectWorkerById(id_Str);
				InitComponent.initClient();
				new MainFrame(helper, worker);
			}else if(r==RPCHelper.ADMIN) {
				this.dispose();
//				Worker worker = helper.selectWorkerById(id_Str);
				new ManagerMainFrame(helper);
			}
		}

	}
}
