package com.greenart.ch1.Recommend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greenart.ch1.PageHandlerAndSearchCondition.CommSearchCondition;

@Repository
public class rec_commentDaoImpl implements rec_commentDao{
	@Autowired
	SqlSession session;
	String namespace="com.greenart.ch1.";
	
	@Override
	public int rm_deleteAll() {
		return session.delete(namespace+"rm_deleteAll");
	}

	@Override
	public int rm_delete(Integer rec_comm_num, String rec_comm_writer) throws Exception{
		Map map= new HashMap();
		map.put("rec_comm_num", rec_comm_num);
		map.put("rec_comm_writer", rec_comm_writer);
		
		return session.delete(namespace+"rm_delete",map);
	}
	
	@Override
	public int rm_insert(rec_commentDto rec_commDto) throws Exception{
		return session.insert(namespace+"rm_insert",rec_commDto);
	}
	
	@Override
	public List<rec_commentDto> rm_selectAll(Integer rec_num,CommSearchCondition sc) throws Exception{
		Map map = new HashMap();
		map.put("rec_num", rec_num);
		map.put("offset",sc.getCommOffset());
		map.put("pageSize",sc.getCommPageSize());
		return session.selectList(namespace+"rm_selectAll",map);
	}
	
	@Override
	public rec_commentDto rm_select(Integer rec_comm_num) throws Exception{
		return session.selectOne(namespace+"rm_select",rec_comm_num);
	}
	
	@Override
	public int rm_count(Integer rec_num) throws Exception{
		return session.selectOne(namespace+"rm_count",rec_num);
	}
	
	@Override
	public int rm_update(rec_commentDto rec_commDto) throws Exception{
		return session.update(namespace+"rm_update",rec_commDto);
	}
	
	@Override
	public List<rec_commentDto> rm_selectPage(Map map) throws Exception{
		return session.selectList(namespace+"rm_selectPage",map);
	}
}
