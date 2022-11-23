package com.greenart.ch1.QuestionsAndAnswers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;

@Service
public class QuestionsServiceImpl implements QuestionsService {
	
	@Autowired
	QuestionsDao quesDao;
	
	@Override
	public int q_getCount(String ques_writer) throws Exception{
		return quesDao.q_count(ques_writer);
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
	public int a_write(AnswerDto ansDto) throws Exception {
		return quesDao.a_insert(ansDto);
	}
	
	@Override
	public List<QuestionsDto> q_getList() throws Exception {
		return quesDao.q_selectAll();
	}
	
	@Override
	public List<AnswerDto> a_getList() throws Exception {
		return quesDao.a_selectAll();
	}
	
	@Override
	public QuestionsDto q_read(Integer ques_num) throws Exception{
		QuestionsDto quesDto = quesDao.q_select(ques_num);
		return quesDto;
	}
	
	@Override
	public AnswerDto a_read(Integer ans_num) throws Exception{
		AnswerDto ansDto = quesDao.a_select(ans_num);
		return ansDto;
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
	public int q_getSearchResultNoAnsManagerCnt(SearchCondition sc) throws Exception{
		return quesDao.q_searchResultNoAnsManagerCnt(sc);
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
	public List<QuestionsDto> q_getSearchResultNoAnsManagerPage(SearchCondition sc) throws Exception{
		return quesDao.q_searchSelectNoAnsManagerPage(sc);
	}
}
