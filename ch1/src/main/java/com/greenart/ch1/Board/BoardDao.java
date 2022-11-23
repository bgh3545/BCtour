package com.greenart.ch1.Board;

import java.util.List;
import java.util.Map;

import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;

public interface BoardDao {

	int deleteAll();

	int delete(Integer bno, String writer) throws Exception;

	int insert(BoardDto dto) throws Exception;

	List<BoardDto> selectAll() throws Exception;

	BoardDto select(Integer bno) throws Exception;

	int increaseViewCnt(Integer bno) throws Exception;

	int count() throws Exception;

	int update(BoardDto dto) throws Exception;

	List<BoardDto> selectPage(Map map) throws Exception;

	List<BoardDto> selectNoticePage(Map map) throws Exception;
	
	int searchResultCnt(SearchCondition sc) throws Exception;
	
	List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception;

}