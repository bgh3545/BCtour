package com.greenart.ch1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnectionTest343 {

	public static void main(String[] args) throws Exception {

		// ��Ű���� �̸�(db�̸�)�� �ٸ� ��� �˸°� �����ؾ� ��
		String DB_URL = "jdbc:mysql://localhost:3306/google_db?useUnicode=true&characterEncoding=utf8";
		
		// DB�� userid�� pwd�� �˸°� �����ؾ� ��
		String DB_USER = "root";
		String DB_PASSWORD = "0000";
		
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // �����ͺ��̽��� ������ ��´�.
		Statement stmt = conn.createStatement(); // Statement�� �����Ѵ�.
		
		String query = "SELECT * FROM member"; // �ý����� ���� ��¥�ð��� ����ϴ� ����(query)
		ResultSet rs = stmt.executeQuery(query); // query�� ������ ����� rs�� ��´�.
		
		// �������� ��� rs���� �� �پ� �ϰϼ� ���
		while(rs.next()) {
			String curDate = rs.getString(1); // �о�� ���� ù��° Į���� ���� String���� �о curDate�� ����
			System.out.println(curDate);
		}
	}
}