package com.greenart.ch1.Community;

import java.util.List;
import java.util.Map;

import com.greenart.ch1.PageHandlerAndSearchCondition.SearchCondition;

public interface CommunityDao {

	int c_deleteAll();

	int c_delete(Integer comm_num, String comm_writer) throws Exception;

	int c_insert(CommunityDto commDto) throws Exception;

	List<CommunityDto> c_selectAll() throws Exception;

	CommunityDto c_select(Integer comm_num) throws Exception;

	int c_increaseViewCnt(Integer comm_num) throws Exception;

	int c_count() throws Exception;

	int c_update(CommunityDto commDto) throws Exception;

	List<CommunityDto> c_selectPage(Map map) throws Exception;

	int c_searchResultCnt(SearchCondition sc) throws Exception;

	List<CommunityDto> c_searchSelectPage(SearchCondition sc) throws Exception;

	int c_increaseCommCnt(Integer comm_num) throws Exception;

	int c_decreaseCommCnt(Integer comm_num) throws Exception;
}