package com.operation.common;

import java.io.Serializable;
import java.sql.Date;
import java.util.Vector;

public class Operation implements Serializable{
	private String id;
	private String name;
	private Date beginTime;
	private String roomId;
	private String patientId;
	private String doctorId;
	private String nurseId;
	private String anesthetistId;
	private String doctorRecord;
	private String nurseRecord;
	private String anesthetistRecord;

	public Operation(String id, String name, Date beginTime, String roomId, String patientId, String doctorId,
			String nurseId, String anesthetistId, String doctorRecord, String nurseRecord, String anesthetistRecord) {
		super();
		this.id = id;
		this.name = name;
		this.beginTime = beginTime;
		this.roomId = roomId;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.nurseId = nurseId;
		this.anesthetistId = anesthetistId;
		this.doctorRecord = doctorRecord;
		this.nurseRecord = nurseRecord;
		this.anesthetistRecord = anesthetistRecord;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getNurseId() {
		return nurseId;
	}

	public void setNurseId(String nurseId) {
		this.nurseId = nurseId;
	}

	public String getAnesthetistId() {
		return anesthetistId;
	}

	public void setAnesthetistId(String anesthetistId) {
		this.anesthetistId = anesthetistId;
	}

	public String getDoctorRecord() {
		return doctorRecord;
	}

	public void setDoctorRecord(String doctorRecord) {
		this.doctorRecord = doctorRecord;
	}

	public String getNurseRecord() {
		return nurseRecord;
	}

	public void setNurseRecord(String nurseRecord) {
		this.nurseRecord = nurseRecord;
	}

	public String getAnesthetistRecord() {
		return anesthetistRecord;
	}

	public void setAnesthetistRecord(String anesthetistRecord) {
		this.anesthetistRecord = anesthetistRecord;
	}

	@Override
	public String toString() {
		return "Operation [id=" + id + ", name=" + name + ", beginTime=" + beginTime + ", roomId=" + roomId
				+ ", patientId=" + patientId + ", doctorId=" + doctorId + ", nurseId=" + nurseId + ", anesthetistId="
				+ anesthetistId + ", doctorRecord=" + doctorRecord + ", nurseRecord=" + nurseRecord
				+ ", anesthetistRecord=" + anesthetistRecord + "]";
	}

	public String[] toStringList() {
		return new String[] { id, name, beginTime.toString(), roomId, patientId, doctorId, nurseId, anesthetistId,
				doctorRecord, nurseRecord, anesthetistRecord };
	}

	public static Operation VectorToOperation(Vector<String> v) {
		Operation operation = new Operation(v.get(0), v.get(1), Date.valueOf(v.get(2)), v.get(3), v.get(4), v.get(5),
				v.get(6), v.get(7), v.get(8), v.get(9), v.get(10));
		return operation;
	}
	public Vector<String> toVector(){
		Vector<String> data=new Vector<String>();
		data.add(this.getId());
		data.add(this.getName());
		data.add(this.getBeginTime()+"");
		data.add(this.getRoomId());
		data.add(this.getPatientId());
		data.add(this.getDoctorId());
		data.add(this.getNurseId());
		data.add(this.getAnesthetistId());
		return data;
	}
}
