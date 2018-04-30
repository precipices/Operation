package com.operation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class SqlHelper {
	//定义数据库需要的东西
	private static final String username="sa";
	private static final String password="sa";
	private static final String database="jdbc:sqlserver://127.0.0.1:1433;databaseName=operation_system";
	private static final String conn="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private Connection ct;
	private PreparedStatement ps;
	private ResultSet rs;

	public Vector<Vector<String>> query(String sql) {
		String[] paras=null;
		return query(sql,paras);
	}
	public Vector<Vector<String>> query(String sql,String[] paras) {
		Vector<Vector<String>> rowData=null;
		try {
			Class.forName(conn);
			ct=DriverManager.getConnection(database,username,password);
			if(paras==null)
				ps=ct.prepareStatement(sql);
			else {
				ps=ct.prepareStatement(sql);
				for(int i=0;i<paras.length;i++) {
					ps.setString(i+1, paras[i]);
				}
			}
			rs=ps.executeQuery();
			rowData=new Vector<Vector<String>>();
			while(rs.next()) {
				Vector<String> hang=new Vector<String>();
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++) {
					hang.add(rs.getString(i));
				}
				rowData.add(hang);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(ps!=null) {ps.close();}
				if(ct!=null) {ct.close();}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return rowData;
	}
	public boolean update(String sql,String[] paras) {
		try {
			Class.forName(conn);
			ct=DriverManager.getConnection(database,username,password);
			ps=ct.prepareStatement(sql);
			for(int i=0;i<paras.length;i++) {
				ps.setString(i+1, paras[i]);
			}

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(ps!=null) {ps.close();}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return true;
	}
}
