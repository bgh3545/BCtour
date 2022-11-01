package com.greenart.ch1;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DBConnectionTest2 {
	@Autowired
	DataSource ds;
	public void main(String[] args) throws Exception {
		Connection conn = ds.getConnection();
	}
	
	public int insertUser(User user) throws Exception{
		Connection coon = ds.getConnection();
		
		String sql = "insert into member values(?,?,?,?,?,?,now())";
		
		PreparedStatement pstmt = coon.prepareStatement(sql);
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getPwd());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getEmail());
		
		pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
		pstmt.setString(6, user.getSns());
		
		int rowCnt = pstmt.executeUpdate();
		return rowCnt;
	}
}
