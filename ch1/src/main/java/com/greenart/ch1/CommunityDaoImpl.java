package com.greenart.ch1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
		map.put("bno", comm_num);
		map.put("writer", comm_writer);
		
		return session.delete(namespace+"c_delete",map);
	}
	
	@Override
	public int c_insert(CommunityDto dto) throws Exception{
		return session.insert(namespace+"c_insert",dto);
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
