package temp;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class TestJOptionPane {
	public static void main(String[] args) {

		//设置图标
		ImageIcon imageIcon = new ImageIcon("./imgs/1.jpg");
		
		
		//信息框
		JOptionPane.showMessageDialog(null,"内容");
		JOptionPane.showMessageDialog(null, "内容","标题",  JOptionPane.ERROR_MESSAGE);
		JOptionPane.showMessageDialog(null, "内容","标题",  JOptionPane.ERROR_MESSAGE,imageIcon);
		//选择框
		System.out.println(JOptionPane.showConfirmDialog(null,"内容"));
		System.out.println(JOptionPane.showConfirmDialog(null, "内容","标题", JOptionPane.YES_NO_OPTION));
		System.out.println(JOptionPane.showConfirmDialog(null,"内容","标题", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE));
		System.out.println(JOptionPane.showConfirmDialog(null,"内容","标题", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,imageIcon));
		//自己设置选项的选择框
		Object[] options = { "授受", "拒绝"};
		System.out.println(
		JOptionPane.showOptionDialog(null, "内容","标题", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0])
		);
		System.out.println(
		JOptionPane.showOptionDialog(null, "内容","标题", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,imageIcon, options, options[0])
		);
	}
}
