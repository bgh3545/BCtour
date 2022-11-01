package com.greenart.ch1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
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
public class DBConnectionTest {
	@Autowired
	DataSource ds; // �����̳ʷκ��� �ڵ� ���Թ޴´�.
	
	// �ڵ�Ŀ�� �׽�Ʈ
	@Test
	public void transactionTest() throws Exception {
		int rowCnt;
		Connection conn = ds.getConnection();
		
		try {
			deleteAll();
			String sql = "insert into member values(?,?,?,?,?,?,now())";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			conn.setAutoCommit(false); // AutoCommit�� �⺻�� true
			pstmt.setString(1, "asdf");
			pstmt.setString(2, "1234");
			pstmt.setString(3, "abc");
			pstmt.setString(4, "aaa@aaaaspect.com");
			pstmt.setDate(5, new java.sql.Date(new Date().getTime()));
			pstmt.setString(6, "fb");
			
			rowCnt = pstmt.executeUpdate();
			pstmt.setString(1, "asdf");
			rowCnt = pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			conn.rollback(); // ������ ���� ��� ��� ( ��� �۾� ���� )
			e.printStackTrace();
		}
	}
	
	
	
	
//	@Test // �׽�Ʈ�ϰ��� �ϴ� �Լ�, ���������� public, �Լ� �̸��� ����
	public void jdbcConnectionTest() throws Exception {
		// ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
		// DataSource ds = ac.getBean(DataSource.class);
		
		Connection conn = ds.getConnection(); // ������ ���̽� ������ ��´�.
		
		System.out.println("conn = " + conn);
		// ��ȣ���� ���ǽ��� ���̸� �׽�Ʈ ����, �ƴϸ� ����
		assertTrue(conn!=null);
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
		
//	@Test
	public void insertUserTest() throws Exception {
		DBUser user = new DBUser("asdf","1234","abcd","aaa@aa.com", new Date(), "facebook", new Date());
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
	
	public DBUser selectUser (String id) throws Exception {
		Connection conn = ds.getConnection();
		String sql = "select * from member where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// id�� �⺻Ű �̹Ƿ� �ߺ��Ǵ� ���� ������ �ϳ��� ���� �������Ե�
		pstmt.setString(1, id);
		// select�� ���
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				DBUser user = new DBUser();
				user.setId(rs.getString(1));
				user.setPwd(rs.getString(2));
				user.setName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setBirth(new Date(rs.getDate(5).getTime()));
				user.setSns(rs.getString(6));
				user.setReg_date(new Date(rs.getDate(7).getTime()));
				return user;
			}
			return null;
	}
	
//	@Test
	public void selectUserTest() throws Exception {
		//�׻� �����ͺ��̽��� ������ �� �ֵ���, ���� ������ ������ ����, �ش� �����͸� �߰� �� ��ȸ
		deleteAll();
		DBUser user = new DBUser("asdf","1234","abcd","aaa@aa.com", new Date(), "facebook", new Date());
		int rowCnt = insertUser(user);
		assertTrue(rowCnt==1);
		DBUser user2 = selectUser("asdf");
		System.out.println("select = " + user2);
		assertTrue(user2.getId().equals("asdf")); 
	}
	
	public int deleteUser (String id) throws Exception {
		Connection conn = ds.getConnection();
		String sql = "delete from member where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		int rowCnt = pstmt.executeUpdate();
		return rowCnt;
	}
	
//	@Test
	public void deleteUserTest() throws Exception {
		deleteAll();
		int rowCnt = deleteUser("asdf");
		// �ش�Ǵ� id���� �����Ƿ� rowCnt�� 0�� ��ȯ
		assertTrue(rowCnt==0);
		
		DBUser user = new DBUser("asdf","1234","abcd","aaa@aa.com", new Date(), "facebook", new Date());
		rowCnt = insertUser(user);
		// insert �����ϸ� 1 ��ȯ ( ���� ������ ���� 1�� )
		assertTrue(rowCnt==1);
		// ���� �� �����ϸ� 1 ��ȯ ( ���� ������ ���� 1�� )
		rowCnt = deleteUser(user.getId());
		assertTrue(rowCnt==1);
		// ������ �Ǿ��ٸ� �ش�Ǵ� id��ȸ���� ��� null���� ��ȯ
		assertTrue(selectUser(user.getId())== null);
	}
	
	public int updateUser (DBUser user) throws Exception {
		Connection conn = ds.getConnection();
		String sql = "update member set pwd=?,name=?,email=?,birth=?,sns=? where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getPwd());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getEmail());
		pstmt.setDate(4, new java.sql.Date(user.getBirth().getTime()));
		pstmt.setString(5, user.getSns());
		pstmt.setString(6, user.getId());
		int rowCnt = pstmt.executeUpdate();
		
		return rowCnt;
	}
	
//	@Test
	public void updateUserTest() throws Exception {
		deleteAll();
		
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2022, 2, 1); // 2�� 3��
		
		DBUser user = new DBUser("asdf","1234","abcd","aaa@aa.com", new Date(), "facebook", new Date());
		int rowCnt = insertUser(user);
		// ���� ������ Ƚ�� ( 1�� )
		assertTrue(rowCnt==1);
		
		// ���� Ȯ�� ( ���� select )		
		DBUser insertUser = selectUser(user.getId());
		System.out.println("insert = " + insertUser);

		DBUser user2 = new DBUser("asdf","123","abc","aa@aa.com", new Date("2022/10/20"), "instagram", new Date());
		rowCnt = updateUser(user2);
		// ������Ʈ ������ Ƚ�� ( 1�� )
		assertTrue(rowCnt==1);
		
		// ���� Ȯ�� ( ������Ʈ select )
		DBUser updateUser = selectUser(user.getId());
		System.out.println("update = " + updateUser);
	}
	
}
