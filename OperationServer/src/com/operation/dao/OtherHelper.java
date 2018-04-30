package com.operation.dao;

import java.sql.Date;
import java.util.Vector;

import com.operation.common.Operation;

public class OtherHelper {
	//查询某天有空的手术室,返回null表示当天没有手术室有空
	public Vector<String> selectRoomByDate(Date date){
		Vector<Vector<String>> data = new SqlHelper().query(
				"select id from room where id not in (select roomId from operation where beginTime = ?)",
				new String[] { date.toString() });
		if (data.size() == 0)
			return null;
		Vector<String> rooms = new Vector<String>();
		String room= null;
		for (Vector<String> e : data) {
			room = e.get(0);
			rooms.add(room);
		}
		return rooms;
	}
	//查询某手术室被占用的时间,返回null表示该手术室没有任何一天在使用或id不存在
	public Vector<Date> selectRoomBusyTimesById(String id){
		Vector<Vector<String>> data = new SqlHelper().query(
				"select beginTime from operation where roomId=?",
				new String[] { id});
		if (data.size() == 0)
			return null;
		Vector<Date> busys = new Vector<Date>();
		Date busy= null;
		for (Vector<String> e : data) {
			busy = Date.valueOf(e.get(0));
			busys.add(busy);
		}
		return busys;
	}

}