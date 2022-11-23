package com.greenart.ch1.Community;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class comm_commentDaoImpl implements comm_commentDao{
	@Autowired
	SqlSession session;
	String namespace="com.greenart.ch1.";
	
	@Override
	public int cm_deleteAll() {
		return session.delete(namespace+"cm_deleteAll");
	}

	@Override
	public int cm_delete(Integer comm_comm_num, String comm_comm_writer) throws Exception{
		Map map= new HashMap();
		map.put("comm_comm_num", comm_comm_num);
		map.put("comm_comm_writer", comm_comm_writer);
		
		return session.delete(namespace+"cm_delete",map);
	}
	
	@Override
	public int cm_insert(comm_commentDto comm_commDto) throws Exception{
		return session.insert(namespace+"cm_insert",comm_commDto);
	}
	
	@Override
	public List<comm_commentDto> cm_selectAll(Integer comm_num) throws Exception{
		Map map = new HashMap();
		map.put("comm_num", comm_num);
		return session.selectList(namespace+"cm_selectAll",map);
	}
	
	@Override
	public comm_commentDto cm_select(Integer comm_comm_num) throws Exception{
		return session.selectOne(namespace+"cm_select",comm_comm_num);
	}
	
	@Override
	public int cm_count(Integer comm_num) throws Exception{
		return session.selectOne(namespace+"cm_count");
	}
	
	@Override
	public int cm_update(comm_commentDto comm_commDto) throws Exception{
		return session.update(namespace+"cm_update",comm_commDto);
	}
	
	@Override
	public List<comm_commentDto> cm_selectPage(Map map) throws Exception{
		return session.selectList(namespace+"cm_selectPage",map);
	}
}
