package com.greenart.ch1;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class DBConnectionTest2 {
	@Autowired 
	DataSource ds; // 컨테이너로부터 자동 주입 받는다.
	
	public void main(String[] args) throws Exception {
		Connection conn = ds.getConnection(); // 데이터 베이스의 연결을 얻는다.
		
		
//		ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//		DataSource ds = ac.getBean(DataSource.class);
		
//		System.out.println("conn = " + conn);
		
	}
	
	public int insertUser(DBUser user) throws Exception {
		Connection conn = ds.getConnection();
		// 값이 들어갈 곳에 ?로 채우기
		String sql = "insert into member values(?,?,?,?,?,?, now())";
		// ?에 해당하는 값 채우기, sql injection 공격과 성능향상
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getPwd());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getEmail());
		// util.date -> sql.date로 타입 변환
		pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
		pstmt.setString(6, user.getSns());
		// insert, delete, update에 사용
		int rowCnt = pstmt.executeUpdate();
		return rowCnt;
	}
}