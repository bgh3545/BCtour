package com.greenart.ch1.QuestionsAndAnswers;

import java.util.List;
import java.util.Map;

import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;

public interface QuestionsDao {

	int q_deleteAll();

	int q_delete(Integer ques_num, String ques_writer) throws Exception;

	int q_insert(QuestionsDto quesDto) throws Exception;

	List<QuestionsDto> q_selectAll() throws Exception;

	List<QuestionsDto> q_selectAllManager() throws Exception;

	List<QuestionsDto> q_selectNoAnsManager() throws Exception;

	QuestionsDto q_select(Integer ques_num) throws Exception;

	int q_ansBool(Integer ques_num) throws Exception;

	int q_delAnsBool(Integer ques_num) throws Exception;

	int q_count(String ques_writer) throws Exception;

	List<QuestionsDto> q_selectPage(Map map) throws Exception;

	List<QuestionsDto> q_searchSelectPage(SearchCondition sc, String writer) throws Exception;

	int q_searchResultCnt(SearchCondition sc, String writer) throws Exception;

	List<QuestionsDto> q_searchSelectManagerPage(SearchCondition sc) throws Exception;

	List<QuestionsDto> q_searchSelectNoAnsManagerPage(SearchCondition sc) throws Exception;

	int q_searchResultManagerCnt(SearchCondition sc) throws Exception;

	int q_searchResultNoAnsManagerCnt(SearchCondition sc) throws Exception;

	int a_insert(AnswerDto ansDto) throws Exception;

	List<AnswerDto> a_selectAll() throws Exception;

	AnswerDto a_select(Integer ans_num) throws Exception;

}