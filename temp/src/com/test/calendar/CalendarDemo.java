package com.test.calendar;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class CalendarDemo {
    Calendar calendar = null;

    @Before
    public void test() {
        calendar = Calendar.getInstance();
    }

    // �����÷�����ȡ������ʱ��������
    @Test
    public void test1() {
        // ��ȡ��
        int year = calendar.get(Calendar.YEAR);

        // ��ȡ�£�������Ҫ��Ҫ�·ݵķ�ΧΪ0~11����˻�ȡ�·ݵ�ʱ����Ҫ+1���ǵ�ǰ�·�ֵ
        int month = calendar.get(Calendar.MONTH) + 1;

        // ��ȡ��
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // ��ȡʱ
        int hour = calendar.get(Calendar.HOUR);
        // int hour = calendar.get(Calendar.HOUR_OF_DAY); // 24Сʱ��ʾ

        // ��ȡ��
        int minute = calendar.get(Calendar.MINUTE);

        // ��ȡ��
        int second = calendar.get(Calendar.SECOND);

        // ���ڣ�Ӣ��������ڴ������տ�ʼ����
        int weekday = calendar.get(Calendar.DAY_OF_WEEK);

        System.out.println("������" + year + "��" + month + "��" + day + "��" + hour
                + "ʱ" + minute + "��" + second + "��" + "����" + weekday);
    }

    // һ���Ľ���
    @Test
    public void test2() {
        // ͬ�����¸��µĽ���calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.YEAR, 1);

        // ��ȡ��
        int year = calendar.get(Calendar.YEAR);

        // ��ȡ��
        int month = calendar.get(Calendar.MONTH) + 1;

        // ��ȡ��
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println("һ���Ľ��죺" + year + "��" + month + "��" + day + "��");
    }

    // ��ȡ����һ���µ����һ��
    @Test
    public void test3() {
        // ������6�µ����һ��
        int currentMonth = 6;
        // �����7�·ݵĵ�һ�죬ʵ��������6Ϊ�ⲿ���ݽ�����currentMonth����
        // 1
        calendar.set(calendar.get(Calendar.YEAR), currentMonth, 1);

        calendar.add(Calendar.DATE, -1);

        // ��ȡ��
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println("6�·ݵ����һ��Ϊ" + day + "��");
    }

    // ��������
    @Test
    public void test4() {
        calendar.set(Calendar.YEAR, 2000);
        System.out.println("������" + calendar.get(Calendar.YEAR) + "��");

        calendar.set(2008, 8, 8);
        // ��ȡ��
        int year = calendar.get(Calendar.YEAR);

        // ��ȡ��
        int month = calendar.get(Calendar.MONTH);

        // ��ȡ��
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println("������" + year + "��" + month + "��" + day + "��");
    }
}