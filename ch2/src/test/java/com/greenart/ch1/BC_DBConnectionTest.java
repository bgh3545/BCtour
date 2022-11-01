package com.greenart.ch1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.sql.DataSource;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
//@FixMethodOrder(MethodSorters.NAME_ASCENDING) // test 순서대로 실행
public class BC_DBConnectionTest {
	@Autowired
	DataSource ds; // 컨테이너로부터 자동 주입받는다.
	@Test // 테스트하고자 하는 함수, 접근제한자 public, 함수 이름은 자유
	public void jdbcConnectionTest() throws Exception {
		// ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
		// DataSource ds = ac.getBean(DataSource.class);
		
		Connection conn = ds.getConnection(); // 데이터 베이스 연결을 얻는다.
		
		System.out.println("conn = " + conn);
		// 괄호안의 조건식이 참이면 테스트 성공, 아니면 실패
		assertTrue(conn!=null);
	}
	
	public int insertUser(BCUser user) throws Exception {
		Connection conn = ds.getConnection();
		// 값이 들어갈 곳에 ?로 채우기
		String sql = "insert into member values(?,?,?,?,?,now())";
		// ?에 해당하는 값 채우기, sql injection 공격과 성능향상
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getPwd());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getEmail());
		pstmt.setString(5, user.getTel());
		// insert, delete, update에 사용
		int rowCnt = pstmt.executeUpdate();
		return rowCnt;
	}
		
	@Test
	public void insertUserTest() throws Exception {
		BCUser user = new BCUser("asdf","1234","abcd","aaa@aa.com","010-0000-0000", new Date());
		deleteAll(); // 모든 데이터를 삭제하는 메서드
		int rowCnt = insertUser(user);
		assertTrue(rowCnt==1);
	}
	
	private void deleteAll() throws Exception {
		Connection conn = ds.getConnection();
		String sql = "delete from member";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
	}
	
	public BCUser selectUser (String id) throws Exception {
		Connection conn = ds.getConnection();
		String sql = "select * from member where mem_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// id가 기본키 이므로 중복되는 값이 업성서 하나의 행을 가져오게됨
		pstmt.setString(1, id);
		// select에 사용
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				BCUser user = new BCUser();
				user.setId(rs.getString(1));
				user.setPwd(rs.getString(2));
				user.setName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setTel(rs.getString(5));
				user.setReg_date(new Date(rs.getDate(6).getTime()));
				return user;
			}
			return null;
	}
	
	@Test
	public void selectUserTest() throws Exception {
		//항상 데이터베이스에 존재할 수 있도록, 먼저 기존에 데이터 삭제, 해당 데이터를 추가 후 조회
		deleteAll();
		BCUser user = new BCUser("asdf","1234","abcd","aaa@aa.com","010-0000-0000", new Date());
		int rowCnt = insertUser(user);
		assertTrue(rowCnt==1);
		BCUser user2 = selectUser("asdf");
		System.out.println("select = " + user2);
		assertTrue(user2.getId().equals("asdf")); 
	}
	
	public int deleteUser (String id) throws Exception {
		Connection conn = ds.getConnection();
		String sql = "delete from member where mem_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		int rowCnt = pstmt.executeUpdate();
		return rowCnt;
	}
	
	@Test
	public void deleteUserTest() throws Exception {
		deleteAll();
		int rowCnt = deleteUser("asdf");
		// 해당되는 id값이 없으므로 rowCnt가 0을 반환
		assertTrue(rowCnt==0);
		
		BCUser user = new BCUser("asdf","1234","abcd","aaa@aa.com","010-0000-0000", new Date());
		rowCnt = insertUser(user);
		// insert 성공하면 1 반환 ( 삽입 실행한 행이 1개 )
		assertTrue(rowCnt==1);
		// 삽입 후 삭제하면 1 반환 ( 삭제 실행한 행이 1개 )
		rowCnt = deleteUser(user.getId());
		assertTrue(rowCnt==1);
		// 삭제가 되었다면 해당되는 id조회했을 경우 null값을 반환
		assertTrue(selectUser(user.getId())== null);
	}
	
	public int updateUser (BCUser user) throws Exception {
		Connection conn = ds.getConnection();
		String sql = "update member set mem_pwd=?,mem_name=?,mem_email=?,mem_tel=? where mem_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getPwd());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getEmail());
		pstmt.setString(4, user.getTel());
		pstmt.setString(5, user.getId());
		int rowCnt = pstmt.executeUpdate();
		
		return rowCnt;
	}
	
	@Test
	public void updateUserTest() throws Exception {
		deleteAll();
		
		BCUser user = new BCUser("asdf","1234","abcd","aaa@aa.com","010-0000-0000", new Date());
		int rowCnt = insertUser(user);
		// 삽입 실행한 횟수 ( 1개 )
		assertTrue(rowCnt==1);
		
		// 들어갔나 확인 ( 삽입 select )		
		BCUser insertUser = selectUser(user.getId());
		System.out.println("insert = " + insertUser);

		BCUser user2 = new BCUser("asdf","12345","abcd5","bb@aa.com","010-1234-5678", new Date());
		rowCnt = updateUser(user2);
		// 업데이트 실행한 횟수 ( 1개 )
		assertTrue(rowCnt==1);
		
		// 들어갔나 확인 ( 업데이트 select )
		BCUser updateUser = selectUser(user.getId());
		System.out.println("update = " + updateUser);
	}
	
}
