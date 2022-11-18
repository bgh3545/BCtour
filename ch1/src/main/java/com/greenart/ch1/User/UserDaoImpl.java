package com.greenart.ch1.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	DataSource ds;
	final int FAIL = 0;
	
	@Override
	public int deleteUser(String id) {
		int rowCnt = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from member where mem_id=?";
		try {
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		finally {
			close(pstmt,conn);
		}
	}
	
	@Override
	public User SelectUser(String id) {
		User user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where mem_id=?";
		try {
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getString(1));
				user.setPwd(rs.getString(2));
				user.setName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setBirth(new Date(rs.getDate(5).getTime()));
				user.setSns(rs.getString(6));
				user.setReg_date(new Timestamp(rs.getDate(7).getTime()));
			}
		}catch(SQLException e) {
			return null;
		}
		finally {
			close(rs,pstmt,conn);
		}
		return user;
	}
	
	@Override
	public User SelectUserEmail(String email) {
		User user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where mem_email=?";
		try {
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getString(1));
				user.setPwd(rs.getString(2));
				user.setName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setBirth(new Date(rs.getDate(5).getTime()));
				user.setSns(rs.getString(6));
				user.setReg_date(new Timestamp(rs.getDate(7).getTime()));
			}
		}catch(SQLException e) {
			return null;
		}
		finally {
			close(rs,pstmt,conn);
		}
		return user;
	}
	
	@Override
	public int insertUser(User user) {
		int rowCnt = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into member values(?,?,?,?,?,?,now())";
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
			pstmt.setString(6, user.getSns());
			return pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return FAIL;
		}
		finally {
			close(pstmt,conn);
		}
	}
	
	@Override
	public int updateUser(User user) {
		int rowCnt=FAIL;
		String sql="update member set mem_pwd=?,mem_name=?,mem_email=?,mem_birth=?,mem_sns=?,mem_reg_date=? where mem_id=?";
		try(
			Connection conn = ds.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			){
			pstmt.setString(1, user.getPwd());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setDate(4, new java.sql.Date(user.getBirth().getTime()));
			pstmt.setString(5, user.getSns());
			pstmt.setTimestamp(6, new java.sql.Timestamp(user.getReg_date().getTime()));
			pstmt.setString(7, user.getId());
			rowCnt = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return FAIL;
		}
		return rowCnt;
	}
	public void deleteAll() throws Exception{
		Connection conn = ds.getConnection();
		String sql = "delete from member";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
	}
	private void close(AutoCloseable... acs) {
		for(AutoCloseable ac:acs)
			try {if(ac!=null)ac.close();
	}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public ArrayList SelectAll() {
		User user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> userlist = new ArrayList<>();
		String sql = "select * from member";
		try {
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setId(rs.getString(1));
				user.setPwd(rs.getString(2));
				user.setName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setBirth(new Date(rs.getDate(5).getTime()));
				user.setSns(rs.getString(6));
				user.setReg_date(new Date(rs.getTimestamp(7).getTime()));
				userlist.add(user);
			}
		}catch(SQLException e) {
			return null;
		}
		finally {
			close(rs,pstmt,conn);
		}
		return userlist;
	}
	
}