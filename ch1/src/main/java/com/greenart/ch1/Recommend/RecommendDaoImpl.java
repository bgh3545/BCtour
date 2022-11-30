package com.greenart.ch1.Recommend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;

@Repository
public class RecommendDaoImpl implements RecommendDao{
	@Autowired
	SqlSession session;
	String namespace="com.greenart.ch1.";
	
	@Override
	public int r_deleteAll() {
		return session.delete(namespace+"r_deleteAll");
	}
	
	@Override
	public int r_delete(Integer rec_num, String rec_writer) throws Exception{
		Map map= new HashMap();
		map.put("rec_num", rec_num);
		map.put("rec_writer", rec_writer);
		
		return session.delete(namespace+"r_delete",map);
	}
	
	@Override
	public int r_insert(RecommendDto recDto) throws Exception{
		return session.insert(namespace+"r_insert",recDto);
	}
	
	@Override
	public List<RecommendDto> r_selectAll() throws Exception{
		return session.selectList(namespace+"r_selectAll");
	}
	
	@Override
	public List<RecommendDto> r_selectTop() throws Exception{
		return session.selectList(namespace+"r_selectTop");
	}
	
	@Override
	public RecommendDto r_select(Integer rec_num) throws Exception{
		return session.selectOne(namespace+"r_select",rec_num);
	}
	
	@Override
	public int r_increaseViewCnt(Integer rec_num) throws Exception{
		return session.update(namespace+"r_increaseViewCnt", rec_num);
	}
	
	@Override
	public int r_decreaseCommCnt(Integer rec_num) throws Exception{
		return session.update(namespace+"r_decreaseCommCnt", rec_num);
	}
	
	@Override
	public int r_increaseCommCnt(Integer rec_num) throws Exception{
		return session.update(namespace+"r_increaseCommCnt", rec_num);
	}
	
	@Override
	public int r_increaseRecCnt(Integer rec_num) throws Exception{
		return session.update(namespace+"r_increaseRecCnt", rec_num);
	}
	
	@Override
	public int r_decreaseRecCnt(Integer rec_num) throws Exception{
		return session.update(namespace+"r_decreaseRecCnt", rec_num);
	}
	
	@Override
	public int r_increaseRecBool(Integer rec_num, String mem_id) throws Exception{
		Map map= new HashMap();
		map.put("rec_num", rec_num);
		map.put("mem_id", mem_id);
		return session.update(namespace+"r_increaseRecBool", map);
	}
	
	@Override
	public int r_decreaseRecBool(Integer rec_num, String mem_id) throws Exception{
		Map map= new HashMap();
		map.put("rec_num", rec_num);
		map.put("mem_id", mem_id);
		return session.update(namespace+"r_decreaseRecBool", map);
	}
	
	@Override
	public RecommendDto rb_select(Integer rec_num, String mem_id) throws Exception{
		Map map= new HashMap();
		map.put("rec_num", rec_num);
		map.put("mem_id", mem_id);
		return session.selectOne(namespace+"rb_select",map);
	}
	
	@Override
	public int rb_insert(RecommendDto recDto) throws Exception{
		return session.insert(namespace+"rb_insert",recDto);
	}
	
	@Override
	public int r_count() throws Exception{
		return session.selectOne(namespace+"r_count");
	}
	
	@Override
	public int r_update(RecommendDto commdto) throws Exception{
		return session.update(namespace+"r_update",commdto);
	}
	
	@Override
	public List<RecommendDto> r_selectPage(Map map) throws Exception{
		return session.selectList(namespace+"r_selectPage",map);
	}
	
	@Override
	public List<RecommendDto> r_searchSelectPage(SearchCondition sc) throws Exception{
		return session.selectList(namespace+"r_searchSelectPage",sc);
	}
	
	@Override
	public List<RecommendDto> r_searchSelectRecommendPage(SearchCondition sc) throws Exception{
		return session.selectList(namespace+"r_searchSelectRecommendPage",sc);
	}
	
	@Override
	public int r_searchResultCnt(SearchCondition sc) throws Exception{
		return session.selectOne(namespace+"r_searchResultCnt", sc);
	}
	
	@Override
	public int r_searchResultRecommendCnt(SearchCondition sc) throws Exception{
		return session.selectOne(namespace+"r_searchResultRecommendCnt", sc);
	}
	
	@Override
	public RecommendDto r_selectReview(Integer pd_num, String id) throws Exception{
		Map map= new HashMap();
		map.put("pd_num", pd_num);
		map.put("id", id);
		return session.selectOne(namespace+"r_selectReview", map);
	}
}
