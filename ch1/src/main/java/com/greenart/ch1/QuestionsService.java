package com.greenart.ch1;

import java.util.List;
import java.util.Map;

public interface QuestionsService {

	int q_getCount() throws Exception;

	int q_remove(Integer ques_num, String ques_writer) throws Exception;

	int q_write(QuestionsDto quesDto) throws Exception;

	List<QuestionsDto> q_getList() throws Exception;

	int q_ansBool(Integer ques_num) throws Exception;

	int q_delAnsBool(Integer ques_num) throws Exception;

	List<QuestionsDto> q_getPage(Map map) throws Exception;

	int q_getSearchResultCnt(SearchCondition sc, String ques_writer) throws Exception;

	List<QuestionsDto> q_getSearchResultPage(SearchCondition sc, String ques_writer) throws Exception;

	int q_getSearchResultManagerCnt(SearchCondition sc) throws Exception;

	int q_getSearchResultNoAnsManagerCnt(SearchCondition sc, Integer ansbool) throws Exception;

	List<QuestionsDto> q_getSearchResultManagerPage(SearchCondition sc) throws Exception;

	List<QuestionsDto> q_getSearchResultNoAnsManagerPage(SearchCondition sc, Integer ansbool) throws Exception;

	QuestionsDto q_read(Integer ques_num) throws Exception;

	
}