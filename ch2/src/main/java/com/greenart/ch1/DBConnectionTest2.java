package com.greenart.ch1;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class DBConnectionTest2 {
	@Autowired 
	DataSource ds; // �����̳ʷκ��� �ڵ� ���� �޴´�.
	
	public void main(String[] args) throws Exception {
		Connection conn = ds.getConnection(); // ������ ���̽��� ������ ��´�.
		
		
//		ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//		DataSource ds = ac.getBean(DataSource.class);
		
//		System.out.println("conn = " + conn);
		
	}
	
	public int insertUser(DBUser user) throws Exception {
		Connection conn = ds.getConnection();
		// ���� �� ���� ?�� ä���
		String sql = "insert into member values(?,?,?,?,?,?, now())";
		// ?�� �ش��ϴ� �� ä���, sql injection ���ݰ� �������
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getPwd());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getEmail());
		// util.date -> sql.date�� Ÿ�� ��ȯ
		pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
		pstmt.setString(6, user.getSns());
		// insert, delete, update�� ���
		int rowCnt = pstmt.executeUpdate();
		return rowCnt;
	}
}