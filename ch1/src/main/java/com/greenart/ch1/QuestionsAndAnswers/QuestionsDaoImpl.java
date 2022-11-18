package com.greenart.ch1.QuestionsAndAnswers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;

@Repository
public class QuestionsDaoImpl implements QuestionsDao{
	@Autowired
	SqlSession session;
	String namespace="com.greenart.ch1.";
	
	@Override
	public int q_deleteAll() {
		return session.delete(namespace+"q_deleteAll");
	}
	
	@Override
	public int q_delete(Integer ques_num, String ques_writer) throws Exception{
		Map map= new HashMap();
		map.put("ques_num", ques_num);
		map.put("ques_writer", ques_writer);
		
		return session.delete(namespace+"q_delete",map);
	}
	
	@Override
	public int q_insert(QuestionsDto quesDto) throws Exception{
		return session.insert(namespace+"q_insert",quesDto);
	}
	
	@Override
	public int a_insert(AnswerDto ansDto) throws Exception{
		return session.insert(namespace+"a_insert",ansDto);
	}
	
	@Override
	public List<QuestionsDto> q_selectAll() throws Exception{
		return session.selectList(namespace+"q_selectAll");
	}
	
	@Override
	public List<AnswerDto> a_selectAll() throws Exception{
		return session.selectList(namespace+"a_selectAll");
	}
	
	@Override
	public List<QuestionsDto> q_selectAllManager() throws Exception{
		return session.selectList(namespace+"q_selectAllManager");
	}
	
	@Override
	public List<QuestionsDto> q_selectNoAnsManager() throws Exception{
		return session.selectList(namespace+"q_selectNoAnsManager");
	}
	
	@Override
	public QuestionsDto q_select(Integer ques_num) throws Exception{
		return session.selectOne(namespace+"q_select",ques_num);
	}
	
	@Override
	public AnswerDto a_select(Integer ans_num) throws Exception{
		return session.selectOne(namespace+"a_select",ans_num);
	}
	
	@Override
	public int q_ansBool( Integer ques_num) throws Exception{
		return session.update(namespace+"q_ansBool",ques_num);
	}
	
	@Override
	public int q_delAnsBool( Integer ques_num) throws Exception{
		return session.update(namespace+"q_delAnsBool",ques_num);
	}
	
	@Override
	public int q_count(String ques_writer) throws Exception{
		return session.selectOne(namespace+"q_count",ques_writer);
	}
	
	@Override
	public List<QuestionsDto> q_selectPage(Map map) throws Exception{
		return session.selectList(namespace+"q_selectPage",map);
	}
	
	@Override
	public List<QuestionsDto> q_searchSelectPage(SearchCondition sc, String ques_writer) throws Exception{
		Map map= new HashMap();
		map.put("ques_writer", ques_writer);
		map.put("offset", sc.getOffset());
		map.put("pageSize", sc.getPageSize());
		return session.selectList(namespace+"q_searchSelectPage",map);
	}
	
	@Override
	public List<QuestionsDto> q_searchSelectManagerPage(SearchCondition sc) throws Exception{
		return session.selectList(namespace+"q_searchSelectManagerPage",sc);
	}
	
	@Override
	public List<QuestionsDto> q_searchSelectNoAnsManagerPage(SearchCondition sc) throws Exception{
		return session.selectList(namespace+"q_searchSelectNoAnsManagerPage",sc);
	}
	
	@Override
	public int q_searchResultCnt(SearchCondition sc,String ques_writer) throws Exception{
		Map map= new HashMap();
		map.put("offset", sc.getOffset());
		map.put("pageSize", sc.getPageSize());
		map.put("ques_writer", ques_writer);
		return session.selectOne(namespace+"q_searchResultCnt", map);
	}
	
	@Override
	public int q_searchResultManagerCnt(SearchCondition sc) throws Exception{
		return session.selectOne(namespace+"q_searchResultManagerCnt", sc);
	}
	
	@Override
	public int q_searchResultNoAnsManagerCnt(SearchCondition sc) throws Exception{
		return session.selectOne(namespace+"q_searchResultNoAnsManagerCnt", sc);
	}
}
