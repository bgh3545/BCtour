package com.greenart.ch1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // �ϴ� DB�� ������Ʈ�� ����
public class UserDaoImpl implements UserDao {
	@Autowired
	DataSource ds;
	final int FAIL = 0;
	
	@Override
	public int deleteUser(String id) {
		int rowCnt = FAIL; // insert, delete, update
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from member where id=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return FAIL; // return 0; �ǹ̸� �и��ϰ�
		} finally {
			// close()�� ȣ���ϴٰ� ���ܰ� �߻��� �� �����Ƿ�, try-catch�� ���ξ���
			// try { if(pstmt!=null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			// try { if(conn!=null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			// �޸𸮰� ����� ��ȯ���� �ʾƼ� ������ �߻��� �� ����
			close(pstmt, conn); // private void close(AutoCloseable... acs) {
		}
	}
	
	@Override
	public DBUser selectUser(String id) {
		DBUser user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from member where id=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); // select;
			if(rs.next()) {
				user = new DBUser();
				user.setId(rs.getString(1));
				user.setPwd(rs.getString(2));
				user.setName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setBirth(new Date(rs.getDate(5).getTime())); // ��¥ ����
				user.setSns(rs.getString(6));
				user.setReg_date(new Date(rs.getTimestamp(7).getTime())); // ��¥ + �ð� ����
			}
		} catch (SQLException e) {
			return null;
		} finally {
			close(rs, pstmt, conn);
		}
		return user;
	}
	// ����� ������ user_info���̺� �����ϴ� �޼���
	@Override
	public int insertUser(DBUser user) {
		int rowCnt = FAIL;
		Connection conn = null; // AutoCloseable �������̽��� �� �� ���� ����
		PreparedStatement pstmt = null;
		
		String sql = "insert into member values(?,?,?,?,?,?, now())"; 
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql); // sql �غ�
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
			pstmt.setString(6, user.getSns());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return FAIL;
		} finally {
			// �Ҹ��� ���� ���� ����
			close(pstmt, conn);
		}
	}
	// �Ű������� ���� ����� ������ user_info ���̺��� update�ϴ� �޼���
	@Override
	public int updateUser(DBUser user) {
		int rowCnt = FAIL;
		
		String sql = "update member " +
					 "set pwd=?, name=?, email=?, birth=?, sns=?, reg_date=?" +
					 "where id=?";
//		String sql = "update member set pwd=?,name=?,email=?,birth=?,sns=?,reg_date=? where id=?";
		
		// try-with-resources - since jdk7, AutoCloseable�� ������ ��ü�� ��� ����
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql); 	// AutoCloseable�� ������ ��ü �̹Ƿ� close�ڵ� ȣ��
		) {
			pstmt.setString(1, user.getPwd());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setDate(4, new java.sql.Date(user.getBirth().getTime()));
			pstmt.setString(5, user.getSns());
			pstmt.setTimestamp(6, new java.sql.Timestamp(user.getReg_date().getTime())); // ��¥ + �ð�
			pstmt.setString(7, user.getId());
			rowCnt = pstmt.executeUpdate();
		} catch ( SQLException e) {
			e.printStackTrace();
			return FAIL;
		} 
		return rowCnt;
	}
	
	@Override
	public void deleteAll() throws Exception {
		Connection conn = ds.getConnection();
		
		String sql = "delete from member";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
	}
	
	// �Ű�Ÿ���� �������ڷ�, n���� �Ű������� �� �� ����( ��ü���� �Ű������� ������ �� ��� ��� )
	private void close(AutoCloseable... acs) {
		for(AutoCloseable ac : acs) {
			try {
				if( ac!=null ) {
					ac.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public ArrayList<DBUser> selectAll(){
		DBUser user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<DBUser> userlist = new ArrayList<>();
		
		String sql = "select * from member";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); // select;
			while(rs.next()) {
				user = new DBUser();
				user.setId(rs.getString(1));
				user.setPwd(rs.getString(2));
				user.setName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setBirth(new Date(rs.getDate(5).getTime())); // ��¥ ����
				user.setSns(rs.getString(6));
				user.setReg_date(new Date(rs.getTimestamp(7).getTime())); // ��¥ + �ð� ����
				userlist.add(user);
			}
		} catch (SQLException e) {
			return null;
		} finally {
			close(rs, pstmt, conn);
		}
		return userlist;
	}
	
//	@Override
//	public ArrayList<DBUser> selectAll(){
//		ArrayList<DBUser> userlist = new ArrayList<>();
//		DBUser user = new DBUser();
//		
//		String sql = "select * from member";
//		
//		
//		try (
//			Connection conn = ds.getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			ResultSet rs = pstmt.executeQuery(); // select;
//		){	
//			while(rs.next()) {
//				user = new DBUser();
//				user.setId(rs.getString(1));
//				user.setPwd(rs.getString(2));
//				user.setName(rs.getString(3));
//				user.setEmail(rs.getString(4));
//				user.setBirth(new Date(rs.getDate(5).getTime())); // ��¥ ����
//				user.setSns(rs.getString(6));
//				user.setReg_date(new Date(rs.getTimestamp(7).getTime())); // ��¥ + �ð� ����
//				userlist.add(user);
//			}
//			return userlist;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return userlist;
//	}
}