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
//@FixMethodOrder(MethodSorters.NAME_ASCENDING) // test ������� ����
public class BC_DBConnectionTest {
	@Autowired
	DataSource ds; // �����̳ʷκ��� �ڵ� ���Թ޴´�.
	@Test // �׽�Ʈ�ϰ��� �ϴ� �Լ�, ���������� public, �Լ� �̸��� ����
	public void jdbcConnectionTest() throws Exception {
		// ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
		// DataSource ds = ac.getBean(DataSource.class);
		
		Connection conn = ds.getConnection(); // ������ ���̽� ������ ��´�.
		
		System.out.println("conn = " + conn);
		// ��ȣ���� ���ǽ��� ���̸� �׽�Ʈ ����, �ƴϸ� ����
		assertTrue(conn!=null);
	}
	
	public int insertUser(BCUser user) throws Exception {
		Connection conn = ds.getConnection();
		// ���� �� ���� ?�� ä���
		String sql = "insert into member values(?,?,?,?,?,now())";
		// ?�� �ش��ϴ� �� ä���, sql injection ���ݰ� �������
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getPwd());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getEmail());
		pstmt.setString(5, user.getTel());
		// insert, delete, update�� ���
		int rowCnt = pstmt.executeUpdate();
		return rowCnt;
	}
		
	@Test
	public void insertUserTest() throws Exception {
		BCUser user = new BCUser("asdf","1234","abcd","aaa@aa.com","010-0000-0000", new Date());
		deleteAll(); // ��� �����͸� �����ϴ� �޼���
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
		// id�� �⺻Ű �̹Ƿ� �ߺ��Ǵ� ���� ������ �ϳ��� ���� �������Ե�
		pstmt.setString(1, id);
		// select�� ���
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
		//�׻� �����ͺ��̽��� ������ �� �ֵ���, ���� ������ ������ ����, �ش� �����͸� �߰� �� ��ȸ
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
		// �ش�Ǵ� id���� �����Ƿ� rowCnt�� 0�� ��ȯ
		assertTrue(rowCnt==0);
		
		BCUser user = new BCUser("asdf","1234","abcd","aaa@aa.com","010-0000-0000", new Date());
		rowCnt = insertUser(user);
		// insert �����ϸ� 1 ��ȯ ( ���� ������ ���� 1�� )
		assertTrue(rowCnt==1);
		// ���� �� �����ϸ� 1 ��ȯ ( ���� ������ ���� 1�� )
		rowCnt = deleteUser(user.getId());
		assertTrue(rowCnt==1);
		// ������ �Ǿ��ٸ� �ش�Ǵ� id��ȸ���� ��� null���� ��ȯ
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
		// ���� ������ Ƚ�� ( 1�� )
		assertTrue(rowCnt==1);
		
		// ���� Ȯ�� ( ���� select )		
		BCUser insertUser = selectUser(user.getId());
		System.out.println("insert = " + insertUser);

		BCUser user2 = new BCUser("asdf","12345","abcd5","bb@aa.com","010-1234-5678", new Date());
		rowCnt = updateUser(user2);
		// ������Ʈ ������ Ƚ�� ( 1�� )
		assertTrue(rowCnt==1);
		
		// ���� Ȯ�� ( ������Ʈ select )
		BCUser updateUser = selectUser(user.getId());
		System.out.println("update = " + updateUser);
	}
	
}
