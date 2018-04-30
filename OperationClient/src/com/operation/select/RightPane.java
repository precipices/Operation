package com.operation.select;

import java.awt.CardLayout;

import javax.swing.JLabel;

import com.operation.mainframe.InitComponent;
import com.operation.myComponent.BackPane;

public class RightPane extends BackPane {
	private static String[] names = { "�����Ҳ�ѯ", "���˲�ѯ", "ҽ���Ű��ѯ", "��ʿ�Ű��ѯ", "����ʦ�Ű��ѯ", "������ѯ", "������ص�����" };
	private CardLayout card = null;

	public RightPane() {
		card = new CardLayout();
		this.setLayout(card);
		for (int i = 0; i < names.length; i++) {
			this.add(new JLabel(names[i]), names[i]);
		}
		this.add(new SelectRoomPane(),"�����Ҳ�ѯ");
		this.add(new SelectNursePane(), "��ʿ�Ű��ѯ");
		this.add(new SelectAnesthetistPane(), "����ʦ�Ű��ѯ");
		this.add(new SelectPatientPane(), "���˲�ѯ");
	}

	public CardLayout getCard() {
		return card;
	}
}