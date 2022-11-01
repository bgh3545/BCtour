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

@Repository // 일단 DB용 컴포넌트라 생각
public class BCUserDaoImpl implements BCUserDao {
	@Autowired
	DataSource ds;
	final int FAIL = 0;

	// 아이디 찾기
	@Override
	public BCUser idToTelUser(BCUser bcuser) {
		BCUser user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from member where mem_name=? and mem_tel=?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bcuser.getName());
			pstmt.setString(2, bcuser.getTel());
			rs = pstmt.executeQuery(); // select;
			if (rs.next()) {
				user = new BCUser();
				user.setId(rs.getString(1)); // 아이디정보
				user.setPwd(rs.getString(2)); // 비밀번호정보
				user.setName(rs.getString(3)); // 이름정보
				user.setEmail(rs.getString(4)); // 이메일정보
				user.setTel(rs.getString(5)); // 휴대번호정보
				user.setReg_date(new Date(rs.getTimestamp(6).getTime())); // 날짜 + 시간 정보
			}
		} catch (SQLException e) {
			return null;
		} finally {
			close(rs, pstmt, conn);
		}
		return user;
	}

	// 비밀번호 찾기
	@Override
	public BCUser pwdToTelUser(BCUser bcuser) {
		BCUser user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from member where mem_id=? and mem_name=? and mem_tel=?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bcuser.getId());
			pstmt.setString(2, bcuser.getName());
			pstmt.setString(3, bcuser.getTel());
			rs = pstmt.executeQuery(); // select;
			if (rs.next()) {
				user = new BCUser();
				user.setId(rs.getString(1)); // 아이디정보
				user.setPwd(rs.getString(2)); // 비밀번호정보
				user.setName(rs.getString(3)); // 이름정보
				user.setEmail(rs.getString(4)); // 이메일정보
				user.setTel(rs.getString(5)); // 휴대번호정보
				user.setReg_date(new Date(rs.getTimestamp(6).getTime())); // 날짜 + 시간 정보
			}
		} catch (SQLException e) {
			return null;
		} finally {
			close(rs, pstmt, conn);
		}
		return user;
	}

	@Override
	public int deleteUser(String id) {
		int rowCnt = FAIL; // insert, delete, update
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "delete from member where mem_id=?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return FAIL; // return 0; 의미를 분명하게
		} finally {
			// close()를 호출하다가 예외가 발생할 수 있으므로, try-catch로 감싸야함
			// try { if(pstmt!=null) pstmt.close(); } catch (SQLException e) {
			// e.printStackTrace(); }
			// try { if(conn!=null) pstmt.close(); } catch (SQLException e) {
			// e.printStackTrace(); }
			// 메모리가 제대로 반환되지 않아서 문제가 발생할 수 있음
			close(pstmt, conn); // private void close(AutoCloseable... acs) {
		}
	}

	@Override
	public BCUser selectUser(String id) {
		BCUser user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from member where mem_id=?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); // select;
			if (rs.next()) {

				user = new BCUser();
				user.setId(rs.getString(1)); // 아이디정보
				user.setPwd(rs.getString(2)); // 비밀번호정보
				user.setName(rs.getString(3)); // 이름정보
				user.setEmail(rs.getString(4)); // 이메일정보
				user.setTel(rs.getString(5)); // 휴대번호정보
				user.setReg_date(new Date(rs.getTimestamp(6).getTime())); // 날짜 + 시간 정보
			}
		} catch (SQLException e) {
			return null;
		} finally {
			close(rs, pstmt, conn);
		}
		return user;
	}

	// 사용자 정보를 user_info테이블에 저장하는 메서드
	@Override
	public int insertUser(BCUser user) {
		int rowCnt = FAIL;
		Connection conn = null; // AutoCloseable 인터페이스를 둘 다 구현 생성
		PreparedStatement pstmt = null;

		String sql = "insert into member values(?,?,?,?,?, now())";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql); // sql 준비
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getTel());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return FAIL;
		} finally {
			// 소멸할 때는 생성 역순
			close(pstmt, conn);
		}
	}

	// 매개변수로 받은 사용자 정보로 user_info 테이블을 update하는 메서드
	@Override
	public int updateUser(BCUser user) {
		int rowCnt = FAIL;

		String sql = "update member " + "set mem_pwd=?, mem_name=?, mem_email=?, mem_tel=?, mem_reg_date=?"
				+ "where mem_id=?";
//		String sql = "update member set pwd=?,name=?,email=?,birth=?,sns=?,reg_date=? where id=?";

		// try-with-resources - since jdk7, AutoCloseable를 구현한 객체만 사용 가능
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); // AutoCloseable로
																											// 구현한 객체
																											// 이므로
																											// close자동
																											// 호출
		) {
			pstmt.setString(1, user.getPwd());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getTel());
			pstmt.setTimestamp(5, new java.sql.Timestamp(user.getReg_date().getTime())); // 날짜 + 시간
			pstmt.setString(6, user.getId()); // 조건 아이디
			rowCnt = pstmt.executeUpdate();
		} catch (SQLException e) {
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

	// 매개타입이 가변인자로, n개의 매개변수가 올 수 있음( 구체적인 매개변수의 개수를 모를 경우 사용 )
	private void close(AutoCloseable... acs) {
		for (AutoCloseable ac : acs) {
			try {
				if (ac != null) {
					ac.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<BCUser> selectAll() {
		BCUser user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BCUser> userlist = new ArrayList<>();

		String sql = "select * from member";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); // select;
			while (rs.next()) {
				user = new BCUser();
				user.setId(rs.getString(1));
				user.setPwd(rs.getString(2));
				user.setName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setTel(rs.getString(5));
				user.setReg_date(new Date(rs.getTimestamp(6).getTime())); // 날짜 + 시간 정보
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
//	public ArrayList<BCUser> selectAll(){
//		ArrayList<BCUser> userlist = new ArrayList<>();
//		BCUser user = new BCUser();
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
//				user = new BCUser();
//				user.setId(rs.getString(1));
//				user.setPwd(rs.getString(2));
//				user.setName(rs.getString(3));
//				user.setEmail(rs.getString(4));
//				user.setTel(rs.getString(5));
//				user.setReg_date(new Date(rs.getTimestamp(6).getTime())); // 날짜 + 시간 정보
//				userlist.add(user);
//		}
//			return userlist;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return userlist;
//	}
}