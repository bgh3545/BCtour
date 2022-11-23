package com.greenart.ch1.Community;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;

@Repository
public class CommunityDaoImpl implements CommunityDao{
	@Autowired
	SqlSession session;
	String namespace="com.greenart.ch1.";
	
	@Override
	public int c_deleteAll() {
		return session.delete(namespace+"c_deleteAll");
	}
	
	@Override
	public int c_delete(Integer comm_num, String comm_writer) throws Exception{
		Map map= new HashMap();
		map.put("comm_num", comm_num);
		map.put("comm_writer", comm_writer);
		
		return session.delete(namespace+"c_delete",map);
	}
	
	@Override
	public int c_insert(CommunityDto commDto) throws Exception{
		return session.insert(namespace+"c_insert",commDto);
	}
	
	@Override
	public List<CommunityDto> c_selectAll() throws Exception{
		return session.selectList(namespace+"c_selectAll");
	}
	
	@Override
	public CommunityDto c_select(Integer comm_num) throws Exception{
		return session.selectOne(namespace+"c_select",comm_num);
	}
	
	@Override
	public int c_increaseViewCnt(Integer comm_num) throws Exception{
		return session.update(namespace+"c_increaseViewCnt", comm_num);
	}
	
	@Override
	public int c_decreaseCommCnt(Integer comm_num) throws Exception{
		return session.update(namespace+"c_decreaseCommCnt", comm_num);
	}
	
	@Override
	public int c_increaseCommCnt(Integer comm_num) throws Exception{
		return session.update(namespace+"c_increaseCommCnt", comm_num);
	}
	
	@Override
	public int c_count() throws Exception{
		return session.selectOne(namespace+"c_count");
	}
	
	@Override
	public int c_update(CommunityDto commdto) throws Exception{
		return session.update(namespace+"c_update",commdto);
	}
	
	@Override
	public List<CommunityDto> c_selectPage(Map map) throws Exception{
		return session.selectList(namespace+"c_selectPage",map);
	}
	
	@Override
	public List<CommunityDto> c_searchSelectPage(SearchCondition sc) throws Exception{
		return session.selectList(namespace+"c_searchSelectPage",sc);
	}
	
	@Override
	public int c_searchResultCnt(SearchCondition sc) throws Exception{
		return session.selectOne(namespace+"c_searchResultCnt", sc);
	}
}
