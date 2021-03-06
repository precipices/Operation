package com.operation.common;

import java.io.Serializable;
import java.sql.Date;
import java.util.Vector;

public class Patient implements Serializable{
	private String id;
	private String name;
	private String sex;
	private Date birth;
	private String call;
	public Patient(String id, String name, String sex, Date birth, String call) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.birth = birth;
		this.call = call;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getCall() {
		return call;
	}
	public void setCall(String call) {
		this.call = call;
	}
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", sex=" + sex + ", birth=" + birth + ", call=" + call + "]";
	}
	public String[] toStringList() {
		return new String[]{id , name, sex, birth.toString(), call};
	}
	public static Patient VectorToPatient(Vector<String> v) {
		Patient patient = new Patient(v.get(0),v.get(1),v.get(2),Date.valueOf(v.get(3)),v.get(4));
		return patient;
	}
	public Vector<String> toVector() {
		Vector<String> data=new Vector<String>();
		data.add(this.getId());
		data.add(this.getName());
		data.add(this.getSex());
		data.add(this.getBirth()+"");
		data.add(this.getCall());
		return data;
	}
}
