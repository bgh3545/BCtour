package com.greenart.ch1.Board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;

@Repository
public class BoardDaoImpl implements BoardDao{
	@Autowired
	SqlSession session;
	String namespace="com.greenart.ch1.";
	
	@Override
	public int deleteAll() {
		return session.delete(namespace+"deleteAll");
	}
	
	@Override
	public int delete(Integer bno, String writer) throws Exception{
		Map map= new HashMap();
		map.put("bno", bno);
		map.put("writer", writer);
		
		return session.delete(namespace+"delete",map);
	}
	
	@Override
	public int insert(BoardDto dto) throws Exception{
		return session.insert(namespace+"insert",dto);
	}
	
	@Override
	public List<BoardDto> selectAll() throws Exception{
		return session.selectList(namespace+"selectAll");
	}
	
	@Override
	public BoardDto select(Integer bno) throws Exception{
		return session.selectOne(namespace+"select",bno);
	}
	
	@Override
	public int increaseViewCnt(Integer bno) throws Exception{
		return session.update(namespace+"increaseViewCnt", bno);
	}
	
	@Override
	public int count() throws Exception{
		return session.selectOne(namespace+"count");
	}
	
	@Override
	public int update(BoardDto dto) throws Exception{
		return session.update(namespace+"update",dto);
	}
	
	@Override
	public List<BoardDto> selectPage(Map map) throws Exception{
		return session.selectList(namespace+"selectPage",map);
	}
	
	@Override
	public List<BoardDto> selectNoticePage(Map map) throws Exception{
		return session.selectList(namespace+"selectNoticePage",map);
	}
	
	@Override
	public List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception{
		return session.selectList(namespace+"searchSelectPage",sc);
	}
	
	@Override
	public int searchResultCnt(SearchCondition sc) throws Exception{
		return session.selectOne(namespace+"searchResultCnt", sc);
	}
}
