package com.greenart.ch1.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // 일단 DB용 컴포넌트라 생각
public class BCUserDaoImpl implements BCUserDao  {
	@Autowired
	SqlSession session; // session 주입, sql명령을 수행하는데 필요한 메서드 제공
	
	String namespace = "com.greenart.ch1.";
	
//	아이디 개수
	@Override
	public int selectIdCount(String id) throws Exception {
		return session.selectOne(namespace + "selectIdCount",id);
	}
	
//	아이디 찾기
	@Override
	public BCUserDto idToEmail(String name, String email) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("name", name);
		map.put("email", email);
		return session.selectOne(namespace + "idToEmail", map);
	}
	
//	비밀번호 찾기
	@Override
	public BCUserDto pwdToEmail(String id, String name, String email) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("name", name);
		map.put("email", email);
		return session.selectOne(namespace + "pwdToEmail", map);
	}
	
//	이메일 확인
	@Override
	public int confirmEmail(String email) throws Exception {
		return session.selectOne(namespace + "confirmEmail", email);
	}
	
//	고객 정보 찾기
	@Override
	public BCUserDto selectUser(String id) throws Exception {
		return session.selectOne(namespace + "selectUser", id);
	}
	
// 고객 정보 삭제
	@Override
	public int deleteUser (String id, String pwd) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pwd", pwd);
		return session.delete(namespace + "deleteUser", map);
	}
	
//	고객 정보 등록
	@Override
	public int insertUser(BCUserDto bcuserDto) throws Exception {
		return session.insert(namespace + "insertUser", bcuserDto);
	}
	
//	고객 정보 업데이트
	@Override
	public int updateUserPwd(String id, String pwd) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pwd", pwd);
		return session.update(namespace + "updateUserPwd", map);
	}
	@Override
	public int updateUserEmail(String id, String email) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("email", email);
		return session.update(namespace + "updateUserEmail", map);
	}
	@Override
	public int updateUserTel(String id, String tel) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("tel", tel);
		return session.update(namespace + "updateUserTel", map);
	}
	
	
//	고객 정보 전체 삭제
	@Override
	public int deleteAll() {
		return session.delete(namespace + "u_deleteAll");
	}
//	고객 정보 전체 검색
	@Override
	public List<BCUserDto> selectAll() throws Exception {
		return session.selectList(namespace + "u_selectAll");
	}
	
}