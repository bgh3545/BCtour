package com.greenart.ch1.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository 
public class BCUserDaoImpl implements BCUserDao  {
	@Autowired
	SqlSession session;
	
	String namespace = "com.greenart.ch1.";
	
	@Override
	public int selectIdCount(String id) throws Exception {
		return session.selectOne(namespace + "selectIdCount",id);
	}
	@Override
	public BCUserDto idToEmail(String name, String email) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("name", name);
		map.put("email", email);
		return session.selectOne(namespace + "idToEmail", map);
	}
	@Override
	public BCUserDto pwdToEmail(String id, String name, String email) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("name", name);
		map.put("email", email);
		return session.selectOne(namespace + "pwdToEmail", map);
	}
	@Override
	public int confirmEmail(String email) throws Exception {
		return session.selectOne(namespace + "confirmEmail", email);
	}
	@Override
	public BCUserDto selectUser(String id) throws Exception {
		return session.selectOne(namespace + "selectUser", id);
	}
	@Override
	public int deleteUser (String id, String pwd) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pwd", pwd);
		return session.delete(namespace + "deleteUser", map);
	}
	@Override
	public int insertUser(BCUserDto bcuserDto) throws Exception {
		return session.insert(namespace + "insertUser", bcuserDto);
	}
	@Override
	public int updateUser(String id, String pwd) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pwd", pwd);
		return session.update(namespace + "updateUser", map);
	}
	@Override
	public int deleteAll() {
		return session.delete(namespace + "u_deleteAll");
	}
	@Override
	public List<BCUserDto> selectAll() throws Exception {
		return session.selectList(namespace + "u_selectAll");
	}
	
}