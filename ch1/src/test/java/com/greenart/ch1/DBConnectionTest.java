package com.greenart.ch1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTest {
	@Autowired
	DataSource ds;
	
	public void jdbcConnectionTest() throws Exception{
		Connection coon = ds.getConnection();
		assertTrue(coon !=null);
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
	
	@Test
	public void insertUserTest() throws Exception{
		User user = new User("asdfff","1234","asd","asd@asd.com",new Date(),"facebook",new Date());
		User user2 = new User("asdff","123123","asd","asd@asd.com",new Date(),"facebook",new Date());
		deleteAll();
		int rowCnt = insertUser(user);
		
		assertTrue(rowCnt==1);
	}
	
	public User selectUser (String id) throws Exception{
		Connection coon = ds.getConnection();
		String sql = "select * from member where id=?";
		PreparedStatement pstmt = coon.prepareStatement(sql);
		pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				User user = new User();
				user.setId(rs.getString(1));
				user.setPwd(rs.getString(2));
				user.setName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setBirth(new Date(rs.getDate(5).getTime()));
				user.setSns(rs.getString(6));
				user.setReg_date(new Timestamp(rs.getDate(7).getTime()));
				return user;
			}
			return null;
	}
	@Test
	public void selectUserTest() throws Exception{
		deleteAll();
		User user = new User("asdff","1234","asd","asd@asd.com",new Date(),"facebook",new Date());
		int rowCnt = insertUser(user);
		User user2 = selectUser("asdff");
		assertTrue(user2.getId().equals("asdff"));
	}
	
	public int deleteUser(String id) throws Exception{
		Connection coon = ds.getConnection();
		
		String sql = "delete from member where id=?";
		
		PreparedStatement pstmt = coon.prepareStatement(sql);
		pstmt.setString(1, id);
		
		int rowCnt= pstmt.executeUpdate();
		return rowCnt;
	}

	@Test
	public void deleteUserTest() throws Exception{
		deleteAll();
		int rowCnt = deleteUser("asdf");
		assertTrue(rowCnt==0);
		
		User user = new User("asdf","1234","asd","asd@asd.com",new Date(),"facebook",new Date());
		rowCnt=insertUser(user);
		
		assertTrue(rowCnt==1);
		
		rowCnt = deleteUser(user.getId());
		assertTrue(rowCnt==1);
		
		assertTrue(selectUser(user.getId())==null);
	}
	
	public int updateUser(User user) throws Exception{
		Connection coon = ds.getConnection();
		String sql = "update member set pwd=? where id=?";
		PreparedStatement pstmt = coon.prepareStatement(sql);
		
		pstmt.setString(1,user.getPwd());
		pstmt.setString(2,user.getId());
		
		int rowCnt=pstmt.executeUpdate();
		return rowCnt;
	}
	@Test
	public void updateUserTest() throws Exception{
		deleteAll();
		
		User user = new User("asdf","1234","asd","asd@asd.com",new Date(),"facebook",new Date());
		int rowCnt=insertUser(user);
		assertTrue(rowCnt==1);
		
		User user2 = new User("asdf","123123","asd","asd@asd.com",new Date(),"facebook",new Date());
		rowCnt=updateUser(user2);
		assertTrue(rowCnt==1);
	}

	private void deleteAll() throws Exception{
		Connection coon = ds.getConnection();
		String sql = "delete from member";
		PreparedStatement pstmt = coon.prepareStatement(sql);
		pstmt.executeUpdate();
	}
	@Test
	public void transactionTest() throws Exception{
		int rowCnt;
		Connection conn = ds.getConnection();
		try {
			deleteAll();
			
			String sql = "insert into member values(?,?,?,?,?,?,now())";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "asdf");
			pstmt.setString(2, "1234");
			pstmt.setString(3, "asd");
			pstmt.setString(4, "asd@asd.com");
			pstmt.setDate(5, new java.sql.Date(new Date().getTime()));
			pstmt.setString(6, "fb");
			
			rowCnt = pstmt.executeUpdate();
			pstmt.setString(1, "asdf");
			rowCnt = pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			conn.rollback();
			e.printStackTrace();
		}finally {
			
		}
	}
	
}
