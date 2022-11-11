package com.greenart.ch1;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionsServiceImpl implements QuestionsService {
	
	@Autowired
	QuestionsDao quesDao;
	
	@Override
	public int q_getCount() throws Exception{
		return quesDao.q_count();
	}
	
	@Override
	public int q_remove(Integer ques_num,String ques_writer) throws Exception{
		return quesDao.q_delete(ques_num, ques_writer);
	}
	
	@Override
	public int q_write(QuestionsDto quesDto) throws Exception {
		return quesDao.q_insert(quesDto);
	}
	
	@Override
	public List<QuestionsDto> q_getList() throws Exception {
		return quesDao.q_selectAll();
	}
	
	@Override
	public QuestionsDto q_read(Integer ques_num) throws Exception{
		QuestionsDto quesDto = quesDao.q_select(ques_num);
		return quesDto;
	}
	
	@Override
	public int q_ansBool(Integer ques_num) throws Exception{
		return quesDao.q_ansBool(ques_num);
	}
	
	@Override
	public int q_delAnsBool(Integer ques_num) throws Exception{
		return quesDao.q_delAnsBool(ques_num);
	}
	
	@Override
	public List<QuestionsDto> q_getPage(Map map) throws Exception{
		return quesDao.q_selectPage(map);
	}
	
	@Override
	public int q_getSearchResultCnt(SearchCondition sc,String ques_writer) throws Exception{
		return quesDao.q_searchResultCnt(sc,ques_writer);
	}
	
	@Override
	public int q_getSearchResultManagerCnt(SearchCondition sc) throws Exception{
		return quesDao.q_searchResultManagerCnt(sc);
	}
	
	@Override
	public int q_getSearchResultNoAnsManagerCnt(SearchCondition sc, Integer ansbool) throws Exception{
		return quesDao.q_searchResultNoAnsManagerCnt(sc,ansbool);
	}
	
	@Override
	public List<QuestionsDto> q_getSearchResultPage(SearchCondition sc,String ques_writer) throws Exception{

		return quesDao.q_searchSelectPage(sc,ques_writer);
	}
	
	@Override
	public List<QuestionsDto> q_getSearchResultManagerPage(SearchCondition sc) throws Exception{
		return quesDao.q_searchSelectManagerPage(sc);
	}
	
	@Override
	public List<QuestionsDto> q_getSearchResultNoAnsManagerPage(SearchCondition sc, Integer ansbool) throws Exception{
		return quesDao.q_searchSelectNoAnsManagerPage(sc,ansbool);
	}
}
