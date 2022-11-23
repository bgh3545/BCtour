package com.greenart.ch1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnectionTest2Test {

	public static void main(String[] args) throws Exception {
		String DB_URL = "jdbc:mysql://localhost:3306/member_db?useUnicode=true&characterEncoding=utf8";
		
		String DB_USER = "root";
		String DB_PASSWORD = "319227";
		
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT *from member";
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			String res1 = rs.getString(1);
			String res2 = rs.getString(2);
			System.out.println(res1);
			System.out.println(res2);
		}
	}

}
