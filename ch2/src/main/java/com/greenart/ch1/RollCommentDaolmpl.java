package com.greenart.ch1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // 일단 DB용 컴포넌트라 생각
public class RollCommentDaolmpl implements RollCommentDao {
	@Autowired
	DataSource ds;
	final int FAIL = 0;
	
	@Override
	public int insertCmt(RollComment cmt) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into comment (name, comment) values (?, ?) ";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cmt.getName());
			pstmt.setString(2, cmt.getComment());
			System.out.println(cmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return FAIL;
		} finally {
			close(pstmt,conn);
		}
	}
	
	@Override
	public ArrayList<RollComment> selectAll() {
		String sql = "select name, comment from comment";
		ArrayList<RollComment> comments = new ArrayList<>();
		
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		) {
			while(rs.next()) {
				RollComment comment = new RollComment();
				comment.setName(rs.getString(1));
				comment.setComment(rs.getString(2));
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}
	
	// auto_increment는 delete해도 값이 남아 있으므로 초기화 진행
	@Override
	public void deleteAll() throws Exception {
		Connection conn = ds.getConnection();
		
		String sql = "delete from comment";
		String sql2 = "alter table comment auto_increment = 0";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		PreparedStatement pstmt2 = conn.prepareStatement(sql2);
		pstmt.executeUpdate();
		pstmt2.executeUpdate();
	}
	
	// 메시지 개수 조회
	@Override
	public int cnt() {
		String sql = "select no from comment";
		int res = 0;
		
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		) {
			while(rs.next()) {
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
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
	
}