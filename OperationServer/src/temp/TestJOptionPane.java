package temp;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class TestJOptionPane {
	public static void main(String[] args) {

		//����ͼ��
		ImageIcon imageIcon = new ImageIcon("./imgs/1.jpg");
		
		
		//��Ϣ��
		JOptionPane.showMessageDialog(null,"����");
		JOptionPane.showMessageDialog(null, "����","����",  JOptionPane.ERROR_MESSAGE);
		JOptionPane.showMessageDialog(null, "����","����",  JOptionPane.ERROR_MESSAGE,imageIcon);
		//ѡ���
		System.out.println(JOptionPane.showConfirmDialog(null,"����"));
		System.out.println(JOptionPane.showConfirmDialog(null, "����","����", JOptionPane.YES_NO_OPTION));
		System.out.println(JOptionPane.showConfirmDialog(null,"����","����", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE));
		System.out.println(JOptionPane.showConfirmDialog(null,"����","����", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,imageIcon));
		//�Լ�����ѡ���ѡ���
		Object[] options = { "����", "�ܾ�"};
		System.out.println(
		JOptionPane.showOptionDialog(null, "����","����", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0])
		);
		System.out.println(
		JOptionPane.showOptionDialog(null, "����","����", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,imageIcon, options, options[0])
		);
	}
}
